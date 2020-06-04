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
     *@Description:
     *      ftp文件上传
     *      这个时候如果你自己测试过，你会发现file是无论如何都无法发送到provider项目中
     *      因为feign默认只能发送普通类型(java8种基本类型，封装类型，集合...)
     *      最终这些普通类型都可以转换为二进制流的形式在http之间传输，但是文件类型不行，
     *      因为文件类型只能转换为字节流/字符流
     *      也就是说，最终我可以让PostMapping去接收Multipart/form-data类型
     *      让feign使用json的数据格式来进行接收
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
