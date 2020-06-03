package com.aaa.six.service;


import com.aaa.six.model.MappingUnit;
import com.github.pagehelper.PageInfo;
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
    PageInfo<MappingUnit> selectUnitByPage(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize, @RequestBody MappingUnit mappingUnit);

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
    Integer addUnit(@RequestBody MappingUnit mappingUnit);

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
    public MappingUnit selectUnitOne(@RequestParam("id") Long id);

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
    Integer updateUnitOne(@RequestBody MappingUnit mappingUnit);

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
    PageInfo selectUnitAudit(@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize")Integer pageSize,@RequestParam("unitName") String unitName,@RequestParam("auditStatus") Integer auditStatus);
}
