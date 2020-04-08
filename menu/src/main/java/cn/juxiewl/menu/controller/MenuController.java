package cn.juxiewl.menu.controller;

import cn.juxiewl.menu.dao.IMenuList;
import cn.juxiewl.menu.entity.MenuEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author shishaolong
 * @datatime 2020/4/8 10:11
 */
@RestController
public class MenuController {

    @Autowired
    private IMenuList menuList;

    @GetMapping("/api/menu")
    public List<MenuEntity> list() {
        List<MenuEntity> list = menuList.list();
        return list;
    }

    @GetMapping("/api/one")
    public MenuEntity one() {
        return new MenuEntity().setCode("lksjfkls").setName("测试");
    }
}
