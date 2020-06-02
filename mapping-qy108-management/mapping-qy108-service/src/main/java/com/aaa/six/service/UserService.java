package com.aaa.six.service;

import com.aaa.six.base.BaseService;
import com.aaa.six.mapper.UserMapper;
import com.aaa.six.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/5/22 14:19
 * @Description
 *用于用户的增删改
 **/
@Service
public class UserService extends BaseService<User> {
    @Autowired
    private UserMapper userMapper;
    /**
     * @author Seven Lee
     * @description
     * 这个方法用来添加用户的 参数user是前端传来的
     *
     * @param [user]
     * @date 2020/5/22
     * @return java.lang.Boolean
     * @throws
    **/
    public Boolean addUser(User user){


        //获取时间
        Date date = new Date();
        //定义时间格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        String format = simpleDateFormat.format(date);
        //获取前端数据
        User user1 = user.setUsername(user.getUsername())
                .setDeptId(user.getDeptId())
                .setStatus(user.getStatus());
        //判断user是否为空
        //如果user为空 返回错误
        if ("".equals(user1)&&user1==null){
            return false;
        }else {
            //从前端拿到数据
            int insert = userMapper.insert(user1);
            //判断我是否插入成功
            //添加数据成功 返回true
            if (insert>0){
                return true;
            }
            else return false;
        }


    }
}
