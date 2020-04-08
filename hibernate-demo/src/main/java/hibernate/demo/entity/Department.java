package hibernate.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 部门表
 *
 * @author shishaolong
 * @datatime 2020/4/2 15:23
 */
@Entity
@Data
@Accessors(chain = true, fluent = true)
@Table(name = "t_department")
public class Department {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
}
