package com.aaa.six.service;

import com.aaa.six.base.BaseService;
import com.aaa.six.mapper.EquipmentMapper;
import com.aaa.six.model.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.aaa.six.staticstatus.TimeProperties.TIME_TYPE;

/**
 * @program: mapping-qy108
 * @author: lwq
 * @create: 2020-06-01 23:43
 * @description:
 *      仪器设备信息管理
 **/
@Service
public class EquipmentService extends BaseService<Equipment> {

    @Autowired
    private EquipmentMapper equipmentMapper;

    /**
     * @author lwq
     * @description
     *    根据userId查询仪器设备信息
     * @param: [userId]
     * @date 2020/6/2
     * @return java.util.List<com.aaa.six.model.Equipment>
     * @throws
     **/
    public List<Equipment> selectEquipmentInfo(Long userId){
        //获取信息
        List<Equipment> equipmentList = equipmentMapper.selectEquipment(userId);
        //判断负责人的信息是否为空
        if (equipmentList.size()>0){
            //不为空就返回信息
            return equipmentList;
        }
        return null;
    }

    /**
     * @author lwq
     * @description
     *    新增仪器设备信息
     * @param: [equipment]
     * @date 2020/6/2
     * @return java.lang.Boolean
     * @throws
     **/
    public Boolean addEquipment(Equipment equipment){
        if (null == equipment && "".equals(equipment)){
            return false;
        }else {
            //获取系统时间
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_TYPE);
            String format = simpleDateFormat.format(date);
            equipment.setCreateTime(format);
            int insert = equipmentMapper.insert(equipment);
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
     *    删除仪器设备信息
     * @param: [equipment]
     * @date 2020/6/2
     * @return boolean
     * @throws
     **/
    public boolean deleteEquipment(Equipment equipment) {
        if ("".equals(equipment) && null == equipment) {
            return false;
        } else {
            int i = equipmentMapper.deleteByPrimaryKey(equipment);
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
     *    修改仪器设备信息
     * @param: [equipment]
     * @date 2020/6/2
     * @return java.lang.Boolean
     * @throws
     **/
    public Boolean updateEquipment(Equipment equipment) {
        //判断user是否为空
        if ("".equals(equipment) && null == equipment) {
            return false;
        } else {
            //获取系统时间
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_TYPE);
            String format = simpleDateFormat.format(date);
            equipment.setCreateTime(format);
            int i = equipmentMapper.updateByPrimaryKey(equipment);
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        }

    }
}
