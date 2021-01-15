package pers.bleibtreu.redis.spike.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author bleibtreu
 * @date 2020/12/31 14:57
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Phone {

    private String name;

    private int Num;

}
