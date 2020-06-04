package com.aaa.six.service;

import com.aaa.six.base.BaseService;
import com.aaa.six.mapper.RoleMapper;
import com.aaa.six.mapper.RoleMenuMapper;
import com.aaa.six.model.Role;
import com.aaa.six.model.RoleMenu;
import com.aaa.six.vo.RoleVo;
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
 * @Date Create in 2020/5/20 18:33
 * @Description
 */
@Service
public class RoleService extends BaseService<Role> {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    /**
     * @author hhy
     * @description
     *    分页查询
     * @param: [role, pageNo, pageSize]
     * @date 2020/5/21 16:23
     * @return com.github.pagehelper.PageInfo<com.aaa.six.model.Role>
     * @throws 
     */
    @Override
    public PageInfo<Role> queryListByPage(Role role, Integer pageNo, Integer pageSize){
        PageHelper.startPage(pageNo, pageSize);
        List<Role> selectAll = roleMapper.selectAll();
        PageInfo<Role> pageInfo = new PageInfo<>(selectAll);
        return pageInfo;
    }

    /**
     * @author hhy
     * @description
     *    查询当前角色信息
     * @param: [role]
     * @date 2020/5/22 15:42
     * @return com.aaa.six.model.Role
     * @throws
     */

    public Role queryOne(Long id){
        return roleMapper.selectByPrimaryKey(id);
    }
    /**
     * @author hhy
     * @description
     *    添加新角色,并给新角色授权
     * @param: [role]
     * @date 2020/5/21 16:29
     * @return java.lang.Integer
     * @throws 
     */
    public Boolean addRole(RoleVo roleVo){
        //判断roleVo是否为空
        //如果roleVo为空，返回错误
        if(null != roleVo && "".equals(roleVo)){
            return false;
        }else{
        //获取前端数据
            //把roleVo数据取出并分别存放
            //创建一个role容器存放角色信息
            Role role = new Role();
            Role role1 = role.setRoleName(roleVo.getRoleName())
                .setRemark(roleVo.getRemark())
                .setId(roleVo.getId())
                    //获取当前时间
                .setCreateTime(super.getCurrentTime());
            //执行添加操作
            int i = roleMapper.insert(role1);

            //创建一个roleMenu容器存放权限信息
            RoleMenu roleMenu = new RoleMenu();
            List<RoleMenu> menu = roleVo.getList();
            for(RoleMenu rm : menu){
                RoleMenu roleMenu1 = roleMenu.setId(roleVo.getId())
                        .setMenuId(rm.getMenuId());
                 int j = roleMenuMapper.insert(roleMenu1);
            }
            //判断是否插入成功
            //添加数据成功，返回true
            if(i>0 ){
                return true;
            }
            return false;
        }

    }

    /**
     * @author hhy
     * @description
     *    更新角色基本信息
     * @param: [role]
     * @date 2020/5/22 15:20
     * @return java.lang.Integer
     * @throws
     */

    public Integer update(RoleVo roleVo) throws Exception {
        Role role = new Role();
        Role role1 = role.setId(roleVo.getId())
                .setRemark(roleVo.getRemark())
                .setModifyTime(super.getCurrentTime());
        return super.update(role1);
    }
}
