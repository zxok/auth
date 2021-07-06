package com.smart.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.security.service.MyUserPrincipal;
import com.smart.security.util.BaseResult;
import com.smart.security.util.JwtTokenUtil;
import com.smart.security.util.ResponseUtils;
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
 * @create: 2020-12-12 11:09
 **/
@Component
public class UserAuthSuccessHandler implements AuthenticationSuccessHandler {
    @Resource
    JwtTokenUtil tokenUtil;
    @Resource
    ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        MyUserPrincipal principal = (MyUserPrincipal) authentication.getPrincipal();
        String token = tokenUtil.generate(principal.getAdmin());
        ResponseUtils.responseToJson(httpServletResponse, objectMapper.writeValueAsString(BaseResult.success(token)));
    }
}
