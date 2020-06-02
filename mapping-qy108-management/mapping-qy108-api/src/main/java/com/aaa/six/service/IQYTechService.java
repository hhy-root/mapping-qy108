package com.aaa.six.service;

import com.aaa.six.model.Technicist;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * @program: mapping-qy108
 * @author: lwq
 * @create: 2020-06-01 23:09
 * @description:
 *      技术员信息管理
 **/
@FeignClient(value = "tech-interface")
public interface IQYTechService {

    /**
     * @author lwq
     * @description
     *    根据userId查询技术员信息
     * @param: [userId]
     * @date 2020/6/1
     * @return java.util.List<com.aaa.six.model.Technicist>
     * @throws
     **/
    @PostMapping("/selectTechnicistInfo")
    List<Technicist> selectTechnicistInfo(@RequestParam("userId") Long userId);
    /**
     * @author lwq
     * @description
     *    新增技术员
     * @param: [technicist]
     * @date 2020/6/1
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/addTechnicist")
    Boolean addTechnicist(@RequestBody Technicist technicist);

    /**
     * @author lwq
     * @description
     *    删除技术员信息
     * @param: [technicist]
     * @date 2020/6/1
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/deleteTechnicist")
    Boolean deleteTechnicist(@RequestBody Technicist technicist);
    /**
     * @author lwq
     * @description
     *    修改单位负责人信息
     * @param: [principal]
     * @date 2020/6/1
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/updateTechnicist")
    Boolean updateTechnicist(@RequestBody Technicist technicist);

}
