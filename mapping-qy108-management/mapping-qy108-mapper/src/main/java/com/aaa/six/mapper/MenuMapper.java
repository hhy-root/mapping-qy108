package com.aaa.six.mapper;

import com.aaa.six.model.Menu;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MenuMapper extends Mapper<Menu> {

    /**
     * @author hhy
     * @description
     *    查询父级权限
     * @param: []
     * @date 2020/5/22 22:58
     * @return java.util.List<com.aaa.six.model.Menu>
     * @throws
     */
    List<Menu> selectMenu(Long parentId);
}