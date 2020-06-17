import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

/**
 * 万年历中的字段
 *
 * @author shishaolong
 * @datatime 2020/6/4 11:41
 */
public class ChronoFieldTest {
    @Test
    public void test01() {
//        Instant now = Instant.now();
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        for (ChronoField chronoField : ChronoField.values()) {
            checkPrint(chronoField, now);
        }
        System.out.println("------ instant.now() -------");
        Instant instantNow = Instant.now();
        for (ChronoField chronoField : ChronoField.values()) {
            checkPrint(chronoField, instantNow);
        }
    }

    private void checkPrint(ChronoField chronoField, Temporal now) {
        String fieldName = chronoField.toString();
        if (now.isSupported(chronoField)) {
            long l = now.getLong(chronoField);
            String content = String.format("-%s: %s", fieldName, l);
            System.out.println(content);
        } else {
            System.out.println("-" + fieldName + ": 不支持此字段");
        }
    }

    @Test
    public void test02() {
        for (ChronoUnit chronoUnit : ChronoUnit.values()) {
            String content = String.format("%s: %s", chronoUnit.name(), chronoUnit.getDuration());
            System.out.println(content);
        }
        Duration yearsDuration = ChronoUnit.YEARS.getDuration();
        long month = yearsDuration.get(ChronoUnit.MONTHS);
        long day = yearsDuration.get(ChronoUnit.DAYS);
        long minutes = yearsDuration.get(ChronoUnit.MINUTES);
        long seconds = yearsDuration.get(ChronoUnit.SECONDS);
        String content = String.format("1年 %s： %s月， %s天， %分钟， %s秒",
                ChronoUnit.YEARS.getDuration(), month, day, minutes, seconds);
        System.out.println(content);
        System.out.println("年是否是估算值：" + ChronoUnit.YEARS.isDurationEstimated());
    }

    @Test
    public void test03() {
        LocalDateTime now = LocalDateTime.now();
        for (ChronoUnit chronoUnit : ChronoUnit.values()) {
            boolean flag = now.isSupported(chronoUnit);
            if (flag) {
                String content = String.format("-%s 支持", chronoUnit.name());
                System.out.println(content);
            } else {
                String content = String.format("-%s 不支持", chronoUnit.name());
                System.out.println(content);
            }
        }
    }
}
