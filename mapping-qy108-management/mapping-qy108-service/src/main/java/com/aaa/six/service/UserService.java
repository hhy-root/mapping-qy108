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

import static com.aaa.six.staticstatus.TimeProperties.TIME_TYPE;
import static com.aaa.six.staticstatus.UserPassword.USER_PASSWORD;


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
     * @return com.github.pagehelper.PageInfo<com.aaa.six.model.User>
     * @throws
     * @author lwq
     * @description 获取所有用户信息并分页
     * @param: [pageNo, pageSize]
     * @date 2020/5/26
     **/
    public PageInfo<User> selectUserInfo(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<User> list = userMapper.selectAll();
        if (list.size() > 0) {
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
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_TYPE);
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
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_TYPE);
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
     * @return java.lang.Integer
     * @throws
     * @author lwq
     * @description 批量删除用户
     * @param: [ids]
     * @date 2020/5/23
     **/
    public Integer deleteUserByIds(List<Object> ids) {
        if (ids.size() == 0) {
            return null;
        } else {
            Integer delete = null;
            try {
                delete = super.delete(ids);
                if (delete > 0) {
                    return delete;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /**
     * @return com.github.pagehelper.PageInfo<com.aaa.six.model.User>
     * @throws
     * @author lwq
     * @description 分页条件查询
     * @param: [username, deptId, pageNo, pageSize]
     * @date 2020/6/1
     **/
    public PageInfo<User> selectUserByFiles(User user, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        //判断前段是否传值成功
        try {
            //使用动态sql查询数据
            List<User> users = userMapper.selectUserByField(user);
            //判断是否查询成功
            if (!"".equals(users) && null !=users){
                //将查询结果放入
                PageInfo<User> userPageInfo = new PageInfo<>(users);
                //返回分页结果
                return userPageInfo;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * @author lwq 
     * @description
     *    根据用户性别查询
     * @param: [ssex, pageNo, pageSize]
     * @date 2020/6/1
     * @return com.github.pagehelper.PageInfo
     * @throws 
     **/
    public PageInfo selectUserBySsex(String ssex,Integer pageNo,Integer pageSize){

        //判断前端传值是否成功
        if (!"".equals(ssex) && !"".equals(pageNo) && !"".equals(pageSize)){
            // 当前页数和一页数量
            PageHelper.startPage(pageNo,pageSize);
            //定义users
            List<User> users = null;
            try {
                //查询用户信息
                users = userMapper.selectUserBySsex(ssex);
                //判断查询结果是否为空
                if (!"".equals(users) && null !=users){
                    //不是空 将结果放入
                    PageInfo<User> userPageInfo = new PageInfo<>(users);
                    //返回userPageInfo
                    return userPageInfo;
                }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    /**
     * @author lwq 
     * @description
     *    根据状态查询用户信息
     * @param: [status, pageNo, pageSize]
     * @date 2020/6/1
     * @return com.github.pagehelper.PageInfo
     * @throws 
     **/
    public PageInfo selectUserBySta(String status,Integer pageNo,Integer pageSize){
        //判断前端传值是否成功

        if (!"".equals(status) && !"".equals(pageNo) && !"".equals(pageSize)){
            // 当前页数和一页数量
            PageHelper.startPage(pageNo,pageSize);
            //定义users
            List<User> users = null;
            try {
                //查询用户信息
                users = userMapper.selectUserBySta(status);
                //判断查询结果是否为空
                if (!"".equals(users) && null !=users){
                    //不是空 将结果放入
                    PageInfo<User> userPageInfo = new PageInfo<>(users);
                    //返回userPageInfo
                    return userPageInfo;
                }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

   /**
    * @author lwq 
    * @description
    *    密码重置成初始密码
    * @param: [user]
    * @date 2020/6/1
    * @return java.lang.Integer
    * @throws 
    **/
    public Integer resetUserPwd(User user){

        //将初始密码set到实体
        user.setPassword(USER_PASSWORD);
        //获取当前时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_TYPE);
        String format = simpleDateFormat.format(new Date());
        //把时间存到实体中
        user.setModifyTime(format);
        //判断前端是否传值成功
        if (!"".equals(user)  && null !=user){

            try {
                //调用父类的方法
                Integer update = super.update(user);
                if (update>0){
                    return update;
                }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

    

