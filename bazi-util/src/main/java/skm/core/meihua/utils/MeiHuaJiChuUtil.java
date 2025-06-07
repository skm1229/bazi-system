package skm.core.meihua.utils;

import java.util.*;

import skm.utils.CommonUtil;
import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;
import skm.core.liuyao.maps.LiuYaoJiChuMap;
import skm.core.meihua.maps.MeiHuaJiChuMap;
import skm.core.meihua.settings.MeiHuaJiChuSetting;

/**
 * 梅花易数 - 基础工具
 *
 * @author skm1229
 * @version 2.0.0
 * @blessing ☯福生无量☯
 */
public class MeiHuaJiChuUtil {

    /**
     * 获取日期
     *
     * @param setting 梅花易数 - 基础设置
     * @return 日期
     */
    public static Map<String, Object> getDate(MeiHuaJiChuSetting setting) {

        Solar solar;
        Lunar lunar;

        // 1、判断日期类型
        Date date = setting.getDate(); // 日期
        if (setting.getDateType() == 0) {
            // 1.1、按公历日期计算
            solar = new Solar(date); // 公历日期
            lunar = solar.getLunar(); // 农历日期
        } else {
            // 1.2、按农历日期计算
            try {
                lunar = new Lunar(date.getYear() + 1900, setting.getLeapMonthType() == 0 ? (date.getMonth() + 1) : -(date.getMonth() + 1), date.getDate(), date.getHours(), date.getMinutes(), date.getSeconds()); // 农历日期
                solar = lunar.getSolar(); // 公历日期
            } catch (IllegalArgumentException e) {
//                lunar = new Lunar(); // 当前农历日期
//                solar = lunar.getSolar(); // 当前公历日期
                throw new IllegalArgumentException(e);
            }
        }

        // 2、添加日期并返回
        Map<String, Object> map = new HashMap<>();
        map.put("solar", solar); // 公历日期
        map.put("lunar", lunar); // 农历日期
        return map;

    }

    /**
     * 获取干支
     *
     * @param setting 梅花易数 - 基础设置
     * @param lunar   农历日期
     * @return 干支
     */
    public static Map<String, List<String>> getGanZhi(MeiHuaJiChuSetting setting, Lunar lunar) {

        List<String> list;
        Map<String, List<String>> map = new HashMap<>();

        // 1、判断年干支类型
        if (setting.getYearGanZhiType() == 0) {
            list = Arrays.asList(lunar.getYearGan(), lunar.getYearZhi(), lunar.getYearInGanZhi()); // 以正月初一作为新年的开始
        } else if (setting.getYearGanZhiType() == 1) {
            list = Arrays.asList(lunar.getYearGanByLiChun(), lunar.getYearZhiByLiChun(), lunar.getYearInGanZhiByLiChun()); // 以立春当天作为新年的开始
        } else {
            list = Arrays.asList(lunar.getYearGanExact(), lunar.getYearZhiExact(), lunar.getYearInGanZhiExact()); // 以立春交接的时刻作为新年的开始
        }
        map.put("yearGanZhi", list);

        // 2、判断月干支类型
        if (setting.getMonthGanZhiType() == 0) {
            list = Arrays.asList(lunar.getMonthGan(), lunar.getMonthZhi(), lunar.getMonthInGanZhi()); // 以节交接当天起算
        } else {
            list = Arrays.asList(lunar.getMonthGanExact(), lunar.getMonthZhiExact(), lunar.getMonthInGanZhiExact()); // 以节交接时刻起算
        }
        map.put("monthGanZhi", list);

        // 3、判断日干支类型
        if (setting.getDayGanZhiType() == 0) {
            list = Arrays.asList(lunar.getDayGanExact(), lunar.getDayZhiExact(), lunar.getDayInGanZhiExact()); // 晚子时日干支算明天
        } else {
            list = Arrays.asList(lunar.getDayGanExact2(), lunar.getDayZhiExact2(), lunar.getDayInGanZhiExact2()); // 晚子时日干支算当天
        }
        map.put("dayGanZhi", list);

        // 4、支持早子时和晚子时
        map.put("hourGanZhi", Arrays.asList(lunar.getTimeGan(), lunar.getTimeZhi(), lunar.getTimeInGanZhi()));

        // 5、返回干支
        return map;

    }

