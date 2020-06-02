package com.aaa.six.controller;


import com.aaa.six.base.BaseService;
import com.aaa.six.base.CommonController;
import com.aaa.six.model.User;
import com.aaa.six.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Company AAA软件教育
 * @Title mapping-qy108
 * @Author hhy
 * @Version 0.1.0
 * @Date Create in 2020/5/13 11:40
 * @Description
 */
@RestController
public class UserController extends CommonController<User> {

    @Autowired
    private UserService userService;


    @Override
    public BaseService<User> getBaseService() {
        return userService;
    }
    /**
     * @author lwq
     * @description
     *    获取全部用户信息并分页
     * @param: [pageNo, pageSize]
     * @date 2020/5/26
     * @return com.github.pagehelper.PageInfo<com.aaa.six.model.User>
     * @throws
     **/
    @PostMapping("/selectUserInfo")
    public PageInfo<User> selectUserInfo(Integer pageNo, Integer pageSize){
        PageInfo<User> userPageInfo = userService.selectUserInfo(pageNo, pageSize);
        if (!"".equals(userPageInfo) && null != userPageInfo){
            return userPageInfo;
        }
        return null;
    }

    /**
     * @author lwq
     * @description
     *    获取单个用户的详细信息
     * @param: [user]
     * @date 2020/5/21
     * @return com.aaa.six.model.User
     * @throws
     **/
    @PostMapping("/selectInfoById")
    public User selectInfoById(@RequestBody User user){
        return userService.selectInfoById(user);
    }

    /**
     * @author lwq
     * @description
     *    删除用户
     * @param:
     * @date 2020/5/21
     * @return
     * @throws
     **/
    @PostMapping("/deleteUserById")
    public Boolean deleteUserById(@RequestBody User user){
        boolean b = userService.deleteUserById(user);
        if (b){
            return true;
        }
        return false;
    }
    
    /**
     * @author lwq 
     * @description
     *    添加用户
     * @param: [user]
     * @date 2020/5/22
     * @return java.lang.Boolean
     * @throws 
     **/
    @PostMapping("/addUser")
    public Boolean addUser(@RequestBody User user){
        Boolean aBoolean = userService.addUser(user);
        if (aBoolean){
            return true;
        }
        return false;
    }

    /**
     * @author lwq
     * @description
     *    修改用户信息
     * @param: [user]
     * @date 2020/5/23
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/updateUser")
    public Boolean updateUser(@RequestBody User user){
        return userService.updateUser(user);
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
    @PostMapping("/deleteUserByIds")
    public Integer deleteUserByIds(@RequestBody List<Object> ids){
        return userService.deleteUserByIds(ids);
    }
    
    /**
     * @author lwq 
     * @description
     *    用户条件分页查询
     * @param: [username, deptId, pageNo, pageSize]
     * @date 2020/6/1
     * @return com.github.pagehelper.PageInfo
     * @throws 
     **/
    @PostMapping("/selectUserByField")
    public PageInfo selectUserByField(@RequestBody User user, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){

        PageInfo pageInfo = userService.selectUserByFiles(user, pageNo, pageSize);
        if (!"".equals(pageInfo) && null !=pageInfo){
            return pageInfo;
        }
        return null;
    }

    /**
     * @author lwq
     * @description
     *    根据性别查询用户信息
     * @param: [ssex, pageNo, pageSize]
     * @date 2020/6/1
     * @return com.github.pagehelper.PageInfo
     * @throws
     **/
    @GetMapping("/selectUserBySsex")
    public PageInfo selectUserBySsex(@RequestParam("ssex") String ssex,@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize){
        return userService.selectUserBySsex(ssex,pageNo,pageSize);
    }

    /**
     * @author lwq
     * @description
     *    根据用户状态查询用户信息
     * @param: [status, pageNo, pageSize]
     * @date 2020/6/1
     * @return com.github.pagehelper.PageInfo
     * @throws
     **/
    @GetMapping("/selectUserBySta")
    public PageInfo selectUserBySta(@RequestParam("status") String status,@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize){
        return userService.selectUserBySta(status,pageNo,pageSize);
    }
    /**
     * @author lwq
     * @description
     *    重置密码
     * @param: [user]
     * @date 2020/6/1
     * @return java.lang.Integer
     * @throws
     **/
    @PostMapping("/resetUserPwd")
    public Integer resetUserPwd(@RequestBody User user){
        return userService.resetUserPwd(user);
    }

}
