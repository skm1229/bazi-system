package skm.core.meihua;

import java.util.*;

import skm.utils.DateUtil;
import skm.utils.CommonUtil;
import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;
import skm.core.meihua.maps.MeiHuaJiChuMap;
import skm.core.meihua.utils.MeiHuaJiChuUtil;
import skm.core.meihua.settings.MeiHuaJiChuSetting;

/**
 * 梅花易数
 *
 * @author skm1229
 * @version 2.0.0
 * @blessing ☯福生无量☯
 */
public class MeiHua {

    /**
     * 梅花 - 基础设置
     */
    private MeiHuaJiChuSetting meiHuaJiChuSetting;

    /**
     * 公历日期
     */
    private Solar solar;
    /**
     * 农历日期
     */
    private Lunar lunar;

    /**
     * 年干
     */
    private String yearGan;
    /**
     * 月干
     */
    private String monthGan;
    /**
     * 日干
     */
    private String dayGan;
    /**
     * 时干
     */
    private String hourGan;

    /**
     * 年支
     */
    private String yearZhi;
    /**
     * 月支
     */
    private String monthZhi;
    /**
     * 日支
     */
    private String dayZhi;
    /**
     * 时支
     */
    private String hourZhi;

    /**
     * 年干支
     */
    private String yearGanZhi;
    /**
     * 月干支
     */
    private String monthGanZhi;
    /**
     * 日干支
     */
    private String dayGanZhi;
    /**
     * 时干支
     */
    private String hourGanZhi;

    /**
     * 上卦数
     */
    private int shangGuaNumber;
    /**
     * 下卦数
     */
    private int xiaGuaNumber;
    /**
     * 动爻数
     */
    private int dongYaoNumber;
    /**
     * 动爻数（文字）
     */
    private String dongYaoNumberStr;

    /**
     * 变卦卦名
     */
    private String bianGuaName;


    /**
     * 上卦是否为用卦（true:用卦。false:体卦）
     */
    protected Boolean shangGuaYong;
    /**
     * 下卦是否为用卦（true:用卦。false:体卦）
     */
    protected Boolean xiaGuaYong;

//*******************************************************************************************************************************

    /**
     * 使用基础设置初始化
     *
     * @param meiHuaJiChuSetting 梅花 - 基础设置
     */
    public MeiHua(MeiHuaJiChuSetting meiHuaJiChuSetting) {

        this.meiHuaJiChuSetting = meiHuaJiChuSetting; // 基础设置

        // 1、获取日期
        Map<String, Object> date = MeiHuaJiChuUtil.getDate(meiHuaJiChuSetting);
        this.solar = (Solar) date.get("solar"); // 公历日期
        this.lunar = (Lunar) date.get("lunar"); // 农历日期

        // 2、获取干支
        Map<String, List<String>> ganZhi = MeiHuaJiChuUtil.getGanZhi(meiHuaJiChuSetting, this.lunar);
        List<String> yearGanZhi = ganZhi.get("yearGanZhi");
        this.yearGan = yearGanZhi.get(0); // 年干
        this.yearZhi = yearGanZhi.get(1); // 年支
        this.yearGanZhi = yearGanZhi.get(2); // 年干支
        List<String> monthGanZhi = ganZhi.get("monthGanZhi");
        this.monthGan = monthGanZhi.get(0); // 月干
        this.monthZhi = monthGanZhi.get(1); // 月支
        this.monthGanZhi = monthGanZhi.get(2); // 月干支
        List<String> dayGanZhi = ganZhi.get("dayGanZhi");
        this.dayGan = dayGanZhi.get(0); // 日干
        this.dayZhi = dayGanZhi.get(1); // 日支
        this.dayGanZhi = dayGanZhi.get(2); // 日干支
        List<String> hourGanZhi = ganZhi.get("hourGanZhi");
        this.hourGan = hourGanZhi.get(0); // 时干
        this.hourZhi = hourGanZhi.get(1); // 时支
        this.hourGanZhi = hourGanZhi.get(2); // 时干支

        // 3、初始化必要数据
        initializeNumber(meiHuaJiChuSetting); // 初始化排盘数

    }

//===============================================================================================================================

    /**
     * 获取公历日期（Solar型）
     *
     * @return 公历日期（如：2024-01-01）
     */
    public Solar getSolar() {
        return this.solar;
    }

    /**
     * 获取农历日期（Lunar型）
     *
     * @return 农历日期（如：二〇二三年冬月二十）
     */
    public Lunar getLunar() {
        return this.lunar;
    }

    /**
     * 获取公历日期（String型）
     *
     * @return 公历日期（如：2024年01月01日00时00分00秒）
     */
    public String getSolarStr() {
        return DateUtil.getSolarStr(this.solar);
    }

    /**
     * 获取农历日期（String型）
     *
     * @return 农历日期（如：二〇二三年冬月二十(早)子时）
     */
    public String getLunarStr() {
        return DateUtil.getLunarStr(this.lunar);
    }

    /**
     * 获取公历日期（Date型）
     *
     * @return 公历日期（如：Mon Jan 01 00:00:00 CST 2024）
     */
    public Date getSolarDate() {
        return DateUtil.stringToDate(DateUtil.getSolarStr(this.solar));
    }

    /**
     * 获取农历日期（Date型）
     *
     * @return 农历日期（如：Mon Nov 20 00:00:00 CST 2023）
     */
    public Date getLunarDate() {
        return DateUtil.stringToDate(DateUtil.getLunarStr2(this.lunar));
    }


    /**
     * 获取姓名
     *
     * @return 姓名（如：命主）
     */
    public String getName() {
        return this.meiHuaJiChuSetting.getName();
    }

    /**
     * 获取性别
     *
     * @return 性别（如：男）
     */
    public String getSex() {
        return this.meiHuaJiChuSetting.getSex() == 0 ? "女" : "男";
    }

