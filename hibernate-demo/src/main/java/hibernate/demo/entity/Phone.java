package hibernate.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * @author shishaolong
 * @datatime 2020/3/18 17:23
 */
@Data
@Entity
@Table(name = "t_phone")
@Accessors(chain = true)
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "number")
    private String number;

    @Column(name = "person_id")
    private Integer personId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @Transient
    @JoinColumn(name = "person_id")
    private Person person;
}
