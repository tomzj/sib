package cn.myzqu.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Category {
    private Integer id;

    private String name;

    private Integer ord;

    private Integer parentId;

    private String parentIds;

    private Boolean available;

    private Date createTime;

    private Date updateTime;

}