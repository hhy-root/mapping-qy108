package com.aaa.six.mapper;

import com.aaa.six.model.Technicist;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TechnicistMapper extends Mapper<Technicist> {

    /**
     * 根据userId查询
     * @param userId
     * @return
     */
    List<Technicist> selectTechnicist(Long userId);
}