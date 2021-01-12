package com.youran.generate.pojo.vo;

import com.youran.common.pojo.vo.AbstractVO;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

import static com.youran.generate.pojo.example.MetaIndexExample.*;

/**
 * 索引列表VO
 *
 * @author: cbb
 * @date: 2017/5/16
 */
public class MetaIndexListVO extends AbstractVO {

    @ApiModelProperty(notes = N_INDEXID, example = E_INDEXID)
    private Integer indexId;

    @ApiModelProperty(notes = N_INDEXNAME, example = E_INDEXNAME)
    private String indexName;

    @ApiModelProperty(notes = N_ENTITYID, example = E_ENTITYID)
    private Integer entityId;

    @ApiModelProperty(notes = N_UNIQUE, example = E_UNIQUE)
    private Boolean unique;

    @ApiModelProperty(notes = N_UNIQUECHECK, example = E_UNIQUECHECK)
    private Boolean uniqueCheck;

    @ApiModelProperty(notes = "字段列表")
    private List<MetaFieldListVO> fields;


    public Integer getIndexId() {
        return indexId;
    }

    public void setIndexId(Integer indexId) {
        this.indexId = indexId;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public Boolean getUnique() {
        return unique;
    }

    public void setUnique(Boolean unique) {
        this.unique = unique;
    }

    public Boolean getUniqueCheck() {
        return uniqueCheck;
    }

    public void setUniqueCheck(Boolean uniqueCheck) {
        this.uniqueCheck = uniqueCheck;
    }

    public List<MetaFieldListVO> getFields() {
        return fields;
    }

    public void setFields(List<MetaFieldListVO> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE).append("indexId", indexId).append("indexName", indexName).append("entityId", entityId).append("unique", unique).append("uniqueCheck", uniqueCheck).append("fields", fields).toString();
    }
}
