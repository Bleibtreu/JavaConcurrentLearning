package pers.bleibtreu.redis.spike.dao;

import java.util.List;

public interface PhoneDao {

    int select(String name);

    void update(String name, int num);

    void insert(String name, int num);

    void delect(String name);

    void delect(List names);
}
