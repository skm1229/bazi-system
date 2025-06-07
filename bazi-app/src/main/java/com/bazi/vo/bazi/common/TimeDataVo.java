package com.bazi.vo.bazi.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 时间周期数据封装类
 *
 * @author skm1229
 * @version 1.0.0
 */
@Data
@Schema(description = "时间周期数据封装")
public class TimeDataVo {

    @Schema(description = "干支", example = "甲寅")
    private String ganZhi;

    @Schema(description = "主星（天干十神）", example = "偏财")
    private String zhuXing;

    @Schema(description = "副星（地支十神）", example = "正印")
    private String fuXing;

    @Schema(description = "公历日期", example = "2003年")
    private String solarDate;

    @Schema(description = "公历年", example = "2003")
    private String solarYear;

    @Schema(description = "年龄", example = "25")
    private String age;

    @Schema(description = "起始年龄", example = "1")
    private Integer startAge;

    @Schema(description = "结束年龄", example = "10")
    private Integer endAge;

    // 流月专用字段
    @Schema(description = "公历几月几日", example = "1月1日")
    private String solarMonthDay;

    @Schema(description = "十二节农历月", example = "正月")
    private String lunarMonth;

    @Schema(description = "十二节", example = "立春")
    private String shiErJie;

    // 流日专用字段
    @Schema(description = "公历日", example = "1日")
    private String solarDay;

    @Schema(description = "农历日", example = "初一")
    private String lunarDay;

    // 流时专用字段
    @Schema(description = "公历时", example = "23时")
    private String solarHour;

    @Schema(description = "十二时辰汉代命名", example = "子时")
    private String shiErShiChenHaMing;

    // ========== 静态封装方法 ==========

    /**
     * 大运初始化专用封装
     * 数据结构: [干支, 天干十神, 地支十神, 公历日期, 公历年, 年龄]
     */
    public static List<TimeDataVo> daYunEncapsulation(List<List<String>> daYun) {
        List<TimeDataVo> lists = new ArrayList<>();
        for (int i = 0; i < daYun.size(); i++) {
            List<String> item = daYun.get(i);
            TimeDataVo vo = new TimeDataVo();
            vo.setGanZhi(item.get(0)); // 干支
            vo.setZhuXing(item.get(1)); // 主星（天干十神）
            vo.setFuXing(item.get(2)); // 副星（地支十神）
            vo.setSolarDate(item.get(3)); // 公历日期
            vo.setSolarYear(item.get(4)); // 公历年
            vo.setAge(item.get(5)); // 年龄

            // 解析年龄范围，设置起始和结束年龄
            String ageStr = item.get(5);
            if (ageStr.contains("~")) {
                String[] ageRange = ageStr.split("~");
                vo.setStartAge(Integer.parseInt(ageRange[0]));
                vo.setEndAge(Integer.parseInt(ageRange[1]));
            } else {
                int age = Integer.parseInt(ageStr);
                vo.setStartAge(age);
                vo.setEndAge(age);
            }
            lists.add(vo);
        }
        return lists;
    }

    /**
     * 流年封装
     * 数据结构: [干支, 天干十神, 地支十神, 公历日期, 公历年, 年龄]
     */
    public static List<TimeDataVo> liuNianEncapsulation(List<List<String>> liuNian) {
        List<TimeDataVo> lists = new ArrayList<>();
        for (int i = 0; i < liuNian.size(); i++) {
            List<String> item = liuNian.get(i);
            TimeDataVo vo = new TimeDataVo();
            vo.setGanZhi(item.get(0)); // 干支
            vo.setZhuXing(item.get(1)); // 主星（天干十神）
            vo.setFuXing(item.get(2)); // 副星（地支十神）
            vo.setSolarDate(item.get(3)); // 公历日期
            vo.setSolarYear(item.get(4)); // 公历年
            vo.setAge(item.get(5)); // 年龄

            // 流年通常是单年，设置起始和结束年龄相同
            int age = Integer.parseInt(item.get(5));
            vo.setStartAge(age);
            vo.setEndAge(age);
            lists.add(vo);
        }
        return lists;
    }

    /**
     * 小运封装
     * 数据结构: [干支, 天干十神, 地支十神, 公历日期, 公历年, 年龄]
     */
    public static List<TimeDataVo> xiaoYunEncapsulation(List<List<String>> xiaoYun) {
        List<TimeDataVo> lists = new ArrayList<>();
        for (int i = 0; i < xiaoYun.size(); i++) {
            List<String> item = xiaoYun.get(i);
            TimeDataVo vo = new TimeDataVo();
            vo.setGanZhi(item.get(0)); // 干支
            vo.setZhuXing(item.get(1)); // 主星（天干十神）
            vo.setFuXing(item.get(2)); // 副星（地支十神）
            vo.setSolarDate(item.get(3)); // 公历日期
            vo.setSolarYear(item.get(4)); // 公历年
            vo.setAge(item.get(5)); // 年龄

            // 小运通常是单年，设置起始和结束年龄相同
            int age = Integer.parseInt(item.get(5));
            vo.setStartAge(age);
            vo.setEndAge(age);
            lists.add(vo);
        }
        return lists;
    }

