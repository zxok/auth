package com.smart.auth.config;

import com.smart.auth.shiro.LoginRealms;
import com.smart.auth.shiro.ShiroRedisSessionManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;

/**
 * 初始化 WebSecurityManager
 * 注册  LoginRealms
 * <p>
 * 权限 必须认证才能正常访问  前端 客服端
 * 认证通过之后需要权限才能访问或者操作
 * @author Hzx
 */
@Configuration
public class ShiroConfig {

    /**
     * 1.注册自定义的 Realm
     * @return
     */
    @Bean
    @Primary
    public Realm realm(HashedCredentialsMatcher hashedCredentialsMatcher) {
        LoginRealms loginRealms = new LoginRealms();
        //在自定义的realm中注册，密码加密
        loginRealms.setCredentialsMatcher(hashedCredentialsMatcher);
        return loginRealms;
    }

    /**
     * 2.配置url过滤器
     *  1> 那些不需要认证，可以直接访问
     *  2> 那些需要认证才能访问
     * @return
     */
    @Bean("shiroFilter")
    public ShiroFilterChainDefinition filterChainDefinition() {
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
        //需要放行的的路径 -- anon
        definition.addPathDefinition("/login", "anon");
        definition.addPathDefinition("/register", "anon");
        definition.addPathDefinition("/druid/**", "anon");
        definition.addPathDefinition("/test", "anon");
        // 配置用户登出 -- logout
        definition.addPathDefinition("/logout", "logout");
        // admin 必须通过认证才能访问 -- authc
        definition.addPathDefinition("/admin/**", "authc");
        definition.addPathDefinition("/**", "authc");
        return definition;
    }

    /**
     * 3.配置security并设置Realm，避免required a bean named 'authorizer' that could not be found.的报错
     * ThreadContext.bind(manager);
     * @param realm
     * @return
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager webSecurityManager(SessionManager sessionManager, Realm realm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        // springboot   跟 shiro版本兼容的
        ThreadContext.bind(manager);
        manager.setRealm(realm);
        // 注册 session对象
        manager.setSessionManager(sessionManager);
        // 设置redis缓存
        manager.setCacheManager(redisCacheManager);
        return manager;
    }

    /**
     *  设置 session
     */
    @Resource
    RedisSessionDAO redisSessionDAO;
    @Resource
    RedisCacheManager redisCacheManager;
    @Bean
    public ShiroRedisSessionManager sessionManager() {
        ShiroRedisSessionManager sessionManager = new ShiroRedisSessionManager();
        // 开启是否删除无效的session信息
        sessionManager.setDeleteInvalidSessions(true);
        // 开启定时任务定期的检测过期信息
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionValidationInterval(60 * 60 * 1000);
        // 取消url后面的jessionid
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        //设置全局session过期时间
        sessionManager.setGlobalSessionTimeout(7 * 24 * 60 * 60);
        sessionManager.setSessionDAO(redisSessionDAO);
        return sessionManager;
    }

    /**
     * 内置密码加密
     * 认证加密
     * 注册加密
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // SHA-256加密算法
        hashedCredentialsMatcher.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
        // MD5加密
        //hashedCredentialsMatcher.setHashAlgorithmName(Md5Hash.ALGORITHM_NAME);
        // 加密的次数
        hashedCredentialsMatcher.setHashIterations(100);
        // 转化成16进制的数据
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }

    /**
     *  防止AOP错误
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator greator = new DefaultAdvisorAutoProxyCreator();
        greator.setProxyTargetClass(true);
        return greator;
    }
}
