package com.aaa.six.controller;

import com.aaa.six.model.SpecialPost;
import com.aaa.six.service.SpecialPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
public class SpecialPostController {

    @Autowired
    private SpecialPostService specialPostService;
    
    /**
     * @author lwq 
     * @description
     *    根据userid查询特殊岗位人员信息
     * @param: [userId]
     * @date 2020/6/2
     * @return java.util.List<com.aaa.six.model.SpecialPost>
     * @throws 
     **/
    @PostMapping("/selectSpecialPostInfo")
    public List<SpecialPost> selectSpecialPostInfo(@RequestParam("userId") Long userId){
        List<SpecialPost> specialPosts = specialPostService.selectSpecialPostInfo(userId);
        if (specialPosts.size()>0){
            return specialPosts;
        }
        return null;
    }

    /**
     * @author lwq 
     * @description
     *    新增特殊岗位人员信息
     * @param: [specialPost]
     * @date 2020/6/2
     * @return java.lang.Boolean
     * @throws 
     **/
    @PostMapping("/addSpecialPost")
    public Boolean addSpecialPost(@RequestBody SpecialPost specialPost){
        Boolean aBoolean = specialPostService.addSpecialPost(specialPost);
        if (aBoolean){
            return true;
        }
        return false;
    }

    /**
     * @author lwq 
     * @description
     *    删除特殊岗位人员信息
     * @param: [specialPost]
     * @date 2020/6/2
     * @return java.lang.Boolean
     * @throws 
     **/
    @PostMapping("/deleteSpecialPost")
    public Boolean deleteSpecialPost(@RequestBody SpecialPost specialPost){
        boolean b = specialPostService.deleteSpecialPost(specialPost);
        if (b){
            return true;
        }
        return false;
    }
    /**
     * @author lwq 
     * @description
     *    修改特殊岗位人员信息
     * @param: [specialPost]
     * @date 2020/6/2
     * @return java.lang.Boolean
     * @throws 
     **/
    @PostMapping("/updateSpecialPost")
    public Boolean updateSpecialPost(@RequestBody SpecialPost specialPost){
        return specialPostService.updateSpecialPost(specialPost);
    }
}
