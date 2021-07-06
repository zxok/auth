package com.smart.sec.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

@Data
@TableName(value = "`user`")
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "username")
    private String username;

    @TableField(value = "password")
    private String password;

    @TableField(value = "add_time")
    private Date addTime;

    @TableField(value = "is_del")
    private Integer isDel;

    /**
     * 账号是否被锁定 1 表示未锁定 0 表示锁定
     */
    @TableField(value = "locked")
    private Boolean locked;

    /**
     * 帐户是否过期
     */
    @TableField(value = "is_account_non_expired")
    private Boolean isAccountNonExpired;

    /**
     * 凭证是否过期
     */
    @TableField(value = "is_credentials_non_expired")
    private Boolean isCredentialsNonExpired;

    /**
     * 是否启用
     */
    @TableField(value = "is_enabled")
    private Boolean isEnabled;

    public static final String COL_ID = "id";

    public static final String COL_USERNAME = "username";

    public static final String COL_PASSWORD = "password";

    public static final String COL_ADD_TIME = "add_time";

    public static final String COL_IS_DEL = "is_del";

    public static final String COL_LOCKED = "locked";

    public static final String COL_IS_ACCOUNT_NON_EXPIRED = "is_account_non_expired";

    public static final String COL_IS_CREDENTIALS_NON_EXPIRED = "is_credentials_non_expired";

    public static final String COL_IS_ENABLED = "is_enabled";
}