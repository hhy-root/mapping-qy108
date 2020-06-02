package com.aaa.six.dynamic.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Company AAA软件教育
 * @Title mapping-qy108
 * @Author hhy
 * @Version 0.1.0
 * @Date Create in 2020/5/31 15:22
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PropertiesUtils implements Serializable {

    private String primary;

    // 所以必然需要一个属性值叫做dynamic
    private Map<String, DBProperties> dynamic = new LinkedHashMap<String, DBProperties>();

}

