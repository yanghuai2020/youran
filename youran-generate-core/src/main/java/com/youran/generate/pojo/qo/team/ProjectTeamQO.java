package com.youran.generate.pojo.qo.team;

import com.youran.common.pojo.qo.AbstractQO;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.Length;

import java.util.List;

import static com.youran.generate.pojo.example.team.ProjectTeamExample.E_NAME;
import static com.youran.generate.pojo.example.team.ProjectTeamExample.N_NAME;

/**
 * 查询【项目组】的参数
 *
 * @author cbb
 * @date 2020/11/23
 */
public class ProjectTeamQO extends AbstractQO {

    /**
     * 创建人
     */
    @ApiParam(hidden = true)
    private String _creator;
    /**
     * 项目组id
     */
    @ApiParam(hidden = true)
    private List<Integer> _teamId;

    @ApiParam(value = N_NAME, example = E_NAME)
    @Length(max = 32, message = "name最大长度不能超过{max}")
    private String name;

    @ApiParam(value = "创建时间排序标识【1升序,-1降序,0不排序】", example = "1")
    private Integer createdTimeSortSign;

    @ApiParam(value = "修改时间排序标识【1升序,-1降序,0不排序】", example = "1")
    private Integer operatedTimeSortSign;


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCreatedTimeSortSign() {
        return this.createdTimeSortSign;
    }

    public void setCreatedTimeSortSign(Integer createdTimeSortSign) {
        this.createdTimeSortSign = createdTimeSortSign;
    }

    public Integer getOperatedTimeSortSign() {
        return this.operatedTimeSortSign;
    }

    public void setOperatedTimeSortSign(Integer operatedTimeSortSign) {
        this.operatedTimeSortSign = operatedTimeSortSign;
    }

    public String get_creator() {
        return _creator;
    }

    public void set_creator(String _creator) {
        this._creator = _creator;
    }

    public List<Integer> get_teamId() {
        return _teamId;
    }

    public void set_teamId(List<Integer> _teamId) {
        this._teamId = _teamId;
    }
}