    /**
     * 获取上卦数、下卦数、动爻数（★可用于排盘模式扩展）
     *
     * @param meiHuaJiChuSetting 梅花易数 - 基础设置
     * @param lunar              农历日期
     * @param yearZhi            年支
     * @param hourZhi            时支
     * @return 上卦数、下卦数、动爻数
     */
    public static Map<String, Integer> getNumber(MeiHuaJiChuSetting meiHuaJiChuSetting, Lunar lunar, String yearZhi, String hourZhi) {

        Map<String, Integer> map = new HashMap<>();

        int shangGuaNumber = 0; // 上卦数
        int xiaGuaNumber = 0; // 下卦数
        int dongYaoNumber = 0; // 动爻数

        if (meiHuaJiChuSetting.getPaiPanType() == 0) {
            // ★★★ 1.1、日期排盘模式
            Map<String, Integer> diZhiShuMap = LiuYaoJiChuMap.DI_ZHI_SHU; // 地支对应的数字（地支为键）
            int yearNumber = diZhiShuMap.get(yearZhi);  // 年数
            int monthNumber = lunar.getMonth(); // 月数
            int dayNumber = lunar.getDay(); // 日数
            int hourNumber = diZhiShuMap.get(hourZhi); // 时数
            shangGuaNumber = shangGuaNumber(yearNumber + monthNumber + dayNumber); // 上卦数：（年数+月数+日数）÷8得出余数
            xiaGuaNumber = xiaGuaNumber(yearNumber + monthNumber + dayNumber + hourNumber); // 下卦数：（年数+月数+日数+时数）÷8得出余数
            dongYaoNumber = dongYaoNumber(yearNumber + monthNumber + dayNumber + hourNumber); // 动爻数：（年数+月数+日数+时数）÷6得出余数
        } else if (meiHuaJiChuSetting.getPaiPanType() == 1) {
            // ★★★ 1.2、自动排盘模式
            shangGuaNumber = CommonUtil.randomList(8); // 随机产生1~8中的1个数字
            xiaGuaNumber = CommonUtil.randomList(8); // 随机产生1~8中的1个数字
            dongYaoNumber = CommonUtil.randomList(6); // 随机产生1~6中的1个数字
        } else if (meiHuaJiChuSetting.getPaiPanType() == 2) {
            // ★★★ 1.3、数字排盘模式
            String number = String.valueOf(meiHuaJiChuSetting.getPaiPanShu());
            shangGuaNumber = shangGuaNumber(Integer.parseInt(String.valueOf(number.charAt(0)))); // 上卦数
            xiaGuaNumber = xiaGuaNumber(Integer.parseInt(String.valueOf(number.charAt(1)))); // 下卦数
            dongYaoNumber = dongYaoNumber(Integer.parseInt(String.valueOf(number.charAt(2)))); // 动爻数
        } else if (meiHuaJiChuSetting.getPaiPanType() == 3) {
            // ★★★ 1.4、单数排盘模式
            String panPanDanShu = String.valueOf(meiHuaJiChuSetting.getPaiPanDanShu()); // 排盘单数
            int length = panPanDanShu.length(); // 排盘单数的位数
            // 计算前半段数字之和、后半段数字之和
            int number1Count = 0; // 前半段数字之和
            int number2Count = 0; // 后半段数字之和
            if (length > 1) {
                // 前半段数字取0~（总位数÷2）位，后半段数字取（总位数÷2）~最后一位
                String number1 = panPanDanShu.substring(0, length / 2);
                String number2 = panPanDanShu.substring(length / 2, length);
                for (int i = 0; i < number1.length(); i++) {
                    number1Count += Integer.parseInt(number1.split("")[i]);
                }
                for (int i = 0; i < number2.length(); i++) {
                    number2Count += Integer.parseInt(number2.split("")[i]);
                }
            }
            shangGuaNumber = shangGuaNumber(number1Count); // 上卦数
            xiaGuaNumber = xiaGuaNumber(number2Count); // 下卦数
            dongYaoNumber = dongYaoNumber(number1Count + number2Count); // 动爻数
        } else if (meiHuaJiChuSetting.getPaiPanType() == 4) {
            // ★★★ 1.5、双数排盘模式
            int paiPanShuangShuOne = meiHuaJiChuSetting.getPaiPanShuangShuOne(); // 排盘双数1
            int paiPanShuangShuTwo = meiHuaJiChuSetting.getPaiPanShuangShuTwo(); // 排盘双数2
            String paiPanShuangShuOneStr = String.valueOf(paiPanShuangShuOne); // 排盘双数1
            String paiPanShuangShuTwoStr = String.valueOf(paiPanShuangShuTwo); // 排盘双数2
            // 双数求和计算上下卦
            if (meiHuaJiChuSetting.getPaiPanShuangShuShangXiaGuaType() == 0) {
                paiPanShuangShuOne = 0;
                paiPanShuangShuTwo = 0;
                // 计算排盘双数1的数字之和
                for (int i = 0; i < paiPanShuangShuOneStr.length(); i++) {
                    paiPanShuangShuOne += Integer.parseInt(paiPanShuangShuOneStr.split("")[i]);
                }
                // 计算排盘双数2的数字之和
                for (int i = 0; i < paiPanShuangShuTwoStr.length(); i++) {
                    paiPanShuangShuTwo += Integer.parseInt(paiPanShuangShuTwoStr.split("")[i]);
                }
            }
            int hourNumber = paiPanShuangShuOne + paiPanShuangShuTwo;
            // 双数求和加时辰数计算动爻
            if (meiHuaJiChuSetting.getPaiPanShuangShuDongYaoType() == 1) {
                hourNumber += MeiHuaJiChuMap.DI_ZHI_SHU.get(hourZhi);
            }
            shangGuaNumber = shangGuaNumber(paiPanShuangShuOne); // 上卦数
            xiaGuaNumber = xiaGuaNumber(paiPanShuangShuTwo); // 下卦数
            dongYaoNumber = dongYaoNumber(hourNumber); // 动爻数
        } else if (meiHuaJiChuSetting.getPaiPanType() == 5) {
            // ★★★ 1.6、在此可扩展更多排盘模式（建议：将每种排盘模式封装为单独方法）
            System.out.println("======================");
            System.out.println("在此可扩展更多排盘模式");
            System.out.println("======================");
        }

        // 2、设置并返回数据
        map.put("shangGuaNumber", shangGuaNumber); // 保存上卦数
        map.put("xiaGuaNumber", xiaGuaNumber); // 保存下卦数
        map.put("dongYaoNumber", dongYaoNumber); // 保存动爻数
        return map;

    }

