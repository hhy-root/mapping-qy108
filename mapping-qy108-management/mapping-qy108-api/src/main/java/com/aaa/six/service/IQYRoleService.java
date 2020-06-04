package com.aaa.six.service;

import com.aaa.six.model.Role;
import com.aaa.six.vo.RoleMenuVo;
import com.aaa.six.vo.RoleVo;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Company AAA软件教育
 * @Title mapping-qy108
 * @Author hhy
 * @Version 0.1.0
 * @Date Create in 2020/6/3 1:01
 * @Description
 *    角色管理
 */
@FeignClient(value = "role-interface")
public interface IQYRoleService {

    /**
     * @author hhy
     * @description
     *    获取所有角色信息
     * @param: [role, pageNo, pageSize]
     * @date 2020/5/25 21:06
     * @return com.github.pagehelper.PageInfo<com.aaa.six.model.Role>
     * @throws
     */
    @PostMapping("/getAllRole")
    PageInfo<Role> getAllRole(Role role, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

    /**
     * @author hhy
     * @description
     *    添加角色
     * @param: [roleVo]
     * @date 2020/5/25 21:11
     * @return java.lang.Integer
     * @throws
     */
    @PostMapping("/addRole")
    Integer addRole(@RequestBody RoleVo roleVo);

    /**
     * @author hhy
     * @description
     *    删除角色
     * @param: [ids]
     * @date 2020/5/25 21:11
     * @return java.lang.Integer
     * @throws
     */
    @PostMapping("/deleteRole")
    Integer deleteRole(@RequestBody List<Object> ids);

    /**
     * @author hhy
     * @description
     *    去修改角色
     * @param: [roleId]
     * @date 2020/5/25 21:12
     * @return java.util.List
     * @throws
     */
    @PostMapping("/toUpdateRole/{id}")
    List toUpdateRole(@RequestParam("id") Long id);

    /**
     * @author hhy
     * @description
     *    修改角色信息
     * @param: [roleMenuVo]
     * @date 2020/5/25 21:13
     * @return java.lang.Boolean
     * @throws
     */
    @PostMapping("/updateRole")
    Boolean updateRole(@RequestBody RoleMenuVo roleMenuVo);
}
