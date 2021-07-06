package com.smart.security.util;

import com.smart.security.config.JWTConfigProperties;
import com.smart.security.entity.Admin;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;

/**
 * @program: cloud-alibaba-example
 * @author: hzx
 * @since: JDK 1.8
 * @create: 2020-12-12 11:14
 **/
@Component
@Data
public
class JwtTokenUtil implements Serializable {
    public static final String UID = "uid";
    // 默认过期时间
    private static final long DEFAULT_EXPIRE = 60 * 1000;
    private static final String ISSUER = "smart";
    private static final String JWT_AUTHORITIES = "authorities";
    @Resource
    private JWTConfigProperties jwtConfigProperties;
    private String secret;
    private long expire;

    @PostConstruct
    private void init() {
        this.secret = jwtConfigProperties.getSecret();
        this.expire = jwtConfigProperties.getExpiration() == 0 ? DEFAULT_EXPIRE : jwtConfigProperties.getExpiration();
    }

    /**
     * 生成token
     *
     * @param
     * @return
     */
    public String generate(Admin admin) {
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + DEFAULT_EXPIRE * 1000);
        return Jwts.builder()
                .setId(admin.getId().toString())
                .setSubject(admin.getUsername())
                //  签发时间
                .setIssuedAt(nowDate)
                //
                .setIssuer(ISSUER)
                // 失效时间
                .setExpiration(expireDate)
                //.claim(JWT_AUTHORITIES, admin.getAuthorities())
                // 签名算法、密钥 不使用公钥
                .signWith(SignatureAlgorithm.HS512, jwtConfigProperties.getSecret())
                // 使用公钥
                .signWith(SignatureAlgorithm.RS512, jwtConfigProperties.getSecret())
                .compact();
    }

    /**
     * 解析Claims
     *
     * @param token
     * @return
     */
    public Claims getClaim(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            //throw new BaseException(ResultCodeEnum.ERROR);
        }
        return claims;
    }

    // 从token中获取用户名
    public String getUsername(String token) {
        return getClaim(token).getSubject();
    }

    /**
     * 获取jwt发布时间
     */
    public Date getIssuedAt(String token) {
        return getClaim(token).getIssuedAt();
    }

    /**
     * 获取UID
     */
    public Integer getUid(String token) {
        String uid = (String) getClaim(token).get(UID);
        return Integer.parseInt(uid);
    }

    /**
     * 获取jwt失效时间
     */
    public Date getExpiration(String token) {
        return getClaim(token).getExpiration();
    }

    /**
     * 验证token是否失效
     *
     * @param token
     * @return true:过期   false:没过期
     */
    public boolean isExpired(String token) {
        try {
            final Date expiration = getExpiration(token);
            return expiration.before(new Date());
        } catch (ExpiredJwtException expiredJwtException) {
            return true;
        }
    }
}
