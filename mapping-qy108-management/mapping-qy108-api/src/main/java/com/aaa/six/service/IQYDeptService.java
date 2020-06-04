package com.aaa.six.service;


import com.aaa.six.model.Dept;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "dept-interface")
public interface IQYDeptService {

    /**
     * @param pid
     * @param pageNo
     * @param pageSize
     * @Author: ly
     * @description: 查询所有的部门
     * @date: 2020/5/22
     * @return: com.github.pagehelper.PageInfo
     */
    @PostMapping("/getAllDept")
    PageInfo getAllDept(@RequestParam("pid") Integer pid, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

    /**
     * @param id
     * @Author: ly
     * @description: 根据主键查找部门
     * @date: 2020/5/22
     * @return: com.aaa.six.model.Dept
     */
    @GetMapping("/getDeptById")
    Dept getDeptById(@RequestParam("id") Integer id);

    /**
     * @param dept
     * @Author: ly
     * @description: 按照调价查询部门
     * @date: 2020/5/22
     * @return: java.util.List<com.aaa.six.model.Dept>
     */
    @PostMapping("/getDeptByFileds")
    List<Dept> getDeptByFileds(@RequestBody Dept dept);

    /**
     * @param dept
     * @Author: ly
     * @description: 更新部门
     * @date: 2020/5/22
     * @return: java.lang.Integer
     */
    @PostMapping("/updateDept")
    Integer updateDept(@RequestBody Dept dept);

    /**
     * @param ids
     * @Author: ly
     * @description: 按照主键批量删除
     * @date: 2020/5/22
     * @return: java.lang.Integer
     */
    @PostMapping("/delByIds")
    Integer delByIds(@RequestBody List<Object> ids);

    /**
     * @param dept
     * @Author: ly
     * @description: 增加部门
     * @date: 2020/5/22
     * @return: java.lang.Integer
     */
    @PostMapping("/addDept")
    Integer addDept(@RequestBody Dept dept);
}
