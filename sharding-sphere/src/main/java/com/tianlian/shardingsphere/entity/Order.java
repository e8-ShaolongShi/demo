package com.tianlian.shardingsphere.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.Instant;

/**
 * 订单实体类
 *
 * @author shishaolong
 * @datatime 2020/8/31 15:19
 */
@Data
//@TableName("t_order")
@Table(name = "t_order")
@Entity
@Accessors(chain = true)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    private Instant gmtCreate;

}
