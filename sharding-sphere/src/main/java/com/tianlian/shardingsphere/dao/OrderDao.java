package com.tianlian.shardingsphere.dao;

import com.tianlian.shardingsphere.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * @author shishaolong
 * @datatime 2020/8/31 17:52
 */
public interface OrderDao extends CrudRepository<Order, Long>, JpaRepository<Order, Long> {
}
