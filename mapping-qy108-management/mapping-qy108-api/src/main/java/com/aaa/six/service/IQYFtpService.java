package com.aaa.six.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ProjectName: mapping-qy108
 * @Package: com.aaa.six.service
 * @ClassName: IQYFtpService
 * @Author: lifuju
 * @Description:
 * @Date: 2020/6/2 21:25
 * @Version: 1.0
 */
@FeignClient("ftp-interface")
public interface IQYFtpService {

    /**
     *@Description: TODO
     * 文件上传
     *@Param :  [file, rfBizType, refBizId, memo]
     *@MethodName: uploadFile
     *@Author: lifuju
     *@Date: 2020/6/2 16:19
     *@Return: java.lang.Boolean
     */

    @PostMapping(value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean uploadFile(@RequestBody MultipartFile file, @RequestParam("rfBizType") String rfBizType, @RequestParam("refBizId") Long refBizId, @RequestParam("memo") String memo);

    /**
     *@Description: TODO
     * 文件下载
     *@Param :  [localPath, id]
     *@MethodName: downloadFile
     *@Author: lifuju
     *@Date: 2020/5/30 10:37
     *@Return: java.lang.Boolean
     */
    @PostMapping("/download")
    Boolean download(@RequestParam("localPath") String localPath, @RequestParam("id") Long id);
}