    /**
     * 获取占事
     *
     * @return 占事（如：未填写）
     */
    public String getOccupy() {
        return this.meiHuaJiChuSetting.getOccupy();
    }

    /**
     * 获取造
     *
     * @return 造（如：乾造）
     */
    public String getZao() {
        return this.meiHuaJiChuSetting.getSex() == 0 ? "坤造" : "乾造";
    }

    /**
     * 获取太岁类型
     *
     * @return 太岁类型，若无数据则返回null（如：值）
     */
    public String getTaiSuiType() {

        if (this.meiHuaJiChuSetting.getYearGanZhiType() == 0) {
            return MeiHuaJiChuMap.TAI_SUI_TYPE.get(new Lunar().getYearZhi() + this.yearZhi); // 以正月初一起算
        } else if (this.meiHuaJiChuSetting.getYearGanZhiType() == 1) {
            MeiHuaJiChuMap.TAI_SUI_TYPE.get(new Lunar().getYearZhiByLiChun() + this.yearZhi); // 以立春当天起算
        } else if (this.meiHuaJiChuSetting.getYearGanZhiType() == 2) {
            MeiHuaJiChuMap.TAI_SUI_TYPE.get(new Lunar().getYearZhiExact() + this.yearZhi); // 以立春交接时刻起算
        }

        return MeiHuaJiChuMap.TAI_SUI_TYPE.get(new Lunar().getYearZhi() + this.yearZhi);

    }

    /**
     * 获取星期
     *
     * @return 星期（如：周一）
     */
    public String getXingQi() {
        return "周" + this.lunar.getWeekInChinese();
    }

    /**
     * 获取季节
     *
     * @return 季节（如：孟春）
     */
    public String getJiJie() {
        return this.lunar.getSeason();
    }

    /**
     * 获取生肖
     *
     * @return 生肖（如：鼠）
     */
    public String getShengXiao() {

        if (this.meiHuaJiChuSetting.getYearGanZhiType() == 0) {
            return this.lunar.getYearShengXiao(); // 以正月初一起算
        } else if (this.meiHuaJiChuSetting.getYearGanZhiType() == 1) {
            return this.lunar.getYearShengXiaoByLiChun(); // 以立春当天起算
        } else if (this.meiHuaJiChuSetting.getYearGanZhiType() == 2) {
            return this.lunar.getYearShengXiaoExact(); // 以立春交接时刻起算
        }

        return this.lunar.getYearShengXiao();

    }

    /**
     * 获取星座
     *
     * @return 星座（如：魔羯座）
     */
    public String getXingZuo() {
        return this.solar.getXingZuo() + "座";
    }

    /**
     * 获取五不遇时
     *
     * @return 五不遇时（true:符合。false:不符合）
     */
    public boolean getWuBuYuShi() {
        return this.hourGanZhi.equals(MeiHuaJiChuMap.WU_BU_YU_SHI.get(this.dayGan));
    }


    /**
     * 获取年干
     *
     * @return 年干（如：甲）
     */
    public String getYearGan() {
        return this.yearGan;
    }

    /**
     * 获取月干
     *
     * @return 月干（如：甲）
     */
    public String getMonthGan() {
        return this.monthGan;
    }

    /**
     * 获取日干
     *
     * @return 日干（如：甲）
     */
    public String getDayGan() {
        return this.dayGan;
    }

    /**
     * 获取时干
     *
     * @return 时干（如：甲）
     */
    public String getHourGan() {
        return this.hourGan;
    }


    /**
     * 获取年支
     *
     * @return 年支（如：子）
     */
    public String getYearZhi() {
        return this.yearZhi;
    }

    /**
     * 获取月支
     *
     * @return 月支（如：子）
     */
    public String getMonthZhi() {
        return this.monthZhi;
    }

    /**
     * 获取日支
     *
     * @return 日支（如：子）
     */
    public String getDayZhi() {
        return this.dayZhi;
    }

    /**
     * 获取时支
     *
     * @return 时支（如：子）
     */
    public String getHourZhi() {
        return this.hourZhi;
    }


    /**
     * 获取年干支
     *
     * @return 年干支（如：甲子）
     */
    public String getYearGanZhi() {
        return this.yearGanZhi;
    }

    /**
     * 获取月干支
     *
     * @return 月干支（如：甲子）
     */
    public String getMonthGanZhi() {
        return this.monthGanZhi;
    }

    /**
     * 获取日干支
     *
     * @return 日干支（如：甲子）
     */
    public String getDayGanZhi() {
        return this.dayGanZhi;
    }

    /**
     * 获取时干支
     *
     * @return 时干支（如：甲子）
     */
    public String getHourGanZhi() {
        return this.hourGanZhi;
    }


    /**
     * 获取年干五行
     *
     * @return 年干五行（如：木）
     */
    public String getYearGanWuXing() {
        return MeiHuaJiChuMap.TIAN_GAN_WU_XING.get(this.yearGan);
    }

    /**
     * 获取月干五行
     *
     * @return 月干五行（如：木）
     */
    public String getMonthGanWuXing() {
        return MeiHuaJiChuMap.TIAN_GAN_WU_XING.get(this.monthGan);
    }

    /**
     * 获取日干五行
     *
     * @return 日干五行（如：木）
     */
    public String getDayGanWuXing() {
        return MeiHuaJiChuMap.TIAN_GAN_WU_XING.get(this.dayGan);
    }

    /**
     * 获取时干五行
     *
     * @return 时干五行（如：木）
     */
    public String getHourGanWuXing() {
        return MeiHuaJiChuMap.TIAN_GAN_WU_XING.get(this.hourGan);
    }


    /**
     * 获取年支五行
     *
     * @return 年支五行（如：木）
     */
    public String getYearZhiWuXing() {
        return MeiHuaJiChuMap.DI_ZHI_WU_XING.get(this.yearZhi);
    }

