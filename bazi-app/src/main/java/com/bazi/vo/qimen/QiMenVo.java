package com.bazi.vo.qimen;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 奇门遁甲排盘结果VO
 *
 * @author skm1229
 * @version 1.0.0
 */
@Data
@Schema(description = "奇门遁甲排盘结果")
public class QiMenVo {

    // 基本信息
    @Schema(description = "姓名", example = "张三")
    private String name;

    @Schema(description = "性别", example = "男")
    private String sex;

    @Schema(description = "乾造/坤造", example = "乾造")
    private String zao;

    @Schema(description = "占事", example = "求财运")
    private String occupy;

    // 日期时间信息
    @Schema(description = "公历日期字符串", example = "2003年1月15日0时0分")
    private String solarStr;

    @Schema(description = "农历日期字符串", example = "二〇〇二年腊月十三日子时")
    private String lunarStr;

    @Schema(description = "公历日期")
    private Date solarDate;

    @Schema(description = "农历日期")
    private Date lunarDate;

    @Schema(description = "星期", example = "星期三")
    private String xingQi;

    @Schema(description = "季节", example = "冬季")
    private String jiJie;

    @Schema(description = "生肖", example = "马")
    private String shengXiao;

    @Schema(description = "星座", example = "摩羯座")
    private String xingZuo;

    @Schema(description = "月相", example = "上弦月")
    private String yueXiang;

    @Schema(description = "月将", example = "丑将")
    private String yueJiang;

    @Schema(description = "月将神", example = "太常")
    private String yueJiangShen;

    @Schema(description = "五不遇时", example = "甲不遇申")
    private String wuBuYuShi;

    // 八字信息
    @Schema(description = "八字")
    private List<String> baZi;

    @Schema(description = "八字五行")
    private List<String> baZiWuXing;

    @Schema(description = "八字空亡")
    private List<String> baZiKongWang;

    @Schema(description = "八字纳音")
    private List<String> baZiNaYin;

    // 天干地支
    @Schema(description = "年干", example = "壬")
    private String yearGan;

    @Schema(description = "月干", example = "癸")
    private String monthGan;

    @Schema(description = "日干", example = "戊")
    private String dayGan;

    @Schema(description = "时干", example = "壬")
    private String hourGan;

    @Schema(description = "年支", example = "午")
    private String yearZhi;

    @Schema(description = "月支", example = "丑")
    private String monthZhi;

    @Schema(description = "日支", example = "子")
    private String dayZhi;

    @Schema(description = "时支", example = "子")
    private String hourZhi;

    @Schema(description = "年干支")
    private String yearGanZhi;

    @Schema(description = "月干支")
    private String monthGanZhi;

    @Schema(description = "日干支")
    private String dayGanZhi;

    @Schema(description = "时干支")
    private String hourGanZhi;

    // 五行信息
    @Schema(description = "年干五行")
    private String yearGanWuXing;

    @Schema(description = "月干五行")
    private String monthGanWuXing;

    @Schema(description = "日干五行")
    private String dayGanWuXing;

    @Schema(description = "时干五行")
    private String hourGanWuXing;

    @Schema(description = "年支五行")
    private String yearZhiWuXing;

    @Schema(description = "月支五行")
    private String monthZhiWuXing;

    @Schema(description = "日支五行")
    private String dayZhiWuXing;

    @Schema(description = "时支五行")
    private String hourZhiWuXing;

    @Schema(description = "年干支五行")
    private String yearGanZhiWuXing;

    @Schema(description = "月干支五行")
    private String monthGanZhiWuXing;

    @Schema(description = "日干支五行")
    private String dayGanZhiWuXing;

    @Schema(description = "时干支五行")
    private String hourGanZhiWuXing;

    // 空亡信息
    @Schema(description = "年干支空亡")
    private String yearGanZhiKongWang;

    @Schema(description = "月干支空亡")
    private String monthGanZhiKongWang;

    @Schema(description = "日干支空亡")
    private String dayGanZhiKongWang;

    @Schema(description = "时干支空亡")
    private String hourGanZhiKongWang;

    // 纳音信息
    @Schema(description = "年干支纳音")
    private String yearGanZhiNaYin;

    @Schema(description = "月干支纳音")
    private String monthGanZhiNaYin;

    @Schema(description = "日干支纳音")
    private String dayGanZhiNaYin;

    @Schema(description = "时干支纳音")
    private String hourGanZhiNaYin;

    // 奇门遁甲核心信息
    @Schema(description = "符头")
    private String fuTou;

    @Schema(description = "节气")
    private String jieQi;

    @Schema(description = "三元")
    private String sanYuan;

    @Schema(description = "局数")
    private int juShu;

    @Schema(description = "阴阳遁")
    private String yinYangDun;

    @Schema(description = "旬首")
    private String xunShou;

    @Schema(description = "旬首仪仗")
    private String xunShouYiZhang;

    @Schema(description = "值符")
    private String zhiFu;

    @Schema(description = "值使")
    private String zhiShi;

    // 天地太乙
    @Schema(description = "天乙")
    private String tianYi;

    @Schema(description = "地乙")
    private String diYi;

    @Schema(description = "太乙")
    private String taiYi;

    // 九宫信息
    @Schema(description = "地盘")
    private List<String> diPan;

    @Schema(description = "天盘")
    private List<String> tianPan;

    @Schema(description = "人盘")
    private List<String> renPan;

    @Schema(description = "神盘")
    private List<String> shenPan;

    // 伏吟反吟
    @Schema(description = "伏吟")
    private List<String> fuYin;

    @Schema(description = "反吟")
    private List<String> fanYin;

    // 击刑入墓
    @Schema(description = "六仪击刑")
    private List<String> liuYiJiXing;

    @Schema(description = "奇仪入墓")
    private List<String> qiYiRuMu;

    // 门迫
    @Schema(description = "门迫状态")
    private List<String> menPoLink;

    // 九遁
    @Schema(description = "九遁")
    private Map<Integer, List<String>> jiuDun;

    // 克应信息
    @Schema(description = "十干克应")
    private Map<Integer, List<String>> shiGanKeYing;

    @Schema(description = "八门克应")
    private Map<Integer, List<String>> baMenKeYing;

    @Schema(description = "八门静应")
    private Map<Integer, List<String>> baMenJingYing;

    @Schema(description = "八门动应")
    private Map<Integer, List<String>> baMenDongYing;

    @Schema(description = "星门克应")
    private Map<Integer, List<String>> xingMenKeYing;

    @Schema(description = "九星时应")
    private Map<Integer, List<String>> jiuXingShiYing;

    // 旺衰信息
    @Schema(description = "八卦旺衰")
    private List<List<String>> baGuaWangShuai;

    @Schema(description = "八门旺衰")
    private List<List<String>> baMenWangShuai;

    @Schema(description = "九星旺衰")
    private List<List<String>> jiuXingWangShuai;

    // 落宫状态
    @Schema(description = "八神落宫状态")
    private List<List<String>> baShenLuoGongStatus;

    @Schema(description = "八门落宫状态")
    private List<List<String>> baMenLuoGongStatus;

    @Schema(description = "九星落宫状态")
    private List<List<String>> jiuXingLuoGongStatus;
}
