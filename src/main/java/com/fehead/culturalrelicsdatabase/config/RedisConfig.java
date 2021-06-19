package com.fehead.culturalrelicsdatabase.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Author Zero
 * @Date 2021/6/5 11:53
 * @Since 1.8
 **/
@Configuration
//@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisConfig {
    //固定模板
    @Autowired
    private LettuceConnectionFactory lettuceConnectionFactory;

    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(lettuceConnectionFactory);
        //json序列化配置
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        //string的序列化配置
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        //key采用string序列化方式
        template.setKeySerializer(stringRedisSerializer);
        //hash的key也采用string的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        //hash的value的序列化方式采用Jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

//    @Bean //此处有bug没有解决，疑似反序列化出现问题
//    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
//
//        // 使用 FastJsonRedisSerializer 来序列化和反序列化redis 的 value的值
//        FastJsonRedisSerializer<Object> serializer = new FastJsonRedisSerializer<>(Object.class);
//        ParserConfig.getGlobalInstance().addAccept("com.muzz");
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setCharset(StandardCharsets.UTF_8);
//        serializer.setFastJsonConfig(fastJsonConfig);
//        return serializer;
//    }

//    @Bean
//    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
//
//        // 使用 FastJsonRedisSerializer 来序列化和反序列化 redis 的 value的值
//        FastJsonRedisSerializer<Object> serializer = new FastJsonRedisSerializer<>(Object.class);
//        ParserConfig.getGlobalInstance().addAccept("com.muzz.");
//        ParserConfig.getGlobalInstance().addAccept("org.springframework.");
//        ParserConfig.getGlobalInstance().addAccept("org.springframework.security.core.context.");
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setCharset(StandardCharsets.UTF_8);
//        serializer.setFastJsonConfig(fastJsonConfig);
//        return new GenericFastJsonRedisSerializer();
//    }

}
