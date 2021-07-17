package com.fehead.culturalrelicsdatabase.interceptor;

import com.fehead.culturalrelicsdatabase.core.error.BusinessException;
import com.fehead.culturalrelicsdatabase.core.error.EmBusinessError;
import com.fehead.culturalrelicsdatabase.utils.JwtTokenUtil;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @Author Zero
 * @Date 2021/7/17 16:19
 * @Since 1.8
 * @Description
 **/
@Component
public class JwtIntercepter implements HandlerInterceptor {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if(StringUtils.isEmpty(token)) {
            throw new BusinessException(EmBusinessError.LOGIN_ERROR);
        }
        try {
            jwtTokenUtil.parseJwtRsa256(token);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            throw new BusinessException(EmBusinessError.SIGNATURE_ERROR);
        } catch (ExpiredJwtException e) {
            throw new BusinessException(EmBusinessError.JWT_TOKEN_EXPIRED);
        } catch (InvalidClaimException e) {
            throw new BusinessException(EmBusinessError.INVALID_CLAIM);
        } catch (PrematureJwtException e) {
            throw new BusinessException(EmBusinessError.WRONG_TOKEN);
        } catch (JwtException e) {
            throw new BusinessException(EmBusinessError.WRONG_TOKEN);
        }
        return true;
    }
}
