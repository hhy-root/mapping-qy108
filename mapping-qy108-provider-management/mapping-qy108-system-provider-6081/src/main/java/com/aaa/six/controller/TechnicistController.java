package com.aaa.six.controller;

import com.aaa.six.model.TechResult;
import com.aaa.six.model.Technicist;
import com.aaa.six.service.TechnicistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: mapping-qy108
 * @author: lwq
 * @create: 2020-06-01 23:03
 * @description:
 **/
@RestController
public class TechnicistController {
    @Autowired
    private TechnicistService technicistService;

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
    public List<Technicist> selectTechnicistInfo(@RequestParam("userId") Long userId){
        List<Technicist> technicists = technicistService.selectTechnicistInfo(userId);
        if (technicists.size()>0){
            return technicists;
        }
        return null;
    }

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
    public Boolean addTechnicist(@RequestBody Technicist technicist){
        Boolean aBoolean = technicistService.addTechnicist(technicist);
        if (aBoolean){
            return true;
        }
        return false;
    }

    /**
     * @author lwq
     * @description
     *    修改技术员信息
     * @param: [technicist]
     * @date 2020/6/1
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/deleteTechnicist")
    public Boolean deleteTechnicist(@RequestBody Technicist technicist){
        boolean b = technicistService.deleteTechnicist(technicist);
        if (b){
            return true;
        }
        return false;
    }
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
    public Boolean updateTechnicist(@RequestBody Technicist technicist){
        return technicistService.updateTechnicist(technicist);
    }

    /**
     * @Author: ly
     * @description:
     *
     *       数据统计  获取单位技术人员 和项目数量
     * @date: 2020/6/3
     * @param userId
     * @return: com.aaa.six.model.TechResult
     *
     */
    @PostMapping("/selectTechTypeNum")
    public TechResult selectTechTypeNum(@RequestParam("userId") Integer userId){
        return technicistService.selectTechTypeNum(userId);
    }
}
