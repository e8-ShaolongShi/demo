/**
 * Lombok @Builder注解测试
 * 构造者模式 当创建对象需要很多参数时，可以考虑使用构造者模式
 *
 * @author shishaolong
 * @datatime 2020/8/6 14:46
 */
public class LombokBuilderTest {

    public static void main(String[] args) {
        Person person = Person.builder().name("少龙").age(12).occupation("hahah").build();
        System.out.println(person);
    }
}
