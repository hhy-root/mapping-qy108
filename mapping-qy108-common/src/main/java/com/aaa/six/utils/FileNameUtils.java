package com.aaa.six.utils;

import java.util.Random;

/**
 * @Company AAA软件教育
 * @Title mapping-qy108
 * @Author hhy
 * @Version 0.1.0
 * @Date Create in 2020/5/31 10:03
 * @Description
 */
public class FileNameUtils {

    private FileNameUtils(){

    }

    /**
     * @author hhy
     * @description
     *     文件名的生成方法
     * @param: []
     * @date 2020/5/31 14:43
     * @return java.lang.String
     * @throws 
     */
    public static String getFileName() {
        // 1.获取系统当前时间的毫秒数
        Long timeMillis = System.currentTimeMillis();
        // 2.创建Random对象
        Random random = new Random();
        // 3.做一个随机数，随机区间是0-999之间随机
        Integer randomNum = random.nextInt(999);
        System.out.println(randomNum);
        // 4.生成最终的文件名(当前系统时间的毫秒数+随机数 来实现)
        /**
         * %:占位符
         * d:数字
         * 03:三位数，如果不够三位自动向前补0
         */
        String fileName = timeMillis + String.format("%03d", randomNum);
        System.out.println(fileName);
        // 5.返回文件名称
        return fileName;
    }

    public static void main(String[] args) {
        FileNameUtils.getFileName();
    }

}
