package com.aaa.six.utils;

import com.aaa.six.properties.FTPProperties;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.net.SocketException;

/**
 * @Company AAA软件教育
 * @Title mapping-qy108
 * @Author hhy
 * @Version 0.1.0
 * @Date Create in 2020/5/15 21:49
 * @Description
 *      这个工具类就是文件上传的通用工具类
 *          按照咱们架构的原则，config就依赖了common
 *          这个时候假设把FTPUtils放到了common项目中，那么也就意味着所有的有关于ftp的jar包
 *          都必须要出现在common项目中，假设如果consumer项目使用到了common，那最终consumer
 *          也会把ftp的jar包引入到项目中，从而增大了项目的负担，无法实现职责单一化！！
 */
public class FTPUtils {


    private FTPUtils(){

    }

    static Logger log = LoggerFactory.getLogger(FTPUtils.class);

   /**
    *@Description: TODO
    *@Param :  [ftpHost, ftpUserName, ftpPassword, ftpPort]
    *  获取FTPClient对象
    *
    *      @param ftpHost     FTP主机服务器
    *      @param ftpPassword FTP 登录密码
    *      @param ftpUserName FTP登录用户名
    *      @param ftpPort     FTP端口 默认为21
    *@MethodName: getFTPClient
    *@Author: lifuju
    *@Date: 2020/6/2 16:38
    *@Return: org.apache.commons.net.ftp.FTPClient
    */

