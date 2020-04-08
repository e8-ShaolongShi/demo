package cn.juxiewl.menu.dao;

import cn.juxiewl.menu.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * @author shishaolong
 * @datatime 2020/4/7 8:40
 */
public interface IItemDao extends CrudRepository<ItemEntity, Integer>, JpaRepository<ItemEntity, Integer> {

}
