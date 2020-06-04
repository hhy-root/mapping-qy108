package com.aaa.six.mapper;


import com.aaa.six.model.MappingProject;
import com.aaa.six.model.MappingProjectTypeNum;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MappingProjectMapper extends Mapper<MappingProject> {

    /**
     * 查询未审核的项目（已提交）
     * @return
     */
    List<MappingProject> selectNOAuditProject();
    /**
     *审核项目
     * @param id
     * @return
     */
    Integer auditProject(Long id);
    /**
     *查询审核通过的项目（项目信息）
     * @return
     */
    List<MappingProject> selectAuditProject();
    /**
     *查询项目成果汇交已提交（未审核）
     * @return
     */
    List<MappingProject> selectNOAuditResults();
    /**
     *审核项目成果汇交
     * @param id
     * @return
     */
    Integer auditResults(Long id);
    /**
     *查询项目成果汇交通过
     * @return
     */
    List<MappingProject> selectAuditResults();
    /**
     * @Author: ly
     * @description:
     *      项目类型统计
     * @date: 2020/6/2
     * @param
     * @return: java.util.List<com.aaa.six.model.MappingProjectTypeNum>
     *
     */
    List<MappingProjectTypeNum> getProjectTypeNum();


    /**
     * @Author: ly
     * @description:
     *
     *      根据userId得到单位的项目数量
     * @date: 2020/6/4
     * @param userId
     * @return: java.lang.Integer
     *
     */
    Integer getProjectNum(Long userId);
}