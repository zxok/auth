package com.smart.security.util;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: cloud-alibaba-example
 * @author: hzx
 * @since: JDK 1.8
 * @create: 2020-12-12 11:28
 **/
@Data
public class BaseResult<T> {

    /**
     * 返回码
     */
    private int status;

    /**
     * 返回说明
     */
    private String msg;

    /**
     * 提示
     */
    private String tips;

    /**
     * 返回数据
     */
    private T info;


    public BaseResult() {
    }

    public BaseResult(T data) {
        this();
        this.info = data;
    }

    public BaseResult(@NotNull ResultCodeEnum code, @NotNull T info) {
        this.status = code.status;
        this.msg = code.msg;
        this.tips = code.tips;
        this.info = info;
    }

    public BaseResult(@NotNull ResultCodeEnum code) {
        this.status = code.status;
        this.msg = code.msg;
        this.tips = code.tips;
    }

    public static <T> BaseResult<T> success() {
        return success(ResultCodeEnum.SUCCESS, null);
    }

    /**
     * @param data
     * @param <T>
     * @return
     */
    public static <T> BaseResult<T> success(@NotNull T data) {
        return success(ResultCodeEnum.SUCCESS, data);
    }

    /**
     * @param codeEnum
     * @param data
     * @param <T>
     * @return
     */
    public static <T> BaseResult<T> success(@NotNull ResultCodeEnum codeEnum, @NotNull T data) {
        return new BaseResult<>(codeEnum, data);
    }

    public static <T> BaseResult<T> error() {
        return new BaseResult<T>(ResultCodeEnum.ERROR);
    }


    public static <T> BaseResult<T> error(@NotNull ResultCodeEnum codeEnum) {
        return error(codeEnum, null);
    }

    public static <T> BaseResult<T> error(@NotNull ResultCodeEnum codeEnum, @NotNull T data) {
        return new BaseResult<>(codeEnum, data);
    }

    public static <T> BaseResult<T> error(@NotNull int status, @NotNull String msg, @NotNull String tips) {
        BaseResult<T> baseResult = new BaseResult<>();
        baseResult.setStatus(status);
        baseResult.setMsg(msg);
        baseResult.setTips(tips);
        return baseResult;
    }
}
