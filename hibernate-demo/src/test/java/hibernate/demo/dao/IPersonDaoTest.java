package hibernate.demo.dao;

import hibernate.demo.dao.IPersonDao;
import hibernate.demo.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IPersonDaoTest {

    @Autowired
    private IPersonDao dao;

    @Test
    public void testFindALl() {
        dao.findAll().parallelStream().forEach(System.out::println);
    }

    @Test
    public void testFindById() {
        Person person = dao.findById(02);
        System.out.println(person);
    }

    @Test
    public void testAdd() {
        for (int i = 0; i < 10; i++) {
            Person person = new Person().setAge(12 + i).setName("long_" + i);
            dao.add(person);
        }
    }

    @Test
    public void testUpdate() {
        final int[] i = {0};
        dao.findAll()
                .parallelStream()
                .peek(person -> {
                    person.setName("lili" + i[0]++);
                })
                .forEach(dao::update);
    }

    @Test
    public void testDel() {
        dao.delete(2);
    }


}