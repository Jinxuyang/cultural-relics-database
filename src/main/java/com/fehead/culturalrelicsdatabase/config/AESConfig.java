package com.fehead.culturalrelicsdatabase.config;

import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Verge
 * @Date 2021/6/28 12:30
 * @Version 1.0
 */
@Configuration
public class AESConfig {
    @Value("${aes.key}")
    String key;

    @Bean
    public SymmetricCrypto getSymmetricCrypto(){
        byte[] byteKey = key.getBytes();
        return new SymmetricCrypto(SymmetricAlgorithm.AES,byteKey);
    }
}