    /**
     * 获取月支五行
     *
     * @return 月支五行（如：木）
     */
    public String getMonthZhiWuXing() {
        return MeiHuaJiChuMap.DI_ZHI_WU_XING.get(this.monthZhi);
    }

    /**
     * 获取日支五行
     *
     * @return 日支五行（如：木）
     */
    public String getDayZhiWuXing() {
        return MeiHuaJiChuMap.DI_ZHI_WU_XING.get(this.dayZhi);
    }

    /**
     * 获取时支五行
     *
     * @return 时支五行（如：木）
     */
    public String getHourZhiWuXing() {
        return MeiHuaJiChuMap.DI_ZHI_WU_XING.get(this.hourZhi);
    }


    /**
     * 获取年干支五行
     *
     * @return 年干支五行（如：木水）
     */
    public String getYearGanZhiWuXing() {
        return getYearGanWuXing() + getYearZhiWuXing();
    }

    /**
     * 获取月干支五行
     *
     * @return 月干支五行（如：木水）
     */
    public String getMonthGanZhiWuXing() {
        return getMonthGanWuXing() + getMonthZhiWuXing();
    }

    /**
     * 获取日干支五行
     *
     * @return 日干支五行（如：木水）
     */
    public String getDayGanZhiWuXing() {
        return getDayGanWuXing() + getDayZhiWuXing();
    }

    /**
     * 获取时干支五行
     *
     * @return 时干支五行（如：木水）
     */
    public String getHourGanZhiWuXing() {
        return getHourGanWuXing() + getHourZhiWuXing();
    }


    /**
     * 获取年干支纳音
     *
     * @return 年干支纳音（如：天上火）
     */
    public String getYearGanZhiNaYin() {
        return MeiHuaJiChuMap.NA_YIN.get(this.yearGanZhi);
    }

    /**
     * 获取月干支纳音
     *
     * @return 月干支纳音（如：天上火）
     */
    public String getMonthGanZhiNaYin() {
        return MeiHuaJiChuMap.NA_YIN.get(this.monthGanZhi);
    }

    /**
     * 获取日干支纳音
     *
     * @return 日干支纳音（如：天上火）
     */
    public String getDayGanZhiNaYin() {
        return MeiHuaJiChuMap.NA_YIN.get(this.dayGanZhi);
    }

    /**
     * 获取时干支纳音
     *
     * @return 时干支纳音（如：天上火）
     */
    public String getHourGanZhiNaYin() {
        return MeiHuaJiChuMap.NA_YIN.get(this.hourGanZhi);
    }


    /**
     * 获取年干支空亡
     *
     * @return 年干支空亡（如：子丑）
     */
    public String getYearGanZhiKongWang() {
        return MeiHuaJiChuMap.KONG_WANG.get(this.yearGanZhi);
    }

    /**
     * 获取月干支空亡
     *
     * @return 月干支空亡（如：子丑）
     */
    public String getMonthGanZhiKongWang() {
        return MeiHuaJiChuMap.KONG_WANG.get(this.monthGanZhi);
    }

    /**
     * 获取日干支空亡
     *
     * @return 日干支空亡（如：子丑）
     */
    public String getDayGanZhiKongWang() {
        return MeiHuaJiChuMap.KONG_WANG.get(this.dayGanZhi);
    }

    /**
     * 获取时干支空亡
     *
     * @return 时干支空亡（如：子丑）
     */
    public String getHourGanZhiKongWang() {
        return MeiHuaJiChuMap.KONG_WANG.get(this.hourGanZhi);
    }


    /**
     * 获取上一节
     *
     * @return 上一节（如：大雪）
     */
    public String getPrevJie() {
        return getLunar().getPrevJie(this.meiHuaJiChuSetting.getJieQiType() == 0).toString();
    }

    /**
     * 获取上一节日期
     *
     * @return 上一节日期（如：2023-12-07 17:32:44）
     */
    public String getPrevJieDateStr() {
        return getLunar().getPrevJie(this.meiHuaJiChuSetting.getJieQiType() == 0).getSolar().toYmdHms();
    }

    /**
     * 获取距上一节天数
     *
     * @return 距上一节天数（如：24）
     */
    public int getPrevJieDay() {
        return Math.toIntExact(DateUtil.dateInterval(getPrevJieDateStr(), this.solar.toYmdHms()).get("days"));
    }

    /**
     * 获取下一节
     *
     * @return 下一节（如：小寒）
     */
    public String getNextJie() {
        return getLunar().getNextJie(this.meiHuaJiChuSetting.getJieQiType() == 0).toString();
    }

    /**
     * 获取下一节日期
     *
     * @return 下一节日期（如：2024-01-06 04:49:08）
     */
    public String getNextJieDateStr() {
        return getLunar().getNextJie(this.meiHuaJiChuSetting.getJieQiType() == 0).getSolar().toYmdHms();
    }

    /**
     * 获取距下一节天数
     *
     * @return 距下一节天数（如：5）
     */
    public int getNextJieDay() {
        return Math.toIntExact(DateUtil.dateInterval(this.solar.toYmdHms(), getNextJieDateStr()).get("days"));
    }

