package pers.bleibtreu.redis.spike.dao.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import pers.bleibtreu.redis.spike.dao.PhoneDao;

import java.util.List;

/**
 * @author bleibtreu
 * @date 2020/12/31 17:06
 */
@Slf4j
@Service
public class PhoneDaoImpl implements PhoneDao {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public int select(String name) {
        return (int) redisTemplate.opsForValue().get(name);
    }

    @Override
    public void update(String name, int num) {
        redisTemplate.opsForValue().getAndSet(name, num);
    }

    @Override
    public void insert(String name, int num) {
        redisTemplate.opsForValue().set(name, num);
    }

    @Override
    public void delect(String name) {
        redisTemplate.delete(name);
    }

    @Override
    public void delect(List names) {
        redisTemplate.delete(names);
    }
}
