import java.math.BigDecimal;
import java.time.*;

/**
 * @author shishaolong
 * @datatime 2020/4/24 13:55
 */
public class OffsetDateTimeTest {

    public static void t1() {
        OffsetDateTime now = OffsetDateTime.now();
        System.out.println(now);
        LocalDateTime now1 = LocalDateTime.now();
        System.out.println(now1);
    }

    /**
     * Instant、OffsetDateTime、ZoneDatetime区别
     */
    public static void t2() {
        Instant instant = Instant.now();
        System.out.println("instant:" + instant);
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime:" + localDateTime);
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        System.out.println("offsetDateTime:" + offsetDateTime);
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("zonedDateTime:" + zonedDateTime);
    }

    /**
     * ZoneOffset
     */
    public static void t3() {
        ZoneOffset utc = ZoneOffset.UTC;
        System.out.println("utc: " + utc);
        ZoneId zoneId = ZoneOffset.systemDefault();
        System.out.println("zoneId: " + zoneId);
        ZoneOffset zoneOffset = ZoneOffset.of("+1");
        System.out.println(zoneOffset);

        System.out.println("UTC-id: " + utc.getId());
    }

    public static void main(String[] args) {
//        t1();
//        t2();
//        t3();
        BigDecimal bigDecimal = new BigDecimal(".23");
        System.out.println(bigDecimal);
    }
}
