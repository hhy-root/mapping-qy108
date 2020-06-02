package com.aaa.six.service;

import com.aaa.six.model.Equipment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * @program: mapping-qy108
 * @author: lwq
 * @create: 2020-06-01 23:42
 * @description:
 *         仪器设备信息管理
 **/
@FeignClient(value = "equi-interface")
public interface IQYEquipmentService {
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
    List<Equipment> selectEquipmentInfo(@RequestParam("userId") Long userId);
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
    Boolean addEquipment(@RequestBody Equipment equipment);

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
    Boolean deleteEquipment(@RequestBody Equipment equipment);
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
    Boolean updateEquipment(@RequestBody Equipment equipment);

}