    /**
     * 获取出生节
     *
     * @return 出生节（如：大雪后24天6小时27分16秒、小寒前5天4小时49分8秒）
     */
    public String getChuShengJie() {

        Map<String, Long> prevMap = DateUtil.dateInterval(getPrevJieDateStr(), this.solar.toYmdHms()); // 计算上一节气与排盘日期的时间间隔
        Map<String, Long> nextMap = DateUtil.dateInterval(this.solar.toYmdHms(), getNextJieDateStr()); // 计算排盘日期与下一节气的时间间隔

        long prevDay = prevMap.get("days") > 0 ? prevMap.get("days") : -prevMap.get("days"); // 天
        long prevHours = prevMap.get("hours") > 0 ? prevMap.get("hours") : -prevMap.get("hours"); // 小时
        long prevMinutes = prevMap.get("minutes") > 0 ? prevMap.get("minutes") : -prevMap.get("minutes"); // 分
        long prevSeconds = prevMap.get("seconds") > 0 ? prevMap.get("seconds") : -prevMap.get("seconds"); // 秒

        long nextDay = nextMap.get("days") > 0 ? nextMap.get("days") : -nextMap.get("days"); // 天
        long nextHours = nextMap.get("hours") > 0 ? nextMap.get("hours") : -nextMap.get("hours"); // 小时
        long nextMinutes = nextMap.get("minutes") > 0 ? nextMap.get("minutes") : -nextMap.get("minutes"); // 分
        long nextSeconds = nextMap.get("seconds") > 0 ? nextMap.get("seconds") : -nextMap.get("seconds"); // 秒

        String prevStr = getPrevJie() + "后" + prevDay + "天" + prevHours + "小时" + prevMinutes + "分" + prevSeconds + "秒";
        String nextStr = getNextJie() + "前" + nextDay + "天" + nextHours + "小时" + nextMinutes + "分" + nextSeconds + "秒";

        return prevStr + "、" + nextStr;

    }


    /**
     * 获取上一气
     *
     * @return 上一气（如：冬至）
     */
    public String getPrevQi() {
        return getLunar().getPrevQi(this.meiHuaJiChuSetting.getJieQiType() == 0).toString();
    }

    /**
     * 获取上一气日期
     *
     * @return 上一气日期（如：2023-12-22 11:27:09）
     */
    public String getPrevQiDateStr() {
        return getLunar().getPrevQi(this.meiHuaJiChuSetting.getJieQiType() == 0).getSolar().toYmdHms();
    }

    /**
     * 获取距上一气天数
     *
     * @return 距上一气天数（如：9）
     */
    public int getPrevQiDay() {
        return Math.toIntExact(DateUtil.dateInterval(getPrevQiDateStr(), this.solar.toYmdHms()).get("days"));
    }

    /**
     * 获取下一气
     *
     * @return 下一气（如：大寒）
     */
    public String getNextQi() {
        return getLunar().getNextQi(this.meiHuaJiChuSetting.getJieQiType() == 0).toString();
    }

    /**
     * 获取下一气日期
     *
     * @return 下一气日期（如：2024-01-20 22:07:08）
     */
    public String getNextQiDateStr() {
        return getLunar().getNextQi(this.meiHuaJiChuSetting.getJieQiType() == 0).getSolar().toYmdHms();
    }

    /**
     * 获取距下一气天数
     *
     * @return 距下一节天数（如：19）
     */
    public int getNextQiDay() {
        return Math.toIntExact(DateUtil.dateInterval(this.solar.toYmdHms(), getNextQiDateStr()).get("days"));
    }

    /**
     * 获取出生气
     *
     * @return 出生气（如：冬至后9天12小时32分51秒、大寒前19天22小时7分8秒）
     */
    public String getChuShengQi() {

        Map<String, Long> prevMap = DateUtil.dateInterval(getPrevQiDateStr(), this.solar.toYmdHms()); // 计算上一节气与排盘日期的时间间隔
        Map<String, Long> nextMap = DateUtil.dateInterval(this.solar.toYmdHms(), getNextQiDateStr()); // 计算排盘日期与下一节气的时间间隔

        long prevDay = prevMap.get("days") > 0 ? prevMap.get("days") : -prevMap.get("days"); // 天
        long prevHours = prevMap.get("hours") > 0 ? prevMap.get("hours") : -prevMap.get("hours"); // 小时
        long prevMinutes = prevMap.get("minutes") > 0 ? prevMap.get("minutes") : -prevMap.get("minutes"); // 分
        long prevSeconds = prevMap.get("seconds") > 0 ? prevMap.get("seconds") : -prevMap.get("seconds"); // 秒

        long nextDay = nextMap.get("days") > 0 ? nextMap.get("days") : -nextMap.get("days"); // 天
        long nextHours = nextMap.get("hours") > 0 ? nextMap.get("hours") : -nextMap.get("hours"); // 小时
        long nextMinutes = nextMap.get("minutes") > 0 ? nextMap.get("minutes") : -nextMap.get("minutes"); // 分
        long nextSeconds = nextMap.get("seconds") > 0 ? nextMap.get("seconds") : -nextMap.get("seconds"); // 秒

        String prevStr = getPrevQi() + "后" + prevDay + "天" + prevHours + "小时" + prevMinutes + "分" + prevSeconds + "秒";
        String nextStr = getNextQi() + "前" + nextDay + "天" + nextHours + "小时" + nextMinutes + "分" + nextSeconds + "秒";

        return prevStr + "、" + nextStr;

    }


    /**
     * 获取月相
     *
     * @return 月相（如：朔）
     */
    public String getYueXiang() {
        return this.lunar.getYueXiang();
    }

    /**
     * 获取月将
     *
     * @return 月将（如：子）
     */
    public String getYueJiang() {
        return MeiHuaJiChuMap.YUE_JIANG.get(getPrevQi() + getNextQi()).get(0);
    }

    /**
     * 获取月将神
     *
     * @return 月将神（如：神后）
     */
    public String getYueJiangShen() {
        return MeiHuaJiChuMap.YUE_JIANG.get(getPrevQi() + getNextQi()).get(1);
    }


    /**
     * 获取上卦卦名
     *
     * @return 上卦卦名（如：乾）
     */
    public String getShangGuaName() {
        return MeiHuaJiChuMap.XIAN_TIAN_BA_GUA.get(this.shangGuaNumber);
    }

