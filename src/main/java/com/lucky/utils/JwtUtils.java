package com.lucky.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

import static com.lucky.constant.TimeConstant.EXPIRATION;

public class JwtUtils {



    public static String generateToken(String secretKey, long ttlMillis, Map<String, Object> claims) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        return Jwts.builder()
                //payload
                .setClaims(claims)
                //sign
                //过期时间
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION))
                //sign
                .signWith(SignatureAlgorithm.HS256,secretKey.getBytes(StandardCharsets.UTF_8))
                .compact();
    }

    public static Claims getUsernameFromToken(String sercertKey,String token) {
            Claims claims = Jwts.parser()
                    .setSigningKey(sercertKey.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token)
                    .getBody();
            return claims;

    }
}
