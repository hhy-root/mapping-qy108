package com.aaa.six.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Company AAA软件教育
 * @Title mapping-qy108
 * @Author hhy
 * @Version 0.1.0
 * @Date Create in 2020/5/12 23:06
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TokenVo implements Serializable {

    /**
     * 就是简单的token值
     */
    private String token;
    /**
     * 标识了方法是否执行成功
     */
    private Boolean ifSuccess;

    /**
     * 保存token的key值
     */
    private String redisKey;

}
