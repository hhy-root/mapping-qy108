package com.aaa.six.base;


import com.aaa.six.utils.Map2BeanUtils;
import com.aaa.six.utils.SpringContextUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @Company AAA软件教育
 * @Title mapping-qy108
 * @Author hhy
 * @Version 0.1.0
 * @Date Create in 2020/5/12 23:31
 * @Description
 *     通用service，这个泛型T指的是实体类
 *     也就是说传递什么样的实体类进来，最终注入的mapper就是什么类型
 *     都是通用mapper的方法
 */

public abstract class BaseService<T> {

    private Class<T> cache=null;//本地缓存池
    /**
     *为了保证安全性，使用private，他的子类（UserService）调用不到
     * 需要提供接口给子类用
     */
    @Autowired
    private Mapper<T> mapper;



    /**
     * @author hhy
     * @description
     *    通用mapper
     * @param: []
     * @date 2020/5/13 9:38
     * @return tk.mybatis.mapper.common.Mapper
     * @throws
     */
    protected Mapper getMapper(){
      return mapper;
    }

    /**
     *@Description: TODO
     * 获取当前时间  用于创建 和更改数据的记录
     *@Param :  []
     *@MethodName: getCurrentTime
     *@Author: lifuju
     *@Date: 2020/5/22 10:39
     *@Return: java.lang.String
     */
    protected String getCurrentTime(){
        return  new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss").format(new Date());
    }



    /**
     * @author hhy
     * @description
     *    新增方法
     * @param: [t]
     * @date 2020/5/13 9:40
     * @return java.lang.Integer
     * @throws
     */
    public Integer add(T t) throws Exception{
        return mapper.insertSelective(t);
    }

    /**
     * @author hhy
     * @description
     *    通过主键删除
     * @param: [t]
     * @date 2020/5/13 9:41
     * @return java.lang.Integer
     * @throws
     */
    public Integer delete(T t) throws Exception{
        return mapper.deleteByPrimaryKey(t);
    }

    public Integer delete(Long id) throws Exception{
        return mapper.deleteByPrimaryKey(id);
    }

    /**
     * @author hhy
     * @description
     *    通过主键批量删除
     *    能用java代码搞定的东西，千万不要上子查询
     *    阿里巴巴代码规约：
     *        如果联查超过两张表，要把联查拆开，放在java代码实现
     *        
     * @param: [ids]
     * @date 2020/5/13 9:42
     * @return java.lang.Integer
     * @throws
     */
    public Integer delete(List<Object> ids) throws Exception{
        Example example = Example.builder(getTypeArguement()).where(Sqls.custom().andIn("id",ids)).build();
        return mapper.deleteByExample(example);
    }

    /**
     * @author hhy
     * @description
     *   更新功能
     * @param: [t]
     * @date 2020/5/13 9:59
     * @return java.lang.Integer
     * @throws
     */
    public Integer update(T t) throws Exception{
        return mapper.updateByPrimaryKeySelective(t);
    }

    /**
     * @author hhy
     * @description
     *    批量更新
     *    t:所要更新的数据(t代表实体类，只能存放一个id)
     *    ids:通用主键来进行更新
     * @param: [t, ids]
     * @date 2020/5/13 10:06
     * @return java.lang.Integer
     * @throws
     */
    public Integer batchUpdate(T t,Object[] ids) throws Exception{
        Example example =Example.builder(getTypeArguement()).where(Sqls.custom().andIn("id", Arrays.asList(ids))).build();
        return mapper.updateByExample(t,example);

    }

    /**
     * @author hhy
     * @description
     *    查询一条数据
     * @param: [t]
     * @date 2020/5/13 10:09
     * @return T
     * @throws 
     */
    public T queryOne(T t) throws Exception{
        return mapper.selectOne(t);
        
    }

