package com.hxy.boot.common.utils.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hxy.boot.common.entity.mysql.UserEntity;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JWTUtil {
    private JWTUtil() {
    }

    /**
     * 请求头key
     */
    public static final String AUTH_HEADER_KEY = "authorization";

    /**
     * token 前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 密钥
     */
    private static final String SECRET = "my_secret";

    /**
     * 过期时间,单位为秒
     **/
    private static final Integer EXPIRATION = 24 * 60 * 60;

    /**
     * 生成用户token,设置token超时时间
     */
    public static String createToken(UserEntity userEntity) {
        // 过期时间
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRATION * 1000);
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        return JWT.create()
                .withHeader(map)// 添加头部
                // 可以将基本信息放到claims中
                .withClaim("id", userEntity.getId())
                .withClaim("name", userEntity.getName())
                .withClaim("account", userEntity.getAccount())
                .withExpiresAt(expireDate) // 超时设置,设置过期的日期
                .withIssuedAt(new Date()) // 签发时间
                .sign(Algorithm.HMAC256(SECRET)); // SECRET加密
    }

    /**
     * 校验token并解析token
     */
    public static Boolean verifyTokenIsValid(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            verifier.verify(token);
        } catch (Exception ex) {
            log.error("token解码异常: {}", ex.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 获取token中的payload
     */
    public static Object getTokenClaims(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);
        } catch (Exception ex) {
            log.error("token解码异常: {}", ex.getMessage());
            return null;
        }
        return jwt.getClaims();
    }
}
