package com.youran.generate.service;

import com.youran.common.constant.ErrorCode;
import com.youran.common.exception.BusinessException;
import com.youran.common.optimistic.OptimisticLock;
import com.youran.common.util.ConvertUtil;
import com.youran.generate.dao.MetaFieldDAO;
import com.youran.generate.dao.MetaIndexDAO;
import com.youran.generate.dao.MetaIndexFieldDAO;
import com.youran.generate.pojo.dto.MetaIndexAddDTO;
import com.youran.generate.pojo.dto.MetaIndexUpdateDTO;
import com.youran.generate.pojo.mapper.MetaIndexMapper;
import com.youran.generate.pojo.po.MetaIndexPO;
import com.youran.generate.pojo.po.MetaProjectPO;
import com.youran.generate.pojo.qo.MetaIndexQO;
import com.youran.generate.pojo.vo.MetaFieldListVO;
import com.youran.generate.pojo.vo.MetaIndexListVO;
import com.youran.generate.pojo.vo.MetaIndexShowVO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 索引增删改查服务
 *
 * @author: cbb
 * @date: 2017/5/12
 */
@Service
public class MetaIndexService {

    @Autowired
    private MetaFieldDAO metaFieldDAO;
    @Autowired
    private MetaIndexDAO metaIndexDAO;
    @Autowired
    private MetaIndexFieldDAO metaIndexFieldDAO;
    @Autowired
    private MetaProjectService metaProjectService;

    /**
     * 新增索引
     *
     * @param metaIndexAddDTO
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public MetaIndexPO save(MetaIndexAddDTO metaIndexAddDTO) {
        // 查询项目,同时校验用户权限
        MetaProjectPO project = metaProjectService.getProjectByEntityId(metaIndexAddDTO.getEntityId(), true);
        //映射属性
        MetaIndexPO metaIndex = MetaIndexMapper.INSTANCE.fromAddDTO(metaIndexAddDTO);
        metaIndex.setProjectId(project.getProjectId());
        this.doSave(metaIndex);
        metaProjectService.updateProject(project);
        return metaIndex;
    }

    public void doSave(MetaIndexPO indexPO) {
        List<Integer> fieldIdList = indexPO.getFieldIds();
        //校验字段id是否是本实体下存在的字段
        int fieldCount = metaFieldDAO.findCount(indexPO.getEntityId(), fieldIdList);
        if (fieldCount != fieldIdList.size()) {
            throw new BusinessException(ErrorCode.BAD_PARAMETER, "索引字段异常");
        }
        //保存索引对象
        metaIndexDAO.save(indexPO);
        //保存关联关系
        int count = metaIndexFieldDAO.saveBatch(indexPO.getIndexId(), fieldIdList, indexPO.getProjectId());
        if (count == 0 || fieldIdList.size() != count) {
            throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR, "索引保存异常");
        }
    }

    /**
     * 修改索引
     *
     * @param metaIndexUpdateDTO
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @OptimisticLock
    public MetaIndexPO update(MetaIndexUpdateDTO metaIndexUpdateDTO) {
        MetaIndexPO metaIndex = this.getIndex(metaIndexUpdateDTO.getIndexId(), true);
        Integer projectId = metaIndex.getProjectId();
        MetaProjectPO project = metaProjectService.getAndCheckProject(projectId);
        //校验新字段id是否是本实体下存在的字段
        String fieldIds = metaIndexUpdateDTO.getFieldIds();
        List<Integer> fieldIdList = ConvertUtil.convertIntegerList(fieldIds);
        int fieldCount = metaFieldDAO.findCount(metaIndexUpdateDTO.getEntityId(), fieldIdList);
        if (fieldCount != fieldIdList.size()) {
            throw new BusinessException(ErrorCode.BAD_PARAMETER, "索引字段异常");
        }
        //映射属性
        MetaIndexMapper.INSTANCE.setPO(metaIndex, metaIndexUpdateDTO);
        //修改索引对象
        metaIndexDAO.update(metaIndex);
        //先清除旧关联关系
        metaIndexFieldDAO.delete(metaIndex.getIndexId());
        //保存新的关联关系
        int count = metaIndexFieldDAO.saveBatch(metaIndex.getIndexId(), fieldIdList, projectId);
        if (count == 0 || fieldIdList.size() != count) {
            throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR, "索引更新异常");
        }

        metaProjectService.updateProject(project);
        return metaIndex;
    }

    /**
     * 获取索引对象
     *
     * @param indexId
     * @param force
     * @return
     */
    public MetaIndexPO getIndex(Integer indexId, boolean force) {
        MetaIndexPO indexPO = metaIndexDAO.findById(indexId);
        if (force && indexPO == null) {
            throw new BusinessException(ErrorCode.RECORD_NOT_FIND, "索引未找到");
        }
        return indexPO;
    }

    /**
     * 查询列表
     *
     * @param metaIndexQO
     * @return
     */
    public List<MetaIndexListVO> list(MetaIndexQO metaIndexQO) {
        List<MetaIndexListVO> list = metaIndexDAO.findListByQuery(metaIndexQO);
        return list;
    }

    /**
     * 查询索引详情
     *
     * @param indexId
     * @return
     */
    public MetaIndexShowVO show(Integer indexId) {
        MetaIndexPO metaIndex = this.getIndex(indexId, true);
        MetaIndexShowVO showVO = MetaIndexMapper.INSTANCE.toShowVO(metaIndex);
        List<MetaFieldListVO> fields = metaIndexFieldDAO.findByIndexId(showVO.getIndexId());
        showVO.setFields(fields);
        return showVO;
    }

    /**
     * 删除索引
     *
     * @param indexId
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public int delete(Integer... indexId) {
        int count = 0;
        for (Integer id : indexId) {
            MetaIndexPO metaIndex = this.getIndex(id, false);
            if (metaIndex == null) {
                continue;
            }
            //校验操作人
            MetaProjectPO project = metaProjectService.getAndCheckProject(metaIndex.getProjectId());
            metaIndexFieldDAO.delete(id);
            count += metaIndexDAO.delete(id);
            metaProjectService.updateProject(project);
        }
        return count;
    }

    /**
     * 移除索引字段
     *
     * @param indexId
     * @param fieldIds
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public int removeField(Integer indexId, List<Integer> fieldIds) {
        MetaIndexPO metaIndex = this.getIndex(indexId, false);
        if (metaIndex == null) {
            return 0;
        }
        //校验操作人
        MetaProjectPO project = metaProjectService.getAndCheckProject(metaIndex.getProjectId());
        int count = metaIndexFieldDAO.remove(indexId, fieldIds);
        if (count == 0) {
            return 0;
        }
        List<MetaFieldListVO> fields = metaIndexFieldDAO.findByIndexId(indexId);
        // 如果所有字段都已经清空，则删除整个索引
        if (CollectionUtils.isEmpty(fields)) {
            metaIndexDAO.delete(indexId);
        }
        metaProjectService.updateProject(project);
        return count;
    }

    /**
     * 根据实体id查询索引对象列表
     *
     * @param entityId
     * @return
     */
    public List<MetaIndexPO> findByEntityId(Integer entityId) {
        return metaIndexDAO.findByEntityId(entityId);
    }

    /**
     * 根据索引id查询字段id列表
     *
     * @param indexId
     * @return
     */
    public List<Integer> findFieldIdsByIndexId(Integer indexId) {
        return metaIndexFieldDAO.findIdsByIndexId(indexId);
    }

}
