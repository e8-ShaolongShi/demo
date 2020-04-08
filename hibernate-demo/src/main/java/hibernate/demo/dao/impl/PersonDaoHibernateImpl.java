package hibernate.demo.dao.impl;

import hibernate.demo.entity.Person;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import hibernate.demo.dao.IPersonDao;

import java.util.List;

/**
 * @author shishaolong
 * @datatime 2020/3/19 16:21
 */
//@Repository
public class PersonDaoHibernateImpl  implements IPersonDao {

    @Autowired
    private Session session;

    @Override
    public List<Person> findAll() {
//        session.createCriteria(Person.class);
        return null;
    }

    @Override
    public Person findById(Integer id) {
        return null;
    }

    @Override
    public void add(Person person) {

    }

    @Override
    public void update(Person person) {

    }

    @Override
    public void delete(Integer id) {

    }
}
