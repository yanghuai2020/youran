package com.youran.generate.web.api;

import com.youran.generate.pojo.dto.MetaCascadeExtAddDTO;
import com.youran.generate.pojo.dto.MetaCascadeExtUpdateDTO;
import com.youran.generate.pojo.qo.MetaCascadeExtQO;
import com.youran.generate.pojo.vo.MetaCascadeExtListVO;
import com.youran.generate.pojo.vo.MetaCascadeExtShowVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * 【级联扩展】api
 *
 * @author: cbb
 * @date: 2017/5/12
 */
@Api(tags = "【级联扩展】API")
public interface MetaCascadeExtAPI {

    /**
     * 新增级联扩展
     */
    @ApiOperation(value = "新增级联扩展")
    @ApiImplicitParams({@ApiImplicitParam(name = "metaCascadeExtAddDTO", dataType = "MetaCascadeExtAddDTO", value = "新增级联扩展参数", paramType = "body"),})
    ResponseEntity<MetaCascadeExtShowVO> save(MetaCascadeExtAddDTO metaCascadeExtAddDTO) throws Exception;

    /**
     * 修改级联扩展
     */
    @ApiOperation(value = "修改级联扩展")
    @ApiImplicitParams({@ApiImplicitParam(name = "metaCascadeExtUpdateDTO", dataType = "MetaCascadeExtUpdateDTO", value = "修改级联扩展参数", paramType = "body"),})
    ResponseEntity<MetaCascadeExtShowVO> update(MetaCascadeExtUpdateDTO metaCascadeExtUpdateDTO);

    /**
     * 查询级联扩展列表
     */
    @ApiOperation(value = "查询级联扩展列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "metaCascadeExtQO", dataType = "MetaCascadeExtQO", value = "分页查询参数", paramType = "body"),})
    ResponseEntity<List<MetaCascadeExtListVO>> list(MetaCascadeExtQO metaCascadeExtQO);

    /**
     * 查看级联扩展详情
     */
    @ApiOperation(value = "查看级联扩展详情")
    @ApiImplicitParams({@ApiImplicitParam(name = "cascadeExtId", dataType = "int", value = "级联扩展id", paramType = "path"),})
    ResponseEntity<MetaCascadeExtShowVO> show(Integer cascadeExtId);

    /**
     * 删除级联扩展
     */
    @ApiOperation(value = "删除级联扩展")
    @ApiImplicitParams({@ApiImplicitParam(name = "cascadeExtId", dataType = "int", value = "级联扩展id", paramType = "path"),})
    ResponseEntity<Integer> delete(Integer cascadeExtId);

    /**
     * 批量删除级联扩展
     */
    @ApiOperation(value = "批量删除级联扩展")
    @ApiImplicitParams({@ApiImplicitParam(name = "cascadeExtId", dataType = "int", value = "级联扩展id数组", paramType = "body"),})
    ResponseEntity<Integer> deleteBatch(Integer[] cascadeExtId);


}
