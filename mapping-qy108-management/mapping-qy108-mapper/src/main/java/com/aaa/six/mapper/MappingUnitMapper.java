package com.aaa.six.mapper;

import com.aaa.six.model.MappingUnit;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MappingUnitMapper extends Mapper<MappingUnit> {


   /**
    * @author lwq 
    * @description
    *    系统主页-测绘单位
    *     模糊查询 查询测绘单位名称
    * @param: [unitName, ownedDistrict, qualificationLevel]
    * @date 2020/6/1
    * @return java.util.List<com.aaa.six.model.MappingUnit>
    * @throws 
    **/
    List<MappingUnit> fuzzyUnitName(@Param("unitName") String unitName,
                                    @Param("ownedDistrict") String ownedDistrict,
                                    @Param("qualificationLevel") String qualificationLevel);

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
    MappingUnit selectUnitInfoById(Long id);

    /**
     *@Description: TODO
     *@Param :  []
     *@MethodName: selectUnitAudit
     *@Author: lifuju
     *@Date: 2020/6/3 23:11
     *@Return: java.util.List<com.aaa.six.model.MappingUnit>
     */
    List<MappingUnit> selectUnitAudit(@Param("unitName") String unitName,@Param("auditStatus")Integer auditStatus);

}