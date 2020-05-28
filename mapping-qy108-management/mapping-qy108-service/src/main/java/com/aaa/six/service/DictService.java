package com.aaa.six.service;

import com.aaa.six.base.BaseService;
import com.aaa.six.mapper.DictMapper;
import com.aaa.six.model.Dict;
import com.aaa.six.model.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @program: mapping-qy108
 * @author: lwq
 * @create: 2020-05-26 16:33
 * @description:
 *      字典管理
 **/
@Service
public class DictService extends BaseService<Dict> {

    @Autowired
    private DictMapper dictMapper;

    /**
     * @author lwq
     * @description
     *    分页查询所有字典信息
     * @param: [pageNo, pageSize]
     * @date 2020/5/26
     * @return com.github.pagehelper.PageInfo<com.aaa.six.model.Dict>
     * @throws
     **/
    public PageInfo<Dict> selectDictInfo(Integer pageNo, Integer pageSize){
        PageHelper.startPage(pageNo,pageSize);
        List<Dict> dicts = dictMapper.selectAll();
        if (dicts.size()>0){
            PageInfo<Dict> dictPageInfo = new PageInfo<>(dicts);
            return dictPageInfo;
        }
        return null;
    }
    
    /**
     * @author lwq 
     * @description
     *    添加字典信息
     * @param: [dict]
     * @date 2020/5/26
     * @return java.lang.Boolean
     * @throws 
     **/
    public Boolean addDict(Dict dict){
        if ("".equals(dict) && null == dict){
            return false;
        } else {
            int insert = dictMapper.insert(dict);
            if (insert > 0){
                return true;
            } else {
                return false;
            }
        }
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
    public Dict selectDictById(Dict dict){
        if ("".equals(dict) && null != dict){
            return null;
        } else {
            Dict dict1 = dictMapper.selectByPrimaryKey(dict);
            if (!"".equals(dict1) && null != dict1) {
                return dict1;
            } else {
                return null;
            }
        }
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
    public Boolean updateDict(Dict dict) {
        //判断dict是否为空
        if ("".equals(dict) && null == dict) {
            return false;
        } else {
            int i = dictMapper.updateByPrimaryKey(dict);
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        }

    }
    /**
     * @author lwq
     * @description
     *    根据id删除字典信息
     * @param: [dict]
     * @date 2020/5/27
     * @return boolean
     * @throws
     **/
    public boolean deleteDictById(Dict dict) {
        if ("".equals(dict) && null == dict) {
            return false;
        } else {
            int i = dictMapper.deleteByPrimaryKey(dict);
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        }

    }

    /**
     * @author lwq
     * @description
     *    批量删除字典信息
     * @param: [ids]
     * @date 2020/5/27
     * @return java.lang.Integer
     * @throws
     **/
    public Integer deleteDictByIds(List<Object> ids){
        if (ids.size() == 0){
            return null;
        } else {
            Integer delete = null;
            try {
                delete = super.delete(ids);
                if (delete > 0){
                    return delete;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
