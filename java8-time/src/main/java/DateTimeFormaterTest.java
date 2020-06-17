import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author shishaolong
 * @datatime 2020/6/4 16:24
 */
public class DateTimeFormaterTest {

    @Test
    public void test01(){
        LocalDateTime now = LocalDateTime.now();
        String date = now.format(DateTimeFormatter.ISO_DATE);
        System.out.println(date);
    }

    @Test
    public void test02(){
        String dateStr = "2015-02-30";
        LocalDate localDate = LocalDate.parse(dateStr, DateTimeFormatter.ISO_DATE);

        System.out.println(localDate);
    }
}
