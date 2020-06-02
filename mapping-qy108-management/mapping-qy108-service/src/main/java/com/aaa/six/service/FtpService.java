package com.aaa.six.service;

import com.aaa.six.model.Resource;
import com.aaa.six.properties.FTPProperties;
import com.aaa.six.utils.DateUtils;
import com.aaa.six.utils.FTPUtils;

import com.aaa.six.utils.FileNameUtils;
import com.aaa.six.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;
import java.util.Date;


/**
 * @ProjectName: mapping-qy108
 * @Package: com.aaa.six.service
 * @ClassName: ftpService
 * @Author: lifuju
 * @Description:
 * @Date: 2020/5/28 8:35
 * @Version: 1.0
 */
@Service
public class FtpService {

    @Autowired
    private FTPProperties ftpProperties;

    @Autowired
    private ResourceService resourceService;

    public Boolean upload(MultipartFile file,String rfBizType,Long refBizId,String memo){
        // 1.获取原始文件的名称(获取目的就是为了获取文件的后缀名)
        String oldFileName = file.getOriginalFilename();
        // 2.获取新的文件名(不带后缀)
        String newFileName = FileNameUtils.getFileName();
        // 3.获取到最终的文件名(新的带后缀的文件名)
        newFileName = newFileName + oldFileName.substring(oldFileName.lastIndexOf("."));

        String filePath=null;
        try {
            // 4.获取今天日期格式化后的数据(yyyy-MM-dd--->yyyy/MM/dd)
            filePath = DateUtils.formatDate(new Date(), "yyyy/MM/dd");
            Resource resource = new Resource();
            resource.setId(IDUtils.genUniqueKey());
            resource.setCreateTime(DateUtils.getCurrentDate());
            resource.setName(oldFileName);
            resource.setSize(file.getSize());
            resource.setPath(ftpProperties.getBasePath()+"/"+filePath+"/"+newFileName);
            resource.setType(oldFileName.substring(oldFileName.lastIndexOf(".")));
            resource.setExtName(oldFileName.substring(oldFileName.lastIndexOf(".")));
            resource.setRefBizType(rfBizType);
            resource.setRefBizId(refBizId);
            resource.setMemo(memo);
            resourceService.addResource(resource);
            return FTPUtils.uploadFile(ftpProperties.getHost(), ftpProperties.getPort(), ftpProperties.getUsername(),
                    ftpProperties.getPassword(), filePath, ftpProperties.getBasePath(), newFileName, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     *@Description: TODO
     *@Param :  [ftpPath, localPath, fileName]
     *
     *  @param localPath 下载到本地的位置 格式：H:/download
     * @param id resource表主键  通过他获取文件记录并下载文件
     *@MethodName: downloadFtpFile
     *@Author: lifuju
     *@Date: 2020/5/28 17:51
     *@Return: java.lang.Boolean
     */

    public Boolean download(String localPath,Long id) {
        Resource resource = resourceService.selectOneResource(id);
        String fileName = resource.getPath().substring(resource.getPath().lastIndexOf("/")+1);
        boolean ftpFileExist = FTPUtils.isFTPFileExist(ftpProperties.getHost(), ftpProperties.getUsername(), ftpProperties.getPassword(), ftpProperties.getPort(),
                fileName, resource.getPath());
        if (ftpFileExist) {
            return FTPUtils.downloadFtpFile(ftpProperties.getHost(), ftpProperties.getUsername(), ftpProperties.getPassword(), ftpProperties.getPort(),
                    resource.getPath(),localPath,fileName);
        }
       return false;
    }
}
