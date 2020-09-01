package com.tianlian.shardingsphere.dao;

import com.tianlian.shardingsphere.entity.Order;
import com.tianlian.shardingsphere.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * @author shishaolong
 * @datatime 2020/8/31 17:52
 */
public interface PersonDao extends CrudRepository<Person, Long>, JpaRepository<Person, Long> {
}
