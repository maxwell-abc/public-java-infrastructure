package com.maxwell.utils.weekendandholiday;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 休息日信息处理类
 * @author mawei63@jd.com
 * @date 2023/1/7 14:15
 */

@Slf4j
@Component
public class WeekendAndHoliday {

    /**
     * 获取指定年份的所有周末
     * @param year
     * @return
     */
    public List<WritExcelBean> getWeekendDay(String year) {
        String startTime = year + "-01-01";
        String endTime = year + "12-31";
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate startLocalDate = LocalDate.parse(startTime, formatter1);
        LocalDate endLocalDate = LocalDate.parse(endTime, formatter1);

        List<WritExcelBean> writeToExcelList = new ArrayList<>();

        while (endLocalDate.compareTo(startLocalDate) >= 0) {
            if (startLocalDate.getDayOfWeek() == DayOfWeek.SATURDAY || startLocalDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                String date = startLocalDate.format(formatter2);
                WritExcelBean writExcelBean = new WritExcelBean(date, HolidayTypeEnum.WEEKDAY.getDesc(), HolidayTypeEnum.WEEKDAY.label);
                writeToExcelList.add(writExcelBean);
                log.info("date: {}", date);
            }
            startLocalDate = startLocalDate.plusDays(1);
        }

        log.info("startLocalDate: {},endLocalDate: {}", startLocalDate, endLocalDate);
        return writeToExcelList;
    }

    /**
     * 获取当前年份的所有的周末
     * @return
     */
    public List<WritExcelBean> getWeekendDay(){
        LocalDate localDateNow = LocalDate.now();
        String year = String.valueOf(localDateNow.getYear());
        return this.getWeekendDay(year);
    }

    /**
     * 从文本文件读取节假日数据
     * @return
     */
    public List<WritExcelBean> readHolidayFromTxtFile(String filePath,String fileName){
        // String filePath = "classpath:/";
        // String fileName = "Holiday2023Year.txt";
        String file = getFileName(filePath, fileName);
        List<WritExcelBean> result = new ArrayList<>();
        try(InputStreamReader isr = new InputStreamReader(new FileInputStream(file),"UTF-8");
            BufferedReader bufferedReader = new BufferedReader(isr)){
            String line;
            while ((line = bufferedReader.readLine())!=null){
                String[] lineArr = line.split("\\t");
                List<String> lineList = Arrays.asList(lineArr);
                // lineList.forEach(System.out::println);
                WritExcelBean writExcelBean = new WritExcelBean();
                writExcelBean.setDate(lineList.get(0)).setDesc(lineList.get(1)).setType(lineList.get(2));
                result.add(writExcelBean);
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }

        return result;
    }

    /**
     * @param path
     * @param name
     * @return
     */
    private String getFileName(String path, String name) {
        return path + name;
    }


    /**
     * 节假日枚举类
     */
    private enum HolidayTypeEnum {
        /**
         * 休息日
         */
        WEEKDAY(0, "休息日", "休息日"),
        /**
         * 节假日
         */
        HOLIDAY(1, "节假日", "节假日");
        /**
         * code
         */
        private final Integer code;
        /**
         * label
         */
        private final String label;
        /**
         * desc
         */
        private final String desc;

        /**
         * 构造函数
         *
         * @param code
         * @param label
         * @param desc
         */
        HolidayTypeEnum(Integer code, String label, String desc) {
            this.code = code;
            this.label = label;
            this.desc = desc;
        }

        /**
         * getLabel
         *
         * @return
         */
        public String getLabel() {
            return this.label;
        }

        /**
         * getDesc
         *
         * @return
         */
        public String getDesc() {
            return this.desc;
        }
    }
}
