package pers.bleibtreu.redis.spike.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

/**
 * @author bleibtreu
 * @date 2021/1/15 12:16
 */
@Mapper
@Service
public interface OrderMapper {

    @Insert("INSERT INTO `` ")
    void saveOrder();

}
