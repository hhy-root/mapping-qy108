package com.aaa.six.service;

import com.aaa.six.model.MappingProject;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ProjectName: mapping-qy108
 * @Package: com.aaa.six.service
 * @ClassName: IQYMappingProjectService
 * @Author: lifuju
 * @Description:
 * @Date: 2020/6/2 21:29
 * @Version: 1.0
 */
@FeignClient("mappingProject-interface")
public interface IQYMappingProjectService {

    /**
     *@Description: TODO
     * 项目管理 新增方法 单个新增
     *@Param :  [mappingProject]
     *@MethodName: add
     *@Author: lifuju
     *@Date: 2020/5/24 8:51
     *@Return: java.lang.Integer
     */
    @PostMapping("/addMappingProject")
    Integer add(@RequestBody MappingProject mappingProject);
    /**
     *@Description: TODO
     * 项目管理 删除方法  单个删除
     *@Param :  [id]
     *@MethodName: delMappingProject
     *@Author: lifuju
     *@Date: 2020/5/24 8:52
     *@Return: java.lang.Integer
     */
    @GetMapping("/delMappingProject/{id}")
    Integer delMappingProject(@PathVariable("id") Long id);

    /**
     *@Description: TODO
     * 项目管理 删除方法 批量删除
     *@Param :  [ids]
     *@MethodName: delBatch
     *@Author: lifuju
     *@Date: 2020/5/24 8:52
     *@Return: java.lang.Integer
     */
    @PostMapping("/deleteBatchMappingProject")
    Integer delBatch(@RequestBody List<Object> ids);

    /**
     *@Description: TODO
     * 项目管理 查询一条
     *@Param :  [id]
     *@MethodName: selectOne
     *@Author: lifuju
     *@Date: 2020/5/24 8:52
     *@Return: com.aaa.six.model.MappingProject
     */
    @GetMapping("lectOne")
    MappingProject selectOne(@RequestParam("id")Long id);

    /**
     *@Description: TODO
     * 项目管理 更新方法 单个更新
     *@Param :  [mappingProject]
     *@MethodName: update
     *@Author: lifuju
     *@Date: 2020/5/24 8:53
     *@Return: java.lang.Integer
     */
    @PostMapping("/updateMappingProject")
    Integer update(@RequestBody MappingProject mappingProject);

    /**
     *@Description: TODO
     * 项目管理 查询方法 分页查询
     *@Param :  [pageNo, pageSize]
     *@MethodName: queryListByPage
     *@Author: lifuju
     *@Date: 2020/5/24 8:53
     *@Return: com.github.pagehelper.PageInfo
     */
    @PostMapping("/mappingProjectByPage")
    PageInfo queryListByPage(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);
}
