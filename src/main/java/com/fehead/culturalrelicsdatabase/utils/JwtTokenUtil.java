package com.fehead.culturalrelicsdatabase.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.security.*;

import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

/**
 * @Author Zero
 * @Date 2021/7/17 15:57
 * @Since 1.8
 * @Description
 **/
@Component
public class JwtTokenUtil {
    private final static Clock CLOCK = DefaultClock.INSTANCE;

    @Value("${jwt.publicKey}")
    private String publicKey;

    @Value("${jwt.privateKey}")
    private String privateKey;

    /**
     * @author Zero
     * @date 2021/3/9 17:36
     * @description 生成jwt token
     */
    public String generateToken(Map<String, Object> claims, String subject, int expiration) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PrivateKey key = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey)));
        // 生成签名密钥
        final Date createdDate = CLOCK.now();
        final Date expirationDate = calculateExpirationDate(createdDate, expiration);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.RS256, key)
                .compact();
    }

    private Date calculateExpirationDate(Date createdDate, int expiration) {
        return new Date(createdDate.getTime() + expiration);
    }

    /**
     * 解密Jwt内容
     *
     * @param jwt jwt
     * @return Claims
     */
    public Claims parseJwtRsa256(String jwt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PublicKey key = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey)));
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt).getBody();
    }
}