    /**
     *@Description: TODO
     * 查询一条
     *@Param :  [id]
     *@MethodName: queryOne
     *@Author: lifuju
     *@Date: 2020/5/23 9:43
     *@Return: T
     */
    public T queryOne(Long id) throws Exception{
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * @author hhy
     * @description
     *    封装条件查询，分页查询以及排序查询的通用方法(多条件查询)
     * @param: [where, orderByField, field]
     * @date 2020/5/13 10:15
     * @return T
     * @throws 
     */
    public T queryByField(Sqls where,String orderByField,String... field) throws Exception{
        return (T) queryByFieldsBase(null,null,where,orderByField,field).get(0);
    }

    /**
     * @author hhy
     * @description
     *    条件查询集合
     * @param: [where, orderByField, field]
     * @date 2020/5/13 10:19
     * @return java.util.List<T>
     * @throws 
     */
    public List<T> queryListByFields(Sqls where,String orderByField,String... field) throws Exception{
        return queryByFieldsBase(null,null,where,orderByField,field);
    }

    /**
     * @author hhy
     * @description
     *    条件查询分页
     * @param: [pageNo, pageSize, where, orderByFileds, fields]
     * @date 2020/5/13 10:20
     * @return com.github.pagehelper.PageInfo<T>
     * @throws 
     */
    public PageInfo<T> queryListByPageAndFields(Integer pageNo, Integer pageSize, Sqls where, String orderByFileds, String... fields) throws Exception {
        return new PageInfo<T>(queryByFieldsBase(pageNo, pageSize, where, orderByFileds, fields));
    }

    /**
     * @author hhy
     * @description
     *    条件查询
     * @param: [t]
     * @date 2020/5/13 10:22
     * @return java.util.List<T>
     * @throws 
     */
    public List<T> queryList(T t) throws Exception {
        return mapper.select(t);
    }

    /**
     * @author hhy
     * @description
     *    分页查询
     * @param: [t, pageNo, pageSize]
     * @date 2020/5/13 10:13
     * @return com.github.pagehelper.PageInfo<T>
     * @throws
     */
    public PageInfo<T> queryListByPage(T t, Integer pageNo, Integer pageSize) throws Exception {
        PageHelper.startPage(pageNo, pageSize);
        List<T> select = mapper.select(t);
        PageInfo<T> pageInfo = new PageInfo<T>(select);
        return pageInfo;
    }

   /**
    *@Description: TODO
    * 分页查询
    *@Param :  [pageNo, pageSize]
    *@MethodName: queryListByPage
    *@Author: lifuju
    *@Date: 2020/5/22 15:08
    *@Return: com.github.pagehelper.PageInfo<T>
    */
   public PageInfo<T> queryListByPage(Integer pageNo, Integer pageSize) throws Exception {
        PageHelper.startPage(pageNo, pageSize);
        List<T> select = mapper.selectAll();
        PageInfo<T> pageInfo = new PageInfo<T>(select);
        return pageInfo;
    }

    /**
     * @author hhy
     * @description
     *    通过反射获取实例对象
     * @param: [map]
     * @date 2020/5/13 10:26
     * @return T
     * @throws 
     */
    public T newInstance(Map map) {
        return (T) Map2BeanUtils.map2Bean(map, getTypeArguement());
    }

    /**
     * @author hhy
     * @description
     *    通过反射获取实例对象的集合
     * @param: [list]
     * @date 2020/5/13 13:56
     * @return java.util.List
     * @throws
     */
    public List newInstance(List<Map> list) {
        return (List) Map2BeanUtils.map2Bean(list, getTypeArguement());
    }



    /**
     * @author hhy
     * @description
     *    获取子类泛型（获取所有参数类型）
     * @param: []
     * @date 2020/5/13 9:47
     * @return java.lang.Class<T>
     * @throws 
     */
    private Class<T> getTypeArguement(){
        if (null == cache){
            cache=(Class<T>) ((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments()[0];
        }
        return cache;
    }

    private List<T> queryByFieldsBase(Integer pageNo, Integer pageSize, Sqls where, String orderByField, String... field) {
        Example.Builder builder = null;
        if(null == field || field.length == 0) {
            // 没有条件查询，说明查询的是所有数据
            builder = Example.builder(getTypeArguement());
        } else {
            // 指定某些/某个字段进行查询
            builder = Example.builder(getTypeArguement())
                    .select(field);
        }
        if(null != where) {
            builder = builder.where(where);
        }

        if(null != orderByField) {
            builder = builder.orderByDesc(orderByField);
        }

        Example example = builder.build();

        if(null != pageNo && null != pageSize) { // pageHelper通用分页插件
            PageHelper.startPage(pageNo, pageSize);
        }

        List list = getMapper().selectByExample(example);
        return list;
    }

   /**
    * @author hhy
    * @description
    *    获取Spring容器
    *    暂时用不到（以后工作能用到）
    * @param: []
    * @date 2020/5/13 11:03
    * @return org.springframework.context.ApplicationContext
    * @throws
    */
    public ApplicationContext getApplicationContext(){
        return SpringContextUtils.getApplicationContext();
    }

}
