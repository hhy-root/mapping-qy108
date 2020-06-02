package com.aaa.six.service;/*
 *@Company：
 *@Author：何康
 *@Date：2020/5/21 16:21
 *@Description:
 */

import com.aaa.six.base.BaseService;
import com.aaa.six.mapper.MenuMapper;
import com.aaa.six.model.Menu;
import com.aaa.six.utils.DateUtils;
import com.aaa.six.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.aaa.six.status.ReturnStatus.CRUD_FALIED;

@Service
public class MenuService extends BaseService<Menu> {

    @Autowired
    private MenuMapper menuMapper;

    /**
    * @Author: He create on 2020/5/21 16:27
    * @param: [menu]
    * @return: com.aaa.zk.model.Menu
    * @Description: 查询一个具体的菜单或者按钮 进行操作
    */
    public List<Menu> selectMenuByField(Map map){
        if (null != map){
            List<Menu> select = menuMapper.selectMenuByField(map);
            if (null != select && select.size() > 0){
                return select;
            }
            return null;
        }
        return null;
    }

    /**
    * @Author: He create on 2020/5/26 14:33
    * @param: [id]
    * @return: com.aaa.zk.model.Menu
    * @Description: 根据主键id查询菜单表数据
    */
    public Menu selectMenuByPrimaryKey(Object id){
        if (null != id){
            Menu menu = menuMapper.selectByPrimaryKey(id);
            if (null != menu){
                return menu;
            }
            return null;
        }
        return null;
    }
    /**
    * @Author: He create on 2020/5/21 17:53
    * @param: [id]
    * @return: java.util.List<com.aaa.zk.vo.MenuVo>
    * @Description: 遍历查询所有菜单 并且把父级下的菜单放在下改菜单的下属目录中
    */
    public List<MenuVo> selectMenuByParentId(Object id){
        //第一次查询传入的id为0 则为查询所有的菜单表
        List<MenuVo> menuVos = menuMapper.selectMenuByParentId(id);
        if (null != menuVos && menuVos.size() > 0){
            //循环遍历第一次查询的集合
            for (MenuVo menuVO : menuVos) {
                //以本身的id为参数  进行查询本身的子菜单
                Object id1 = menuVO.getId();
                //循环查询 直到本身菜单不在存在子菜单
                List<MenuVo> menuVos1 = this.selectMenuByParentId(id1);
                //添加到父级菜单的集合中 进行数据的返回
                menuVO.setChildrenList(menuVos1);
            }
            return menuVos;
        }
        return null;
    }
    /**
    * @Author: He create on 2020/5/21 17:58
    * @param: [menu]
    * @return: java.lang.Integer
    * @Description: 添加菜单
    */
    public Integer insertMenu(Menu menu){
        if ( null != menu){
            //获取当前时间 添加到数据库
            menu.setCreateTime(DateUtils.getCurrentDate());
            int insertResult = menuMapper.insert(menu);
            if (insertResult > 0){
                return insertResult;
            }
        }
        return CRUD_FALIED;
    }
    /**
    * @Author: He create on 2020/5/21 17:58
    * @param: [id]
    * @return: java.lang.Integer
    * @Description: 根据主键删除菜单
    */
    public Integer deleteMenuByPrimaryKey(Object menuId){
        if (null != menuId){
            int deleteResult = menuMapper.deleteByPrimaryKey(menuId);
            if (deleteResult > 0){
                return deleteResult;
            }
        }
        return CRUD_FALIED;
    }
    /**
     * @Author: He create on 2020/5/21 23:07
     * @param: [map]
     * @return: java.lang.Integer
     * @Description: 通过主键id批量删除数据
     */
    public Integer deleteMenuByPrimaryKeyList(List<Map> list){
        if (null != list && list.size() > 0){
            Integer deleteNum = 0;
            //循环遍历list中的map 取出其中的id进行删除操作
            for (Map map : list){
                Object id = map.get("id");
                int deleteResult = menuMapper.deleteByPrimaryKey(id);
                if (deleteResult > 0){
                    deleteNum += 1;
                }
            }
            return deleteNum;
        }
       return CRUD_FALIED;
    }
    /**
    * @Author: He create on 2020/5/21 17:59
    * @param: [menu]
    * @return: java.lang.Integer
    * @Description: 根据主键id 更新菜单信息
    */
    public Integer updateMenuByPrimaryKey(Menu menu){
        if (null != menu){
            //更新时传入当前时间 更新数据
            menu.setModifyTime(DateUtils.getCurrentDate());
            int updateResult = menuMapper.updateByPrimaryKey(menu);
            if (updateResult  > 0){
                return updateResult;
            }
        }
        return CRUD_FALIED;
    }
}
