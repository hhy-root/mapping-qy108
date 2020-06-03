package com.aaa.six.controller;

import com.aaa.six.model.MappingUnit;
import com.aaa.six.service.MappingUnitService;
import com.aaa.six.utils.DateUtils;
import com.aaa.six.utils.IDUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    /**
     *@Description: TODO
     * 单位条件查询
     *@Param :  [pageNo, pageSize, mappingUnit]
     *@MethodName: selectUnitByPage
     *@Author: lifuju
     *@Date: 2020/6/3 23:07
     *@Return: com.github.pagehelper.PageInfo<com.aaa.six.model.MappingUnit>
     */
    @PostMapping("/selectUnitByPage")
    PageInfo<MappingUnit> selectUnitByPage(@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize,@RequestBody MappingUnit mappingUnit){
      return  mappingUnitService.selectUnitByPage(pageNo,pageSize,mappingUnit);

    }

    /**
     *@Description: TODO
     *  企业注册 单位新增
     *@Param :  [mappingUnit]
     *@MethodName: addUnit
     *@Author: lifuju
     *@Date: 2020/6/3 23:25
     *@Return: java.lang.Integer
     */
    @PostMapping("/addUnit")
    Integer addUnit(@RequestBody MappingUnit mappingUnit){

        return mappingUnitService.addUnit(mappingUnit);
    }

    /**
     *@Description: TODO
     * 通过主键  单个查询单位
     *@Param :  [id]
     *@MethodName: selectUnitOne
     *@Author: lifuju
     *@Date: 2020/6/3 23:29
     *@Return: com.aaa.six.model.MappingUnit
     */
    @PostMapping("/selectUnitOne")
    MappingUnit selectUnitOne(@RequestParam("id") Long id){
        return mappingUnitService.selectUnitOne(id);
    }

    /**
     *@Description: TODO
     * 单个更新
     *@Param :  [mappingUnit]
     *@MethodName: updateUnitOne
     *@Author: lifuju
     *@Date: 2020/6/3 23:32
     *@Return: java.lang.Integer
     */
    @PostMapping("/updateUnitOne")
    Integer updateUnitOne(@RequestBody MappingUnit mappingUnit){
        return mappingUnitService.updateUnitOne(mappingUnit);
    }

    /**
     *@Description: TODO
     * 审核查询
     *@Param :  [pageNo, pageSize, unitName, auditStatus]
     *@MethodName: selectUnitAudit
     *@Author: lifuju
     *@Date: 2020/6/3 23:49
     *@Return: com.github.pagehelper.PageInfo
     */
    @PostMapping("/selectUnitAudit")
    PageInfo selectUnitAudit(@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize")Integer pageSize,@RequestParam("unitName") String unitName,@RequestParam("auditStatus") Integer auditStatus){
        return mappingUnitService.selectUnitAudit(pageNo,pageSize,unitName,auditStatus);
    }
}
