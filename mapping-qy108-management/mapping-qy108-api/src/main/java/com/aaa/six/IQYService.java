package com.aaa.six;

import com.aaa.six.base.ResultData;
import com.aaa.six.model.*;
import com.aaa.six.vo.TokenVo;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Company AAA软件教育
 * @Title mapping-qy108
 * @Author hhy
 * @Version 0.1.0
 * @Date Create in 2020/5/16 8:44
 * @Description
 *      fallbackFactory:就是来实现熔断的，在实际开发中，开发阶段不能去开启熔断
 *      因为一旦开启了熔断，整个异常都不会再抛出，不方便调bug
 *
 *      实际开发必须注意的东西:
 *          无论是springcloud1.x还是2.x版本
 *          一旦使用feign来进行传递参数的时候，必须要注意两个点:
 *              1.如果是简单类型(8种基本类型，String)--->必须使用注解@RequestParam
 *                  基本类型可以传多个，也就是说一个方法的参数中可以使用多@RequestParam
 *
 *              2.如果传递包装类型(List, Map, Vo, Po)，只能传递一个，而且必须要使用@RequestBody注解
 *
 *         也就是说最终把这些参数值传递到provider项目的controller中，在这provider项目的controller中也必须使用
 *         相同的注解，而且provider和api的方法必须要一模一样(copy是最方便的)
 *
 */
@FeignClient(value = "system-interface")
public interface IQYService {

    /**
     * @return TokenVo
     * @throws
     * @author hhy
     * @description 执行登陆操作
     * @param: [user]
     * @date 2020/5/16 9:08
     */
    @PostMapping("/doLogin")
    TokenVo doLogin(@RequestBody User user);

