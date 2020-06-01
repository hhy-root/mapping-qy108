package com.aaa.six.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_dict")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Dict implements Serializable {
    /**
     * 字典ID
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 键
     */
    @Column(name = "KEYY")
    private Long keyy;

    /**
     * 值
     */
    @Column(name = "VALUEE")
    private String valuee;

    /**
     * 字段名称
     */
    @Column(name = "FIELD_NAME")
    private String fieldName;

    /**
     * 表名
     */
    @Column(name = "TABLE_NAME")
    private String tableName;


}