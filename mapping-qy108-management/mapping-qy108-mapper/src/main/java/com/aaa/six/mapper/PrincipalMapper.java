package com.aaa.six.mapper;

import com.aaa.six.model.Principal;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PrincipalMapper extends Mapper<Principal> {

    /**
     * 根据用户id查询单位负责人信息
     * @param userId
     * @return
     */
    List<Principal> selectPrincipalInfo(Long userId);
}