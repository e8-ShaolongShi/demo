package hibernate.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * @author shishaolong
 * @datatime 2020/4/2 15:28
 */
@Data
@Accessors(chain = true, fluent = true)
@Entity
@Table(name = "t_person_depart")
public class PersonDepartment {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "person_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Person person;

    @JoinColumn(name = "depart_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Department department;
}
