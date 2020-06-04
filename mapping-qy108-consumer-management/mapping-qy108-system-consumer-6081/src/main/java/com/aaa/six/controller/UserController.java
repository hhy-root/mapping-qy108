package com.aaa.six.controller;

import com.aaa.six.base.BaseController;
import com.aaa.six.base.ResultData;
import com.aaa.six.model.User;
import com.aaa.six.service.IQYUserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: mapping-qy108
 * @author: lwq
 * @create: 2020-05-20 21:24
 * @description:
 *      用户管理
 **/
@RestController
@Api(value = "用户管理", tags = "用户管理接口")
public class UserController extends BaseController {

    @Autowired
    private IQYUserService iqyUserService;

    /**
     * @author lwq
     * @description
     *    获取全部用户信息并分页
     * @param: [pageNo, pageSize]
     * @date 2020/5/26
     * @return com.aaa.six.base.ResultData
     * @throws
     **/
    @PostMapping("/selectUserInfo")
    @ApiOperation(value = "查询功能", notes = "查询项目管理信息")
    public ResultData selectUserInfo(Integer pageNo,Integer pageSize){
        PageInfo<User> userPageInfo = iqyUserService.selectUserInfo(pageNo, pageSize);
        if (!"".equals(userPageInfo) && null != userPageInfo){
            return super.selectSuccess(userPageInfo);
        }
        return super.selectFailed();
    }

    /**
     * @author lwq
     * @description
     *    获取单个用户的详细信息
     * @param: [user]
     * @date 2020/5/21
     * @return com.aaa.six.base.ResultData
     * @throws
     **/
    @PostMapping("/selectInfoById")
    @ApiOperation(value = "查询功能", notes = "单个查询")
    public ResultData selectInfoById(User user){
        User user1 = iqyUserService.selectInfoById(user);
        if (null != user1 && !"".equals(user1)){
            return super.selectSuccess(user1);
        }
        return super.selectFailed();
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
    @ApiOperation(value = "删除功能", notes = "删除")
    public ResultData deleteUserById(User user){
        Boolean aBoolean = iqyUserService.deleteUserById(user);
        if (aBoolean){
            return super.deleteSuccess();
        }
        return super.deleteFailed();
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
    @ApiOperation(value = "添加功能", notes = "添加")
    public ResultData addUser(User user){
        Boolean aBoolean = iqyUserService.addUser(user);
        if (aBoolean){
            return super.insertSuccess();
        }
        return super.insertFailed();
    }
    /**
     * @author lwq 
     * @description
     *    修改用户信息
     * @param: [user]
     * @date 2020/5/23
     * @return com.aaa.six.base.ResultData
     * @throws 
     **/
    @PostMapping("/updateUser")
    @ApiOperation(value = "修改功能", notes = "单个修改")
    public ResultData updateUser(User user){
        Boolean aBoolean = iqyUserService.updateUser(user);
        if (aBoolean){
            return super.updateSuccess();

        }else {
            return super.updateFailed();
        }
    }
    
    /**
     * @author lwq 
     * @description
     *    批量删除
     * @param: [ids]
     * @date 2020/5/23
     * @return com.aaa.six.base.ResultData
     * @throws 
     **/
    @PostMapping("/deleteUserByIds")
    @ApiOperation(value = "删除功能", notes = "批量删除")
    public ResultData deleteUserByIds(@RequestBody List<Object> ids){
        Integer integer = iqyUserService.deleteUserByIds(ids);
        if (integer > 0 ){
            return super.deleteSuccess();
        }else {
            return super.deleteFailed();
        }
    }
    
    /**
     * @author lwq 
     * @description
     *    用户条件分页查询
     * @param: [username, deptId, pageNo, pageSize]
     * @date 2020/6/1
     * @return com.aaa.six.base.ResultData
     * @throws 
     **/
    @PostMapping("/selectUserByField")
    @ApiOperation(value = "查询功能", notes = "用户条件查询分页")
    public ResultData selectUserByField(User user, Integer pageNo, Integer pageSize){
        PageInfo pageInfo = iqyUserService.selectUserByField(user, pageNo, pageSize);
        //判断查询是否成功
        if (!"".equals(pageInfo) && null !=pageInfo){
            return super.selectSuccess(pageInfo);
        }
        return super.selectFailed();
    }

    /**
     * @author lwq
     * @description
     *    根据用户性别查询用户信息
     * @param: [ssex, pageNo, pageSize]
     * @date 2020/6/1
     * @return com.aaa.six.base.ResultData
     * @throws
     **/
    @GetMapping("/selectUserBySsex")
    @ApiOperation(value = "查询功能", notes = "用户性别查询")
    public ResultData selectUserBySsex(String ssex, Integer pageNo, Integer pageSize){
        PageInfo pageInfo = iqyUserService.selectUserBySsex(ssex, pageNo, pageSize);
        if (!"".equals(pageInfo) && null !=pageInfo){
            return super.selectSuccess(pageInfo);
        }
        return super.selectFailed();
    }

    /**
     * @author lwq
     * @description
     *    根据状态查询用户信息
     * @param: [status, pageNo, pageSize]
     * @date 2020/6/1
     * @return com.aaa.six.base.ResultData
     * @throws
     **/
    @GetMapping("/selectUserBySta")
    @ApiOperation(value = "查询功能", notes = "根据状态查询")
    public ResultData selectUserBySta(String status,Integer pageNo,Integer pageSize){
        PageInfo pageInfo = iqyUserService.selectUserBySta(status, pageNo, pageSize);
        if (!"".equals(pageInfo) && null !=pageInfo){
            return super.selectSuccess(pageInfo);
        }
        return super.selectFailed();
    }

    /**
     * @author lwq
     * @description
     *    重置密码
     * @param: [user]
     * @date 2020/6/1
     * @return com.aaa.six.base.ResultData
     * @throws
     **/
    @PostMapping("/resetUserPwd")
    @ApiOperation(value = "密码重置", notes = "密码重置")
    public ResultData resetUserPwd(User user){
        Integer integer = iqyUserService.resetUserPwd(user);
        if (integer!=null){
            return super.updateSuccess();
        }
        return super.updateFailed();
    }
}
