package com.aaa.six.service;

import com.aaa.six.base.BaseService;
import com.aaa.six.mapper.SpecialPostMapper;
import com.aaa.six.model.SpecialPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.aaa.six.staticstatus.TimeProperties.TIME_TYPE;

/**
 * @program: mapping-qy108
 * @author: lwq
 * @create: 2020-06-01 23:38
 * @description:
 *      特殊岗位人员信息管理
 **/
@Service
public class SpecialPostService extends BaseService<SpecialPost> {

    @Autowired
    private SpecialPostMapper specialPostMapper;

    /**
     * @author lwq 
     * @description
     *    根据userid查询特殊岗位人员信息
     * @param: [userId]
     * @date 2020/6/2
     * @return java.util.List<com.aaa.six.model.SpecialPost>
     * @throws 
     **/
    public List<SpecialPost> selectSpecialPostInfo(Long userId){
        //获取信息
        List<SpecialPost> specialPosts = specialPostMapper.selectSpecialPost(userId);
        //判断负责人的信息是否为空
        if (specialPosts.size()>0){
            //不为空就返回信息
            return specialPosts;
        }
        return null;
    }

    /**
     * @author lwq 
     * @description
     *    新增特殊岗位人员信息
     * @param: [specialPost]
     * @date 2020/6/2
     * @return java.lang.Boolean
     * @throws 
     **/
    public Boolean addSpecialPost(SpecialPost specialPost){
        if (null == specialPost && "".equals(specialPost)){
            return false;
        }else {
            //获取系统时间
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_TYPE);
            String format = simpleDateFormat.format(date);
            specialPost.setCreateTime(format);
            int insert = specialPostMapper.insert(specialPost);
            if (insert > 0){
                return true;
            } else{
                return false;
            }
        }
    }

    /**
     * @author lwq 
     * @description
     *    删除特殊岗位人员信息
     * @param: [specialPost]
     * @date 2020/6/2
     * @return boolean
     * @throws 
     **/
    public boolean deleteSpecialPost(SpecialPost specialPost) {
        if ("".equals(specialPost) && null == specialPost) {
            return false;
        } else {
            int i = specialPostMapper.deleteByPrimaryKey(specialPost);
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        }

    }
    /**
     * @author lwq 
     * @description
     *    修改特殊岗位人员信息
     * @param: [specialPost]
     * @date 2020/6/2
     * @return java.lang.Boolean
     * @throws 
     **/
    public Boolean updateSpecialPost(SpecialPost specialPost) {
        //判断user是否为空
        if ("".equals(specialPost) && null == specialPost) {
            return false;
        } else {
            //获取系统时间
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_TYPE);
            String format = simpleDateFormat.format(date);
            specialPost.setCreateTime(format);
            int i = specialPostMapper.updateByPrimaryKey(specialPost);
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        }

    }
}
