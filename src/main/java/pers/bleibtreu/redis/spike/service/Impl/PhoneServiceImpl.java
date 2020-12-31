package pers.bleibtreu.redis.spike.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.bleibtreu.redis.spike.dao.PhoneDao;
import pers.bleibtreu.redis.spike.entity.Phone;
import pers.bleibtreu.redis.spike.service.PhoneService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bleibtreu
 * @date 2020/12/31 15:03
 */
@Slf4j
@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneDao phoneDao;

    private String[] phoneList = {"iphone6","iphone7","iphone8","iphone9","iphone10","iphone11","iphone12","xiaomi10","xiaomi11","huaweiP40"};

    @Override
    public void initNum() {
        phoneDao.delect(Arrays.stream(Arrays.stream(phoneList).toArray()).collect(Collectors.toList()));
        Arrays.stream(phoneList).forEach(x -> phoneDao.insert(x, 100));
    }

    @Override
    public List<Phone> getAllInfo() {
        List<Phone> list = new ArrayList<>();
        Arrays.stream(phoneList).forEach(x -> {
            phoneDao.select(x);
            list.add(new Phone(x, phoneDao.select(x)));
        });
        return list;
    }
}
