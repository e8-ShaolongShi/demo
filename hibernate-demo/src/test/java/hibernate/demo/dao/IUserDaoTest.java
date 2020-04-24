package hibernate.demo.dao;

import hibernate.demo.entity.Person;
import hibernate.demo.entity.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class IUserDaoTest {

    @Autowired
    private IUserDao  dao;

    @Test
    public void test(){
        UserInfo userInfo = new UserInfo();
        userInfo.setName("时少龙").setCreateTime(LocalDateTime.now());
        dao.save(userInfo);
    }
}