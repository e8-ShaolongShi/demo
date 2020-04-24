package hibernate.demo.dao;

import hibernate.demo.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author shishaolong
 * @datatime 2020/4/24 16:02
 */
@Repository
public interface IUserDao extends CrudRepository<UserInfo, Integer> {
}
