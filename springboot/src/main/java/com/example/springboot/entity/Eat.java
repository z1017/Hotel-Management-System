package com.example.springboot.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description hotel
 * @date 2023-03-20
 */

@Data
public class Eat extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 日期
     */
    private String eatDate;

    /**
     * 负责人
     */
    private String head;

    /**
     * 分类
     */
    private String eatCategory;

    /**
     * 餐品编码
     */
    private String eatNo;

    /**
     * 图片
     */
    private String cover;


    private List<String> eatCategories;
    private Integer score;
    private Integer nums;

}
