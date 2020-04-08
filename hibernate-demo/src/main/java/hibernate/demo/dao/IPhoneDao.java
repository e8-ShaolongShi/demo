package hibernate.demo.dao;


import hibernate.demo.entity.Phone;

import java.util.List;

/**
 * @author shishaolong
 * @datatime 2020/3/18 17:28
 */
public interface IPhoneDao {

    List<Phone> findAll();

    Phone findById(Integer id);

    void add(Phone person);

    void update(Phone person);

    void delete(Integer id);
}
