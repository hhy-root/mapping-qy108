package com.aaa.six.service;

import com.aaa.six.base.BaseService;
import com.aaa.six.mapper.MappingUnitMapper;
import com.aaa.six.model.MappingUnit;
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


}
