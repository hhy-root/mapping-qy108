package com.aaa.six.service;

import com.aaa.six.base.BaseService;
import com.aaa.six.mapper.MappingProjectMapper;
import com.aaa.six.mapper.MappingUnitMapper;
import com.aaa.six.model.MappingUnit;
import com.aaa.six.utils.DateUtils;
import com.aaa.six.utils.IDUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: mapping-qy108
 * @author: lwq
 * @create: 2020-05-27 20:13
 * @description:
 *      系统主页-测绘单位
 **/
@Service
public class MappingUnitService extends BaseService<MappingUnit> {

    @Autowired
    private MappingUnitMapper mappingUnitMapper;


    /**
     * @author lwq 
     * @description
     *    系统主页-测绘单位
     *    模糊查询 查询测绘单位名称
     * @param: [unitName, ownedDistrict, qualificationLevel]
     * @date 2020/6/1
     * @return java.util.List<com.aaa.six.model.MappingUnit>
     * @throws 
     **/
    public List<MappingUnit> fuzzyUnitName(String unitName, String ownedDistrict, String qualificationLevel) {
        // 调用 mappingUnitMapper 中的 fuzzyUnitName 方法，得到查询结果
        List<MappingUnit> mappingUnitList = mappingUnitMapper.fuzzyUnitName(unitName, ownedDistrict, qualificationLevel);

        // 判断 结果是否为空
        if (mappingUnitList != null && mappingUnitList.size() > 0) {
            // 说明结果不为空，查询成功，返回结果
            return mappingUnitList;
        }else {
            // 查询失败，返回null
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
    public MappingUnit selectUnitInfoById(Long id) {
        // 调用 mappingUnitMapper 中的 selectUnitInfoById 方法，得到查询结果
        MappingUnit mappingUnit1 = mappingUnitMapper.selectUnitInfoById(id);

        // 判断 结果是否为空
        if (null != mappingUnit1 && !"".equals(mappingUnit1)) {
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
    public PageInfo<MappingUnit> selectUnitByPage(Integer pageNo, Integer pageSize, MappingUnit mappingUnit){
        PageHelper.startPage(pageNo,pageSize);
        List<MappingUnit> select = mappingUnitMapper.select(mappingUnit);
        PageInfo<MappingUnit> mappingUnitPageInfo = new PageInfo<>(select);
        if (null!=mappingUnitPageInfo){
            return mappingUnitPageInfo;
        }
        return null;
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
    public Integer addUnit(MappingUnit mappingUnit){
        mappingUnit.setId(IDUtils.genUniqueKey());
        mappingUnit.setCreateTime(DateUtils.getCurrentDate());
        try {
            return  super.add(mappingUnit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
    public MappingUnit selectUnitOne(Long id){

        MappingUnit mappingUnit = mappingUnitMapper.selectByPrimaryKey(id);
        if (mappingUnit != null) {
            return mappingUnit;
        }
        return null;
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
    public Integer updateUnitOne(MappingUnit mappingUnit){
        try {
            return super.update(mappingUnit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
    public PageInfo selectUnitAudit(Integer pageNo,Integer pageSize,String unitName,Integer auditStatus){
        PageHelper.startPage(pageNo,pageSize);
        List<MappingUnit> mappingUnits = mappingUnitMapper.selectUnitAudit(unitName, auditStatus);
        PageInfo<MappingUnit> mappingUnitPageInfo = new PageInfo<>(mappingUnits);
        if (mappingUnitPageInfo != null) {
            return mappingUnitPageInfo;
        }
        return null;
    }
}
