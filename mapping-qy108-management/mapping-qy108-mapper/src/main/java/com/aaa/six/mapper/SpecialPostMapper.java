package com.aaa.six.mapper;

import com.aaa.six.model.SpecialPost;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SpecialPostMapper extends Mapper<SpecialPost> {
    
    /**
     * @author lwq 
     * @description
     *    根据userid查询特殊岗位人员信息
     * @param: [userId]
     * @date 2020/6/2
     * @return java.util.List<com.aaa.six.model.SpecialPost>
     * @throws 
     **/
    List<SpecialPost> selectSpecialPost(Long userId);
}