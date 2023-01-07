import com.maxwell.utils.weekendandholiday.WeekendAndHoliday;
import lombok.Setter;
import lombok.experimental.StandardException;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * 节假日功能测试类
 * @author mawei63@jd.com
 * @date 2023/1/7 14:23
 */

@Slf4j
public class WeekendAndHolidayTest {

    /**
     * 节假日实例
     */
    @Resource
    private WeekendAndHoliday weekendAndHoliday;

    @Test
    public void getYearFirstDay(){
        String year = "2023";
        String yearLocalDate = year+"-01-01";
        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDateYear = LocalDate.parse(yearLocalDate,yearFormatter);
        LocalDate nowDate = LocalDate.now();

        LocalDate firstDayOfYear = nowDate.with(TemporalAdjusters.firstDayOfYear());
        log.info("current year first Day: {}",firstDayOfYear);
    }
}
