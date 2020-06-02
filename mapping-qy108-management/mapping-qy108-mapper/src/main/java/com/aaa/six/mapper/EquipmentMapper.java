package com.aaa.six.mapper;

import com.aaa.six.model.Equipment;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface EquipmentMapper extends Mapper<Equipment> {

    /**
     * 根据用户id查询仪器设备信息
     * @param userId
     * @return
     */
    List<Equipment> selectEquipment(Long userId);
}