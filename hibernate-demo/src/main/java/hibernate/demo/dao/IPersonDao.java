package hibernate.demo.dao;


import hibernate.demo.entity.Person;

import java.util.List;

/**
 * @author shishaolong
 * @datatime 2020/3/18 17:28
 */
public interface IPersonDao {

    List<Person> findAll();

    Person findById(Integer id);

    void add(Person person);

    void update(Person person);

    void delete(Integer id);
}
