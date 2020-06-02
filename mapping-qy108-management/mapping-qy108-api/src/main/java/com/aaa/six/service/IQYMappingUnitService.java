package com.aaa.six.service;


import com.aaa.six.model.MappingUnit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @program: mapping-qy108
 * @author: lwq
 * @create: 2020-05-27 21:24
 * @description:
 *          系统主页-测绘单位
 **/
@FeignClient(value = "mappingUnit-interface")
public interface IQYMappingUnitService {

    /**
     * @return java.util.List<com.aaa.six.model.MappingUnit>
     * @throws
     * @author lwq
     * @description 系统主页-测绘单位
     * 查询测绘单位名称
     * @param: [unitName, ownedDistrict, qualificationLevel]
     * @date 2020/6/1
     **/
    @PostMapping("/fuzzyUnitName")
    List<MappingUnit> fuzzyUnitName(@RequestParam("unitName") String unitName,
                                    @RequestParam("ownedDistrict") String ownedDistrict,
                                    @RequestParam("qualificationLevel") String qualificationLevel);

    /**
     * @return com.aaa.six.model.MappingUnit
     * @throws
     * @author lwq
     * @description 系统主页-测绘单位
     * 查询测绘单位基本信息
     * @param: [id]
     * @date 2020/6/1
     **/
    @PostMapping("/selectUnitInfoById")
    MappingUnit selectUnitInfoById(@RequestParam("id") Long id);
}
