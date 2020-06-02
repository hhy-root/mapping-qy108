package com.aaa.six.controller;

import com.aaa.six.IQYService;
import com.aaa.six.base.BaseController;
import com.aaa.six.base.ResultData;
import com.aaa.six.model.SpecialPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: mapping-qy108
 * @author: lwq
 * @create: 2020-06-01 23:40
 * @description:
 *      特殊岗位人员信息管理
 **/
@RestController
public class SpecialPostController extends BaseController {

    @Autowired
    private IQYService qyService;

    /**
     * @author lwq 
     * @description
     *    根据userId查询特殊岗位人员信息
     * @param: [userId]
     * @date 2020/6/2
     * @return com.aaa.six.base.ResultData
     * @throws 
     **/
    @PostMapping("/selectSpecialPostInfo")
    public ResultData selectSpecialPostInfo(@RequestParam("userId") Long userId) {
        List<SpecialPost> specialPosts = qyService.selectSpecialPostInfo(userId);
        if (specialPosts.size()>0) {
            return selectSuccess(specialPosts);
        }
        return selectFailed();
    }

    /**
     * @author lwq 
     * @description
     *    新增特殊岗位人员信息
     * @param: [specialPost]
     * @date 2020/6/2
     * @return com.aaa.six.base.ResultData
     * @throws 
     **/
    @PostMapping("/addSpecialPost")
    public ResultData addSpecialPost(SpecialPost specialPost){
        Boolean aBoolean = qyService.addSpecialPost(specialPost);
        if (aBoolean){
            return super.insertSuccess();
        }
        return super.insertFailed();
    }
    /**
     * @author lwq 
     * @description
     *    删除特殊岗位人员信息
     * @param: [specialPost]
     * @date 2020/6/2
     * @return com.aaa.six.base.ResultData
     * @throws 
     **/
    @PostMapping("/deleteSpecialPost")
    public ResultData deleteSpecialPost(SpecialPost specialPost){
        Boolean aBoolean = qyService.deleteSpecialPost(specialPost);
        if (aBoolean){
            return super.deleteSuccess();
        }
        return super.deleteFailed();
    }

    /**
     * @author lwq
     * @description
     *    修改特殊岗位人员信息
     * @param: [specialPost]
     * @date 2020/6/2
     * @return com.aaa.six.base.ResultData
     * @throws
     **/
    @PostMapping("/updateSpecialPost")
    public ResultData updateSpecialPost(SpecialPost specialPost){
        Boolean aBoolean = qyService.updateSpecialPost(specialPost);
        if (aBoolean){
            return super.updateSuccess();

        }else {
            return super.updateFailed();
        }
    }
}
