package com.aaa.six.controller;

import com.aaa.six.model.Dict;
import com.aaa.six.model.User;
import com.aaa.six.service.DictService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: mapping-qy108
 * @author: lwq
 * @create: 2020-05-26 16:34
 * @description:
 **/
@RestController
public class DictController {

    @Autowired
    private DictService dictService;

    /**
     * @author lwq
     * @description
     *    分页查询字典信息
     * @param: [pageNo, pageSize]
     * @date 2020/5/26
     * @return com.github.pagehelper.PageInfo<com.aaa.six.model.Dict>
     * @throws
     **/
    @PostMapping("/selectDictInfo")
    public PageInfo<Dict> selectDictInfo(Integer pageNo , Integer pageSize){
        PageInfo<Dict> dictPageInfo = dictService.selectDictInfo(pageNo, pageSize);
        if (!"".equals(dictPageInfo) && null != dictPageInfo){
            return dictPageInfo;
        }
        return null;
    }

    /**
     * @author lwq
     * @description
     *    新增字典信息
     * @param: [dict]
     * @date 2020/5/26
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/addDict")
    public Boolean addDict(@RequestBody Dict dict){
        Boolean aBoolean = dictService.addDict(dict);
        if (aBoolean){
            return true;
        }
        return false;
    }

    /**
     * @author lwq
     * @description
     *    根据id查询用户详细信息
     * @param: [dict]
     * @date 2020/5/26
     * @return com.aaa.six.model.Dict
     * @throws
     **/
    @PostMapping("/selectDictById")
    public Dict selectDictById(@RequestBody Dict dict){
        Dict dict1 = dictService.selectDictById(dict);
        if (!"".equals(dict1) && null != dict1){
            return dict1;
        }
        return null;
    }
    
    /**
     * @author lwq 
     * @description
     *    修改字典信息
     * @param: [dict]
     * @date 2020/5/27
     * @return java.lang.Boolean
     * @throws 
     **/
    @PostMapping("/updateDict")
    public Boolean updateDict(@RequestBody Dict dict){
        return dictService.updateDict(dict);
    }
    
    /**
     * @author lwq 
     * @description
     *    根据id删除字典信息
     * @param: [dict]
     * @date 2020/5/27
     * @return java.lang.Boolean
     * @throws 
     **/
    @PostMapping("/deleteDictById")
    public Boolean deleteDictById(@RequestBody Dict dict){
        boolean b = dictService.deleteDictById(dict);
        if (b){
            return true;
        }
        return false;
    }
    
    /**
     * @author lwq 
     * @description
     *    批量删除
     * @param: [ids]
     * @date 2020/5/27
     * @return java.lang.Integer
     * @throws 
     **/
    @PostMapping("/deleteDictByIds")
    public Integer deleteDictByIds(@RequestBody List<Object> ids){
        return dictService.deleteDictByIds(ids);
    }
    
    /**
     * @author lwq 
     * @description
     *    分页条件查询
     * @param: [dict, pageNo, pageSize]
     * @date 2020/6/1
     * @return com.github.pagehelper.PageInfo
     * @throws 
     **/
    @PostMapping("/selectDictByField")
    public PageInfo selectDictByField(@RequestBody Dict dict, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
        PageInfo pageInfo = dictService.selectDictByFiles(dict, pageNo, pageSize);
        if (!"".equals(pageInfo) && null !=pageInfo){
            return pageInfo;
        }
        return null;
    }

}
