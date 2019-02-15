package com.youran.generate.web.rest;

import com.youran.common.pojo.vo.ReplyVO;
import com.youran.common.util.DateUtil;
import com.youran.generate.constant.GenerateConst;
import com.youran.generate.pojo.po.GenHistoryPO;
import com.youran.generate.service.MetaCodeGenService;
import com.youran.generate.service.MetaProjectService;
import com.youran.generate.web.AbstractController;
import com.youran.generate.web.api.MetaCodeGenAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;

/**
 * Title:【代码生成】控制器
 * Description:
 * Author: cbb
 * Create Time:2017/5/13 23:00
 */
@Controller
@RequestMapping(GenerateConst.API_PATH + "/code_gen")
public class MetaCodeGenController extends AbstractController implements MetaCodeGenAPI {

    @Autowired
    private MetaCodeGenService metaCodeGenService;
    @Autowired
    private MetaProjectService metaProjectService;

    @Override
    @GetMapping(value = "/genSql")
    public void genSql(Integer projectId, HttpServletResponse response) {
        String text = metaCodeGenService.genSql(projectId);
        this.replyDownloadText(response,text,"db.sql");
    }

    @Override
    @GetMapping(value = "/sqlPreview")
    @ResponseBody
    public ReplyVO<String> sqlPreview(Integer entityId) {
        String text = metaCodeGenService.sqlPreview(entityId);
        return ReplyVO.success().data(text);
    }


    @Override
    @GetMapping(value = "/genCode")
    public void genCode(Integer projectId, HttpServletResponse response) {
        File zipFile = metaCodeGenService.genCodeZip(projectId,null);
        if (zipFile == null || !zipFile.exists()) {
            this.replyNotFound(response);
        }else {
            String normalProjectName = metaProjectService.getNormalProjectName(projectId);
            String downloadFileName = normalProjectName + DateUtil.getDateStr(new Date(), "yyyyMMddHHmmss") + ".zip";
            this.replyDownloadFile(response, zipFile, downloadFileName);
        }
    }

    @Override
    @GetMapping(value = "/gitCommit")
    @ResponseBody
    public ReplyVO<Void> gitCommit(Integer projectId) {
        //校验操作人
        metaProjectService.checkOperatorByProjectId(projectId);
        GenHistoryPO genHistory = metaCodeGenService.gitCommit(projectId, null);
        ReplyVO replyVO = ReplyVO.success();
        replyVO.setMessage("已创建自动分支【"+ genHistory.getBranch() +"】，并提交到远程");
        return replyVO;
    }

}