    /**
     * 流月封装
     * 数据结构: [干支, 天干十神, 地支十神, 公历日期, 公历几月几日, 十二节农历月, 十二节]
     */
    public static List<TimeDataVo> liuYueEncapsulation(List<List<String>> liuYue) {
        List<TimeDataVo> lists = new ArrayList<>();
        for (int i = 0; i < liuYue.size(); i++) {
            List<String> item = liuYue.get(i);
            TimeDataVo vo = new TimeDataVo();
            vo.setGanZhi(item.get(0)); // 干支
            vo.setZhuXing(item.get(1)); // 主星（天干十神）
            vo.setFuXing(item.get(2)); // 副星（地支十神）
            vo.setSolarDate(item.get(3)); // 公历日期
            vo.setSolarMonthDay(item.get(4)); // 公历几月几日
            vo.setLunarMonth(item.get(5)); // 十二节农历月
            vo.setShiErJie(item.get(6)); // 十二节

            // 从公历日期中提取年份信息
            String solarDateStr = item.get(3);
            if (solarDateStr != null && solarDateStr.length() >= 4) {
                try {
                    String yearStr = solarDateStr.substring(0, 4);
                    vo.setSolarYear(yearStr);
                } catch (Exception e) {
                    // 解析失败时设置为null
                }
            }
            lists.add(vo);
        }
        return lists;
    }

    /**
     * 流日封装
     * 数据结构: [干支, 天干十神, 地支十神, 公历日期, 公历日, 农历日]
     *
     * @param liuRi 流日（干支、主星、副星、公历日期、公历日、农历日）
     */
    public static List<TimeDataVo> liuRiEncapsulation(List<List<String>> liuRi) {
        List<TimeDataVo> lists = new ArrayList<>();
        for (int i = 0; i < liuRi.size(); i++) {
            List<String> item = liuRi.get(i);
            TimeDataVo vo = new TimeDataVo();
            vo.setGanZhi(item.get(0)); // 干支
            vo.setZhuXing(item.get(1)); // 主星（天干十神）
            vo.setFuXing(item.get(2)); // 副星（地支十神）
            vo.setSolarDate(item.get(3)); // 公历日期
            vo.setSolarDay(item.get(4)); // 公历日
            vo.setLunarDay(item.get(5)); // 农历日

            // 从公历日期中提取年份和月日信息
            String solarDateStr = item.get(3);
            if (solarDateStr != null && solarDateStr.length() >= 10) {
                try {
                    String yearStr = solarDateStr.substring(0, 4);
                    String monthStr = solarDateStr.substring(5, 7);
                    String dayStr = solarDateStr.substring(8, 10);
                    vo.setSolarYear(yearStr);
                    vo.setSolarMonthDay(Integer.parseInt(monthStr) + "月" + Integer.parseInt(dayStr) + "日");
                } catch (Exception e) {
                    // 解析失败时设置为null
                }
            }
            lists.add(vo);
        }
        return lists;
    }

    /**
     * 流时封装
     * 数据结构: [干支, 天干十神, 地支十神, 公历日期, 公历时, 汉代命名]
     *
     * @param liuShi 流时（干支、主星、副星、公历日期、公历时、汉代命名）
     */
    public static List<TimeDataVo> liuShiEncapsulation(List<List<String>> liuShi) {
        List<TimeDataVo> lists = new ArrayList<>();
        for (int i = 0; i < liuShi.size(); i++) {
            List<String> item = liuShi.get(i);
            TimeDataVo vo = new TimeDataVo();
            vo.setGanZhi(item.get(0)); // 干支
            vo.setZhuXing(item.get(1)); // 主星（天干十神）
            vo.setFuXing(item.get(2)); // 副星（地支十神）
            vo.setSolarDate(item.get(3)); // 公历日期
            vo.setSolarHour(item.get(4)); // 公历时
            vo.setShiErShiChenHaMing(item.get(5)); // 汉代命名

            // 从公历日期中提取年份、月日信息
            String solarDateStr = item.get(3);
            if (solarDateStr != null && solarDateStr.length() >= 10) {
                try {
                    String yearStr = solarDateStr.substring(0, 4);
                    String monthStr = solarDateStr.substring(5, 7);
                    String dayStr = solarDateStr.substring(8, 10);
                    vo.setSolarYear(yearStr);
                    vo.setSolarMonthDay(Integer.parseInt(monthStr) + "月" + Integer.parseInt(dayStr) + "日");
                    vo.setSolarDay(Integer.parseInt(dayStr) + "日");
                } catch (Exception e) {
                    // 解析失败时设置为null
                }
            }
            lists.add(vo);
        }
        return lists;
    }
}
