package pers.bleibtreu.redis.spike.vo;

import lombok.Data;

/**
 * @author  bleibtreu
 * @date  2020/12/28 17:00
 */
@Data
public class ResponseMessage {

    private int status;

    private String desc;

    private Object body;

}
