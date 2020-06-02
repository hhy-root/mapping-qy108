package com.aaa.six.mapper;


import com.aaa.six.model.Menu;
import com.aaa.six.vo.MenuVo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface MenuMapper extends Mapper<Menu> {

    List<MenuVo> selectMenuByParentId(Object parentId);
    List<Menu> selectMenuByField(Map map);
}