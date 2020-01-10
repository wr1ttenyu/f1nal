package wr1ttenyu.study.f1nal.springboot.demo.web;

import io.lettuce.core.cluster.api.async.RedisClusterAsyncCommands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.lettuce.LettuceConnection;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redis")
public class RedisOperationController {

    private static Logger LOGGER = LoggerFactory.getLogger(RedisOperationController.class);

    @Autowired
    StringRedisTemplate redisTemplate;

    @RequestMapping("/setValue")
    public String setValue(@RequestParam("key") String key, @RequestParam("value") String value) {
        LOGGER.info("redis setValue key:{} value:{}", key, value);
        redisTemplate.opsForValue().set(key, value);
        return key + " => " + value;
    }

    @RequestMapping("/getValue")
    public String getValue(@RequestParam("key") String key) {
        ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
        String value = stringStringValueOperations.get(key);
        LOGGER.info("redis getValue key:{} value:{}", key, value);
        return key + " => " + value;
    }

    @RequestMapping("/popListValue")
    public String popListValue(@RequestParam("key") String key) {
        String value = redisTemplate.opsForList().leftPop("list1", 10, TimeUnit.SECONDS);
        LOGGER.info("redis popListValue key:{} value:{}", key, value);
        return key + " => " + value;
    }

    @RequestMapping("/pushListValue")
    public String pushListValue(@RequestParam("key") String key, @RequestParam("value") String value) {
        redisTemplate.opsForList().leftPush(key, value);
        LOGGER.info("redis pushListValue key:{} value:{}", key, value);
        return key + " => " + value;
    }
}
