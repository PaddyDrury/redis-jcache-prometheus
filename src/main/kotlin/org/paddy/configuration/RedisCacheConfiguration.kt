package org.paddy.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder
import org.springframework.data.redis.connection.RedisConnectionFactory

@Configuration
class RedisCacheConfiguration {
    @Bean
    fun redisCacheManager(redisConnectionFactory: RedisConnectionFactory): RedisCacheManager
    = RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory)
        .withInitialCacheConfigurations(
            mapOf("redisCache" to defaultCacheConfig())
        )
        .enableStatistics()
        .build()
}