    /**
     * 获取下卦卦名
     *
     * @return 下卦卦名（如：乾）
     */
    public String getXiaGuaName() {
        return MeiHuaJiChuMap.XIAN_TIAN_BA_GUA.get(this.xiaGuaNumber);
    }

    /**
     * 获取本卦卦名
     *
     * @return 本卦卦名（如：乾为天）
     */
    public String getBenGuaName() {
        return MeiHuaJiChuMap.LIU_SHI_SI_GUA_NAME.get(Arrays.asList(this.shangGuaNumber, this.xiaGuaNumber));
    }

    /**
     * 获取变卦卦名
     *
     * @return 变卦卦名（如：乾为天）
     */
    public String getBianGuaName() {
        return this.bianGuaName;
    }

    /**
     * 获取互卦卦名
     *
     * @return 互卦卦名（如：乾为天）
     */
    public String getHuGuaName() {
        return MeiHuaJiChuMap.LIU_SHI_SI_GUA.get(getHuGuaAs());
    }

    /**
     * 获取错卦卦名
     *
     * @return 错卦卦名（如：乾为天）
     */
    public String getCuoGuaName() {
        return MeiHuaJiChuMap.LIU_SHI_SI_GUA.get(getCuoGuaAs());
    }

    /**
     * 获取综卦卦名
     *
     * @return 综卦卦名（如：乾为天）
     */
    public String getZongGuaName() {
        return MeiHuaJiChuMap.LIU_SHI_SI_GUA.get(getZongGuaAs());
    }


    /**
     * 获取上卦卦象
     *
     * @return 上卦卦象（如：☰）
     */
    public String getShangGuaAs() {
        return MeiHuaJiChuMap.BA_GUA_AS.get(getShangGuaName());
    }

    /**
     * 获取下卦卦象
     *
     * @return 下卦卦象（如：☰）
     */
    public String getXiaGuaAs() {
        return MeiHuaJiChuMap.BA_GUA_AS.get(getXiaGuaName());
    }

    /**
     * 获取本卦卦象
     *
     * @return 本卦卦象（如：䷀）
     */
    public String getBenGuaAs() {
        return MeiHuaJiChuMap.LIU_SHI_SI_GUA_AS.get(getBenGuaName());
    }

    /**
     * 获取变卦卦象
     *
     * @return 变卦卦象（如：䷀）
     */
    public String getBianGuaAs() {
        return MeiHuaJiChuMap.LIU_SHI_SI_GUA_AS.get(getBianGuaName());
    }

    /**
     * 获取互卦卦象
     *
     * @return 互卦卦象（如：䷀）
     */
    public String getHuGuaAs() {
        return MeiHuaJiChuMap.HU_CUO_ZONG_FU.get(getBenGuaAs()).get(0);
    }

    /**
     * 获取错卦卦象
     *
     * @return 错卦卦象（如：䷀）
     */
    public String getCuoGuaAs() {
        return MeiHuaJiChuMap.HU_CUO_ZONG_FU.get(getBenGuaAs()).get(1);
    }

    /**
     * 获取综卦卦象
     *
     * @return 综卦卦象（如：䷀）
     */
    public String getZongGuaAs() {
        return MeiHuaJiChuMap.HU_CUO_ZONG_FU.get(getBenGuaAs()).get(2);
    }


    /**
     * 获取本卦卦辞
     *
     * @return 本卦卦辞（如：元，亨，利，贞。）
     */
    public String getBenGuaGuaCi() {
        return MeiHuaJiChuMap.LIU_SHI_SI_GUA_SHEN_CI.get(getBenGuaName()).get(2);
    }

    /**
     * 获取变卦卦辞
     *
     * @return 变卦卦辞（如：元，亨，利，贞。）
     */
    public String getBianGuaGuaCi() {
        return MeiHuaJiChuMap.LIU_SHI_SI_GUA_SHEN_CI.get(getBianGuaName()).get(2);
    }

    /**
     * 获取互卦卦辞
     *
     * @return 互卦卦辞（如：元，亨，利，贞。）
     */
    public String getHuGuaGuaCi() {
        return MeiHuaJiChuMap.LIU_SHI_SI_GUA_SHEN_CI.get(getHuGuaName()).get(2);
    }

    /**
     * 获取错卦卦辞
     *
     * @return 错卦卦辞（如：元，亨，利，贞。）
     */
    public String getCuoGuaGuaCi() {
        return MeiHuaJiChuMap.LIU_SHI_SI_GUA_SHEN_CI.get(getCuoGuaName()).get(2);
    }

    /**
     * 获取综卦卦辞
     *
     * @return 综卦卦辞（如：元，亨，利，贞。）
     */
    public String getZongGuaGuaCi() {
        return MeiHuaJiChuMap.LIU_SHI_SI_GUA_SHEN_CI.get(getZongGuaName()).get(2);
    }


    /**
     * 获取本卦的上卦卦名
     *
     * @return 本卦的上卦卦名（如：乾）
     */
    public String getBenGuaShangGuaName() {
        return MeiHuaJiChuMap.LIU_SHI_SI_GUA_TWO_GUA_NAME.get(getBenGuaName()).get(0);
    }

    /**
     * 获取变卦的上卦卦名
     *
     * @return 变卦的上卦卦名（如：乾）
     */
    public String getBianGuaShangGuaName() {
        return MeiHuaJiChuMap.LIU_SHI_SI_GUA_TWO_GUA_NAME.get(getBianGuaName()).get(0);
    }

    /**
     * 获取互卦的上卦卦名
     *
     * @return 互卦的上卦卦名（如：乾）
     */
    public String getHuGuaShangGuaName() {
        return MeiHuaJiChuMap.LIU_SHI_SI_GUA_TWO_GUA_NAME.get(getHuGuaName()).get(0);
    }

