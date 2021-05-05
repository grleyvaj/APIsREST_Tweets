package com.example.api.twitter.core.configuration;

import com.example.api.twitter.domain.entity.HashtagPtnEntity;
import com.example.api.twitter.domain.entity.LocationEntity;
import com.example.api.twitter.domain.entity.TweetEntity;
import com.example.api.twitter.domain.entity.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

/**
 * @author Gloria R. Leyva Jerez
 */
@Configuration
@EnableRedisRepositories
public class RedisConfig {

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("localhost", 6379);
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    RedisTemplate<String, TweetEntity> redisTemplateTweetEntity() {
        RedisTemplate<String, TweetEntity> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }

    @Bean
    RedisTemplate<String, UserEntity> redisTemplateUserEntity() {
        RedisTemplate<String, UserEntity> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }

    @Bean
    RedisTemplate<String, LocationEntity> redisTemplateLocationEntity() {
        RedisTemplate<String, LocationEntity> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }

    @Bean
    RedisTemplate<String, HashtagPtnEntity> redisTemplateHashtagPtnEntity() {
        RedisTemplate<String, HashtagPtnEntity> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }
}
