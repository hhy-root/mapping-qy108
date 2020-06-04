package com.aaa.six.service;

import com.aaa.six.base.BaseService;
import com.aaa.six.mapper.MappingProjectMapper;
import com.aaa.six.mapper.SpecialPostMapper;
import com.aaa.six.mapper.TechnicistMapper;
import com.aaa.six.model.TechResult;
import com.aaa.six.model.TechTypeNum;
import com.aaa.six.model.Technicist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.aaa.six.staticstatus.TimeProperties.TIME_TYPE;

/**
 * @program: mapping-qy108
 * @author: lwq
 * @create: 2020-06-01 23:06
 * @description:
 **/
@Service
public class TechnicistService extends BaseService<Technicist> {

    @Autowired
    private TechnicistMapper technicistMapper;

    @Autowired
    private SpecialPostMapper specialPostMapper;

    @Autowired
    private MappingProjectMapper mappingProjectMapper;
    /**
     * @author lwq 
     * @description
     *    根据userId查询技术员信息
     * @param: [userId]
     * @date 2020/6/1
     * @return java.util.List<com.aaa.six.model.Technicist>
     * @throws 
     **/
    public List<Technicist> selectTechnicistInfo(Long userId){
        //获取信息
        List<Technicist> technicists = technicistMapper.selectTechnicist(userId);
        //判断负责人的信息是否为空
        if (technicists.size()>0){
            //不为空就返回信息
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
    public Boolean addTechnicist(Technicist technicist){
        if (null == technicist && "".equals(technicist)){
            return false;
        }else {
            //获取系统时间
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_TYPE);
            String format = simpleDateFormat.format(date);
            technicist.setCreateTime(format);
            int insert = technicistMapper.insert(technicist);
            if (insert > 0){
                return true;
            } else{
                return false;
            }
        }
    }

    /**
     * @author lwq 
     * @description
     *    删除技术员信息
     * @param: [technicist]
     * @date 2020/6/1
     * @return boolean
     * @throws 
     **/
    public boolean deleteTechnicist(Technicist technicist) {
        if ("".equals(technicist) && null == technicist) {
            return false;
        } else {
            int i = technicistMapper.deleteByPrimaryKey(technicist);
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        }

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
    public Boolean updateTechnicist(Technicist technicist) {
        //判断user是否为空
        if ("".equals(technicist) && null == technicist) {
            return false;
        } else {
            //获取系统时间
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_TYPE);
            String format = simpleDateFormat.format(date);
            technicist.setCreateTime(format);
            int i = technicistMapper.updateByPrimaryKey(technicist);
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        }

    }


    /**
     * @Author: ly
     * @description:
     *
     *      数据统计  获取单位技术人员 和项目数量
     * @date: 2020/6/3
     * @param userId
     * @return: java.lang.String
     *
     */
    public TechResult selectTechTypeNum(Integer userId){
        List<TechTypeNum> techTypeNum = technicistMapper.selectTechTypeNum(userId.longValue());
        TechResult techResult = new TechResult();
        List nameList = new ArrayList();
        List valueList = new ArrayList();

        if(techTypeNum.size()>0){
            Integer i = 0;
            for (TechTypeNum tech:techTypeNum) {
                nameList.add(i,tech.getMajorType());
                valueList.add(i,tech.getNum());
            }

            //得到特殊人才数量
            Integer specialPostNum = specialPostMapper.getSpecialPostNum(userId.longValue());
            nameList.add("特岗人员");
            valueList.add(specialPostNum);

            //得到项目数量
            Integer projectNum = mappingProjectMapper.getProjectNum(userId.longValue());
            nameList.add("项目数量");
            valueList.add(projectNum);

            //封装前台需要的数据
            techResult.setName(nameList);
            techResult.setValue(valueList);

            return techResult;
        }
        return null;
    }
}
