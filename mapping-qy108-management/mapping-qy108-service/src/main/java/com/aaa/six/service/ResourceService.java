package com.aaa.six.service;

import com.aaa.six.base.BaseService;
import com.aaa.six.mapper.ResourceMapper;
import com.aaa.six.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Company AAA软件教育
 * @Title mapping-qy108
 * @Author hhy
 * @Version 0.1.0
 * @Date Create in 2020/5/31 18:36
 * @Description
 */
@Service
public class ResourceService extends BaseService<Resource> {

    @Autowired
    private ResourceMapper resourceMapper;
}
