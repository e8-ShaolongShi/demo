import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.chrono.Chronology;
import java.time.temporal.IsoFields;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalQueries;

/**
 * @author shishaolong
 * @datatime 2020/6/4 15:56
 */
public class TemporalQueriesTest {

    @Test
    public void test01(){
        LocalDateTime now = LocalDateTime.now();
        print(IsoFields.DAY_OF_QUARTER, now);
        print(IsoFields.QUARTER_OF_YEAR, now);
        print(IsoFields.WEEK_BASED_YEAR, now);
        print(IsoFields.WEEK_OF_WEEK_BASED_YEAR, now);
//        print(IsoFields.QUARTER_YEARS, now);
//        print(IsoFields.WEEK_BASED_YEARS, now);
    }

    private void print(TemporalField temporalField, Temporal temporal){
        System.out.println(temporalField.toString()+": "+temporal.get(temporalField));
    }

    @Test
    public void test02(){
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        LocalDate date = now.query(TemporalQueries.localDate());
        System.out.println(date);
        Chronology chronology = now.query(TemporalQueries.chronology());
        System.out.println(chronology);
        ZoneOffset zoneOffset = now.query(TemporalQueries.offset());
        System.out.println(zoneOffset);
    }
}
