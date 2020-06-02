package com.aaa.six.controller;

import com.aaa.six.IQYService;
import com.aaa.six.base.BaseController;
import com.aaa.six.base.ResultData;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ProjectName: mapping-qy108
 * @Package: com.aaa.six.controller
 * @ClassName: FtpController
 * @Author: lifuju
 * @Description:
 * @Date: 2020/5/29 15:43
 * @Version: 1.0
 */
@RestController
public class FtpController extends BaseController{
    @Autowired
    private IQYService qyService;

    @PostMapping("/upload")
    @ApiOperation(value = "实现文件上传", notes = "单文件上传接口")
    public ResultData uploadFile(@RequestBody MultipartFile file,@RequestParam("rfBizType") String rfBizType,@RequestParam("refBizId") Long refBizId,@RequestParam("memo") String memo) {
        Boolean bool = qyService.uploadFile(file,rfBizType,refBizId,memo);
        if (bool){
            return selectSuccess("上传成功");
        }
        return selectFailed("上传失败");
    }

    @PostMapping("/download")
    @ApiOperation(value = "实现文件下载", notes = "文件下载接口")
    public ResultData download(@RequestParam("localPath") String localPath, @RequestParam("id") Long id){
        Boolean bool = qyService.download(localPath, id);
        if (bool){
            return selectSuccess("下载成功");
        }
        return selectFailed("下载失败");
    }
}