    /**
     * 获取错卦的上卦卦名
     *
     * @return 错卦的上卦卦名（如：乾）
     */
    public String getCuoGuaShangGuaName() {
        return MeiHuaJiChuMap.LIU_SHI_SI_GUA_TWO_GUA_NAME.get(getCuoGuaName()).get(0);
    }

    /**
     * 获取综卦的上卦卦名
     *
     * @return 综卦的上卦卦名（如：乾）
     */
    public String getZongGuaShangGuaName() {
        return MeiHuaJiChuMap.LIU_SHI_SI_GUA_TWO_GUA_NAME.get(getZongGuaName()).get(0);
    }


    /**
     * 获取本卦的下卦卦名
     *
     * @return 本卦的下卦卦名（如：乾）
     */
    public String getBenGuaXiaGuaName() {
        return MeiHuaJiChuMap.LIU_SHI_SI_GUA_TWO_GUA_NAME.get(getBenGuaName()).get(1);
    }

    /**
     * 获取变卦的下卦卦名
     *
     * @return 变卦的下卦卦名（如：乾）
     */
    public String getBianGuaXiaGuaName() {
        return MeiHuaJiChuMap.LIU_SHI_SI_GUA_TWO_GUA_NAME.get(getBianGuaName()).get(1);
    }

    /**
     * 获取互卦的下卦卦名
     *
     * @return 互卦的下卦卦名（如：乾）
     */
    public String getHuGuaXiaGuaName() {
        return MeiHuaJiChuMap.LIU_SHI_SI_GUA_TWO_GUA_NAME.get(getHuGuaName()).get(1);
    }

    /**
     * 获取错卦的下卦卦名
     *
     * @return 错卦的下卦卦名（如：乾）
     */
    public String getCuoGuaXiaGuaName() {
        return MeiHuaJiChuMap.LIU_SHI_SI_GUA_TWO_GUA_NAME.get(getCuoGuaName()).get(1);
    }

    /**
     * 获取综卦的下卦卦名
     *
     * @return 综卦的下卦卦名（如：乾）
     */
    public String getZongGuaXiaGuaName() {
        return MeiHuaJiChuMap.LIU_SHI_SI_GUA_TWO_GUA_NAME.get(getZongGuaName()).get(1);
    }


    /**
     * 获取本卦的上卦卦象
     *
     * @return 本卦的上卦卦象（如：☰）
     */
    public String getBenGuaShangGuaAs() {
        return MeiHuaJiChuMap.BA_GUA_AS.get(getBenGuaShangGuaName());
    }

    /**
     * 获取变卦的上卦卦象
     *
     * @return 变卦的上卦卦象（如：☰）
     */
    public String getBianGuaShangGuaAs() {
        return MeiHuaJiChuMap.BA_GUA_AS.get(getBianGuaShangGuaName());
    }

    /**
     * 获取互卦的上卦卦象
     *
     * @return 互卦的上卦卦名（如：☰）
     */
    public String getHuGuaShangGuaAs() {
        return MeiHuaJiChuMap.BA_GUA_AS.get(getHuGuaShangGuaName());
    }

    /**
     * 获取错卦的上卦卦象
     *
     * @return 错卦的上卦卦象（如：☰）
     */
    public String getCuoGuaShangGuaAs() {
        return MeiHuaJiChuMap.BA_GUA_AS.get(getCuoGuaShangGuaName());
    }

    /**
     * 获取综卦的上卦卦象
     *
     * @return 综卦的上卦卦象（如：☰）
     */
    public String getZongGuaShangGuaAs() {
        return MeiHuaJiChuMap.BA_GUA_AS.get(getZongGuaShangGuaName());
    }


    /**
     * 获取本卦的下卦卦象
     *
     * @return 本卦的下卦卦象（如：☰）
     */
    public String getBenGuaXiaGuaAs() {
        return MeiHuaJiChuMap.BA_GUA_AS.get(getBenGuaXiaGuaName());
    }

    /**
     * 获取变卦的下卦卦象
     *
     * @return 变卦的下卦卦象（如：☰）
     */
    public String getBianGuaXiaGuaAs() {
        return MeiHuaJiChuMap.BA_GUA_AS.get(getBianGuaXiaGuaName());
    }

    /**
     * 获取互卦的下卦卦象
     *
     * @return 互卦的下卦卦名象（如：☰）
     */
    public String getHuGuaXiaGuaAs() {
        return MeiHuaJiChuMap.BA_GUA_AS.get(getHuGuaXiaGuaName());
    }

    /**
     * 获取错卦的下卦卦象
     *
     * @return 错卦的下卦卦象（如：☰）
     */
    public String getCuoGuaXiaGuaAs() {
        return MeiHuaJiChuMap.BA_GUA_AS.get(getCuoGuaXiaGuaName());
    }

    /**
     * 获取综卦的下卦卦象
     *
     * @return 综卦的下卦卦象（如：☰）
     */
    public String getZongGuaXiaGuaAs() {
        return MeiHuaJiChuMap.BA_GUA_AS.get(getZongGuaXiaGuaName());
    }


    /**
     * 获取本卦六爻爻名
     *
     * @return 本卦六爻爻名（如：[初九, 九二, 九三, 九四, 九五, 上九]）
     */
    public List<String> getBenGuaLiuYaoName() {
        return MeiHuaJiChuMap.LIU_SHI_SI_GUA_LIU_YAO_YAO_MING.get(getBenGuaAs());
    }

    /**
     * 获取变卦六爻爻名
     *
     * @return 变卦六爻爻名（如：[初九, 九二, 九三, 九四, 九五, 上九]）
     */
    public List<String> getBianGuaLiuYaoName() {
        return MeiHuaJiChuMap.LIU_SHI_SI_GUA_LIU_YAO_YAO_MING.get(getBianGuaAs());
    }

