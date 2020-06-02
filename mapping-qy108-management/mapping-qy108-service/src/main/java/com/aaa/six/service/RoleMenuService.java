package com.aaa.six.service;

import com.aaa.six.base.BaseService;
import com.aaa.six.mapper.RoleMenuMapper;
import com.aaa.six.model.RoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Company AAA软件教育
 * @Title mapping-qy108
 * @Author hhy
 * @Version 0.1.0
 * @Date Create in 2020/5/22 14:08
 * @Description
 */
@Service
public class RoleMenuService extends BaseService<RoleMenu> {

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    /**
     * @author hhy
     * @description
     *    查询当前角色的权限信息
     * @param: [roleMenu]
     * @date 2020/5/22 14:30
     * @return java.util.List
     * @throws 
     */
    public List<RoleMenu> select(Long id){
        return roleMenuMapper.selectRoleMenu(id);
    }

    /**
     * @author hhy
     * @description
     *    批量更新角色权限
     * @param: [roleMenu, ids]
     * @date 2020/5/22 14:41
     * @return java.lang.Integer
     * @throws 
     */
    
    /**
     * @author hhy
     * @description
     *    通过主键id，批量删除角色权限
     * @param: [ids]
     * @date 2020/5/22 14:46
     * @return java.lang.Integer
     * @throws 
     */
    @Override
    public Integer delete(List<Object> ids){
        if (ids == null) {
            return null;
        }
        try {
            return super.delete(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
