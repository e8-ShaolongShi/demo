package cn.juxiewl.websocket;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class test {

    @Test
   public void test02(){
//        List<Boolean> list = new ArrayList<>();
//        list.add(false);
//        list.add(true);
//        list.add(false);
//        list.add(true);
//        boolean b = list.stream().anyMatch(a -> a == false);
//        System.out.println(b);

        boolean flag = false;
        System.out.println(flag);
        flag = flag ^ false;
        System.out.println(flag);
        flag = flag ^ false;
        System.out.println(flag);
        flag = flag ^ false;
        System.out.println(flag);
    }

}
