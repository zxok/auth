package com.smart.sec.config;

import com.smart.sec.handler.LoginErrorHandler;
import com.smart.sec.handler.LoginSuccessHandler;
import com.smart.sec.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.annotation.Resource;

/**
 * @program: cloud-alibaba-example
 * @author: hzx
 * @since: JDK 1.8
 * @create: 2020-12-14 15:18
 **/
@Configuration
/*开启认证*/
@EnableWebSecurity
/* 开启前置权限注解,默认是关闭的 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    //@Qualifier("userDetailsServiceImpl")
    UserDetailsService userDetailsService;
    @Resource
    LoginSuccessHandler loginSuccessHandler;
    @Resource
    LoginErrorHandler loginErrorHandler;
    /*@Resource
    PasswordEncoder passwordEncoder;*/

    /**
     * 安全过滤配置的方法
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                /** 1.登录操作的配置 */
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                //  登录成功处理的接口回调类
                .successHandler(loginSuccessHandler)
                // 登录失败处理的接口回调类
                .failureHandler(loginErrorHandler)
                // 放开登录(登录界面不拦截)
                .permitAll()
                /** 2.登出操作配置信息 */
                .and().logout()
                .logoutUrl("/logout")
                .permitAll()
                /** 3.授权认证配置信息 */
                .and().authorizeRequests()
                // 3.1 所有的请求
                .anyRequest()
                // 3.2 表示所有的请求必须授权认证
                .authenticated();
    }

    /**
     * 配置登录认证
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    /**
     * 过滤静态资源的
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
}
