import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author shishaolong
 * @datatime 2020/4/22 9:00
 */
public class LocalDateTest {

   public static void test1(){
       LocalDateTime localDateTime = LocalDate.now().atStartOfDay();
       System.out.println(localDateTime);
       int i = LocalDate.now().lengthOfYear();

       System.out.println(i);
   }

    public static void main(String[] args) {
        test1();
    }
}
