package com.smart.sec.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.sec.util.BaseResult;
import com.smart.sec.util.ResponseUtils;
import com.smart.sec.util.ResultCodeEnum;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
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
public class LoginErrorHandler implements AuthenticationFailureHandler {

    @Resource
    ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        // 这些对于操作的处理类可以根据不同异常进行不同处理
        BaseResult<Object> result;
        if (e instanceof UsernameNotFoundException | e instanceof BadCredentialsException) {
            result = BaseResult.error(ResultCodeEnum.ACCOUNT_LOGIN_ERROR);
        } else if (e instanceof LockedException) {
            result = BaseResult.error(ResultCodeEnum.USER_ACCOUNT_LOCKED);
        } else {
            //认证失败
            result = BaseResult.error(ResultCodeEnum.AUTH_ERROR);
        }
        ResponseUtils.responseToJson(response, objectMapper.writeValueAsString(result));
    }
}