   public static FTPClient getFTPClient(String ftpHost, String ftpUserName,
                                         String ftpPassword, int ftpPort) {
        FTPClient ftpClient = null;
        try {
            ftpClient = new FTPClient();
            ftpClient.connect(ftpHost, ftpPort);
            // 连接FTP服务器
            ftpClient.login(ftpUserName, ftpPassword);
            // 登陆FTP服务器
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                System.out.println("未连接到FTP，用户名或密码错误。");
                ftpClient.disconnect();
            } else {
                System.out.println("FTP连接成功。");
            }
        } catch (SocketException e) {
            e.printStackTrace();
            System.out.println("FTP的IP地址可能错误，请正确配置。");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("FTP的端口错误,请正确配置。");
        }
        return ftpClient;
    }
    /**
     *@Description: TODO
     * 文件上传
     *@Param :  [host, port, username, password, filePath, basePath, fileName, input]
     *@MethodName: uploadFile
     *@Author: lifuju
     *@Date: 2020/6/2 16:39
     *@Return: java.lang.Boolean
     */
    public static Boolean uploadFile(String host, Integer port, String username, String password,
                                     String filePath, String basePath, String fileName, InputStream input){

        //1.创建临时路径（方便与后面我们需要上传文件的时候，检测日期文件夹是否存在，如果不存在需要进行创建）
        String temPath="";
        //2020-->2020/05--->2020/05/15(方便拼接这个文件夹目录)
        //2.创建FTPClient对象(这也就是FTP提供给java的API，可以实现连接FTP，登陆FTP，创建文件夹，实现文件上传和下载)
        FTPClient ftpClient = new FTPClient();

        try {
            int reply;
            ftpClient = getFTPClient(host, username, password, port);
            reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                return false;
            }
            //如果没有进入if，就说明已经连接和登陆成功了
            //7.那么需要开始判断目标上传路径是否存在(/home/ftp/2020/05/15)
            //basePath +filePath---> /home/ftp/2020/05/15
            //changeWorkingDirectory():判断路径是否存在，如果存在返回true，如果不存在则返回false
            if(!ftpClient.changeWorkingDirectory(basePath+filePath)){
                // 说明路径不存在，需要进行创建文件夹
                // java中可是没有mkdir -p命令的 mkdir -p /home/ftp/2020/05/15/，只能一层一层创建
                // 8.分割filePath--->String[] ---> ["", "2020", "05", "15"]
                String[] dirs = filePath.split("/");
                // 9.把basePath(/home/ftp)赋值给临时路径(tmpPath)
                temPath =basePath;
                // 10.循环dirs数组
                for(String dir : dirs){
                    //严谨判断，判断dir一定不能为null
                    if(null == dir || "".equals(dir)) continue;
                    //跳过本次循环，进入下次循环
                    //11.拼接临时路径
                    temPath +="/"+ dir;
                    // :/home/ftp/2020
                    //12.再次检测确保该路径不存在
                    if(!ftpClient.changeWorkingDirectory(temPath)){
                        //13.创建文件夹
                        //makeDirectory()--->就是创建文件夹的方法，返回为boolean
                        if(!ftpClient.makeDirectory(temPath)){
                            return false;
                        }else{
                            System.out.println(ftpClient.changeWorkingDirectory(temPath));
                        }
                    }

                }

            }
            //14.如果if没有进入，就说名文件夹已经存在，可以直接上传
            //ftp是支持IO的，但是IO性能很低，所以可以使用二进制流的形式进行上传
            //把文件转换成二进制字符流的模式来进行上传
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            //15.这才是真正的上传方法
            //storeFile():就是文件上传的方法，返回true/false
            if(!ftpClient.storeFile(fileName,input)){
                return false;
            }
            //16.关闭输入流
            input.close();
            //17.退出ftp
            ftpClient.logout();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true ;
    }

    /**
     *@Description: TODO
     * 从FTP服务器下载文件
     *@Param :  [ftpHost, ftpUserName, ftpPassword, ftpPort, ftpPath, localPath, fileName]
     * @param ftpHost FTP IP地址
     * @param ftpUserName FTP 用户名
     * @param ftpPassword FTP用户名密码
     * @param ftpPort FTP端口
     * @param ftpPath FTP服务器中文件所在路径 格式： ftptest/aa
     * @param localPath 下载到本地的位置 格式：H:/download
     * @param fileName 文件名称
     *@MethodName: downloadFtpFile
     *@Author: lifuju
     *@Date: 2020/6/2 16:38
     *@Return: boolean
     */

    public static boolean downloadFtpFile(String ftpHost, String ftpUserName,
                                          String ftpPassword, int ftpPort, String ftpPath, String localPath,
                                          String fileName) {

        FTPClient ftpClient = null;

        try {
            ftpClient = getFTPClient(ftpHost, ftpUserName, ftpPassword, ftpPort);
            ftpClient.setControlEncoding("UTF-8");
            // 中文支持
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(ftpPath);
            File localFile = new File(localPath, fileName);
            OutputStream os = new FileOutputStream(localFile);
            ftpClient.retrieveFile(fileName, os);
            os.close();
            ftpClient.logout();
        } catch (FileNotFoundException e) {
            log.info("没有找到" + ftpPath + "文件");
            e.printStackTrace();
            return false;
        } catch (SocketException e) {
            log.info("连接FTP失败.");
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            log.info("文件读取错误。");
            e.printStackTrace();
            return false;
        }
        return true;
    }
   /**
    *@Description: TODO
    * 方法描述：检验指定路径的文件是否存在ftp服务器中
    *@param filePath 指定绝对路径的文件/home/ftp/。。。
    *@Param :  [ftpHost, ftpUserName, ftpPassword, ftpPort, fileName, filePath]
    *@MethodName: isFTPFileExist
    *@Author: lifuju
    *@Date: 2020/6/2 16:41
    *@Return: boolean 存在返回true，不存在返回false
    */

   public static boolean isFTPFileExist(String ftpHost, String ftpUserName,
                                         String ftpPassword, int ftpPort, String fileName, String filePath) {

        FTPClient ftpClient = null;
        try {
            ftpClient = getFTPClient(ftpHost, ftpUserName, ftpPassword, ftpPort);
            ftpClient.setControlEncoding("UTF-8");
            // 中文支持
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            // 进入文件所在目录，注意编码格式，以能够正确识别中文目录
            ftpClient.changeWorkingDirectory(new String(filePath.getBytes("UTF-8"),
                    FTP.DEFAULT_CONTROL_ENCODING));
            // 检验文件是否存在
            InputStream is = ftpClient.retrieveFileStream(new String(fileName
                    .getBytes("UTF-8"), FTP.DEFAULT_CONTROL_ENCODING));
            if (is == null || ftpClient.getReplyCode() == FTPReply.FILE_UNAVAILABLE) {
                log.info("文件" + filePath + "/" + fileName + "不存在");
                return false;
            }
            log.info("文件" + filePath + "/" + fileName + "存在");
            if (is != null) {
                is.close();
                ftpClient.completePendingCommand();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (ftpClient != null) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }

}
