package skm.core.qimen.settings;

import java.util.Date;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * 转盘奇门 - 基础设置
 *
 * @author skm1229
 * @version 2.0.0
 * @blessing ☯福生无量☯
 */
@Data
public class QiMenZhuanPanJiChuSetting {

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别（0:女。1:男）
     */
    private int sex;

    /**
     * 占事
     */
    private String occupy;

    /**
     * 日期
     */
    private Date date;

    /**
     * 日期类型（0:公历。1:农历）
     */
    private int dateType;

    /**
     * 闰月类型（0:不使用闰月。1:使用闰月）
     */
    private int leapMonthType;

    /**
     * 节气类型（0:按天计算。1:按分钟计算）
     */
    private int jieQiType;

    /**
     * 排盘类型（0:年家奇门。1:月家奇门。2:日家奇门。3:时家奇门）
     */
    private int paiPanType;

    /**
     * 值使类型（0:天禽星为值符时，一律用[死门]为值使。1:天禽星为值符时，根据阴阳遁判断。2:天禽星为值符时，根据节气判断）
     */
    private int zhiShiType;

    /**
     * 月家奇门起局类型（0:使用年支起局。1:使用年干支的符头地支起局）
     */
    private int yueJiaQiJuType;

    /**
     * 年干支类型（0:以正月初一作为新年的开始。1:以立春当天作为新年的开始。2:以立春交接时刻作为新年的开始）
     */
    private int yearGanZhiType;

    /**
     * 月干支类型（0:以节交接当天起算。1:以节交接时刻起算）
     */
    private int monthGanZhiType;

    /**
     * 日干支类型（0:晚子时日柱按明天。1:晚子时日柱按当天）
     */
    private int dayGanZhiType;

    /**
     * 时干支类型（0:支持早子时和晚子时）
     */
    private int hourGanZhiType;

//*******************************************************************************************************************************

    /**
     * 初始化设置
     */
    public QiMenZhuanPanJiChuSetting() {

        this.name = ""; // 姓名（默认→ 空）
        this.sex = 1; // 性别（默认→ 男）
        this.occupy = ""; // 占事（默认→ 空）
        this.date = new Date(); // 日期（默认→ 当前时刻）
        this.dateType = 0; // 日期类型（默认→ 公历）
        this.leapMonthType = 0; // 闰月类型（默认→ 不使用闰月）
        this.jieQiType = 1; // 节气类型（默认→ 按分钟计算）
        this.paiPanType = 3; // 排盘类型（默认→ 时家奇门）
        this.zhiShiType = 0; // 值使类型（默认→ 天禽星为值符时，一律用[死门]为值使)
        this.yueJiaQiJuType = 1; // 月家奇门起局类型（默认→ 使用年干支的符头地支起局）
        this.yearGanZhiType = 2; // 年干支类型（默认→ 以立春交接时刻作为新年的开始）
        this.monthGanZhiType = 1; // 月干支类型（默认→ 以节交接时刻起算）
        this.dayGanZhiType = 0; // 日干支类型（默认→ 晚子时日柱按当天）
        this.hourGanZhiType = 0; // 时干支类型（默认→ 支持早子时和晚子时）

    }

//===============================================================================================================================

    /**
     * 姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = StringUtils.isNotBlank(name) ? name : "";
    }

    /**
     * 性别
     *
     * @param sex 性别（0:女。1:男）
     */
    public void setSex(int sex) {
        this.sex = (sex == 0 || sex == 1) ? sex : 1;
    }

    /**
     * 占事
     *
     * @param occupy 占事
     */
    public void setOccupy(String occupy) {
        this.occupy = StringUtils.isNotBlank(occupy) ? occupy : "";
    }

    /**
     * 日期
     *
     * @param date 日期
     */
    public void setDate(Date date) {
        this.date = (null != date) ? date : new Date();
    }

    /**
     * 日期类型
     *
     * @param dateType 日期类型（0:公历。1:农历）
     */
    public void setDateType(int dateType) {
        this.dateType = (dateType == 0 || dateType == 1) ? dateType : 0;
    }

    /**
     * 闰月类型
     *
     * @param leapMonthType 闰月类型（0:不使用闰月。1:使用闰月）
     */
    public void setLeapMonthType(int leapMonthType) {
        this.leapMonthType = (leapMonthType == 0 || leapMonthType == 1) ? leapMonthType : 0;
    }

    /**
     * 节气类型
     *
     * @param jieQiType 节气类型（0:按天计算。1:按分钟计算）
     */
    public void setJieQiType(int jieQiType) {
        this.jieQiType = (jieQiType == 0 || jieQiType == 1) ? jieQiType : 1;
    }

    /**
     * 排盘类型
     *
     * @param paiPanType 排盘类型（0:年家奇门。1:月家奇门。2:日家奇门。3:时家奇门）
     */
    public void setPaiPanType(int paiPanType) {
        this.paiPanType = (paiPanType == 0 || paiPanType == 1 || paiPanType == 2 || paiPanType == 3) ? paiPanType : 3;
    }

    /**
     * 值使类型
     *
     * @param zhiShiType 值使类型（0:天禽星为值符时，一律用[死门]为值使。1:天禽星为值符时，根据阴阳遁判断。2:天禽星为值符时，根据节气判断）
     */
    public void setZhiShiType(int zhiShiType) {
        this.zhiShiType = (zhiShiType == 0 || zhiShiType == 1 || zhiShiType == 2) ? zhiShiType : 0;
    }

    /**
     * 月家奇门起局类型
     *
     * @param yueJiaQiJuType 月家奇门起局类型（0:使用年支起局。1:使用年干支的符头地支起局）
     */
    public void setYueJiaQiJuType(int yueJiaQiJuType) {
        this.yueJiaQiJuType = (yueJiaQiJuType == 0 || yueJiaQiJuType == 1) ? yueJiaQiJuType : 1;
    }

    /**
     * 年干支类型
     *
     * @param yearGanZhiType 年干支类型（0:以正月初一作为新年的开始。1:以立春当天作为新年的开始。2:以立春交接时刻作为新年的开始）
     */
    public void setYearGanZhiType(int yearGanZhiType) {
        this.yearGanZhiType = (yearGanZhiType == 0 || yearGanZhiType == 1 || yearGanZhiType == 2) ? yearGanZhiType : 2;
    }

    /**
     * 月干支类型
     *
     * @param monthGanZhiType 月干支类型（0:以节交接当天起算。1:以节交接时刻起算）
     */
    public void setMonthGanZhiType(int monthGanZhiType) {
        this.monthGanZhiType = (monthGanZhiType == 0 || monthGanZhiType == 1) ? monthGanZhiType : 1;
    }

    /**
     * 日干支类型
     *
     * @param dayGanZhiType 日干支类型（0:晚子时日干支按当天。1:晚子时日干支按明天）
     */
    public void setDayGanZhiType(int dayGanZhiType) {
        this.dayGanZhiType = (dayGanZhiType == 0 || dayGanZhiType == 1) ? dayGanZhiType : 0;
    }

    /**
     * 时干支类型
     *
     * @param hourGanZhiType 时干支类型（0:支持早子时和晚子时）
     */
    public void setHourGanZhiType(int hourGanZhiType) {
        this.hourGanZhiType = (hourGanZhiType == 0 || hourGanZhiType == 1) ? hourGanZhiType : 0;
    }


}


