package com.smart.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.security.util.BaseResult;
import com.smart.security.util.ResponseUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: cloud-alibaba-example
 * @author: hzx
 * @since: JDK 1.8
 * @create: 2020-12-12 11:11
 *
 *  自定义用户登出的成功处理器
 **/
@Component
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {
    @Resource
    ObjectMapper objectMapper;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        ResponseUtils.responseToJson(httpServletResponse, objectMapper.writeValueAsString(BaseResult.success()));
    }
}

