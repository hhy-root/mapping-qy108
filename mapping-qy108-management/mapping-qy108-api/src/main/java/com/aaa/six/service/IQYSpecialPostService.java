package com.aaa.six.service;

import com.aaa.six.model.SpecialPost;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: mapping-qy108
 * @author: lwq
 * @create: 2020-06-01 23:40
 * @description:
 *      特殊岗位人员信息管理
 **/
@FeignClient(value = "specialPost-interface")
public interface IQYSpecialPostService {
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
    List<SpecialPost> selectSpecialPostInfo(@RequestParam("userId") Long userId);
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
    Boolean addSpecialPost(@RequestBody SpecialPost specialPost);

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
    Boolean deleteSpecialPost(@RequestBody SpecialPost specialPost);
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
    Boolean updateSpecialPost(@RequestBody SpecialPost specialPost);
}
