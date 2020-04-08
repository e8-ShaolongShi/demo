package hibernate.demo.dao.impl;

import hibernate.demo.dao.IDepartDao;
import hibernate.demo.entity.Department;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author shishaolong
 * @datatime 2020/4/2 16:00
 */
//@Repository
public class DepartDaoImp implements IDepartDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Department> findAll() {
        return null;
    }

    @Override
    public Department findById(int id) {
        return entityManager.find(Department.class, id);
    }
}
