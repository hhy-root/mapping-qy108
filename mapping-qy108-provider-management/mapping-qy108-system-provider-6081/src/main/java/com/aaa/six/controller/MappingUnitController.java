package com.aaa.six.controller;

import com.aaa.six.model.MappingUnit;
import com.aaa.six.service.MappingUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: mapping-qy108
 * @author: lwq
 * @create: 2020-05-16 11:16
 * @description:
 *      系统主页-测绘单位
 **/
@RestController
public class MappingUnitController {

    @Autowired
    private MappingUnitService mappingUnitService;


    /**
     * @author lwq 
     * @description
     *    模糊查询 查询测绘单位名称
     * @param: [unitName, ownedDistrict, qualificationLevel]
     * @date 2020/6/1
     * @return java.util.List<com.aaa.six.model.MappingUnit>
     * @throws 
     **/
    @PostMapping("/fuzzyUnitName")
    public List<MappingUnit> fuzzyUnitName(@RequestParam("unitName") String unitName,
                                           @RequestParam("ownedDistrict") String ownedDistrict,
                                           @RequestParam("qualificationLevel") String qualificationLevel) {
        // 调用 mappingUnitService 中的 fuzzyUnitName 方法，得到查询结果
        List<MappingUnit> mappingUnits = mappingUnitService.fuzzyUnitName(unitName, ownedDistrict, qualificationLevel);

        // 判断 结果是否为空
        if (mappingUnits != null) {
            // 说明结果不为空，返回结果数据
            return mappingUnits;
        }else {
            // 返回null
            return null;
        }
    }

    /**
     * @author lwq 
     * @description
     *    系统主页-测绘单位
     *    查询测绘单位基本信息
     * @param: [id]
     * @date 2020/6/1
     * @return com.aaa.six.model.MappingUnit
     * @throws 
     **/
    @PostMapping("/selectUnitInfoById")
    public MappingUnit selectUnitInfoById(@RequestParam("id") Long id) {
        // 调用 mappingUnitService 中的 selectUnitInfoById 方法，得到查询结果
        MappingUnit mappingUnit1 = mappingUnitService.selectUnitInfoById(id);
        // 判断 结果是否为空
        if (null != mappingUnit1) {
            // 说明结果不为空，查询成功，返回结果
            return mappingUnit1;
        }else {
            // 查询失败，返回null
            return null;
        }
    }

}
