package com.bazi.vo.liuyao;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 六爻数据返回类
 *
 * @author skm1229
 */
@Data
@Schema(description = "六爻数据返回类")
public class LiuYaoVo {

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
    @Schema(description = "八字", example = "[\"壬午\", \"癸丑\", \"戊子\", \"壬子\"]")
    private List<String> baZi;

    @Schema(description = "八字五行", example = "[\"水火\", \"水土\", \"土水\", \"水水\"]")
    private List<String> baZiWuXing;

    @Schema(description = "八字空亡", example = "[\"戌亥\", \"戌亥\", \"戌亥\", \"戌亥\"]")
    private List<String> baZiKongWang;

    @Schema(description = "八字纳音", example = "[\"杨柳木\", \"桑柘木\", \"霹雳火\", \"桑柘木\"]")
    private List<String> baZiNaYin;

    // 干支信息
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

    @Schema(description = "年干支", example = "壬午")
    private String yearGanZhi;

    @Schema(description = "月干支", example = "癸丑")
    private String monthGanZhi;

    @Schema(description = "日干支", example = "戊子")
    private String dayGanZhi;

    @Schema(description = "时干支", example = "壬子")
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

    // 六爻信息
    @Schema(description = "六爻爻象")
    private List<String> liuYaoAs;

    @Schema(description = "六爻爻象标识")
    private List<String> liuYaoYaoXiangMark;

    @Schema(description = "六爻爻象标识名称")
    private List<String> liuYaoYaoXiangMarkName;

    // 卦象信息
    @Schema(description = "上卦")
    private String shangGua;

    @Schema(description = "上卦卦象")
    private String shangGuaAs;

    @Schema(description = "下卦")
    private String xiaGua;

    @Schema(description = "下卦卦象")
    private String xiaGuaAs;

    // 本卦信息
    @Schema(description = "本卦")
    private String benGua;

    @Schema(description = "本卦卦象")
    private String benGuaAs;

    @Schema(description = "本卦类型")
    private String benGuaType;

    @Schema(description = "本卦卦身")
    private String benGuaGuaShen;

    @Schema(description = "本卦卦辞")
    private String benGuaGuaCi;

    @Schema(description = "本卦的六爻爻名（顺序：初爻、二爻、三爻、四爻、五爻、上爻）")
    private List<String> benGuaLiuYaoName;

    @Schema(description = "本卦的六爻爻象（顺序：初爻、二爻、三爻、四爻、五爻、上爻）")
    private List<String> benGuaLiuYaoAs;

    @Schema(description = "本卦的六爻世应（顺序：初爻、二爻、三爻、四爻、五爻、上爻）")
    private List<String> benGuaLiuYaoShiYing;

    @Schema(description = "本卦的六爻六亲（顺序：初爻、二爻、三爻、四爻、五爻、上爻）")
    private List<String> benGuaLiuYaoLiuQin;

    @Schema(description = "本卦的六爻干支（顺序：初爻、二爻、三爻、四爻、五爻、上爻）")
    private List<String> benGuaLiuYaoGanZhi;

    @Schema(description = "本卦的六爻五行（顺序：初爻、二爻、三爻、四爻、五爻、上爻）")
    private List<String> benGuaLiuYaoWuXing;

    @Schema(description = "本卦的六爻六神（顺序：初爻、二爻、三爻、四爻、五爻、上爻）")
    private List<String> benGuaLiuYaoLiuShen;

    @Schema(description = "本卦的六爻爻辞（顺序：初爻、二爻、三爻、四爻、五爻、上爻）")
    private List<String> benGuaLiuYaoYaoCi;

    @Schema(description = "本卦的六爻干支纳音（顺序：初爻、二爻、三爻、四爻、五爻、上爻）")
    private List<String> benGuaLiuYaoGanZhiNaYin;

}
