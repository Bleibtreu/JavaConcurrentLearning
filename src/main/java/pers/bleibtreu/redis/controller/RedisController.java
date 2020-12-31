package pers.bleibtreu.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bleibtreu
 * @date 2020/11/19 11:19
 */
@RestController
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/getValue")
    public String getValue() {
        return (String) redisTemplate.opsForValue().get("6800911-1376017308");
    }

    @GetMapping("/setValue")
    public void setValue() {
        redisTemplate.opsForValue().set("6800911-1376017308", "6800911%E6%9C%80%E6%96%B0%E5%8A%A8%E6%80%81");
    }

}
