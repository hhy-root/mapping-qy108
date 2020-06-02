package com.aaa.six.mapper;

import com.aaa.six.model.Audit;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AuditMapper extends Mapper<Audit> {

    /**
     * 查询审核记录
     * @param id
     * @return
     */
    List<Audit> selectAuditLog(Long id);
}