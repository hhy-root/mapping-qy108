package com.aaa.six.controller;

import com.aaa.six.model.*;
import com.aaa.six.service.MenuService;
import com.aaa.six.service.RoleMenuService;
import com.aaa.six.service.RoleService;
import com.aaa.six.vo.MenuVo;
import com.aaa.six.vo.RoleMenuVo;
import com.aaa.six.vo.RoleVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Company AAA软件教育
 * @Title mapping-qy108
 * @Author hhy
 * @Version 0.1.0
 * @Date Create in 2020/5/20 21:30
 * @Description
 */
@RestController
public class RoleController  {
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private RoleMenuService roleMenuService;
    
    @Autowired
    private MenuService menuService;

    /**
     * @author hhy
     * @description
     *    查询所有角色
     * @param: [pageNo, pageSize]
     * @date 2020/5/21 11:15
     * @return com.github.pagehelper.PageInfo<com.aaa.six.model.Role>
     * @throws 
     */
    @PostMapping("/getAllRole")
    public PageInfo<Role> getAllRole(Role role,@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
        try{
            PageInfo<Role> allRole = roleService.queryListByPage(role,pageNo, pageSize);
            return allRole;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @author hhy
     * @description
     *    添加角色
     * @param: [role]
     * @date 2020/5/22 15:48
     * @return java.lang.Integer
     * @throws 
     */
    @PostMapping("/addRole")
    public Integer addRole(@RequestBody RoleVo roleVo){
        try{
            Boolean b = roleService.addRole(roleVo);
            if(b)
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @author hhy
     * @description
     *    删除角色
     * @param: [ids]
     * @date 2020/5/23 13:32
     * @return java.lang.Integer
     * @throws 
     */
    @PostMapping("/deleteRole")
    public Integer deleteRole(@RequestBody List<Object> ids){
        try {
            Integer delete = roleService.delete(ids);
            Integer delete1 = roleMenuService.delete(ids);
            if(null != delete && !"".equals(delete)){
                return delete;
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @author hhy
     * @description
     *    去修改(查询这个角色的所有信息)
     * @param: [roleId]
     * @date 2020/5/23 13:37
     * @return java.util.List
     * @throws
     */
    @PostMapping("/toUpdateRole/{id}")
    public List toUpdateRole(@RequestParam("id") Long id){
        ArrayList list = new ArrayList();
        try{
            //通过角色id查询角色基本信息
            Role role = roleService.queryOne(id);
            //通过角色id查询当前角色所拥有的权限
            List<RoleMenu> menuList = roleMenuService.select(id);
            //查询所有权限
            List<MenuVo> allMenu = menuService.selectMenuByParentId(0);
            if(null != menuList && !"".equals(menuList) && null != allMenu && !"".equals(allMenu)){
                list.add(menuList);
                list.add(allMenu);
                list.add(role);
                return list;
            }

            return null;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @author hhy
     * @description
     *    修改角色信息（批量修改权限）
     * @param: [roleMenuVo]
     * @date 2020/5/23 16:20
     * @return java.lang.Boolean
     * @throws 
     */
    @PostMapping("/updateRole")
    public Boolean updateRole(@RequestBody RoleMenuVo roleMenuVo){
        try{

            Integer batchUpdate = roleMenuService.batchUpdate(roleMenuVo.getRoleVo().getList().get(0), roleMenuVo.getIds());
            Integer update = roleService.update(roleMenuVo.getRoleVo());
            if(batchUpdate>0){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
