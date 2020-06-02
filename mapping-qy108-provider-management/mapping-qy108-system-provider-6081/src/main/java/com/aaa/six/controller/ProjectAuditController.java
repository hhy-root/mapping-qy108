package com.aaa.six.controller;

import com.aaa.six.model.Audit;
import com.aaa.six.model.MappingProject;
import com.aaa.six.model.Resource;
import com.aaa.six.service.AuditService;
import com.aaa.six.service.ProjectAuditService;
import com.aaa.six.service.ResourceService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Company AAA软件教育
 * @Title mapping-qy108
 * @Author hhy
 * @Version 0.1.0
 * @Date Create in 2020/5/31 18:40
 * @Description
 */
@RestController
public class ProjectAuditController  {
    @Autowired
    private ProjectAuditService projectAuditService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private AuditService auditService;

    /**
     * @author hhy
     * @description
     *    查询未审核的项目信息
     * @param: [pageNo, pageSize]
     * @date 2020/5/31 18:44
     * @return com.github.pagehelper.PageInfo<com.aaa.six.model.MappingProject>
     * @throws
     */
    @PostMapping("/selectNOAuditProject")
    public PageInfo<MappingProject> selectNOAuditProject(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
        try{
            PageInfo<MappingProject> noAuditProject = projectAuditService.selectNOAuditProject(pageNo, pageSize);
            return noAuditProject;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @author hhy
     * @description
     *    查询审核通过的项目信息
     * @param: [pageNo, pageSize]
     * @date 2020/5/31 18:45
     * @return com.github.pagehelper.PageInfo<com.aaa.six.model.MappingProject>
     * @throws
     */
    @PostMapping("/selectAuditProject")
    public PageInfo<MappingProject> selectAuditProject(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
        try{
            PageInfo<MappingProject> noAuditProject = projectAuditService.selectAuditProject(pageNo, pageSize);
            return noAuditProject;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @author hhy
     * @description
     *    查询未审核的项目成功汇交
     * @param: [pageNo, pageSize]
     * @date 2020/5/31 18:47
     * @return com.github.pagehelper.PageInfo<com.aaa.six.model.MappingProject>
     * @throws
     */
    @PostMapping("/selectNOAuditResults")
    public PageInfo<MappingProject> selectNOAuditResults(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
        try{
            PageInfo<MappingProject> noAuditProject = projectAuditService.selectNOAuditResults(pageNo, pageSize);
            return noAuditProject;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @author hhy
     * @description
     *    查询审核通过的项目成功汇交（分页）
     * @param: [pageNo, pageSize]
     * @date 2020/5/31 18:47
     * @return com.github.pagehelper.PageInfo<com.aaa.six.model.MappingProject>
     * @throws
     */
    @PostMapping("/selectAuditResults")
    public PageInfo<MappingProject> selectAuditResults(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
        try{
            PageInfo<MappingProject> noAuditProject = projectAuditService.selectAuditResults(pageNo, pageSize);
            return noAuditProject;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @author hhy
     * @description
     *    去审核，通过id查询信息
     * @param: [id]
     * @date 2020/5/31 18:52
     * @return com.aaa.six.model.MappingProject
     * @throws
     */
    @PostMapping("/toAudit")
    public HashMap toAudit(@RequestParam("id") Long id){
        try{
            //查询基本信息
            MappingProject mappingProject = projectAuditService.queryOne(id);
            //查询附件信息
            Resource resource = resourceService.queryOne(id);
            HashMap map = new HashMap();
            map.put("mappingProject",mappingProject);
            map.put("resource",resource);
            return map;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @author hhy
     * @description
     *    查询审核记录（分页）
     * @param: [id]
     * @date 2020/5/31 19:58
     * @return java.util.List<com.aaa.six.model.Audit>
     * @throws
     */
    @PostMapping("/selectAuditLog")
    public PageInfo<Audit> selectAuditLog(@RequestParam("id") Long id,@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
        try{
            PageInfo<Audit> noAuditProject = auditService.selectAuditLog(id,pageNo, pageSize);
            return noAuditProject;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @author hhy
     * @description
     *    添加审核记录
     * @param: [audit]
     * @date 2020/5/31 20:37
     * @return java.lang.Integer
     * @throws
     */
    @PostMapping("/addAuditLog")
    public Integer addAuditLog(@RequestBody Audit audit){
        try {
            return auditService.add(audit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
