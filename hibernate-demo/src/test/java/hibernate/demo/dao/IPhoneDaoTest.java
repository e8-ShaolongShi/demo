package hibernate.demo.dao;

import hibernate.demo.dao.IPhoneDao;
import hibernate.demo.entity.Phone;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IPhoneDaoTest {

    @Autowired
    private IPhoneDao dao;

    @Test
    public void testAdd() {
        for (int i = 0; i < 10; i++) {
            Phone phone = new Phone().setNumber("1" + i + i + "555555" + i);
            dao.add(phone);
        }
    }
}