package com.tianlian.shardingsphere.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

/**
 * @author shishaolong
 * @datatime 2020/8/31 19:56
 */
@Entity
@Data
@Accessors(chain = true)
@Table(name = "t_person")
public class Person {
    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    private String id;
    private String name;
    private Integer age;
}
