package com.aaa.six.service;

import com.aaa.six.base.BaseService;
import com.aaa.six.mapper.PrincipalMapper;
import com.aaa.six.model.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.aaa.six.staticstatus.TimeProperties.TIME_TYPE;


/**
 * @program: mapping-qy108
 * @author: lwq
 * @create: 2020-05-27 20:13
 * @description:
 *      单位负责人管理
 **/
@Service
public class PrincipalService extends BaseService<Principal> {

    @Autowired
    private PrincipalMapper principalMapper;
    
    /**
     * @author lwq
     * @description
     *    单位负责人查询
     * @param: [userId]
     * @date 2020/6/1
     * @return java.util.List<com.aaa.six.model.Principal>
     * @throws
     **/
    public List<Principal> selectPrincipalInfo(Long userId){
        //获取信息
        List<Principal> principals = principalMapper.selectPrincipalInfo(userId);
        //判断负责人的信息是否为空
        if (principals.size()>0){
            //不为空就返回信息
            return principals;
        }
        return null;
    }
    
    /**
     * @author lwq 
     * @description
     *    新增单位负责人信息
     * @param: [principal]
     * @date 2020/6/1
     * @return java.lang.Boolean
     * @throws 
     **/
    public Boolean addPrincipal(Principal principal){
        if (null == principal && "".equals(principal)){
            return false;
        }else {
            //获取系统时间
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_TYPE);
            String format = simpleDateFormat.format(date);
            principal.setCreateTime(format);
            int insert = principalMapper.insert(principal);
            if (insert > 0){
                return true;
            } else{
                return false;
            }
        }
    }
    
    /**
     * @author lwq 
     * @description
     *    删除单位负责人信息
     * @param: [principal]
     * @date 2020/6/1
     * @return boolean
     * @throws 
     **/
    public boolean deletePrincipal(Principal principal) {
        if ("".equals(principal) && null == principal) {
            return false;
        } else {
            int i = principalMapper.deleteByPrimaryKey(principal);
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
     *    修改单位负责人
     * @param: [principal]
     * @date 2020/6/1
     * @return java.lang.Boolean
     * @throws 
     **/
    public Boolean updatePrincipal(Principal principal) {
        //判断user是否为空
        if ("".equals(principal) && null == principal) {
            return false;
        } else {
            //获取系统时间
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_TYPE);
            String format = simpleDateFormat.format(date);
            principal.setCreateTime(format);
            int i = principalMapper.updateByPrimaryKey(principal);
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        }

    }

}
