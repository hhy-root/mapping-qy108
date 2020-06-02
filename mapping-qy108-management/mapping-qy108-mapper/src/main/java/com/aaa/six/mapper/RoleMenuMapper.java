package com.aaa.six.mapper;

import com.aaa.six.model.RoleMenu;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

public interface RoleMenuMapper extends Mapper<RoleMenu> {

    /**
     * @author hhy
     * @description
     *    查询当前角色的权限信息
     * @param: [roleMenu]
     * @date 2020/5/22 14:28
     * @return java.util.List<java.util.HashMap>
     * @throws
     */
    List<RoleMenu> selectRoleMenu(Long id);

}