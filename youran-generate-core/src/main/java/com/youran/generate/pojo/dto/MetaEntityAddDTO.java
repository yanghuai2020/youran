package com.youran.generate.pojo.dto;

import com.youran.common.pojo.dto.AbstractDTO;
import com.youran.generate.constant.PatternConst;
import com.youran.generate.constant.WordBlacklist;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.util.List;

import static com.youran.generate.pojo.example.MetaEntityExample.*;


/**
 * 新增实体DTO
 *
 * @author: cbb
 * @date: 2017/5/12
 */
@ApiModel(description = "新增实体参数")
public class MetaEntityAddDTO extends AbstractDTO {


    @ApiModelProperty(notes = N_PROJECTID, example = E_PROJECTID)
    @NotNull
    private Integer projectId;

    @ApiModelProperty(notes = N_SCHEMANAME, example = E_SCHEMANAME)
    @Length(max = 20, message = "schemaName最大长度不能超过{max}")
    private String schemaName;

    @ApiModelProperty(notes = N_CLASSNAME, example = E_CLASSNAME)
    @NotNull
    @Length(max = 50, message = "className最大长度不能超过{max}")
    @Pattern(regexp = PatternConst.CLASS_NAME, message = PatternConst.CLASS_NAME_MSG)
    private String className;

    @ApiModelProperty(notes = N_TABLENAME, example = E_TABLENAME)
    @NotNull
    @Length(max = 50, message = "tableName最大长度不能超过{max}")
    private String tableName;

    @ApiModelProperty(notes = N_TITLE, example = E_TITLE)
    @NotNull
    @Length(max = 25, message = "title最大长度不能超过{max}")
    private String title;

    @ApiModelProperty(notes = N_MODULE, example = E_MODULE)
    @Length(max = 25, message = "module最大长度不能超过{max}")
    @Pattern(regexp = PatternConst.MODULE, message = PatternConst.MODULE_MSG)
    private String module;

    @ApiModelProperty(notes = N_DESC, example = E_DESC)
    @Length(max = 250, message = "desc最大长度不能超过{max}")
    private String desc;

    @ApiModelProperty(notes = N_PAGESIGN, example = E_PAGESIGN)
    private Boolean pageSign;

    /**
     * 实体特性
     */
    private MetaEntityFeatureDTO feature;

    /**
     * 标签
     */
    @ApiModelProperty(notes = "标签")
    private List<LabelDTO> labels;

    @AssertTrue(message = "类名不合法")
    public boolean isClassNameValid() {
        return !WordBlacklist.isClassNameBlacklist(this.className);
    }

    public MetaEntityFeatureDTO getFeature() {
        return feature;
    }

    public void setFeature(MetaEntityFeatureDTO feature) {
        this.feature = feature;
    }

    public Boolean getPageSign() {
        return pageSign;
    }

    public void setPageSign(Boolean pageSign) {
        this.pageSign = pageSign;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<LabelDTO> getLabels() {
        return labels;
    }

    public void setLabels(List<LabelDTO> labels) {
        this.labels = labels;
    }
}
