package com.aaa.six.service;

import com.aaa.six.base.BaseModel;
import com.aaa.six.base.BaseService;
import com.aaa.six.mapper.MappingProjectMapper;
import com.aaa.six.model.MappingProject;
import com.aaa.six.utils.DateUtils;
import com.aaa.six.utils.IDUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName: mapping-qy108
 * @Package: com.aaa.six.service
 * @ClassName: MappingProjectService
 * @Author: lifuju
 * @Description:
 * @Date: 2020/5/20 21:57
 * @Version: 1.0
 */
@Service
public class MappingProjectService extends BaseService<MappingProject> {

    @Autowired
    private MappingProjectMapper mappingProjectMapper;



    /**
     *@Description: TODO
     * 项目管理 新增方法 单个新增
     *@Param :  [mappingProject]
     *@MethodName: add
     *@Author: lifuju
     *@Date: 2020/5/21 14:12
     *@Return: java.lang.Integer
     */
    @Override
    public Integer add(MappingProject mappingProject){
        mappingProject.setId(IDUtils.genUniqueKey());
        mappingProject.setCreateTime(DateUtils.getCurrentDate());
        try {
            return super.add(mappingProject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *@Description: TODO
     * 项目管理 删除方法  单个删除
     *@Param :  [mappingProject]
     *@MethodName: delete
     *@Author: lifuju
     *@Date: 2020/5/21 14:08
     *@Return: java.lang.Integer
     */
    @Override
    public Integer delete(Long id){
        try {
            return super.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *@Description: TODO
     * 项目管理 删除方法 批量删除
     *@Param :  [ids]
     *@MethodName: delete
     *@Author: lifuju
     *@Date: 2020/5/21 14:16
     *@Return: java.lang.Integer
     */

    public Integer delete(List<Object> ids){
        if (ids == null) {
            return null;
        }
        try {
            return super.delete(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *@Description: TODO
     * 项目管理 更新方法 单个更新
     *@Param :  [mappingProject]
     *@MethodName: update
     *@Author: lifuju
     *@Date: 2020/5/21 14:18
     *@Return: java.lang.Integer
     */

    @Override
    public Integer update(MappingProject mappingProject){
        mappingProject.setCreateTime(DateUtils.getCurrentDate());
        try {
            return super.update(mappingProject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     *@Description: TODO
     * 查询一条数据
     *@Param :  [mappingProject]
     *@MethodName: queryOne
     *@Author: lifuju
     *@Date: 2020/5/21 14:20
     *@Return: com.aaa.six.model.MappingProject
     */
    public MappingProject getOne(Long id){
        MappingProject mappingProject = mappingProjectMapper.selectByPrimaryKey(id);
        if(mappingProject!=null){
            return mappingProject;
        }
        return null;
    }
    /**
     *@Description: TODO
     * 项目管理 更新方法 批量更新
     *@Param :  [mappingProject, ids]
     *@MethodName: batchUpdate
     *@Author: lifuju
     *@Date: 2020/5/21 14:18
     *@Return: java.lang.Integer
     */
    @Override
    public Integer batchUpdate(MappingProject mappingProject, Object[] ids){
        mappingProject.setModifyTime(DateUtils.getCurrentDate());
        try {
            return super.batchUpdate(mappingProject, ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *@Description: TODO
     * 分页查询
     *@Param :  [mappingProject, pageNo, pageSize]
     *@MethodName: queryListByPage
     *@Author: lifuju
     *@Date: 2020/5/21 13:52
     *@Return: com.github.pagehelper.PageInfo<com.aaa.six.model.MappingProject>
     */
    @Override
    public PageInfo<MappingProject> queryListByPage(Integer pageNo, Integer pageSize){
        try {
            return super.queryListByPage(pageNo, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     *@Description: TODO
     * 条件分页
     *@Param :  [pageNo, pageSize, mappingProject]
     *@MethodName: selectPageFiled
     *@Author: lifuju
     *@Date: 2020/6/1 19:23
     *@Return: com.github.pagehelper.PageInfo<com.aaa.six.model.MappingProject>
     */

    public PageInfo<MappingProject> selectPageFiled(Integer pageNo, Integer pageSize,MappingProject mappingProject) {
        PageHelper.startPage(pageNo, pageSize);
        List<MappingProject> mappingProjects = mappingProjectMapper.select(mappingProject);
        if (!"".equals(mappingProjects) && null != mappingProjects) {
            PageInfo<MappingProject> pageInfo = new PageInfo<MappingProject>(mappingProjects);
            return pageInfo;
        }
        return null;
    }

}
