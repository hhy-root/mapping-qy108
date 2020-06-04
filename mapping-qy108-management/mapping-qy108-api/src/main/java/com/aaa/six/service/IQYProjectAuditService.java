package com.aaa.six.service;

import com.aaa.six.model.Audit;
import com.aaa.six.model.MappingProject;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

/**
 * @Company AAA软件教育
 * @Title mapping-qy108
 * @Author hhy
 * @Version 0.1.0
 * @Date Create in 2020/6/3 1:06
 * @Description
 *     项目审核
 */
@FeignClient("projectAudit-interface")
public interface IQYProjectAuditService {

    /**
     * @author hhy
     * @description
     *    查询未审核项目
     * @param: [pageNo, pageSize]
     * @date 2020/5/31 23:11
     * @return com.github.pagehelper.PageInfo<com.aaa.six.model.MappingProject>
     * @throws
     */
    @PostMapping("/selectNOAuditProject")
    PageInfo<MappingProject> selectNOAuditProject(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

    /**
     * @author hhy
     * @description
     *    查询审核通过的项目
     * @param: [pageNo, pageSize]
     * @date 2020/5/31 23:41
     * @return com.github.pagehelper.PageInfo<com.aaa.six.model.MappingProject>
     * @throws
     */
    @PostMapping("/selectAuditProject")
    PageInfo<MappingProject> selectAuditProject(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

    /**
     * @author hhy
     * @description
     *    查询未审核的项目汇交
     * @param: [pageNo, pageSize]
     * @date 2020/5/31 23:42
     * @return com.github.pagehelper.PageInfo<com.aaa.six.model.MappingProject>
     * @throws
     */
    @PostMapping("/selectNOAuditResults")
    PageInfo<MappingProject> selectNOAuditResults(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

    /**
     * @author hhy
     * @description
     *    查询审核通过的项目汇交
     * @param: [pageNo, pageSize]
     * @date 2020/5/31 23:43
     * @return com.github.pagehelper.PageInfo<com.aaa.six.model.MappingProject>
     * @throws
     */
    @PostMapping("/selectAuditResults")
    PageInfo<MappingProject> selectAuditResults(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

    /**
     * @author hhy
     * @description
     *    去审核（通过id查询要审核的信息）
     * @param: [id]
     * @date 2020/5/31 23:44
     * @return java.util.HashMap
     * @throws
     */
    @PostMapping("/toAudit")
    HashMap toAudit(@RequestParam("id") Long id);

    /**
     * @author hhy
     * @description
     *    查询审核记录（分页）
     * @param: [id, pageNo, pageSize]
     * @date 2020/5/31 23:45
     * @return com.github.pagehelper.PageInfo<com.aaa.six.model.Audit>
     * @throws
     */
    @PostMapping("/selectAuditLog")
    PageInfo<Audit> selectAuditLog(@RequestParam("id") Long id, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

    /**
     * @author hhy
     * @description
     *    添加审核日志
     * @param: [audit]
     * @date 2020/5/31 23:53
     * @return java.lang.Integer
     * @throws
     */
    @PostMapping("/addAuditLog")
    Integer addAuditLog(@RequestBody Audit audit);
}
