package com.aaa.six.service;

import com.aaa.six.base.BaseService;
import com.aaa.six.mapper.AuditMapper;
import com.aaa.six.model.Audit;
import com.aaa.six.model.MappingProject;
import com.aaa.six.utils.DateUtils;
import com.aaa.six.utils.IDUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Company AAA软件教育
 * @Title mapping-qy108
 * @Author hhy
 * @Version 0.1.0
 * @Date Create in 2020/5/31 19:13
 * @Description
 */
@Service
public class AuditService extends BaseService<Audit> {

    @Autowired
    private AuditMapper auditMapper;

    /**
     * @author hhy
     * @description
     *    查询审核日志信息(分页)
     * @param: [id]
     * @date 2020/5/31 19:15
     * @return com.aaa.six.model.Audit
     * @throws
     */
    public PageInfo<Audit> selectAuditLog(Long id,Integer pageNo, Integer pageSize){
        if(null ==id || "".equals(id) ){
            return null;
        }
        PageHelper.startPage(pageNo, pageSize);
        try{
            List<Audit> audits = auditMapper.selectAuditLog(id);
            PageInfo<Audit> pageInfo = new PageInfo<>(audits);
            if(audits.size()>0){
                return pageInfo;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
            return null;
    }

   /**
    * @author hhy
    * @description
    *   设置唯一主键 审核信息添加
    * @param: [audit]
    * @date 2020/6/2 17:04
    * @return java.lang.Integer
    * @throws 
    */
    @Override
    public Integer add(Audit audit){
        audit.setId(IDUtils.genUniqueKey());
        try {
            return super.add(audit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

