package com.aaa.six.controller;

import com.aaa.six.service.IQYService;
import com.aaa.six.base.BaseController;
import com.aaa.six.base.ResultData;
import com.aaa.six.model.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: mapping-qy108
 * @author: lwq
 * @create: 2020-06-01 23:42
 * @description:
 *         仪器设备信息管理
 **/
@RestController
public class EquipmentController extends BaseController {
    @Autowired
    private IQYService qyService;

    /**
     * @author lwq 
     * @description
     *    根据userid查询仪器设备信息
     * @param: [userId]
     * @date 2020/6/2
     * @return com.aaa.six.base.ResultData
     * @throws 
     **/
    @PostMapping("/selectEquipmentInfo")
    public ResultData selectEquipmentInfo(@RequestParam("userId") Long userId) {
        List<Equipment> equipmentList = qyService.selectEquipmentInfo(userId);
        if (equipmentList.size()>0) {
            return selectSuccess(equipmentList);
        }
        return selectFailed();
    }

    /**
     * @author lwq 
     * @description
     *    新增仪器设备信息
     * @param: [equipment]
     * @date 2020/6/2
     * @return com.aaa.six.base.ResultData
     * @throws 
     **/
    @PostMapping("/addEquipment")
    public ResultData addEquipment(Equipment equipment){
        Boolean aBoolean = qyService.addEquipment(equipment);
        if (aBoolean){
            return super.insertSuccess();
        }
        return super.insertFailed();
    }
    /**
     * @author lwq 
     * @description
     *    删除仪器设备信息
     * @param: [equipment]
     * @date 2020/6/2
     * @return com.aaa.six.base.ResultData
     * @throws 
     **/
    @PostMapping("/deleteEquipment")
    public ResultData deleteEquipment(Equipment equipment){
        Boolean aBoolean = qyService.deleteEquipment(equipment);
        if (aBoolean){
            return super.deleteSuccess();
        }
        return super.deleteFailed();
    }

    /**
     * @author lwq 
     * @description
     *    修改仪器设备信息
     * @param: [equipment]
     * @date 2020/6/2
     * @return com.aaa.six.base.ResultData
     * @throws 
     **/
    @PostMapping("/updateEquipment")
    public ResultData updateEquipment(Equipment equipment){
        Boolean aBoolean = qyService.updateEquipment(equipment);
        if (aBoolean){
            return super.updateSuccess();

        }else {
            return super.updateFailed();
        }
    }
}
