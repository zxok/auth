package com.smart.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "permission")
public class Permission {
    @TableId(value = "per_id", type = IdType.INPUT)
    private Integer perId;

    /**
     * 权限名称
     */
    @TableField(value = "per_name")
    private String perName;

    /**
     * 创建日期
     */
    @TableField(value = "create_date")
    private Date createDate;

    /**
     * 权限说明
     */
    @TableField(value = "per_desc")
    private String perDesc;

    /**
     * 是否删除 0 表示未删除  1 表示删除
     */
    @TableField(value = "is_del")
    private Integer isDel;

    public static final String COL_PER_ID = "per_id";

    public static final String COL_PER_NAME = "per_name";

    public static final String COL_CREATE_DATE = "create_date";

    public static final String COL_PER_DESC = "per_desc";

    public static final String COL_IS_DEL = "is_del";
}