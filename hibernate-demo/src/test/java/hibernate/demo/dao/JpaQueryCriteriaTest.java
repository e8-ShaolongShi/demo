package hibernate.demo.dao;

import hibernate.demo.dao.impl.jpa_query_creteria.DepartDaoImpl;
import hibernate.demo.entity.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author shishaolong
 * @datatime 2020/4/3 10:06
 */
@SpringBootTest
public class JpaQueryCriteriaTest {

    @Autowired()
    private DepartDaoImpl departDao;

    @Test
    public void test() {
        departDao.findAll().forEach(System.out::println);
    }

    @Test
    public void testFindById() {
        Department byId = departDao.findById(2);
        System.out.println(byId);
    }
}
