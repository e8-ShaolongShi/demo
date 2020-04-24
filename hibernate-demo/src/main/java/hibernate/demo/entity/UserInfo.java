package hibernate.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 测试时间
 *
 * @author shishaolong
 * @datatime 2020/4/24 15:56
 */
@Table(name = "t_user_info")
@Accessors(chain = true)
@Data
@Entity
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private LocalDateTime createTime;
}
