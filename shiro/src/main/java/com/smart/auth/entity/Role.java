package com.smart.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "`role`")
public class Role {
    @TableId(value = "role_id", type = IdType.INPUT)
    private Integer roleId;

    /**
     * 角色名称
     */
    @TableField(value = "role_name")
    private String roleName;

    /**
     * 创建日期
     */
    @TableField(value = "create_date")
    private Date createDate;

    /**
     * 角色说明
     */
    @TableField(value = "role_desc")
    private String roleDesc;

    /**
     * 是否删除 0 表示未删除  1 表示删除
     */
    @TableField(value = "is_del")
    private Integer isDel;

    public static final String COL_ROLE_ID = "role_id";

    public static final String COL_ROLE_NAME = "role_name";

    public static final String COL_CREATE_DATE = "create_date";

    public static final String COL_ROLE_DESC = "role_desc";

    public static final String COL_IS_DEL = "is_del";
}