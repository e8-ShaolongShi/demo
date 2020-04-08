package cn.juxiewl.menu.dao;

import cn.juxiewl.menu.entity.ItemEntity;
import cn.juxiewl.menu.entity.MenuEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
class IMenuDaoTest {

    @Autowired
    private IMenuDao menuDao;
    @Autowired
    private IItemDao iItemDao;

    @Test
    public void testInsert() {
        List<String> oneCodeList = new ArrayList<>();
        List<String> twoCodeList = new ArrayList<>();
        List<String> threeCodeList = new ArrayList<>();
        List<MenuEntity> menuEntityList = new ArrayList<>();
        List<ItemEntity> itemEntityList = new ArrayList<>();
        for (int i = 0; i < 1500; i++) {
            MenuEntity menuEntity = null;
            if (i < 10) { // 一级菜单
                int j = i + 1;
                String code = getCode(j);
                oneCodeList.add(code);
                menuEntity = new MenuEntity();
                menuEntity.setName("一级菜单：" + j).setCode(code);
            } else if (i < 200) {
                int j = i - 10 + 1;
                int randI = new Random().nextInt(oneCodeList.size());
                String code = oneCodeList.get(randI) + getCode(j);
                twoCodeList.add(code);
                menuEntity = new MenuEntity().setName("二级菜单：" + j).setCode(code);
            } else if (i < 500) {
                int j = i - 200 + 1;
                int randI = new Random().nextInt(twoCodeList.size());
                String code = twoCodeList.get(randI) + getCode(j);
                threeCodeList.add(code);
                menuEntity = new MenuEntity().setName("三级菜单：" + j).setCode(code);
            } else {
                int j = i - 500 + 1;
                int randI = new Random().nextInt(threeCodeList.size());
                String code = threeCodeList.get(randI) + getCode(j);
                ItemEntity itemEntity = new ItemEntity().setCode(code).setName("四级菜单:" + j);
                itemEntityList.add(itemEntity);
            }
            if (menuEntity != null) {
                menuEntityList.add(menuEntity);
            }
        }
        menuDao.saveAll(menuEntityList);
        iItemDao.saveAll(itemEntityList);
    }

    @Test
    public void testFindAll() {
        menuDao.findAll().forEach(System.out::println);
    }

    private String getCode(int i) {
        String code = "000" + i;
        return code.substring(code.length() - 3);
    }
}