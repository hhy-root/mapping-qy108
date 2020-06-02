package com.aaa.six.controller;

import com.aaa.six.model.Equipment;
import com.aaa.six.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: mapping-qy108
 * @author: lwq
 * @create: 2020-06-01 23:44
 * @description:
 *          仪器设备信息管理
 **/
@RestController
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    /**
     * @author lwq
     * @description
     *    根据userid查询仪器设备信息
     * @param: [userId]
     * @date 2020/6/2
     * @return java.util.List<com.aaa.six.model.Equipment>
     * @throws
     **/
    @PostMapping("/selectEquipmentInfo")
    public List<Equipment> selectEquipmentInfo(@RequestParam("userId") Long userId){
        List<Equipment> equipmentList = equipmentService.selectEquipmentInfo(userId);
        if (equipmentList.size()>0){
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
    @PostMapping("/addEquipment")
    public Boolean addEquipment(@RequestBody Equipment equipment){
        Boolean aBoolean = equipmentService.addEquipment(equipment);
        if (aBoolean){
            return true;
        }
        return false;
    }

    /**
     * @author lwq
     * @description
     *    删除仪器设备信息
     * @param: [equipment]
     * @date 2020/6/2
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/deleteEquipment")
    public Boolean deleteEquipment(@RequestBody Equipment equipment){
        boolean b = equipmentService.deleteEquipment(equipment);
        if (b){
            return true;
        }
        return false;
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
    @PostMapping("/updateEquipment")
    public Boolean updateEquipment(@RequestBody Equipment equipment){
        return equipmentService.updateEquipment(equipment);
    }
}
