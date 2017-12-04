package com.youran.generate.web.api;

import com.youran.common.pojo.vo.ReplyVO;
import com.youran.generate.pojo.dto.MetaManyToManyAddDTO;
import com.youran.generate.pojo.dto.MetaManyToManyQueryDTO;
import com.youran.generate.pojo.dto.MetaManyToManyUpdateDTO;
import com.youran.generate.pojo.vo.MetaManyToManyListVO;
import com.youran.generate.pojo.vo.MetaManyToManyShowVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * Title:多对多关联管理api
 * Description:
 * Author: cbb
 * Create Time:2017/5/12 13:40
 */
@Api(tags = "MetaManyToManyPO", description = "多对多关联")
public interface MetaManyToManyAPI {

    /**
     * 新增多对多关联
     */
    @ApiOperation(value = "新增多对多关联")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "metaManyToManyAddDTO", dataType = "MetaManyToManyAddDTO", value = "新增多对多关联参数", paramType = "body"),
    })
    ReplyVO<Integer> save(MetaManyToManyAddDTO metaManyToManyAddDTO);

    /**
     * 修改多对多关联
     */
    @ApiOperation(value = "修改多对多关联")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "metaManyToManyUpdateDTO", dataType = "MetaManyToManyUpdateDTO", value = "修改多对多关联参数", paramType = "body"),
    })
    ReplyVO<Void> update(MetaManyToManyUpdateDTO metaManyToManyUpdateDTO);

    /**
     * 多对多关联列表查询
     */
    @ApiOperation(value = "多对多关联列表查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "metaManyToManyQueryDTO", dataType = "MetaManyToManyQueryDTO", value = "查询参数", paramType = "body"),
    })
    ReplyVO<List<MetaManyToManyListVO>> list(MetaManyToManyQueryDTO metaManyToManyQueryDTO);

    /**
     * 查看多对多关联详情
     */
    @ApiOperation(value = "查看多对多关联详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mtmId", dataType = "int", value = "多对多关联id", paramType = "path"),
    })
    ReplyVO<MetaManyToManyShowVO> show(Integer mtmId);

    /**
     * 删除多对多关联
     */
    @ApiOperation(value = "删除多对多关联")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mtmId", dataType = "int", value = "多对多关联id", paramType = "path"),
    })
    ReplyVO<Integer> delete(Integer mtmId);
    /**
     * 批量删除多对多关联
     */
    @ApiOperation(value = "批量删除多对多关联")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "mtmId", dataType = "int", value = "多对多关联id数组", paramType = "body"),
    })
    ReplyVO<Integer> deleteBatch(Integer[] mtmId);


}