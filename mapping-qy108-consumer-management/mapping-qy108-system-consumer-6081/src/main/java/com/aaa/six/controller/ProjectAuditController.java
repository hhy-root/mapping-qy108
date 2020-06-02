package com.aaa.six.controller;

import com.aaa.six.base.BaseController;
import com.aaa.six.base.ResultData;
import com.aaa.six.model.Audit;
import com.aaa.six.model.MappingProject;
import com.aaa.six.service.IQYService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @Company AAA软件教育
 * @Title mapping-qy108
 * @Author hhy
 * @Version 0.1.0
 * @Date Create in 2020/5/31 23:51
 * @Description
 */
@RestController
@Api(value = "项目审核", tags = "项目审核板块")
public class ProjectAuditController  extends BaseController {

    @Autowired
    private IQYService qyService;

    /**
     * @author hhy
     * @description
     *    查询未审核的项目信息
     * @param: [pageNo, pageSize]
     * @date 2020/6/2 13:23
     * @return com.aaa.six.base.ResultData
     * @throws
     */
    @PostMapping("/selectNOAuditProject")
    @ApiOperation(value = "项目审核", notes = "查询未审核的项目信息")
    public ResultData selectNOAuditProject(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) {
        PageInfo<MappingProject> noAuditProject = qyService.selectNOAuditProject(pageNo, pageSize);
        if(null != noAuditProject && !"".equals(noAuditProject) ){
            return selectSuccess(noAuditProject);
        }
        return selectFailed();
    }

    /**
     * @author hhy
     * @description
     *    查询审核通过的项目信息
     * @param: [pageNo, pageSize]
     * @date 2020/6/2 13:23
     * @return com.aaa.six.base.ResultData
     * @throws
     */
    @PostMapping("/selectAuditProject")
    @ApiOperation(value = "项目审核", notes = "查询审核通过的项目信息")
    public ResultData selectAuditProject(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) {
        PageInfo<MappingProject> auditProject = qyService.selectAuditProject(pageNo, pageSize);
        if(null != auditProject && !"".equals(auditProject) ){
            return selectSuccess(auditProject);
        }
        return selectFailed();
    }

    /**
     * @author hhy
     * @description
     *    查询未审核的项目汇交
     * @param: [pageNo, pageSize]
     * @date 2020/6/2 13:23
     * @return com.aaa.six.base.ResultData
     * @throws
     */
    @PostMapping("/selectNOAuditResults")
    @ApiOperation(value = "项目审核", notes = "查询未审核的项目汇交")
    public ResultData selectNOAuditResults(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) {
        PageInfo<MappingProject> noAuditResults = qyService.selectNOAuditResults(pageNo, pageSize);
        if(null != noAuditResults && !"".equals(noAuditResults) ){
            return selectSuccess(noAuditResults);
        }
        return selectFailed();
    }

    /**
     * @author hhy
     * @description
     *
     * @param: [pageNo, pageSize]
     * @date 2020/6/2 13:23
     * @return com.aaa.six.base.ResultData
     * @throws
     */
    @PostMapping("/selectAuditResults")
    @ApiOperation(value = "项目审核", notes = "查询审核通过的项目汇交")
    public ResultData selectAuditResults(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) {
        PageInfo<MappingProject> auditResults = qyService.selectAuditResults(pageNo, pageSize);
        if(null != auditResults && !"".equals(auditResults) ){
            return selectSuccess(auditResults);
        }
        return selectFailed();
    }

    /**
     * @author hhy
     * @description
     *    通过id查询当前需要审核的数据信息
     * @param: [id]
     * @date 2020/6/2 13:25
     * @return java.util.HashMap
     * @throws
     */
    @PostMapping("/toAudit")
    @ApiOperation(value = "项目审核", notes = "去审核")
    public ResultData toAudit(@RequestParam("id") Long id){
        HashMap map = qyService.toAudit(id);
        if(null != map && !"".equals(map) ){
            return selectSuccess(map);
        }
        return selectFailed();
    }

    /**
     * @author hhy
     * @description
     *    查询审核日志信息
     * @param: [id, pageNo, pageSize]
     * @date 2020/6/2 13:28
     * @return com.aaa.six.base.ResultData
     * @throws
     */
    @PostMapping("/selectAuditLog")
    @ApiOperation(value = "项目审核", notes = "查询审核日志")
    public ResultData selectAuditLog(@RequestParam("id") Long id, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) {
        PageInfo<Audit> pageInfo = qyService.selectAuditLog(id, pageNo, pageSize);
        if(null != pageInfo && !"".equals(pageInfo) ){
            return selectSuccess(pageInfo);
        }
        return selectFailed();
    }

    /**
     * @author hhy
     * @description
     *
     * @param: [audit]
     * @date 2020/6/2 13:28
     * @return java.lang.Integer
     * @throws
     */
    @PostMapping("/addAuditLog")
    @ApiOperation(value = "项目审核", notes = "添加审核日志")
    public ResultData addAuditLog(@RequestBody Audit audit) {
        Integer integer = qyService.addAuditLog(audit);
        if(integer>0){
            return insertSuccess();
        }
        return insertFailed();
    }

}
