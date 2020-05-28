package com.aaa.six.service;

import com.aaa.six.base.BaseService;
import com.aaa.six.mapper.UserMapper;
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
 * @create: 2020-05-20 17:05
 * @description:
 **/
@Service
public class UserService extends BaseService<User> {

    @Autowired
    private UserMapper userMapper;

   /**
    * @author lwq
    * @description
    *    获取所有用户信息并分页
    * @param: [pageNo, pageSize]
    * @date 2020/5/26
    * @return com.github.pagehelper.PageInfo<com.aaa.six.model.User>
    * @throws
    **/
    public PageInfo<User> selectUserInfo(Integer pageNo,Integer pageSize){
        PageHelper.startPage(pageNo ,pageSize);
        List<User> list = userMapper.selectAll();
        if (list.size()>0){
            PageInfo<User> userPageInfo = new PageInfo<>(list);
            return userPageInfo;
        }
       return null;
    }
    /**
     * @return com.aaa.six.model.User
     * @throws
     * @author lwq
     * @description 获取单个用户详细信息
     * @param: [user]
     * @date 2020/5/20
     **/
    public User selectInfoById(User user) {
        //判断user是否为空
        if ("".equals(user) && null == user) {
            return null;
        } else {
            //执行查询
            User user1 = userMapper.selectByPrimaryKey(user);
            //判断是否查询出数据
            if (!"".equals(user1) && null != user1) {
                return user1;
            } else {
                return null;
            }
        }
    }


    /**
     * @return boolean
     * @throws
     * @author lwq
     * @description 删除用户
     * @param: [user]
     * @date 2020/5/22
     **/
    public boolean deleteUserById(User user) {
        if ("".equals(user) && null == user) {
            return false;
        } else {
            int i = userMapper.deleteByPrimaryKey(user);
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        }

    }


    /**
     * @return java.lang.Boolean
     * @throws
     * @author lwq
     * @description 新增用户
     * @param: []
     * @date 2020/5/22
     **/
    public Boolean addUser(User user) {
        //判断user是否为空
        if ("".equals(user) && null == user) {
            return false;
        } else {
            //获取系统时间
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
            String format = simpleDateFormat.format(date);
            user.setCreateTime(format);
            //执行新增操作
            int i = userMapper.insert(user);
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * @return java.lang.Boolean
     * @throws
     * @author lwq
     * @description 修改用户信息
     * @param: [user]
     * @date 2020/5/22
     **/
    public Boolean updateUser(User user) {
        //判断user是否为空
        if ("".equals(user) && null == user) {
            return false;
        } else {
            //获取系统时间
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
            String format = simpleDateFormat.format(new Date());
            user.setCreateTime(format);
            int i = userMapper.updateByPrimaryKey(user);
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
     *    批量删除用户
     * @param: [ids]
     * @date 2020/5/23
     * @return java.lang.Integer
     * @throws
     **/
    public Integer deleteUserByIds(List<Object> ids){
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



    

