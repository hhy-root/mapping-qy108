package com.aaa.six;

import com.aaa.six.base.ResultData;
import com.aaa.six.model.Dept;
import com.aaa.six.model.Dict;
import com.aaa.six.model.MappingProject;
import com.aaa.six.model.User;
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
     * @Author: ly
     * @description:
     *
     *      查询所有的部门
     * @date: 2020/5/22
     * @param pid
     * @param pageNo
     * @param pageSize
     * @return: com.github.pagehelper.PageInfo
     *
     */
    @PostMapping("/getAllDept")
    PageInfo getAllDept(@RequestParam("pid") Integer pid, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

    /**
     * @Author: ly
     * @description:
     *
     *      根据主键查找部门
     * @date: 2020/5/22
     * @param id
     * @return: com.aaa.six.model.Dept
     *
     */
    @GetMapping("/getDeptById")
    Dept getDeptById(@RequestParam("id")Integer id);

    /**
     * @Author: ly
     * @description:
     *
     *      按照调价查询部门
     * @date: 2020/5/22
     * @param dept
     * @return: java.util.List<com.aaa.six.model.Dept>
     *
     */
    @PostMapping("/getDeptByFileds")
    List<Dept> getDeptByFileds(@RequestBody Dept dept);

    /**
     * @Author: ly
     * @description:
     *
     *      更新部门
     * @date: 2020/5/22
     * @param dept
     * @return: java.lang.Integer
     *
     */
    @PostMapping("/updateDept")
    Integer updateDept(@RequestBody Dept dept);

    /**
     * @Author: ly
     * @description:
     *
     *      按照主键批量删除
     * @date: 2020/5/22
     * @param ids
     * @return: java.lang.Integer
     *
     */
    @PostMapping("/delByIds")
    Integer delByIds(@RequestBody List<Object> ids);

    /**
     * @Author: ly
     * @description:
     *
     *      增加部门
     * @date: 2020/5/22
     * @param dept
     * @return: java.lang.Integer
     *
     */
    @PostMapping("/addDept")
    Integer addDept(@RequestBody Dept dept);


    /**
     *@Description: TODO
     * 项目管理 新增方法 单个新增
     *@Param :  [mappingProject]
     *@MethodName: add
     *@Author: lifuju
     *@Date: 2020/5/24 8:51
     *@Return: java.lang.Integer
     */
    @PostMapping("/addMappingProject")
    Integer add(@RequestBody MappingProject mappingProject);
    /**
     *@Description: TODO
     * 项目管理 删除方法  单个删除
     *@Param :  [id]
     *@MethodName: delMappingProject
     *@Author: lifuju
     *@Date: 2020/5/24 8:52
     *@Return: java.lang.Integer
     */
    @GetMapping("/delMappingProject/{id}")
    Integer delMappingProject(@PathVariable("id") Long id);

    /**
     *@Description: TODO
     * 项目管理 删除方法 批量删除
     *@Param :  [ids]
     *@MethodName: delBatch
     *@Author: lifuju
     *@Date: 2020/5/24 8:52
     *@Return: java.lang.Integer
     */
    @PostMapping("/deleteBatchMappingProject")
    Integer delBatch(@RequestBody List<Object> ids);

    /**
     *@Description: TODO
     * 项目管理 更新方法 单个更新
     *@Param :  [id]
     *@MethodName: selectOne
     *@Author: lifuju
     *@Date: 2020/5/24 8:52
     *@Return: com.aaa.six.model.MappingProject
     */
    @GetMapping("lectOne")
    MappingProject selectOne(@RequestParam("id")Long id);

    /**
     *@Description: TODO
     * 项目管理 更新方法 单个更新
     *@Param :  [mappingProject]
     *@MethodName: update
     *@Author: lifuju
     *@Date: 2020/5/24 8:53
     *@Return: java.lang.Integer
     */
    @PostMapping("/updateMappingProject")
    Integer update(@RequestBody MappingProject mappingProject);

    /**
     *@Description: TODO
     * 项目管理 查询方法 分页查询
     *@Param :  [pageNo, pageSize]
     *@MethodName: queryListByPage
     *@Author: lifuju
     *@Date: 2020/5/24 8:53
     *@Return: com.github.pagehelper.PageInfo
     */
    @PostMapping("/mappingProjectByPage")
    PageInfo queryListByPage(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

    /**
     * @author lwq
     * @description
     *    分页查询用户信息
     * @param: [pageNo, pageSize]
     * @date 2020/5/27
     * @return com.github.pagehelper.PageInfo<com.aaa.six.model.User>
     * @throws
     **/
    @PostMapping("/selectUserInfo")
    PageInfo<User> selectUserInfo(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

    /**
     * @author lwq
     * @description
     *    根据id查询用户信息
     * @param: [user]
     * @date 2020/5/27
     * @return com.aaa.six.model.User
     * @throws
     **/
    @PostMapping("/selectInfoById")
    User selectInfoById(@RequestBody User user);

    /**
     * @author lwq
     * @description
     *    根据id删除用户
     * @param: [user]
     * @date 2020/5/27
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/deleteUserById")
    Boolean deleteUserById(@RequestBody User user);

    /**
     * @author lwq
     * @description
     *    新增用户信息
     * @param: [user]
     * @date 2020/5/27
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/addUser")
    Boolean addUser(@RequestBody User user);
    /**
     * @author lwq
     * @description
     *    修改用户信息
     * @param: [user]
     * @date 2020/5/27
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/updateUser")
    Boolean updateUser(@RequestBody User user);
    /**
     * @author lwq
     * @description
     *    批量删除用户
     * @param: [ids]
     * @date 2020/5/27
     * @return java.lang.Integer
     * @throws
     **/
    @PostMapping("/deleteUserByIds")
    Integer deleteUserByIds(@RequestBody List<Object> ids);

    /**
     * @author lwq
     * @description
     *    分页查询字典信息
     * @param: [pageNo, pageSize]
     * @date 2020/5/27
     * @return com.github.pagehelper.PageInfo<com.aaa.six.model.Dict>
     * @throws
     **/
    @PostMapping("/selectDictInfo")
    PageInfo<Dict> selectDictInfo(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

    /**
     * @author lwq
     * @description
     *    新增字典信息
     * @param: [dict]
     * @date 2020/5/27
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/addDict")
    Boolean addDict(@RequestBody Dict dict);

    /**
     * @author lwq
     * @description
     *    根据id查询字典信息
     * @param: [dict]
     * @date 2020/5/27
     * @return com.aaa.six.model.Dict
     * @throws
     **/
    @PostMapping("/selectDictById")
    Dict selectDictById(@RequestBody Dict dict);

    /**
     * @author lwq
     * @description
     *    修改字典信息
     * @param: [dict]
     * @date 2020/5/27
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/updateDict")
    Boolean updateDict(@RequestBody Dict dict);

    /**
     * @author lwq
     * @description
     *    根据id删除字典信息
     * @param: [dict]
     * @date 2020/5/27
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/deleteDictById")
    Boolean deleteDictById(@RequestBody Dict dict);

    /**
     * @author lwq
     * @description
     *    批量删除字典信息
     * @param: [ids]
     * @date 2020/5/27
     * @return java.lang.Integer
     * @throws
     **/
    @PostMapping("/deleteDictByIds")
    Integer deleteDictByIds(@RequestBody List<Object> ids);

    /**
     * @author lwq
     * @description
     *    添加登录日志信息
     * @param: [map]
     * @date 2020/5/28
     * @return com.aaa.six.base.ResultData
     * @throws
     **/
    @PostMapping("/addLoginLog")
    ResultData addLoginLog(@RequestBody Map map);
}
