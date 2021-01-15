package pers.bleibtreu.redis.spike.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author bleibtreu
 * @date 2021/1/15 11:31
 */
@Data
@EqualsAndHashCode
public class Order {

    private String orderCode;

    private String orderName;

    private Integer num;

    private Integer status;

    private Date createTime;

}
