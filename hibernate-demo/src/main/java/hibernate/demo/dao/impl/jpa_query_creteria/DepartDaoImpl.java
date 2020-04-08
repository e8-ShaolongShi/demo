package hibernate.demo.dao.impl.jpa_query_creteria;

import hibernate.demo.dao.IDepartDao;
import hibernate.demo.entity.Department;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author shishaolong
 * @datatime 2020/4/3 11:48
 */
@Repository
public class DepartDaoImpl implements IDepartDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Department> findAll() {
        CriteriaQuery<Department> cq = entityManager.getCriteriaBuilder().createQuery(Department.class);
        Root<Department> d = cq.from(Department.class);
        TypedQuery<Department> query = entityManager.createQuery(cq);
        cq.select(d);
        return query.getResultList();
    }

    @Override
    public Department findById(int id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Department> cq = cb.createQuery(Department.class);
        Root<Department> d = cq.from(Department.class);

        // 构造where条件
        ParameterExpression<Integer> idParam = cb.parameter(Integer.class);
//        cq.where(cb.equal(d.get("id"), idParam));
        cq.multiselect(d.get("id"), d.get("name")).distinct(true).where(cb.equal(d.get("id"), idParam));
        TypedQuery<Department> query = entityManager.createQuery(cq);
        query.setParameter(idParam, id);
        List<Department> resultList = query.getResultList();
        return resultList.get(0);
    }
}
