package com.smart.auth.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseResult<T>  implements Serializable {
    private int status;
    private String msg;
    private T data;

}
