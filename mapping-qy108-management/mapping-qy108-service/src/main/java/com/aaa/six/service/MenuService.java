package com.aaa.six.service;

import com.aaa.six.base.BaseService;
import com.aaa.six.mapper.MenuMapper;
import com.aaa.six.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Company AAA软件教育
 * @Title mapping-qy108
 * @Author hhy
 * @Version 0.1.0
 * @Date Create in 2020/5/21 22:20
 * @Description
 */
@Service
public class MenuService extends BaseService<Menu> {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * @author hhy
     * @description
     *    查询所有权限
     * @param: []
     * @date 2020/5/22 10:27
     * @return java.util.List<com.aaa.six.model.Menu>
     * @throws 
     */
    public List<Menu> getAllMenu(){

        // 查询父权限
        List<Menu> menus1 = menuMapper.selectMenu(0L);
        //遍历父权限
        for (Menu menus:menus1){
            // 这个list的作用是，将二级节点存储到父菜单中
            List list = new ArrayList();
            //获取父权限的菜单id
            Long menuId = menus.getId();
            //遍历二级权限
            // 查询当前父权限拥有的二级权限
            List<Menu> menus2 = menuMapper.selectMenu(menuId);
            for (Menu menu:menus2){
                //获取二级权限菜单id
                Long menuId1 = menu.getId();
                //获取二级权限父权限id
                Long parentId = menu.getParentId();
                //判断菜单id和父权限id是否相同，相同则这个二级权限在这个父权限下
                if (menuId.equals(parentId)) {
                    //将二级权限添加到父菜单中
                    list.add(menu);
                    // 这个list的作用是，将子节点存储到二级菜单中
                    ArrayList list1 = new ArrayList();
                    //查询当前角色拥有的三级权限（子权限）
                    List<Menu> menus3 = menuMapper.selectMenu(menuId1);
                    //遍历子权限
                    for (Menu menu1 : menus3){
                        //获取子权限的父权限id
                        Long parentId1 = menu1.getParentId();
                        //判断菜单id和父权限id是否相同，相同则这个子权限在这个二级权限下
                        if(menuId1.equals(parentId1)){
                            list1.add(menu1);
                        }
                    }
                    menu.setSon(list1);
                }
            }
            menus.setSon(list);
        }

        return menus1;
    }


}
