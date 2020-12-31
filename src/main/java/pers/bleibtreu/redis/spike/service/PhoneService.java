package pers.bleibtreu.redis.spike.service;

import pers.bleibtreu.redis.spike.entity.Phone;

import java.util.List;

public interface PhoneService {

    void initNum();

    List<Phone> getAllInfo();
}
