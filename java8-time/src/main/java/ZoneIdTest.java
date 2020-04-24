import java.time.ZoneId;

/**
 * @author shishaolong
 * @datatime 2020/4/21 17:50
 */
public class ZoneIdTest {

    // 获取当前时区固定的偏移量
    public static void test1() {
        ZoneId zoneId = ZoneId.systemDefault();
        ZoneId normalized = zoneId.normalized();
        System.out.println(normalized.getId());
    }

    public static void main(String[] args) {
        test1();
    }
}
