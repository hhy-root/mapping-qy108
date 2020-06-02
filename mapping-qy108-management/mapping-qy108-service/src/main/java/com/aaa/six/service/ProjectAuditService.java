package com.aaa.six.service;

import com.aaa.six.base.BaseService;
import com.aaa.six.mapper.AuditMapper;
import com.aaa.six.mapper.MappingProjectMapper;
import com.aaa.six.model.Audit;
import com.aaa.six.model.MappingProject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Company AAA软件教育
 * @Title mapping-qy108
 * @Author hhy
 * @Version 0.1.0
 * @Date Create in 2020/5/31 15:53
 * @Description
 *     项目审核模块
 */
@Service
public class ProjectAuditService extends BaseService<MappingProject> {

    @Autowired
    private MappingProjectMapper mappingProjectMapper;


    /**
     * @author hhy
     * @description
     *    查询未审核的项目（分页）
     * @param: [mappingProject, pageNo, pageSize]
     * @date 2020/5/31 18:20
     * @return com.github.pagehelper.PageInfo<com.aaa.six.model.MappingProject>
     * @throws
     */
    public PageInfo<MappingProject> selectNOAuditProject( Integer pageNo, Integer pageSize){
        PageHelper.startPage(pageNo, pageSize);
        List<MappingProject> noAuditProject = mappingProjectMapper.selectNOAuditProject();
        PageInfo<MappingProject> pageInfo = new PageInfo<>(noAuditProject);
        return pageInfo;
    }

    /**
     * @author hhy
     * @description
     *    查询审核通过的项目（分页）
     * @param: [pageNo, pageSize]
     * @date 2020/5/31 18:22
     * @return com.github.pagehelper.PageInfo<com.aaa.six.model.MappingProject>
     * @throws
     */
    public PageInfo<MappingProject> selectAuditProject( Integer pageNo, Integer pageSize){
        PageHelper.startPage(pageNo, pageSize);
        List<MappingProject> noAuditProject = mappingProjectMapper.selectAuditProject();
        PageInfo<MappingProject> pageInfo = new PageInfo<>(noAuditProject);
        return pageInfo;
    }

    /**
     * @author hhy
     * @description
     *    查询未审核的项目成功汇交（分页）
     * @param: [pageNo, pageSize]
     * @date 2020/5/31 18:23
     * @return com.github.pagehelper.PageInfo<com.aaa.six.model.MappingProject>
     * @throws
     */
    public PageInfo<MappingProject> selectNOAuditResults( Integer pageNo, Integer pageSize){
        PageHelper.startPage(pageNo, pageSize);
        List<MappingProject> noAuditProject = mappingProjectMapper.selectNOAuditResults();
        PageInfo<MappingProject> pageInfo = new PageInfo<>(noAuditProject);
        return pageInfo;
    }

    /**
     * @author hhy
     * @description
     *    查询审核通过的项目成功汇交（分页）
     * @param: [pageNo, pageSize]
     * @date 2020/5/31 18:24
     * @return com.github.pagehelper.PageInfo<com.aaa.six.model.MappingProject>
     * @throws
     */
    public PageInfo<MappingProject> selectAuditResults( Integer pageNo, Integer pageSize){
        PageHelper.startPage(pageNo, pageSize);
        List<MappingProject> noAuditProject = mappingProjectMapper.selectAuditResults();
        PageInfo<MappingProject> pageInfo = new PageInfo<>(noAuditProject);
        return pageInfo;
    }





}
