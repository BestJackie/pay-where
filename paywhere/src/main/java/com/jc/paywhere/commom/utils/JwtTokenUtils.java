package com.jc.paywhere.commom.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenUtils {

    public static final String TOKEN_HEADER = "X-ACCESS-TOKEN";
    public static final String TOKEN_PREFIX = "Bear ";
    public static final String CLAIM_KEY_CREATED = "createTime";
    private static final String SECRET = "$2a$10$PTxJHgCfpai4CUUqxGSyUedMIWX6EiLyOH0fjfBkoejuhQ457qmRe"; //1234qwer
    private static final String ISS = "1355252693@163.com";
    private static final String ROLE_CLAIM = "rol";

    // 过期时间是3600秒，既是1个小时
    private static final long EXPIRATION = 3600L;

    // 选择了记住我之后的过期时间为7天
    private static final long EXPIRATION_REMEMBER = 604800L;

    private JwtTokenUtils() {
    }

    // 创建token
    public static String createToken(String username, String role, boolean isRememberMe) {
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.hasText(role))
            map.put(ROLE_CLAIM, role);
        map.put(CLAIM_KEY_CREATED, new Date());
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setClaims(map)
                .setIssuer(ISS)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
    }

    public static String createToken(Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setClaims(claims)
                .setIssuer(ISS)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION * 1000))
                .compact();
    }

    /**
     * 当原来的token没过期时是可以刷新的
     *
     * @param oldToken 带tokenHead的token
     */
    public static String refreshHeadToken(String oldToken) {
        if (!StringUtils.hasText(oldToken)) {
            return null;
        }
        //token校验不通过
        Claims claims = getTokenBody(oldToken);
        if (claims == null) {
            return null;
        }
        //如果token已经过期，不支持刷新
        if (isExpiration(oldToken)) {
            return null;
        }
        //如果token在30分钟之内刚刷新过，返回原token
        if (tokenRefreshJustBefore(oldToken, 30 * 60)) {
            return oldToken;
        } else {
            claims.put(CLAIM_KEY_CREATED, new Date());
            return createToken(claims);
        }
    }


    private static boolean tokenRefreshJustBefore(String token, int time) {
        Claims claims = getTokenBody(token);
        Date created = claims.get(CLAIM_KEY_CREATED, Date.class);
        Date refreshDate = new Date();
        //刷新时间在创建时间的指定时间内
        return refreshDate.after(created) && refreshDate.getTime() - created.getTime() < time * 1000;
    }

    // 从token中获取用户名
    public static String getUsername(String token) {
        return getTokenBody(token).getSubject();
    }

    // 是否已过期
    public static boolean isExpiration(String token) {
        return getTokenBody(token).getExpiration().before(new Date());
    }

    private static Claims getTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    public static String getUserRoles(String token) {
        return (String) getTokenBody(token).get(ROLE_CLAIM);
    }

}
