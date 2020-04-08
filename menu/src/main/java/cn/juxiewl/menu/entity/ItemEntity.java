package cn.juxiewl.menu.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * @author shishaolong
 * @datatime 2020/4/7 8:38
 */
@Entity
@Table(name = "t_item")
@Data
@Accessors(chain = true)
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private String name;
}
