package com.aaa.six.service;/*
 *@Company：
 *@Author：何康
 *@Date：2020/5/22 12:03
 *@Description:
 */

import com.aaa.six.base.ResultData;
import com.aaa.six.model.Menu;
import org.springframework.cloud.openfeign.FeignClient;
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
@FeignClient(value = "menu-interface")
public interface IQYMenuService {

    /**
     * @author lwq
     * @description
     *    条件查询菜单
     * @param: [map]
     * @date 2020/6/2
     * @return com.aaa.six.base.ResultData
     * @throws
     **/
    @PostMapping("selectMenuByField")
    ResultData selectMenuByField(@RequestBody Map map);

    /**
     * @author lwq
     * @description
     *    根据主键id查询菜单表数据
     * @param: [id]
     * @date 2020/6/2
     * @return com.aaa.six.base.ResultData
     * @throws
     **/
    @GetMapping("selectMenuByPrimaryKey/{id}")
    ResultData selectMenuByPrimaryKey(@PathVariable("id") Object id);

    /**
     * @author lwq
     * @description
     *    查询菜单表所有信息 并且把子菜单放在父级菜单下（传入的id应为最大父级id 0）
     * @param: [id]
     * @date 2020/6/2
     * @return com.aaa.six.base.ResultData
     * @throws
     **/
    @GetMapping("selectMenuByParentId/{id}")
    ResultData selectMenuByParentId(@PathVariable("id") Object id);

    /**
     * @author lwq
     * @description
     *    添加新菜单
     * @param: [menu]
     * @date 2020/6/2
     * @return com.aaa.six.base.ResultData
     * @throws
     **/
    @PutMapping("insertMenu")
    ResultData insertMenu(@RequestBody Menu menu);

    /**
     * @author lwq
     * @description
     *    根据主键id修改菜单信息
     * @param: [menu]
     * @date 2020/6/2
     * @return com.aaa.six.base.ResultData
     * @throws
     **/
    @PostMapping("updateMenuByPrimaryKey")
    ResultData updateMenuByPrimaryKey(@RequestBody Menu menu);

    /**
     * @author lwq
     * @description
     *    根据主键id删除一条数据
     * @param: [id]
     * @date 2020/6/2
     * @return com.aaa.six.base.ResultData
     * @throws
     **/
    @DeleteMapping("deleteMenuByPrimaryKey")
    ResultData deleteMenuByPrimaryKey(@RequestBody Object id);

    /**
     * @author lwq
     * @description
     *    根据主键id批量删除
     * @param: [list]
     * @date 2020/6/2
     * @return com.aaa.six.base.ResultData
     * @throws
     **/
    @DeleteMapping("deleteMenuByPrimaryKeyList")
    ResultData deleteMenuByPrimaryKeyList(@RequestBody List<Map> list);
}
