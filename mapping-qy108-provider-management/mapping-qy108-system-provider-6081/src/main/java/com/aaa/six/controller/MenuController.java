package com.aaa.six.controller;
/*
 *@Company：
 *@Author：何康
 *@Date：2020/5/21 16:59
 *@Description:
 */

import com.aaa.six.base.BaseController;
import com.aaa.six.base.ResultData;
import com.aaa.six.model.Menu;
import com.aaa.six.service.MenuService;
import com.aaa.six.utils.DateUtils;
import com.aaa.six.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
/**
 * @program: mapping-qy108
 * @author: lwq
 * @create: 2020-05-27 20:13
 * @description:
 *      菜单管理
 **/
@RestController
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    /**
     * @author lwq 
     * @description
     *    查询一个菜单的具体信息
     * @param: [map]
     * @date 2020/6/2
     * @return com.aaa.six.base.ResultData
     * @throws 
     **/
    @PostMapping("selectMenuByField")
    public ResultData selectMenuByField(@RequestBody Map map){
        List<Menu> menus = menuService.selectMenuByField(map);
        if (null != menus){
            return super.selectSuccess(menus);
        }
        return super.selectFailed();
    }

    /**
     * @author lwq 
     * @description
     *    根据主键id查询菜单信息
     * @param: [id]
     * @date 2020/6/2
     * @return com.aaa.six.base.ResultData
     * @throws 
     **/
    @GetMapping("selectMenuByPrimaryKey/{id}")
    public ResultData selectMenuByPrimaryKey(@PathVariable("id") Object id){
        Menu menu = menuService.selectMenuByPrimaryKey(id);
        if (null != menu){
            return super.selectSuccess(menu);
        }
        return super.selectFailed();
    }
    /**
     * @author lwq 
     * @description
     *    遍历查询所有的权限菜单
     * @param: [id]
     * @date 2020/6/2
     * @return com.aaa.six.base.ResultData
     * @throws 
     **/
    @GetMapping("selectMenuByParentId/{id}")
    public ResultData selectMenuByParentId(@PathVariable("id") Object id){
        List<MenuVo> menuVos = menuService.selectMenuByParentId(id);
        if (null != menuVos){
            return super.selectSuccess(menuVos);
        }
        return super.selectFailed();
    }
    /**
     * @author lwq 
     * @description
     *    添加菜单
     * @param: [menu]
     * @date 2020/6/2
     * @return com.aaa.six.base.ResultData
     * @throws 
     **/
    @PutMapping("insertMenu")
    public ResultData insertMenu(@RequestBody Menu menu){
        Integer insertResult = menuService.insertMenu(menu);
        if (insertResult > 0 ){
            return super.insertSuccess();
        }
        return super.insertFailed();
    }
    /**
     * @author lwq 
     * @description
     *    根据主键id 进行菜单数据的更新
     * @param: [menu]
     * @date 2020/6/2
     * @return com.aaa.six.base.ResultData
     * @throws 
     **/
    @PostMapping("updateMenuByPrimaryKey")
    public ResultData updateByPrimaryKey(@RequestBody Menu menu){
        Integer updateResult = menuService.updateMenuByPrimaryKey(menu);
        if (updateResult > 0 ){
            return super.updateSuccess();
        }
        return super.updateFailed();
    }
    /**
     * @author lwq 
     * @description
     *    根据主键id删除菜单数据
     * @param: [id]
     * @date 2020/6/2
     * @return com.aaa.six.base.ResultData
     * @throws 
     **/
    @DeleteMapping("deleteMenuByPrimaryKey")
    public ResultData deleteMenuByPrimaryKey(@RequestBody Object id){
        Integer deleteResult = menuService.deleteMenuByPrimaryKey(id);
        if (deleteResult > 0 ){
            return super.deleteSuccess();
        }
        return super.deleteFailed();
    }
    /**
     * @author lwq 
     * @description
     *    根据前台传入的list  遍历map id 进行删除操作
     * @param: [list]
     * @date 2020/6/2
     * @return com.aaa.six.base.ResultData
     * @throws 
     **/
    @DeleteMapping("deleteMenuByPrimaryKeyList")
    public ResultData deleteDeptByPrimaryKeyList(@RequestBody List<Map> list){
        Integer integer = menuService.deleteMenuByPrimaryKeyList(list);
        if (integer > 0){
            return super.deleteSuccess("删除成功，共"+integer+"数据！");
        }
        return super.deleteFailed();
    }
}
