package com.smart.sec.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.sec.entity.User;
import com.smart.sec.util.BaseResult;
import com.smart.sec.util.ResponseUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
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
 * @create: 2020-12-14 15:29
 **/
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();

        ResponseUtils.responseToJson(httpServletResponse, objectMapper, BaseResult.success(user));
    }
}
