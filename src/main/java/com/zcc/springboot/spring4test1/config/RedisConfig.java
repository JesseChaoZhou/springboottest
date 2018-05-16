package com.zcc.springboot.spring4test1.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.lang.reflect.Method;


@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

   @Value("${spring.redis.host}")
    private String host;
   @Value("${spring.redis.port}")
    private int port;
   @Value("${spring.redis.timeout}")
    private int timeout;
   @Bean
   public KeyGenerator keyGenerator(){
       return  new KeyGenerator(){
           @Override
           public Object generate(Object target, Method method, Object... objects) {
               StringBuffer sb = new StringBuffer();
               sb.append(target.getClass().getName());
               sb.append(method.getName());
               for(Object object:objects){
                   sb.append(object);
               }
               return sb.toString();
           }
       };
   }

    @Bean
   public CacheManager cacheManager(RedisTemplate redisTemplate){
       RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
       cacheManager.setDefaultExpiration(10000);
       return cacheManager;
   }
    @Bean
   public RedisTemplate<String,String> redisTemplate(RedisConnectionFactory factory){
       StringRedisTemplate template = new StringRedisTemplate(factory);
        setSerializer(template);
        template.afterPropertiesSet();
        return template;
   }

   private void setSerializer(StringRedisTemplate template){
       Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
       ObjectMapper om = new ObjectMapper();
       om.setVisibility(PropertyAccessor.ALL,JsonAutoDetect.Visibility.ANY);
       om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
       jackson2JsonRedisSerializer.setObjectMapper(om);
       template.setValueSerializer(jackson2JsonRedisSerializer);
   }

}
