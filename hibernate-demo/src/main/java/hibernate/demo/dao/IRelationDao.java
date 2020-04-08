package hibernate.demo.dao;

import hibernate.demo.entity.PersonDepartment;

import java.util.List;

/**
 * @author shishaolong
 * @datatime 2020/4/2 15:42
 */
public interface IRelationDao {
    List<PersonDepartment> findAll();

    void save(PersonDepartment personDepartment);
}