    /**
     * 返回用卦与体卦关系
     *
     * @param shangGua     上卦
     * @param xiaGua       下卦
     * @param mark         本卦、变卦、互卦、错卦、综卦
     * @param shangGuaYong 上卦是否为用卦（true:用卦。false:体卦）
     * @return 用卦与体卦关系
     */
    public static String getLink(String shangGua, String xiaGua, String mark, boolean shangGuaYong) {

        String yongGua; // 用卦
        String tiGua; // 体卦
        String yongGuaMark; // 用卦标识
        String tiGuaMark; // 体卦标识

        // 1、判断上卦，下卦
        if (shangGuaYong) {
            yongGua = shangGua; // 用卦为上卦
            yongGuaMark = "上卦";
            tiGua = xiaGua; // 体卦为下卦
            tiGuaMark = "下卦";
        } else {
            tiGua = shangGua; // 体卦为上卦
            tiGuaMark = "上卦";
            yongGua = xiaGua; // 用卦为下卦
            yongGuaMark = "下卦";
        }

        // 2、获取用卦与体卦之间的关系
        Map<String, String> baGuaWuXing = MeiHuaJiChuMap.BA_GUA_WU_XING; // 八卦五行
        String info = mark + yongGuaMark + yongGua + "(" + baGuaWuXing.get(yongGua) + ")为用卦、" + tiGuaMark + tiGua + "(" + baGuaWuXing.get(tiGua) + ")为体卦。";
        Map<String, String> yongTiGuanXi = MeiHuaJiChuMap.YONG_TI_GUAN_XI; // 用卦与体卦的关系（用卦+体卦）

        // 3、返回信息
        return info + yongTiGuanXi.get(yongGua + tiGua);

    }

//------------------------------------------------------------------------------------------------------------------------------------

    /**
     * 计算上卦数
     *
     * @param number 初始数
     * @return 上卦数
     */
    private static Integer shangGuaNumber(int number) {

        int shangGuaNumber = number % 8;
        return shangGuaNumber == 0 ? 8 : shangGuaNumber; // 若除尽则统一用8来表示

    }

    /**
     * 计算下卦数
     *
     * @param number 初始数
     * @return 下卦数
     */
    private static Integer xiaGuaNumber(int number) {

        int xiaGuaNumber = number % 8;
        return xiaGuaNumber == 0 ? 8 : xiaGuaNumber; // 若除尽则统一用8来表示

    }

    /**
     * 计算动爻数
     *
     * @param number 初始数
     * @return 动爻数
     */
    private static Integer dongYaoNumber(int number) {

        int dongYaoNumber = number % 6;
        return dongYaoNumber == 0 ? 6 : dongYaoNumber; // 若除尽则统一用6来表示

    }


}


