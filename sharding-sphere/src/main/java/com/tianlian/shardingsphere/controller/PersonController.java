package com.tianlian.shardingsphere.controller;

import com.tianlian.shardingsphere.dao.PersonDao;
import com.tianlian.shardingsphere.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * @author shishaolong
 * @datatime 2020/8/31 15:48
 */
@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    private PersonDao personDao;

    @GetMapping("saveAll")
    public String saveAll() {
        try {
            for (int i = 0; i < 100; i++) {
                Person person = new Person();
                person.setName("少龙" + i).setAge((18 + i) % 18 + i);
                personDao.save(person);
            }
            return "成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "失败";
        }
    }


    @GetMapping("list")
    public List<Person> list() {
        return personDao.findAll();
    }
}