    /**
     * @param pid
     * @param pageNo
     * @param pageSize
     * @Author: ly
     * @description: 查询所有的部门
     * @date: 2020/5/22
     * @return: com.github.pagehelper.PageInfo
     */
    @PostMapping("/getAllDept")
    PageInfo getAllDept(@RequestParam("pid") Integer pid, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

    /**
     * @param id
     * @Author: ly
     * @description: 根据主键查找部门
     * @date: 2020/5/22
     * @return: com.aaa.six.model.Dept
     */
    @GetMapping("/getDeptById")
    Dept getDeptById(@RequestParam("id") Integer id);

    /**
     * @param dept
     * @Author: ly
     * @description: 按照调价查询部门
     * @date: 2020/5/22
     * @return: java.util.List<com.aaa.six.model.Dept>
     */
    @PostMapping("/getDeptByFileds")
    List<Dept> getDeptByFileds(@RequestBody Dept dept);

    /**
     * @param dept
     * @Author: ly
     * @description: 更新部门
     * @date: 2020/5/22
     * @return: java.lang.Integer
     */
    @PostMapping("/updateDept")
    Integer updateDept(@RequestBody Dept dept);

    /**
     * @param ids
     * @Author: ly
     * @description: 按照主键批量删除
     * @date: 2020/5/22
     * @return: java.lang.Integer
     */
    @PostMapping("/delByIds")
    Integer delByIds(@RequestBody List<Object> ids);

    /**
     * @param dept
     * @Author: ly
     * @description: 增加部门
     * @date: 2020/5/22
     * @return: java.lang.Integer
     */
    @PostMapping("/addDept")
    Integer addDept(@RequestBody Dept dept);


    /**
     * @Description: TODO
     * 项目管理 新增方法 单个新增
     * @Param :  [mappingProject]
     * @MethodName: add
     * @Author: lifuju
     * @Date: 2020/5/24 8:51
     * @Return: java.lang.Integer
     */
    @PostMapping("/addMappingProject")
    Integer add(@RequestBody MappingProject mappingProject);

    /**
     * @Description: TODO
     * 项目管理 删除方法  单个删除
     * @Param :  [id]
     * @MethodName: delMappingProject
     * @Author: lifuju
     * @Date: 2020/5/24 8:52
     * @Return: java.lang.Integer
     */
    @GetMapping("/delMappingProject/{id}")
    Integer delMappingProject(@PathVariable("id") Long id);

    /**
     * @Description: TODO
     * 项目管理 删除方法 批量删除
     * @Param :  [ids]
     * @MethodName: delBatch
     * @Author: lifuju
     * @Date: 2020/5/24 8:52
     * @Return: java.lang.Integer
     */
    @PostMapping("/deleteBatchMappingProject")
    Integer delBatch(@RequestBody List<Object> ids);

    /**
     * @Description: TODO
     * 项目管理 更新方法 单个更新
     * @Param :  [id]
     * @MethodName: selectOne
     * @Author: lifuju
     * @Date: 2020/5/24 8:52
     * @Return: com.aaa.six.model.MappingProject
     */
    @GetMapping("lectOne")
    MappingProject selectOne(@RequestParam("id") Long id);

    /**
     * @Description: TODO
     * 项目管理 更新方法 单个更新
     * @Param :  [mappingProject]
     * @MethodName: update
     * @Author: lifuju
     * @Date: 2020/5/24 8:53
     * @Return: java.lang.Integer
     */
    @PostMapping("/updateMappingProject")
    Integer update(@RequestBody MappingProject mappingProject);

    /**
     * @Description: TODO
     * 项目管理 查询方法 分页查询
     * @Param :  [pageNo, pageSize]
     * @MethodName: queryListByPage
     * @Author: lifuju
     * @Date: 2020/5/24 8:53
     * @Return: com.github.pagehelper.PageInfo
     */
    @PostMapping("/mappingProjectByPage")
    PageInfo queryListByPage(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

    /**
     * @return com.github.pagehelper.PageInfo<com.aaa.six.model.User>
     * @throws
     * @author lwq
     * @description 分页查询用户信息
     * @param: [pageNo, pageSize]
     * @date 2020/5/27
     **/
    @PostMapping("/selectUserInfo")
    PageInfo<User> selectUserInfo(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

    /**
     * @return com.aaa.six.model.User
     * @throws
     * @author lwq
     * @description 根据id查询用户信息
     * @param: [user]
     * @date 2020/5/27
     **/
    @PostMapping("/selectInfoById")
    User selectInfoById(@RequestBody User user);

    /**
     * @return java.lang.Boolean
     * @throws
     * @author lwq
     * @description 根据id删除用户
     * @param: [user]
     * @date 2020/5/27
     **/
    @PostMapping("/deleteUserById")
    Boolean deleteUserById(@RequestBody User user);

    /**
     * @return java.lang.Boolean
     * @throws
     * @author lwq
     * @description 新增用户信息
     * @param: [user]
     * @date 2020/5/27
     **/
    @PostMapping("/addUser")
    Boolean addUser(@RequestBody User user);

    /**
     * @return java.lang.Boolean
     * @throws
     * @author lwq
     * @description 修改用户信息
     * @param: [user]
     * @date 2020/5/27
     **/
    @PostMapping("/updateUser")
    Boolean updateUser(@RequestBody User user);

    /**
     * @return java.lang.Integer
     * @throws
     * @author lwq
     * @description 批量删除用户
     * @param: [ids]
     * @date 2020/5/27
     **/
    @PostMapping("/deleteUserByIds")
    Integer deleteUserByIds(@RequestBody List<Object> ids);

    /**
     * 用户分页条件查询
     *
     * @param user
     * @param pageNo
     * @param pageSize
     * @return
     */
    @PostMapping("/selectUserByField")
    PageInfo selectUserByField(@RequestBody User user, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

    /**
     * 根据用户性别查询用户信息
     *
     * @param ssex
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/selectUserBySsex")
    PageInfo selectUserBySsex(@RequestParam("ssex") String ssex, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

    /**
     * 根据用户状态查询用户信息
     *
     * @param status
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/selectUserBySta")
    PageInfo selectUserBySta(@RequestParam("status") String status, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

    /**
     * 重置密码
     *
     * @param user
     * @return
     */
    @PostMapping("/resetUserPwd")
    Integer resetUserPwd(@RequestBody User user);

    /**
     * @return com.github.pagehelper.PageInfo<com.aaa.six.model.Dict>
     * @throws
     * @author lwq
     * @description 分页查询字典信息
     * @param: [pageNo, pageSize]
     * @date 2020/5/27
     **/
    @PostMapping("/selectDictInfo")
    PageInfo<Dict> selectDictInfo(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

    /**
     * @return java.lang.Boolean
     * @throws
     * @author lwq
     * @description 新增字典信息
     * @param: [dict]
     * @date 2020/5/27
     **/
    @PostMapping("/addDict")
    Boolean addDict(@RequestBody Dict dict);

    /**
     * @return com.aaa.six.model.Dict
     * @throws
     * @author lwq
     * @description 根据id查询字典信息
     * @param: [dict]
     * @date 2020/5/27
     **/
    @PostMapping("/selectDictById")
    Dict selectDictById(@RequestBody Dict dict);

    /**
     * @return java.lang.Boolean
     * @throws
     * @author lwq
     * @description 修改字典信息
     * @param: [dict]
     * @date 2020/5/27
     **/
    @PostMapping("/updateDict")
    Boolean updateDict(@RequestBody Dict dict);

    /**
     * @return java.lang.Boolean
     * @throws
     * @author lwq
     * @description 根据id删除字典信息
     * @param: [dict]
     * @date 2020/5/27
     **/
    @PostMapping("/deleteDictById")
    Boolean deleteDictById(@RequestBody Dict dict);

    /**
     * @return java.lang.Integer
     * @throws
     * @author lwq
     * @description 批量删除字典信息
     * @param: [ids]
     * @date 2020/5/27
     **/
    @PostMapping("/deleteDictByIds")
    Integer deleteDictByIds(@RequestBody List<Object> ids);

    /**
     * 字典信息分页条件查询
     *
     * @param dict
     * @param pageNo
     * @param pageSize
     * @return
     */
    @PostMapping("/selectDictByField")
    PageInfo selectDictByField(@RequestBody Dict dict, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);


    /**
     * @return com.aaa.six.base.ResultData
     * @throws
     * @author lwq
     * @description 添加登录日志信息
     * @param: [map]
     * @date 2020/5/28
     **/
    @PostMapping("/addLoginLog")
    ResultData addLoginLog(@RequestBody Map map);


    /**
     * @return java.util.List<com.aaa.six.model.MappingUnit>
     * @throws
     * @author lwq
     * @description 系统主页-测绘单位
     * 查询测绘单位名称
     * @param: [unitName, ownedDistrict, qualificationLevel]
     * @date 2020/6/1
     **/
    @PostMapping("/fuzzyUnitName")
    List<MappingUnit> fuzzyUnitName(@RequestParam("unitName") String unitName,
                                    @RequestParam("ownedDistrict") String ownedDistrict,
                                    @RequestParam("qualificationLevel") String qualificationLevel);

    /**
     * @return com.aaa.six.model.MappingUnit
     * @throws
     * @author lwq
     * @description 系统主页-测绘单位
     * 查询测绘单位基本信息
     * @param: [id]
     * @date 2020/6/1
     **/
    @PostMapping("/selectUnitInfoById")
    MappingUnit selectUnitInfoById(@RequestParam("id") Long id);

    /**
     * @author lwq
     * @description
     *    获取单位负责人信息
     * @param: [userId]
     * @date 2020/6/1
     * @return java.util.List<com.aaa.six.model.Principal>
     * @throws
     **/
    @PostMapping("/selectPrincipalInfo")
    List<Principal> selectPrincipalInfo(@RequestParam("userId") Long userId);


    /**
     * @author lwq
     * @description
     *    新增单位负责人
     * @param: [principal]
     * @date 2020/6/1
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/addPrincipal")
    Boolean addPrincipal(@RequestBody Principal principal);
    /**
     * @author lwq
     * @description
     *    删除单位负责人信息
     * @param: [principal]
     * @date 2020/6/1
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/deletePrincipal")
    Boolean deletePrincipal(@RequestBody Principal principal);

    /**
     * @author lwq
     * @description
     *    修改单位负责人信息
     * @param: [principal]
     * @date 2020/6/1
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/updatePrincipal")
    Boolean updatePrincipal(@RequestBody Principal principal);

    /**
     * @author lwq
     * @description
     *    根据userId查询技术员信息
     * @param: [userId]
     * @date 2020/6/1
     * @return java.util.List<com.aaa.six.model.Technicist>
     * @throws
     **/
    @PostMapping("/selectTechnicistInfo")
    List<Technicist> selectTechnicistInfo(@RequestParam("userId") Long userId);
    /**
     * @author lwq
     * @description
     *    新增技术员
     * @param: [technicist]
     * @date 2020/6/1
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/addTechnicist")
    Boolean addTechnicist(@RequestBody Technicist technicist);

    /**
     * @author lwq
     * @description
     *    删除技术员信息
     * @param: [technicist]
     * @date 2020/6/1
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/deleteTechnicist")
    Boolean deleteTechnicist(@RequestBody Technicist technicist);
    /**
     * @author lwq
     * @description
     *    修改单位负责人信息
     * @param: [principal]
     * @date 2020/6/1
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/updateTechnicist")
    Boolean updateTechnicist(@RequestBody Technicist technicist);
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


    /**
     * @author lwq
     * @description
     *    根据userid查询特殊岗位人员信息
     * @param: [userId]
     * @date 2020/6/2
     * @return java.util.List<com.aaa.six.model.SpecialPost>
     * @throws
     **/
    @PostMapping("/selectSpecialPostInfo")
    List<SpecialPost> selectSpecialPostInfo(@RequestParam("userId") Long userId);
    /**
     * @author lwq
     * @description
     *    新增特殊岗位人员信息
     * @param: [specialPost]
     * @date 2020/6/2
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/addSpecialPost")
    Boolean addSpecialPost(@RequestBody SpecialPost specialPost);

    /**
     * @author lwq
     * @description
     *    删除特殊岗位人员信息
     * @param: [specialPost]
     * @date 2020/6/2
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/deleteSpecialPost")
    Boolean deleteSpecialPost(@RequestBody SpecialPost specialPost);
    /**
     * @author lwq
     * @description
     *    修改特殊岗位人员信息
     * @param: [specialPost]
     * @date 2020/6/2
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/updateSpecialPost")
    Boolean updateSpecialPost(@RequestBody SpecialPost specialPost);
}