package com.aaa.six.controller;

import com.aaa.six.base.BaseController;
import com.aaa.six.base.ResultData;
import com.aaa.six.model.MappingUnit;
import com.aaa.six.service.IQYMappingUnitService;
import com.github.pagehelper.PageInfo;
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
    private IQYMappingUnitService iqyMappingUnitService;
    
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
        List<MappingUnit> mappingUnits = iqyMappingUnitService.fuzzyUnitName(unitName, ownedDistrict, qualificationLevel);

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
        MappingUnit mappingUnit1 = iqyMappingUnitService.selectUnitInfoById(id);

        // 判断 结果是否为空
        if (null != mappingUnit1) {
            // 说明结果不为空，查询成功，返回系统消息，自定义返回值
            return selectSuccess(mappingUnit1);
        }else {
            // 查询失败，返回系统信息
            return selectFailed();
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
    public ResultData selectUnitByPage(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize, @RequestBody MappingUnit mappingUnit){
        PageInfo<MappingUnit> mappingUnitPageInfo = iqyMappingUnitService.selectUnitByPage(pageNo, pageSize, mappingUnit);
        // 判断 结果是否为空
        if (null != mappingUnitPageInfo) {
            // 说明结果不为空，查询成功，返回系统消息，自定义返回值
            return selectSuccess(mappingUnitPageInfo);
        }
            // 查询失败，返回系统信息
            return selectFailed();
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
    public ResultData addUnit(@RequestBody MappingUnit mappingUnit){

        Integer integer = iqyMappingUnitService.addUnit(mappingUnit);
        if (integer != null) {
            return insertSuccess();
        }
        return insertFailed();
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
    public ResultData selectUnitOne(@RequestParam("id") Long id){
        MappingUnit mappingUnit = iqyMappingUnitService.selectUnitOne(id);
        if (mappingUnit != null) {
            return selectSuccess(mappingUnit);
        }
        return selectFailed();
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
    public ResultData updateUnitOne(@RequestBody MappingUnit mappingUnit){
        Integer integer = iqyMappingUnitService.updateUnitOne(mappingUnit);
        if (integer != null) {
            return updateSuccess();
        }
        return updateFailed();
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
    public ResultData selectUnitAudit(@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize")Integer pageSize,@RequestParam("unitName") String unitName,@RequestParam("auditStatus") Integer auditStatus){
        PageInfo pageInfo = iqyMappingUnitService.selectUnitAudit(pageNo, pageSize, unitName, auditStatus);
        if (pageInfo != null) {
            return selectSuccess(pageInfo);
        }
        return selectFailed();
    }

}
