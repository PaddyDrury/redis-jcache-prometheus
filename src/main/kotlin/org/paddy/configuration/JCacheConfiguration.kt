package org.paddy.configuration

import org.ehcache.jsr107.EhcacheCachingProvider
import org.springframework.beans.factory.BeanClassLoaderAware
import org.springframework.beans.factory.ObjectProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizers
import org.springframework.cache.jcache.JCacheCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.io.Resource
import javax.cache.CacheManager
import javax.cache.Caching


@Configuration
class JCacheConfiguration : BeanClassLoaderAware {
    private lateinit var beanClassLoader: ClassLoader

    @Value("classpath:ehcache.xml")
    private lateinit var ehcacheConfig: Resource

    @Bean
    @Primary
    fun cacheManager(customizers: CacheManagerCustomizers, jCacheCacheManager: CacheManager) =
        JCacheCacheManager(jCacheCacheManager).let {
            customizers.customize(it)
        }

    @Bean
    fun cacheManagerCustomizers(cuztomizers: ObjectProvider<CacheManagerCustomizer<*>>) =
        CacheManagerCustomizers(cuztomizers.orderedStream().toList())

    @Bean
    fun jCacheCacheManager() =
        Caching.getCachingProvider(EhcacheCachingProvider::class.qualifiedName)
            .getCacheManager(ehcacheConfig.uri, beanClassLoader)

    override fun setBeanClassLoader(classLoader: ClassLoader) {
        this.beanClassLoader = classLoader
    }
}