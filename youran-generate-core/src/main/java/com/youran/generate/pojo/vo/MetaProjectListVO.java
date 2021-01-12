package com.youran.generate.pojo.vo;

import com.youran.common.pojo.vo.AbstractVO;
import com.youran.generate.pojo.dto.LabelDTO;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

import static com.youran.generate.pojo.example.MetaProjectExample.*;

/**
 * 项目列表展示对象
 *
 * @author: cbb
 * @date 2017/5/24
 */
public class MetaProjectListVO extends AbstractVO {

    @ApiModelProperty(notes = N_PROJECTID, example = E_PROJECTID)
    private Integer projectId;

    @ApiModelProperty(notes = N_PACKAGENAME, example = E_PACKAGENAME)
    private String packageName;

    @ApiModelProperty(notes = N_PROJECTNAME, example = E_PROJECTNAME)
    private String projectName;

    @ApiModelProperty(notes = N_PROJECTDESC, example = E_PROJECTDESC)
    private String projectDesc;

    @ApiModelProperty(notes = N_GROUPID, example = E_GROUPID)
    private String groupId;

    @ApiModelProperty(notes = N_AUTHOR, example = E_AUTHOR)
    private String author;

    @ApiModelProperty(notes = N_REMOTE, example = E_REMOTE)
    private Boolean remote;

    @ApiModelProperty(notes = N_TEMPLATEID, example = E_TEMPLATEID)
    private Integer templateId;

    @ApiModelProperty(notes = N_TEMPLATEID, example = E_TEMPLATEID)
    private Integer templateId2;

    @ApiModelProperty(notes = N_TEMPLATEID, example = E_TEMPLATEID)
    private Integer templateId3;

    @ApiModelProperty(notes = N_REMOTEURL, example = E_REMOTEURL)
    private String remoteUrl;

    @ApiModelProperty(notes = N_REMOTEURL, example = E_REMOTEURL)
    private String remoteUrl2;

    @ApiModelProperty(notes = N_REMOTEURL, example = E_REMOTEURL)
    private String remoteUrl3;

    @ApiModelProperty(notes = N_USERNAME, example = E_USERNAME)
    private String username;

    @ApiModelProperty(notes = "标签")
    private List<LabelDTO> labels;

    @ApiModelProperty(notes = N_TEAM_ID, example = E_TEAM_ID)
    private Integer teamId;

    @ApiModelProperty(notes = N_CREATEDBY, example = E_CREATEDBY)
    private String createdBy;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Boolean getRemote() {
        return remote;
    }

    public void setRemote(Boolean remote) {
        this.remote = remote;
    }

    public String getRemoteUrl() {
        return remoteUrl;
    }

    public void setRemoteUrl(String remoteUrl) {
        this.remoteUrl = remoteUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public Integer getTemplateId2() {
        return templateId2;
    }

    public void setTemplateId2(Integer templateId2) {
        this.templateId2 = templateId2;
    }

    public Integer getTemplateId3() {
        return templateId3;
    }

    public void setTemplateId3(Integer templateId3) {
        this.templateId3 = templateId3;
    }

    public String getRemoteUrl2() {
        return remoteUrl2;
    }

    public void setRemoteUrl2(String remoteUrl2) {
        this.remoteUrl2 = remoteUrl2;
    }

    public String getRemoteUrl3() {
        return remoteUrl3;
    }

    public void setRemoteUrl3(String remoteUrl3) {
        this.remoteUrl3 = remoteUrl3;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public List<LabelDTO> getLabels() {
        return labels;
    }

    public void setLabels(List<LabelDTO> labels) {
        this.labels = labels;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE).append("projectId", projectId).append("packageName", packageName).append("projectName", projectName).append("projectDesc", projectDesc).append("groupId", groupId).append("author", author).append("remote", remote).toString();
    }
}
