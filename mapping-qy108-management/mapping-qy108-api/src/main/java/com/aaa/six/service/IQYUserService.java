package com.aaa.six.service;

import com.aaa.six.model.User;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @program: mapping-qy108
 * @author: lwq
 * @create: 2020-05-20 21:24
 * @description:
 *      用户管理
 **/
@FeignClient(value = "user-interface")
public interface IQYUserService {

    /**
     * @return com.github.pagehelper.PageInfo<com.aaa.six.model.User>
     * @throws
     * @author lwq
     * @description 分页查询用户信息
     * @param: [pageNo, pageSize]
     * @date 2020/5/27
     **/
    @PostMapping("/selectUserInfo")
    PageInfo<User> selectUserInfo(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

    /**
     * @return com.aaa.six.model.User
     * @throws
     * @author lwq
     * @description 根据id查询用户信息
     * @param: [user]
     * @date 2020/5/27
     **/
    @PostMapping("/selectInfoById")
    User selectInfoById(@RequestBody User user);

    /**
     * @return java.lang.Boolean
     * @throws
     * @author lwq
     * @description 根据id删除用户
     * @param: [user]
     * @date 2020/5/27
     **/
    @PostMapping("/deleteUserById")
    Boolean deleteUserById(@RequestBody User user);

    /**
     * @return java.lang.Boolean
     * @throws
     * @author lwq
     * @description 新增用户信息
     * @param: [user]
     * @date 2020/5/27
     **/
    @PostMapping("/addUser")
    Boolean addUser(@RequestBody User user);

    /**
     * @return java.lang.Boolean
     * @throws
     * @author lwq
     * @description 修改用户信息
     * @param: [user]
     * @date 2020/5/27
     **/
    @PostMapping("/updateUser")
    Boolean updateUser(@RequestBody User user);

    /**
     * @return java.lang.Integer
     * @throws
     * @author lwq
     * @description 批量删除用户
     * @param: [ids]
     * @date 2020/5/27
     **/
    @PostMapping("/deleteUserByIds")
    Integer deleteUserByIds(@RequestBody List<Object> ids);

    /**
     * 用户分页条件查询
     *
     * @param user
     * @param pageNo
     * @param pageSize
     * @return
     */
    @PostMapping("/selectUserByField")
    PageInfo selectUserByField(@RequestBody User user, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

    /**
     * 根据用户性别查询用户信息
     *
     * @param ssex
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/selectUserBySsex")
    PageInfo selectUserBySsex(@RequestParam("ssex") String ssex, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

    /**
     * 根据用户状态查询用户信息
     *
     * @param status
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/selectUserBySta")
    PageInfo selectUserBySta(@RequestParam("status") String status, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

    /**
     * 重置密码
     *
     * @param user
     * @return
     */
    @PostMapping("/resetUserPwd")
    Integer resetUserPwd(@RequestBody User user);
}
