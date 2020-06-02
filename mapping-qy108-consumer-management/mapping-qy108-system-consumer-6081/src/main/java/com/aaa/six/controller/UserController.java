package com.aaa.six.controller;

import com.aaa.six.IQYService;
import com.aaa.six.base.BaseController;
import com.aaa.six.base.ResultData;
import com.aaa.six.model.User;
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
@Api(value = "项目管理信息", tags = "项目管理接口")
public class UserController extends BaseController {

    @Autowired
    private IQYService qyService;

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
        PageInfo<User> userPageInfo = qyService.selectUserInfo(pageNo, pageSize);
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
    public ResultData selectInfoById(User user){
        User user1 = qyService.selectInfoById(user);
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
    public ResultData deleteUserById(User user){
        Boolean aBoolean = qyService.deleteUserById(user);
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
    public ResultData addUser(User user){
        Boolean aBoolean = qyService.addUser(user);
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
    public ResultData updateUser(User user){
        Boolean aBoolean = qyService.updateUser(user);
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
    public ResultData deleteUserByIds(@RequestBody List<Object> ids){
        Integer integer = qyService.deleteUserByIds(ids);
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
    public ResultData selectUserByField(User user, Integer pageNo, Integer pageSize){
        PageInfo pageInfo = qyService.selectUserByField(user, pageNo, pageSize);
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
    public ResultData selectUserBySsex(String ssex, Integer pageNo, Integer pageSize){
        PageInfo pageInfo = qyService.selectUserBySsex(ssex, pageNo, pageSize);
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
    public ResultData selectUserBySta(String status,Integer pageNo,Integer pageSize){
        PageInfo pageInfo = qyService.selectUserBySta(status, pageNo, pageSize);
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
    public ResultData resetUserPwd(User user){
        Integer integer = qyService.resetUserPwd(user);
        if (integer!=null){
            return super.updateSuccess();
        }
        return super.updateFailed();
    }
}
