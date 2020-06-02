package com.aaa.six.mapper;

import com.aaa.six.model.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {

    /**
     *   分页条件查询
     * @param user
     * @return
     */
    List<User> selectUserByField(User user);


    /**
     * 根据性别查询
     * @param ssex
     * @return
     */


    List<User> selectUserBySsex(String ssex);

    /**
     * 根据状态查询用户
     * @param status
     * @return
     */

    List<User> selectUserBySta(String status);
}