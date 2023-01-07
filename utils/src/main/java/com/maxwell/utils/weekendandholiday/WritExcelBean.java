package com.maxwell.utils.weekendandholiday;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * WritExcelBean
 * @author mawei63@jd.com
 * @date 2022/12/2 11:24
 */

@Getter
@Setter
@EqualsAndHashCode
@Accessors(chain = true)
public class WritExcelBean implements Serializable {
    /**
     * 日期
     */
    @ExcelProperty("日期")
    private String date;
    /**
     * 描述
     */
    @ExcelProperty("描述")
    private String desc;
    /**
     * 类型: 休息日，节假日
     */
    @ExcelProperty("类型")
    private String type;

    /**
     * WritExcelBean
     * @param desc
     * @param type
     */
    public WritExcelBean(String date, String desc, String type){
        this.date = date;
        this.desc = desc;
        this.type = type;
    }

    /**
     * 空构造方法
     */
    public WritExcelBean(){

    }

}
