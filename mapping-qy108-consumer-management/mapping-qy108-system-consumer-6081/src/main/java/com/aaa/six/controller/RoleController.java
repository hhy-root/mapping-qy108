package com.aaa.six.controller;

import com.aaa.six.service.IQYRoleService;
import com.aaa.six.base.BaseController;
import com.aaa.six.base.ResultData;
import com.aaa.six.model.Role;
import com.aaa.six.vo.RoleMenuVo;
import com.aaa.six.vo.RoleVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Company AAA软件教育
 * @Title mapping-qy108
 * @Author hhy
 * @Version 0.1.0
 * @Date Create in 2020/5/21 11:14
 * @Description
 */
@RestController
@Api(value = "角色信息", tags = "角色管理接口")
public class RoleController extends BaseController {
    @Autowired
    private IQYRoleService roleService;

    /**
     * @author hhy
     * @description
     *    查询角色信息
     * @param: [user]
     * @date 2020/5/16 8:23
     * @return com.aaa.six.base.ResultData
     * @throws
     */
    @PostMapping("/getAllRole")
    @ApiOperation(value = "查询所有角色信息", notes = "角色管理接口")
    public ResultData getAllRole(Role role,@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
        PageInfo<Role> allRole = roleService.getAllRole(role,pageNo, pageSize);
        if(null != allRole) {
            return selectSuccess(allRole);
        }
        return selectFailed();
    }

    /**
     * @author hhy
     * @description
     *    添加角色
     * @param: [role]
     * @date 2020/5/21 16:59
     * @return com.aaa.six.base.ResultData
     * @throws
     */
    @PostMapping("/addRole")
    @ApiOperation(value = "添加角色", notes = "角色管理接口")
    public ResultData addRole(@RequestBody RoleVo roleVo){
        Integer i = roleService.addRole(roleVo);
        if(null != i && !"".equals(i)) {
            return insertSuccess();
        }
        return insertFailed();
    }

    /**
     * @author hhy
     * @description
     *    删除角色
     * @param: [ids]
     * @date 2020/5/22 18:27
     * @return com.aaa.six.base.ResultData
     * @throws 
     */
    @PostMapping("/deleteRole")
    @ApiOperation(value = "删除角色", notes = "角色管理接口")
    public ResultData deleteRole(@RequestBody List<Object> ids){
        Integer d = roleService.deleteRole(ids);
        if(null != d && !"".equals(d)) {
            return deleteSuccess();
        }
        return deleteFailed();
    }

    @PostMapping("/toUpdateRole/{id}")
    @ApiOperation(value = "查询这个角色信息", notes = "角色管理接口")
    public ResultData toUpdateRole(@RequestParam("id") Long id){
        List list = roleService.toUpdateRole(id);
        if(list.size()>0) {
            return selectSuccess(list);
        }
        return selectFailed();
    }

    @PostMapping("/updateRole")
    @ApiOperation(value = "修改角色信息", notes = "角色管理接口")
    public ResultData toUpdateRole(@RequestBody RoleMenuVo roleMenuVo){
        Boolean aBoolean = roleService.updateRole(roleMenuVo);
        if(aBoolean) {
            return updateSuccess();
        }
        return updateFailed();
    }
}
