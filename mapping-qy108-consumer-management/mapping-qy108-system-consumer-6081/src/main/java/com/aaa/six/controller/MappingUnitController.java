package com.aaa.six.controller;

import com.aaa.six.base.BaseController;
import com.aaa.six.base.ResultData;
import com.aaa.six.model.MappingUnit;
import com.aaa.six.IQYService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @program: mapping-qy108
 * @author: lwq
 * @create: 2020-05-27 21:24
 * @description:
 *          系统主页-测绘单位
 **/
@RestController
public class MappingUnitController extends BaseController {

    @Autowired
    private IQYService iqyService;
    
    /**
     * @author lwq 
     * @description
     *    系统主页-测绘单位
     *    查询测绘单位名称
     * @param: [unitName, ownedDistrict, qualificationLevel]
     * @date 2020/6/1
     * @return com.aaa.six.base.ResultData<com.aaa.six.model.MappingUnit>
     * @throws 
     **/
    @PostMapping("/fuzzyUnitName")
    public ResultData<MappingUnit> getUnitName(String unitName, String ownedDistrict, String qualificationLevel) {
        // 调用 iqyService 中的 fuzzyUnitName 方法，得到查询结果
        List<MappingUnit> mappingUnits = iqyService.fuzzyUnitName(unitName, ownedDistrict, qualificationLevel);

        // 判断 结果是否为空
        if (mappingUnits != null) {
            // 说明结果不为空，查询成功，使用系统消息 自定义返回值
            return selectSuccess(mappingUnits);
        }else {
            // 查询失败，返回系统信息
            return selectFailed();
        }

    }

    /**
     * @author lwq 
     * @description
     *    系统主页-测绘单位
     *    查询测绘单位基本信息
     * @param: [mappingUnit]
     * @date 2020/6/1
     * @return com.aaa.six.base.ResultData<com.aaa.six.model.MappingUnit>
     * @throws 
     **/
    @PostMapping("/selectUnitInfoById")
    public ResultData<MappingUnit> selectUnitInfoById(@RequestParam("id") Long id) {
        // 调用 iqyService 中的 selectUnitInfoById 方法，得到查询结果
        MappingUnit mappingUnit1 = iqyService.selectUnitInfoById(id);

        // 判断 结果是否为空
        if (null != mappingUnit1) {
            // 说明结果不为空，查询成功，返回系统消息，自定义返回值
            return selectSuccess(mappingUnit1);
        }else {
            // 查询失败，返回系统信息
            return selectFailed();
        }
    }

}
