# redis-jcache-prometheus

Minimal sample application to reproduce an issue with the prometheus scrape endpoint when concurrently using
RedisCacheManager and JCacheCacheManager managed caches.

To reproduce, first run the app

```bash
./gradlew bootRun
```

then browse to http://localhost:8080/actuator/prometheus