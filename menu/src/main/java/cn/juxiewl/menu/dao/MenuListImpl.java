package cn.juxiewl.menu.dao;

import cn.juxiewl.menu.entity.ItemEntity;
import cn.juxiewl.menu.entity.MenuEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shishaolong
 * @datatime 2020/4/8 10:19
 */
@Service
public class MenuListImpl implements IMenuList {

    @Autowired
    private IMenuDao menuDao;
    @Autowired
    private IItemDao itemDao;

    @Override
    public List<MenuEntity> list() {

        Iterable<ItemEntity> itemList = itemDao.findAll(Sort.by("code"));
        Iterable<MenuEntity> menuList = menuDao.findAll(Sort.by("code"));
        HashMap<String, MenuEntity> oneMenu = new LinkedHashMap<>();
        HashMap<String, MenuEntity> twoMenu = new LinkedHashMap<>();
        HashMap<String, MenuEntity> threeMenu = new LinkedHashMap<>();
        menuList.forEach(menuEntity -> {
            int length = menuEntity.getCode().length();
            if (length == 3) {
                oneMenu.put(menuEntity.getCode(), menuEntity);
            } else if (length == 6) {
                twoMenu.put(menuEntity.getCode(), menuEntity);
            } else {
                threeMenu.put(menuEntity.getCode(), menuEntity);
            }
        });

        itemList.forEach(itemEntity -> {
            String code = itemEntity.getCode();
            String pidCode = code.substring(0, code.length() - 3);
            threeMenu.get(pidCode).getMenuList().add(itemEntity);
        });

        threeMenu.entrySet().parallelStream().forEachOrdered(entry -> {
            String code = entry.getValue().getCode();
            String pidCode = code.substring(0, code.length() - 3);
            twoMenu.get(pidCode).getMenuList().add(entry.getValue());
        });

        twoMenu.entrySet().parallelStream().forEachOrdered(entry -> {
            String code = entry.getValue().getCode();
            String pidCode = code.substring(0, code.length() - 3);
            oneMenu.get(pidCode).getMenuList().add(entry.getValue());
        });

        List<MenuEntity> menuEntityList = oneMenu.entrySet()
                .parallelStream()
                .map(entry -> entry.getValue())
                .collect(Collectors.toList());

        return menuEntityList;
    }
}