    /**
     * 获取互卦六爻爻名
     *
     * @return 互卦六爻爻名（如：[初九, 九二, 九三, 九四, 九五, 上九]）
     */
    public List<String> getHuGuaLiuYaoName() {
        return MeiHuaJiChuMap.LIU_SHI_SI_GUA_LIU_YAO_YAO_MING.get(getHuGuaAs());
    }

    /**
     * 获取错卦六爻爻名
     *
     * @return 错卦六爻爻名（如：[初九, 九二, 九三, 九四, 九五, 上九]）
     */
    public List<String> getCuoGuaLiuYaoName() {
        return MeiHuaJiChuMap.LIU_SHI_SI_GUA_LIU_YAO_YAO_MING.get(getCuoGuaAs());
    }

    /**
     * 获取综卦六爻爻名
     *
     * @return 综卦六爻爻名（如：[初九, 九二, 九三, 九四, 九五, 上九]）
     */
    public List<String> getZongGuaLiuYaoName() {
        return MeiHuaJiChuMap.LIU_SHI_SI_GUA_LIU_YAO_YAO_MING.get(getZongGuaAs());
    }


    /**
     * 获取本卦六爻爻象
     *
     * @return 本卦六爻爻象（如：[—, —, —, —, —, —]）
     */
    public List<String> getBenGuaLiuYaoAs() {
        return MeiHuaJiChuMap.LIU_SHI_SI_GUA_LIU_YAO_AS.get(getBenGuaAs());
    }

    /**
     * 获取变卦六爻爻象
     *
     * @return 变卦六爻爻象（如：[—, —, —, —, —, —]）
     */
    public List<String> getBianGuaLiuYaoAs() {
        return MeiHuaJiChuMap.LIU_SHI_SI_GUA_LIU_YAO_AS.get(getBianGuaAs());
    }

    /**
     * 获取互卦六爻爻象
     *
     * @return 互卦六爻爻象（如：[—, —, —, —, —, —]）
     */
    public List<String> getHuGuaLiuYaoAs() {
        return MeiHuaJiChuMap.LIU_SHI_SI_GUA_LIU_YAO_AS.get(getHuGuaAs());
    }

    /**
     * 获取错卦六爻爻象
     *
     * @return 错卦六爻爻象（如：[—, —, —, —, —, —]）
     */
    public List<String> getCuoGuaLiuYaoAs() {
        return MeiHuaJiChuMap.LIU_SHI_SI_GUA_LIU_YAO_AS.get(getCuoGuaAs());
    }

    /**
     * 获取综卦六爻爻象
     *
     * @return 综卦六爻爻象（如：[—, —, —, —, —, —]）
     */
    public List<String> getZongGuaLiuYaoAs() {
        return MeiHuaJiChuMap.LIU_SHI_SI_GUA_LIU_YAO_AS.get(getZongGuaAs());
    }


    /**
     * 获取本卦六爻爻辞
     *
     * @return 本卦六爻爻辞（如：[潜龙勿用。, 见龙在田，利见大人。, 君子终日乾乾，夕惕若厉，无咎。, 或跃在渊，无咎。, 飞龙在天，利见大人。, 亢龙有悔。]）
     */
    public List<String> getBenGuaLiuYaoYaoCi() {
        return MeiHuaJiChuMap.LIU_SHI_SI_GUA_LIU_YAO_YAO_CI.get(getBenGuaAs());
    }

    /**
     * 获取变卦六爻爻辞
     *
     * @return 变卦六爻爻辞（如：[潜龙勿用。, 见龙在田，利见大人。, 君子终日乾乾，夕惕若厉，无咎。, 或跃在渊，无咎。, 飞龙在天，利见大人。, 亢龙有悔。]）
     */
    public List<String> getBianGuaLiuYaoYaoCi() {
        return MeiHuaJiChuMap.LIU_SHI_SI_GUA_LIU_YAO_YAO_CI.get(getBianGuaAs());
    }

    /**
     * 获取互卦六爻爻辞
     *
     * @return 互卦六爻爻辞（如：[潜龙勿用。, 见龙在田，利见大人。, 君子终日乾乾，夕惕若厉，无咎。, 或跃在渊，无咎。, 飞龙在天，利见大人。, 亢龙有悔。]）
     */
    public List<String> getHuGuaLiuYaoYaoCi() {
        return MeiHuaJiChuMap.LIU_SHI_SI_GUA_LIU_YAO_YAO_CI.get(getHuGuaAs());
    }

    /**
     * 获取错卦六爻爻辞
     *
     * @return 错卦六爻爻辞（如：[潜龙勿用。, 见龙在田，利见大人。, 君子终日乾乾，夕惕若厉，无咎。, 或跃在渊，无咎。, 飞龙在天，利见大人。, 亢龙有悔。]）
     */
    public List<String> getCuoGuaLiuYaoYaoCi() {
        return MeiHuaJiChuMap.LIU_SHI_SI_GUA_LIU_YAO_YAO_CI.get(getCuoGuaAs());
    }

    /**
     * 获取综卦六爻爻辞
     *
     * @return 综卦六爻爻辞（如：[潜龙勿用。, 见龙在田，利见大人。, 君子终日乾乾，夕惕若厉，无咎。, 或跃在渊，无咎。, 飞龙在天，利见大人。, 亢龙有悔。]）
     */
    public List<String> getZongGuaLiuYaoYaoCi() {
        return MeiHuaJiChuMap.LIU_SHI_SI_GUA_LIU_YAO_YAO_CI.get(getZongGuaAs());
    }


    /**
     * 获取本卦关系
     *
     * @return 本卦六爻爻辞（如：火雷噬嗑卦代表事物初始、开始阶段的信息或目前的情况；）
     */
    public String getBenGuaLink() {
        return MeiHuaJiChuUtil.getLink(getBenGuaShangGuaName(), getBenGuaXiaGuaName(), getBenGuaName(), this.shangGuaYong);
    }

