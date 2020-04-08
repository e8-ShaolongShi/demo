package cn.juxiewl.menu.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shishaolong
 * @datatime 2020/4/7 8:36
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "t_menu")
public class MenuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private String name;

    @Transient
    private List<Object> menuList = new ArrayList<>();
}
