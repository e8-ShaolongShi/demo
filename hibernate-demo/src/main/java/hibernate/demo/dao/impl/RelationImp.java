package hibernate.demo.dao.impl;


import hibernate.demo.dao.IRelationDao;
import hibernate.demo.entity.PersonDepartment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author shishaolong
 * @datatime 2020/4/2 15:42
 */
@Repository
public class RelationImp implements IRelationDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PersonDepartment> findAll() {
        String hql = "from PersonDepartment";
        List resultList = entityManager.createQuery(hql).getResultList();
        return resultList;
    }

    @Transactional
    @Override
    public void save(PersonDepartment personDepartment) {
        entityManager.persist(personDepartment);
    }
}
