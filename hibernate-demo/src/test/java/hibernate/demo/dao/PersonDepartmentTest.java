package hibernate.demo.dao;

import hibernate.demo.HibernateDemoApplication;
import hibernate.demo.entity.Department;
import hibernate.demo.entity.Person;
import hibernate.demo.entity.PersonDepartment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * JPA 保存对应关系
 */
@SpringBootTest(classes = HibernateDemoApplication.class)
class PersonDepartmentTest {

    @Autowired
    private IRelationDao relationDao;
    @Autowired
    private IDepartDao departDao;
    @Autowired
    private IPersonDao personDao;

    @Test
    public void getAll() {
        List<PersonDepartment> all = relationDao.findAll();
        all.forEach(System.out::println);
    }

    @Test
    public void save() {
        Department department = departDao.findById(2);
        Person person = personDao.findById(10);
        person.setName("啊哈哈哈哈");
        PersonDepartment personDepartment = new PersonDepartment();
        personDepartment.department(department);
        personDepartment.person(person);
        relationDao.save(personDepartment);
    }
}