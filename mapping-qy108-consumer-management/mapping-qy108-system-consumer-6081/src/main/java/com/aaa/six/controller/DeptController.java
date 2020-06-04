package com.aaa.six.controller;

import com.aaa.six.base.BaseController;
import com.aaa.six.base.ResultData;
import com.aaa.six.model.Dept;
import com.aaa.six.service.IQYDeptService;
import com.aaa.six.service.IQYEquipmentService;
import com.aaa.six.service.IQYService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Api(value = "部门管理", tags = "部门管理")
public class DeptController extends BaseController {

    @Autowired
    private IQYDeptService qyService;
    /**
     * @Author: ly
     * @description:
     *      获取所有的部门信息
     * @date: 2020/5/20
     * @param pid
     * @param pageNo
     * @param pageSize
     * @return: com.aaa.six.base.ResultData
     *
     */
    @PostMapping("/getAllDept")
    public ResultData getAllDept(@RequestParam("pid") Integer pid, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
        PageInfo allDept = qyService.getAllDept(pid, pageNo, pageSize);
        if(null != allDept){
            return selectSuccess(allDept);
        }
        return selectFailed();
    }

    /**
     * @Author: ly
     * @description:
     *
     *      根据主键查询部门信息
     * @date: 2020/5/22
     * @param id
     * @return: com.aaa.six.base.ResultData
     *
     */
    @GetMapping("/getDeptById")
    public ResultData getDeptByID(@RequestParam("id") Integer id){
        Dept dept = qyService.getDeptById(id);
        if(null != dept && !"".equals(dept)){
            return selectSuccess(dept);
        }
        return selectFailed();
    }

    /**
     * @Author: ly
     * @description:
     *
     *      根据条件查询部门信息
     * @date: 2020/5/22
     * @param dept
     * @return: com.aaa.six.base.ResultData
     *
     */
    @PostMapping("/getDeptByFileds")
    public ResultData getDeptByFileds(@RequestBody Dept dept){
        List<Dept> deptByFileds = qyService.getDeptByFileds(dept);
        if(deptByFileds.size()>0){
            return selectSuccess(deptByFileds);
        }
        return selectFailed();
    }

    /**
     * @Author: ly
     * @description:
     *
     *      修改部门信息
     * @date: 2020/5/22
     * @param dept
     * @return: com.aaa.six.base.ResultData
     *
     */
    @PostMapping("/updateDept")
    public ResultData updateDept(@RequestBody Dept dept){
        Integer i = qyService.updateDept(dept);
        if(i>0){
            return updateSuccess();
        }
        return updateFailed();
    }

    /**
     * @Author: ly
     * @description:
     *
     *      批量删除
     * @date: 2020/5/23
     * @param ids
     * @return: com.aaa.six.base.ResultData
     *
     */
    @PostMapping("/delByIds")
    public ResultData delByIds(@RequestBody List<Object> ids){
        Integer i = qyService.delByIds(ids);
        if(i>0){
            return deleteSuccess();
        }
        return deleteFailed();
    }

    /**
     * @Author: ly
     * @description:
     *
     *      添加新的部门
     * @date: 2020/5/23
     * @param dept
     * @return: com.aaa.six.base.ResultData
     *
     */
    @PostMapping("/addDept")
    public ResultData addDept(@RequestBody Dept dept){
        Integer i = qyService.addDept(dept);
        if (i>0){
            return insertSuccess();
        }

        return insertFailed();

    }

}
