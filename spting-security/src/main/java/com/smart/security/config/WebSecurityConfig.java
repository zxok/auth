package com.smart.security.config;


import com.smart.security.handler.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

/**
 * 认证
 * 三个核心类
 * WebSecurityConfigurerAdapter
 * UserDetailsService
 * UserDetail 登录对象实现的接口
 * AuthenticationProvider自定义认证
 * EnableGlobalMethodSecurity 权限控制
 * 1.自定义登录成功处理器
 * 2.自定义登录失败处理器
 * 3.自定义注销成功处理器
 * 4.自定义暂无权限处理器
 * 5.自定义未登录的处理器
 * 6.自定义登录逻辑验证器(可选)
 */

@Configuration
/*开启认证*/
@EnableWebSecurity
/* 开启前置权限注解,默认是关闭的 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 登录成功处理器
     */
    @Resource
    UserAuthSuccessHandler userAuthSuccessHandler;
    /**
     * 登录失败处理器
     */
    @Resource
    UserAuthFailHandler userAuthFailHandler;
    /**
     * 注销成功处理器
     */
    @Resource
    UserLogoutSuccessHandler logoutSuccessHandler;
    /**
     * 未登录的处理器
     */
    @Resource
    UserAuthenticationEntryPoint authenticationDetailsSource;

    /**
     * 未授权的处理器
     */
    @Resource
    UserAccessDeniedHandler userAccessDeniedHandler;


    @Resource
    JWTConfigProperties jwtConfig;

    /**
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                /* 1.认证配置过滤url */
                .authorizeRequests()
                // 不需要权限验证的请求或资源
                .antMatchers(jwtConfig.getAntMatchers()).permitAll()
                // 其他的需要登陆后才能访问
                .anyRequest().authenticated()
                .and()
                /* ---------2. 登录配置--------- */
                .formLogin()
                .loginPage("/login")
                //定义登录时，用户名的 key，默认为 username
                .usernameParameter("username")
                //定义登录时，用户密码的 key，默认为 password
                .passwordParameter("password")
                //配置登录成功自定义处理器
                .successHandler(userAuthSuccessHandler)
                //配置登录失败自定义处理类
                .failureHandler(userAuthFailHandler)
                /* ---------3. 登出配置--------- */
                .and()
                .logout()
                .logoutUrl("/logout")
                // 配置登出自定义处理器
                .logoutSuccessHandler(logoutSuccessHandler)
                .and()
                /* ---------配置未登录自定义处理类 ---------*/
                .httpBasic().authenticationEntryPoint(authenticationDetailsSource)
                /*--------- 4. 无权限配置 --------- */
                .and()
                //配置没有权限自定义处理类
                .exceptionHandling()
                .accessDeniedHandler(userAccessDeniedHandler)
                /* ---------5.添加 jwt过滤器 ---------*/
//                .and()
//                .addFilter();
                /* ---------6. 其它配置--------- */
                .and()
                // 6.1 禁用session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // 6.2 禁用缓存
                .and()
                .headers().cacheControl().disable()
                .and()
                //6.3 关闭请求伪造
                .csrf().disable()
                //6.4 取消跨域资源共享
                .cors().disable();
    }


    /**
     * 数据库认证
     */
    @Resource
    @Qualifier("userDetailsServiceImpl")
    UserDetailsService userDetailsService;

    /**
     * 配置登录验证逻辑
     *
     * @param auth
     * @throws Exception
     */
    @Resource
    BCryptPasswordEncoder encoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder);
                //.passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    /**
     * 过滤静态资源的
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    /**
     * 加密方式
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

