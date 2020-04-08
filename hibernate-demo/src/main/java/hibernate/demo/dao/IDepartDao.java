package hibernate.demo.dao;

import hibernate.demo.entity.Department;

import java.util.List;

/**
 * @author shishaolong
 * @datatime 2020/4/2 15:42
 */
public interface IDepartDao {
    List<Department> findAll();

    Department findById(int id);
}
