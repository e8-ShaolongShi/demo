package com.tianlian.shardingsphere.controller;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tianlian.shardingsphere.entity.Order;
import com.tianlian.shardingsphere.dao.OrderDao;
import com.tianlian.shardingsphere.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

/**
 * @author shishaolong
 * @datatime 2020/8/31 15:48
 */
@RestController
@RequestMapping("order")
public class OrderController {

//    @Autowired
    private OrderService orderService;

    @GetMapping("saveAll")
    public String saveAll() {
        try {
            for (int i = 0; i < 100; i++) {
                Order order = new Order();
                order.setName("电脑" + i).setType("办公" + i).setGmtCreate(Instant.now());
//                orderService.save(order);
                orderDao.save(order);
            }
            return "成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "失败";
        }
    }

//    @GetMapping("list")
//    public List<Order> list() {
//        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
//        orderQueryWrapper.lambda().orderByAsc(Order::getName);
//        return orderService.list(orderQueryWrapper);
//    }

    @Autowired
    private OrderDao orderDao;
    @GetMapping("list")
    public List<Order> list() {
        return orderDao.findAll();
//        return orderService.list(orderQueryWrapper);
    }

    @GetMapping("list-page")
    public Page<Order> listPage(){
        Page<Order> page = new Page<>();
        page.addOrder(OrderItem.asc("name"));
//        return orderService.page(page, Wrappers.<Order>lambdaQuery().like(Order::getName, "电脑"));
        return null;
    }
}
