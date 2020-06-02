package com.aaa.six.service;

import com.aaa.six.base.BaseService;
import com.aaa.six.mapper.ResourceMapper;
import com.aaa.six.model.Resource;
import com.aaa.six.utils.DateUtils;
import com.aaa.six.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Date;


/**
 * @ProjectName: mapping-qy108
 * @Package: com.aaa.six.service
 * @ClassName: ResourceService
 * @Author: lifuju
 * @Description:
 * @Date: 2020/5/26 21:34
 * @Version: 1.0
 */
@Service
public class ResourceService extends BaseService<Resource> {

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private FtpService ftpService;



    /**
     *@Description:
     * 上传文件并新增路径到数据库
     *@Param :  [resource]
     *@MethodName: addResource
     *@Author: lifuju
     *@Date: 2020/5/26 22:09
     *@Return: java.lang.Integer
     */
    public Integer addResource(Resource resource){
            return resourceMapper.insert(resource);

    }
    /**
     *@Description: TODO
     *@Param :  [id, localPath]
     * @param id 获取下载文件的id
     *@MethodName: downloadFtpFileAndSelectOne
     *@Author: lifuju
     *@Date: 2020/5/28 17:54
     *@Return: java.lang.Integer
     */

    public Resource selectOneResource(Long id){
        return resourceMapper.selectByPrimaryKey(id);
    }



}
