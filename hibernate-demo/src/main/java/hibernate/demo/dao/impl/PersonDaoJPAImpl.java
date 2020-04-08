package hibernate.demo.dao.impl;

import hibernate.demo.dao.IPersonDao;
import hibernate.demo.entity.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author shishaolong
 * @datatime 2020/3/18 17:31
 */
@Repository
public class PersonDaoJPAImpl implements IPersonDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Person> findAll() {
        String hql = "FROM Person";
        return entityManager.createQuery(hql).getResultList();
    }

    @Override
    public Person findById(Integer id) {
        return entityManager.find(Person.class, id);
    }

    @Transactional
    @Override
    public void add(Person person) {
        entityManager.persist(person);
    }

    @Transactional
    @Override
    public void update(Person person) {
        Person p = findById(person.getId());
        p.setAge(person.getAge());
        p.setName(person.getName());

//        entityManager
//
//        entityManager.flush();
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        entityManager.remove(id);
    }
}
