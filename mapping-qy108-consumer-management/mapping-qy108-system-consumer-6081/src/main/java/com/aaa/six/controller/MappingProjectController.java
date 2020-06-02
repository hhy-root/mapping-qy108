package com.aaa.six.controller;

import com.aaa.six.service.IQYMappingProjectService;
import com.aaa.six.base.BaseController;
import com.aaa.six.base.ResultData;
import com.aaa.six.model.MappingProject;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ProjectName: mapping-qy108
 * @Package: com.aaa.six.controller
 * @ClassName: MappingProjectController
 * @Author: lifuju
 * @Description:
 * @Date: 2020/5/21 11:42
 * @Version: 1.0
 */
@RestController
@Api(value = "项目信息", tags = "项目管理板块")
public class MappingProjectController extends BaseController{
    @Autowired
    private IQYMappingProjectService qyService;


    /**
     *@Description: TODO
     * 项目管理 新增方法 单个新增
     *@Param :  [mappingProject]
     *@MethodName: add
     *@Author: lifuju
     *@Date: 2020/5/22 15:48
     *@Return: com.aaa.six.base.ResultData
     */
    @PostMapping("/addMappingProject")
    @ApiOperation(value = "项目管理", notes = "新增一条数据")
    public ResultData add(@RequestBody MappingProject mappingProject){
        Integer i = qyService.add(mappingProject);
        if (i!= null && i!=0) {
            return insertSuccess();
        }
        return insertFailed();
    }
    /**
     *@Description: TODO
     *项目管理 删除方法  单个删除
     *@Param :  [id]
     *@MethodName: delMappingProject
     *@Author: lifuju
     *@Date: 2020/5/22 21:47
     *@Return: com.aaa.six.base.ResultData
     */
    @GetMapping("/delMappingProject/{id}")
    @ApiOperation(value = "项目管理", notes = "删除一条数据")
    public ResultData delMappingProject(@PathVariable("id")Long id){
        Integer integer = qyService.delMappingProject(id);
        if (integer!= null && integer!=0) {
            return deleteSuccess();
        }
        return deleteFailed();
    }

    /**
     *@Description: TODO
     * 项目管理 删除方法 批量删除
     *@Param :  [ids]
     *@MethodName: delBatch
     *@Author: lifuju
     *@Date: 2020/5/24 8:57
     *@Return: com.aaa.six.base.ResultData
     */
    @PostMapping("/deleteBatchMappingProject")
    @ApiOperation(value = "项目管理", notes = "批量删除数据")
    public ResultData delBatch(@RequestBody List<Object> ids) throws  Exception{
        Integer integer = qyService.delBatch(ids);
        if (integer>0) {
            return deleteSuccess();
        }
        return deleteFailed();
    }

    /**
     *@Description: TODO
     * 项目管理 查询方法 单个查询
     *@Param :  [id]
     *@MethodName: selectOne
     *@Author: lifuju
     *@Date: 2020/5/24 8:58
     *@Return: com.aaa.six.base.ResultData
     */

    @GetMapping("/selectOne")
    @ApiOperation(value = "项目管理", notes = "查询一条数据")
    public ResultData selectOne(@RequestParam("id")Long id){

        MappingProject mappingProject = qyService.selectOne(id);
        if (null != mappingProject&& !"".equals(mappingProject)) {
            return selectSuccess(mappingProject);
        }
        return selectFailed();
    }

    /**
     *@Description: TODO
     * 项目管理 更新方法 单个更新
     *@Param :  [mappingProject]
     *@MethodName: update
     *@Author: lifuju
     *@Date: 2020/5/24 8:58
     *@Return: com.aaa.six.base.ResultData
     */

    @PostMapping("/updateMappingProject")
    @ApiOperation(value = "项目管理", notes = "更新一条数据")
    ResultData update(@RequestBody MappingProject mappingProject){
        Integer integer = qyService.update(mappingProject);
        if (integer!=null && integer!=0){
            return updateSuccess();
        }
        return updateFailed();
    }

    /**
     *@Description: TODO
     * 项目管理 查询方法 分页查询
     *@Param :  [pageNo, pageSize]
     *@MethodName: queryListByPage
     *@Author: lifuju
     *@Date: 2020/5/24 8:59
     *@Return: com.aaa.six.base.ResultData
     */
    @PostMapping("/mappingProjectByPage")
    @ApiOperation(value = "项目管理", notes = "项目管理分页查询")
    public ResultData queryListByPage(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
        PageInfo mappingProjectPageInfo = qyService.queryListByPage(pageNo, pageSize);
        if (null!=mappingProjectPageInfo) {
            return selectSuccess(mappingProjectPageInfo);
        }
        return selectFailed();

    }



}
