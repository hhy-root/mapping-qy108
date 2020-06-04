package com.aaa.six.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MappingProjectTypeNum {
    private String projectType;
    private Integer status;
    private Integer num;
}
