package hibernate.demo.dao.impl;


import hibernate.demo.dao.IPhoneDao;
import hibernate.demo.entity.Phone;
import org.hibernate.Session;
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
public class PhoneDaoJPAImpl implements IPhoneDao {

    @PersistenceContext
    private EntityManager entityManager;

    private Session session;

    @Override
    public List<Phone> findAll() {
        String hql = "from Phone";
        return entityManager.createQuery(hql).getResultList();
    }

    @Override
    public Phone findById(Integer id) {
        return entityManager.find(Phone.class, id);
    }

    @Transactional
    @Override
    public void add(Phone phone) {
        entityManager.persist(phone);
        //
//        entityManager.m
    }

    @Transactional
    @Override
    public void update(Phone phone) {
        Phone p1 = findById(phone.getId());
        p1.setNumber(phone.getNumber());
        entityManager.flush();
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        entityManager.remove(findById(id));
    }
}
