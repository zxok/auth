package com.smart.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.security.util.BaseResult;
import com.smart.security.util.ResponseUtils;
import com.smart.security.util.ResultCodeEnum;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: cloud-alibaba-example
 * @author: hzx
 * @since: JDK 1.8
 * @create: 2020-12-12 11:12
 *
 *  未认证处理器
 **/
public class UserUnAuthPointHandler implements AuthenticationEntryPoint {
    @Resource
    ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResponseUtils.responseToJson(httpServletResponse, objectMapper.writeValueAsString(BaseResult.error(ResultCodeEnum.ACCOUNT_NOT_LOGIN)));
    }
}