    /**
     * 获取变卦关系
     *
     * @return 变卦六爻爻辞（如：震为雷卦代表事物发展变化的最终结果；）
     */
    public String getBianGuaLink() {
        return MeiHuaJiChuUtil.getLink(getBianGuaShangGuaName(), getBianGuaXiaGuaName(), getBianGuaName(), this.shangGuaYong);
    }

    /**
     * 获取互卦关系
     *
     * @return 互卦六爻爻辞（如：水山蹇卦代表事物发展的过程；）
     */
    public String getHuGuaLink() {
        return MeiHuaJiChuUtil.getLink(getHuGuaShangGuaName(), getHuGuaXiaGuaName(), getHuGuaName(), this.shangGuaYong);
    }

    /**
     * 获取错卦关系
     *
     * @return 错卦六爻爻辞（如：水风井卦代表事物的危机或转机；）
     */
    public String getCuoGuaLink() {
        return MeiHuaJiChuUtil.getLink(getCuoGuaShangGuaName(), getCuoGuaXiaGuaName(), getCuoGuaName(), this.shangGuaYong);
    }

    /**
     * 获取综卦关系
     *
     * @return 综卦六爻爻辞（如：山火贲卦代表事物的另一个面，以第三者角度综合观察这个事物；）
     */
    public String getZongGuaLink() {
        return MeiHuaJiChuUtil.getLink(getZongGuaShangGuaName(), getZongGuaXiaGuaName(), getZongGuaName(), this.shangGuaYong);
    }


    /**
     * 获取动爻
     *
     * @return 动爻（如：6）
     */
    public Integer getDongYao() {
        return this.dongYaoNumber;
    }

    /**
     * 获取动爻文字
     *
     * @return 动爻文字（如：六）
     */
    public String getDongYaoStr() {
        return this.dongYaoNumberStr;
    }

    /**
     * 获取卦码
     *
     * @return 卦码（如：346）
     */
    public String getGuaMa() {
        return "" + this.shangGuaNumber + this.xiaGuaNumber + this.dongYaoNumber;
    }

//-------------------------------------------------------------------------------------------------------------------------------

    /**
     * 初始化上卦数、下卦数、动爻数
     *
     * @param meiHuaJiChuSetting 梅花易数 - 基础设置
     */
    protected void initializeNumber(MeiHuaJiChuSetting meiHuaJiChuSetting) {

        // 1、获取上卦数、下卦数、动爻数（★可用于排盘模式扩展）
        Map<String, Integer> map = MeiHuaJiChuUtil.getNumber(meiHuaJiChuSetting, this.lunar, this.yearZhi, this.hourZhi);
        this.shangGuaNumber = map.get("shangGuaNumber"); // 设置上卦数
        this.xiaGuaNumber = map.get("xiaGuaNumber"); // 设置下卦数
        this.dongYaoNumber = map.get("dongYaoNumber"); // 设置动爻数
        this.dongYaoNumberStr = CommonUtil.shuToHan(map.get("dongYaoNumber")); // 设置动爻数（文字）

        // 2、判断上卦是否为用卦、下卦是否为用卦
        this.shangGuaYong = this.dongYaoNumber > 3; // 上卦是否为用卦
        this.xiaGuaYong = !this.shangGuaYong; // 下卦是否为用卦

        // 3、计算变卦卦名
        int bianShangGuaNumber; // 变卦的上卦数
        int bianXiaGuaNumber; // 变卦的下卦数
        if (this.dongYaoNumber >= 1 && this.dongYaoNumber <= 3) {
            // 1.1、动爻数为(1\2\3)时：上卦不变、下卦变
            bianXiaGuaNumber = MeiHuaJiChuMap.BIAN_GUA.get(this.xiaGuaNumber).get(this.dongYaoNumber - 1); // 下卦数
            this.bianGuaName = MeiHuaJiChuMap.LIU_SHI_SI_GUA_NAME.get(Arrays.asList(this.xiaGuaNumber, bianXiaGuaNumber)); // 根据上卦数和下卦数获取变卦
        } else {
            // 1.2、动爻数为(4\5\6)时：上卦变、下卦不变
            bianShangGuaNumber = MeiHuaJiChuMap.BIAN_GUA.get(this.shangGuaNumber).get(this.dongYaoNumber - 4); // 上卦数
            this.bianGuaName = MeiHuaJiChuMap.LIU_SHI_SI_GUA_NAME.get(Arrays.asList(bianShangGuaNumber, this.xiaGuaNumber)); // 根据上卦数和下卦数获取变卦
        }

    }

    @Override
    public String toString() {

        return "公历:" + getSolarStr() + "   " +
                "农历:" + getLunarStr() + "   " +
                "星期:" + getXingQi() + "   " +
                "季节:" + getJiJie() + "   " +
                "生肖:" + getShengXiao() + "   " +
                "星座:" + getXingZuo() + "   " +
                "月相:" + getYueXiang() + "   " +
                "月将:" + getYueJiang() + "   " +
                "月将神:" + getYueJiangShen() + "   " +
                "五不遇时:" + getWuBuYuShi() + "   " +
                "卦码:" + getGuaMa() + "   " +
                "上卦:" + getShangGuaName() + "(" + getShangGuaAs() + ")" + "   " +
                "下卦:" + getXiaGuaName() + "(" + getXiaGuaAs() + ")" + "   " +
                "动爻:" + getDongYao() + "   " +
                "本卦:" + getBenGuaName() + "(" + getBenGuaAs() + ")" + "   " +
                "变卦:" + getBianGuaName() + "(" + getBianGuaAs() + ")" + "   " +
                "互卦:" + getHuGuaName() + "(" + getHuGuaAs() + ")" + "   " +
                "错卦:" + getCuoGuaName() + "(" + getCuoGuaAs() + ")" + "   " +
                "综卦:" + getZongGuaName() + "(" + getZongGuaAs() + ")";

    }


}


