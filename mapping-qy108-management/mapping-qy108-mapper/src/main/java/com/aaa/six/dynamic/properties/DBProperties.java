package com.aaa.six.dynamic.properties;

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
 * @Date Create in 2020/5/31 14:17
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DBProperties implements Serializable {

    private String pollName;// 称之为唯一标识的名字(这个标识就是来装载mysql,oracle,sqlserver)

    private String driverClassName;

    private String url;

    private String username;

    private String password;

    private String type;

}
