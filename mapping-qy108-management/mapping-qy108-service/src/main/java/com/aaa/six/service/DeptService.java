package com.aaa.six.service;

import com.aaa.six.base.BaseService;
import com.aaa.six.mapper.DeptMapper;
import com.aaa.six.model.Dept;
import com.aaa.six.redis.RedisService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
* @Description: 部门service
* @Author:        ly
* @CreateDate:    2020/5/20
* @UpdateUser:    修改人：
* @UpdateDate:    2020/5/20
* @UpdateRemark:  更新备注
* @Version:        1.0

*/
@Service
public class DeptService extends BaseService<Dept> {
    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private RedisService redisService;


    /**
     * @Author: ly
     * @description:
     *      查询所有的部门
     * @date: 2020/5/20
     * @param pid
     * @param pageNo
     * @param pageSize
     * @return: com.github.pagehelper.PageInfo
     *
     */
    public PageInfo getAllDept(Integer pid, Integer pageNo, Integer pageSize){
        PageHelper.startPage(pageNo,pageSize);
        //把pid Integer类型转换为long类型
        long id = pid.longValue();//此id为父id

        List<Dept> allDept = deptMapper.getAllDept(id);//根据父id查询父部门
        //判断
        if(allDept.size()>0 && null!=allDept){
            //遍历
            for(Dept dept : allDept){
                Long id1 = dept.getId();//得到父部门的id 作为子部门的父id查询
                List<Dept> children = deptMapper.getAllDept(id1);
                //得到的结果放到children中
                dept.setChildren(children);
            }

            PageInfo<Dept> deptPageInfo = new PageInfo<>(allDept);

            return deptPageInfo;
        }

        return null;
    }


    /**
     * @Author: ly
     * @description:
     *      根据主键查询部门信息
     * @date: 2020/5/21
     * @param id
     * @return: java.util.List<com.aaa.six.model.Dept>
     *
     */
     public Dept getDeptByID(Integer id){
         if(null == id || "".equals(id) ){
             return null;
         }

         try {
             Dept dept = deptMapper.selectByPrimaryKey(id);
             if(null!=dept && !"".equals(dept)){
                 return dept;
             }
         } catch (Exception e){
             e.printStackTrace();
         }
         return null;
     }


    /**
     * @Author: ly
     * @description:
     *      修改部门信息
     * @date: 2020/5/22
     * @param dept
     * @return: java.lang.Integer
     *
     */
    public Integer updateDept(Dept dept)  {

        if(null == dept || "".equals(dept)){
            return null;
        }

        //获取修改时间
        String currentTime = getCurrentTime();


//        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
//        String modifyTime = dateFormat.format(new Date());
        dept.setModifyTime(currentTime);
        try {
            Integer i = super.update(dept);
            if(i>0){
                return i;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * @Author: ly
     * @description:
     *      通过主键批量删除
     * @date: 2020/5/23
     * @param ids
     * @return: java.lang.Integer
     *
     */

    public Integer delByIds(List<Object> ids){
        if(ids.size() == 0 ){
            return null;
        }


        try {
            Integer i = super.delete(ids);
            if (i>0){
                return i;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @Author: ly
     * @description:
     *
     *  添加新的部门
     * @date: 2020/5/23
     * @param dept
     * @return: java.lang.Integer
     *
     */
    public Integer addDept(Dept dept){
        if(null == dept || "".equals(dept)){
            return null;
        }
        //获取时间
        String currentTime = getCurrentTime();

//        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
//        String dateTime = dateFormat.format(new Date());

        dept.setModifyTime(currentTime);
        dept.setCreateTime(currentTime);

        //判断是否有父id
        if(null != dept.getParentId()){
            //有父id直接添加
            try {
                Integer i = super.add(dept);
                if(i>0){
                    return i;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //没有父id 说明为最初的父部门 父id为0
            Integer pid = 0;
            dept.setParentId(pid.longValue());
            try {
                Integer i = super.add(dept);
                if(i>0){
                    return i;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
