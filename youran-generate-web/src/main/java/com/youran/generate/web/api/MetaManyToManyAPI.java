package com.youran.generate.web.api;

import com.youran.generate.pojo.dto.MetaManyToManyAddDTO;
import com.youran.generate.pojo.dto.MetaManyToManyUpdateDTO;
import com.youran.generate.pojo.qo.MetaManyToManyQO;
import com.youran.generate.pojo.vo.MetaManyToManyListVO;
import com.youran.generate.pojo.vo.MetaManyToManyShowVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * 【多对多关联】API
 *
 * @author: cbb
 * @date: 2017/5/12
 */
@Api(tags = "【多对多关联】API")
public interface MetaManyToManyAPI {

    /**
     * 新增多对多关联
     */
    @ApiOperation(value = "新增多对多关联")
    @ApiImplicitParams({@ApiImplicitParam(name = "metaManyToManyAddDTO", dataType = "MetaManyToManyAddDTO", value = "新增多对多关联参数", paramType = "body"),})
    ResponseEntity<MetaManyToManyShowVO> save(MetaManyToManyAddDTO metaManyToManyAddDTO) throws Exception;

    /**
     * 修改多对多关联
     */
    @ApiOperation(value = "修改多对多关联")
    @ApiImplicitParams({@ApiImplicitParam(name = "metaManyToManyUpdateDTO", dataType = "MetaManyToManyUpdateDTO", value = "修改多对多关联参数", paramType = "body"),})
    ResponseEntity<MetaManyToManyShowVO> update(MetaManyToManyUpdateDTO metaManyToManyUpdateDTO);

    /**
     * 多对多关联列表查询
     */
    @ApiOperation(value = "多对多关联列表查询")
    @ApiImplicitParams({@ApiImplicitParam(name = "metaManyToManyQO", dataType = "MetaManyToManyQO", value = "查询参数", paramType = "body"),})
    ResponseEntity<List<MetaManyToManyListVO>> list(MetaManyToManyQO metaManyToManyQO);

    /**
     * 查看多对多关联详情
     */
    @ApiOperation(value = "查看多对多关联详情")
    @ApiImplicitParams({@ApiImplicitParam(name = "mtmId", dataType = "int", value = "多对多关联id", paramType = "path"),})
    ResponseEntity<MetaManyToManyShowVO> show(Integer mtmId);

    /**
     * 删除多对多关联
     */
    @ApiOperation(value = "删除多对多关联")
    @ApiImplicitParams({@ApiImplicitParam(name = "mtmId", dataType = "int", value = "多对多关联id", paramType = "path"),})
    ResponseEntity<Integer> delete(Integer mtmId);

    /**
     * 批量删除多对多关联
     */
    @ApiOperation(value = "批量删除多对多关联")
    @ApiImplicitParams({@ApiImplicitParam(name = "mtmId", dataType = "int", value = "多对多关联id数组", paramType = "body"),})
    ResponseEntity<Integer> deleteBatch(Integer[] mtmId);


}
