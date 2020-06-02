package com.aaa.six.service;


import com.aaa.six.model.Dict;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: mapping-qy108
 * @author: lwq
 * @create: 2020-05-26 16:35
 * @description:
 *      字典管理
 **/
@FeignClient(value = "dict-interface")
public interface IQYDictService {
    /**
     * @return com.github.pagehelper.PageInfo<com.aaa.six.model.Dict>
     * @throws
     * @author lwq
     * @description 分页查询字典信息
     * @param: [pageNo, pageSize]
     * @date 2020/5/27
     **/
    @PostMapping("/selectDictInfo")
    PageInfo<Dict> selectDictInfo(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

    /**
     * @return java.lang.Boolean
     * @throws
     * @author lwq
     * @description 新增字典信息
     * @param: [dict]
     * @date 2020/5/27
     **/
    @PostMapping("/addDict")
    Boolean addDict(@RequestBody Dict dict);

    /**
     * @return com.aaa.six.model.Dict
     * @throws
     * @author lwq
     * @description 根据id查询字典信息
     * @param: [dict]
     * @date 2020/5/27
     **/
    @PostMapping("/selectDictById")
    Dict selectDictById(@RequestBody Dict dict);

    /**
     * @return java.lang.Boolean
     * @throws
     * @author lwq
     * @description 修改字典信息
     * @param: [dict]
     * @date 2020/5/27
     **/
    @PostMapping("/updateDict")
    Boolean updateDict(@RequestBody Dict dict);

    /**
     * @return java.lang.Boolean
     * @throws
     * @author lwq
     * @description 根据id删除字典信息
     * @param: [dict]
     * @date 2020/5/27
     **/
    @PostMapping("/deleteDictById")
    Boolean deleteDictById(@RequestBody Dict dict);

    /**
     * @return java.lang.Integer
     * @throws
     * @author lwq
     * @description 批量删除字典信息
     * @param: [ids]
     * @date 2020/5/27
     **/
    @PostMapping("/deleteDictByIds")
    Integer deleteDictByIds(@RequestBody List<Object> ids);

    /**
     * 字典信息分页条件查询
     *
     * @param dict
     * @param pageNo
     * @param pageSize
     * @return
     */
    @PostMapping("/selectDictByField")
    PageInfo selectDictByField(@RequestBody Dict dict, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);


}
