package com.youran.generate.web.api;

import com.youran.generate.pojo.dto.CodeTemplateAddDTO;
import com.youran.generate.pojo.dto.CodeTemplateUpdateDTO;
import com.youran.generate.pojo.qo.CodeTemplateQO;
import com.youran.generate.pojo.vo.CodeTemplateListVO;
import com.youran.generate.pojo.vo.CodeTemplateShowVO;
import com.youran.generate.pojo.vo.TemplateDirTreeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 【代码模板】API
 * <p> swagger接口文档
 *
 * @author cbb
 * @date 2019/10/24
 */
@Api(tags = "【代码模板】API")
public interface CodeTemplateAPI {

    /**
     * 新增【代码模板】
     */
    @ApiOperation(value = "新增【代码模板】")
    @ApiImplicitParams({@ApiImplicitParam(name = "codeTemplateAddDTO", dataType = "CodeTemplateAddDTO", value = "新增【代码模板】参数", paramType = "body"),})
    ResponseEntity<CodeTemplateShowVO> save(CodeTemplateAddDTO codeTemplateAddDTO) throws Exception;

    /**
     * 修改【代码模板】
     */
    @ApiOperation(value = "修改【代码模板】")
    @ApiImplicitParams({@ApiImplicitParam(name = "codeTemplateUpdateDTO", dataType = "CodeTemplateUpdateDTO", value = "修改【代码模板】参数", paramType = "body"),})
    ResponseEntity<CodeTemplateShowVO> update(CodeTemplateUpdateDTO codeTemplateUpdateDTO);

    /**
     * 分页查询【代码模板】
     */
    @ApiOperation(value = "分页查询【代码模板】")
    ResponseEntity<List<CodeTemplateListVO>> list(CodeTemplateQO codeTemplateQO);

    /**
     * 查看【代码模板】详情
     */
    @ApiOperation(value = "查看【代码模板】详情")
    @ApiImplicitParams({@ApiImplicitParam(name = "templateId", dataType = "int", value = "【代码模板】id", paramType = "path"),})
    ResponseEntity<CodeTemplateShowVO> show(Integer templateId);

    /**
     * 删除单个【代码模板】
     */
    @ApiOperation(value = "删除单个【代码模板】")
    @ApiImplicitParams({@ApiImplicitParam(name = "templateId", dataType = "int", value = "【代码模板】id", paramType = "path"),})
    ResponseEntity<Integer> delete(Integer templateId);

    /**
     * 批量删除【代码模板】
     */
    @ApiOperation(value = "批量删除【代码模板】")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", dataType = "int", value = "id数组", paramType = "body"),})
    ResponseEntity<Integer> deleteBatch(Integer[] id);


    /**
     * 查看模板文件目录结构
     *
     * @param templateId 模板id
     * @return
     */
    @ApiOperation(value = "查看模板文件目录结构")
    @ApiImplicitParams({@ApiImplicitParam(name = "templateId", dataType = "int", value = "模板id", paramType = "path")})
    ResponseEntity<TemplateDirTreeVO> dirTree(Integer templateId);

    /**
     * 模板导出
     *
     * @param templateId
     * @param response
     */
    @ApiOperation(value = "模板导出")
    @ApiImplicitParams({@ApiImplicitParam(name = "templateId", dataType = "int", value = "模板id", paramType = "path")})
    void export(Integer templateId, HttpServletResponse response);

    /**
     * 模板复制
     *
     * @param templateId
     */
    @ApiOperation(value = "模板复制")
    @ApiImplicitParams({@ApiImplicitParam(name = "templateId", dataType = "int", value = "模板id", paramType = "path")})
    ResponseEntity<CodeTemplateShowVO> copy(Integer templateId) throws Exception;

    /**
     * 模板导入
     *
     * @param file
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "模板导入")
    ResponseEntity<CodeTemplateShowVO> importTemplate(MultipartFile file) throws Exception;
}

