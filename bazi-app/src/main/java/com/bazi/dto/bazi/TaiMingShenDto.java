package com.bazi.dto.bazi;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 胎命身切换设置接收类 - 与父模块保持一致
 *
 * @author skm1229
 * @version 1.0.0
 */
@Data
@Schema(description = "胎命身参数")
public class TaiMingShenDto {

    // 四柱干支信息
    @Schema(description = "年干", example = "甲")
    private String yearGan;

    @Schema(description = "月干", example = "乙")
    private String monthGan;

    @Schema(description = "日干", example = "丙")
    private String dayGan;

    @Schema(description = "时干", example = "丁")
    private String hourGan;

    @Schema(description = "年支", example = "子")
    private String yearZhi;

    @Schema(description = "月支", example = "丑")
    private String monthZhi;

    @Schema(description = "日支", example = "寅")
    private String dayZhi;

    @Schema(description = "时支", example = "卯")
    private String hourZhi;

    // 胎命身干支信息
    @Schema(description = "胎元干", example = "戊")
    private String taiYuanGan;

    @Schema(description = "胎息干", example = "己")
    private String taiXiGan;

    @Schema(description = "命宫干", example = "庚")
    private String mingGongGan;

    @Schema(description = "身宫干", example = "辛")
    private String shenGongGan;

    @Schema(description = "胎元支", example = "辰")
    private String taiYuanZhi;

    @Schema(description = "胎息支", example = "巳")
    private String taiXiZhi;

    @Schema(description = "命宫支", example = "午")
    private String mingGongZhi;

    @Schema(description = "身宫支", example = "未")
    private String shenGongZhi;

    // 基本信息
    @Schema(description = "公历日期")
    private Date solarDate;

    @Schema(description = "性别", example = "男")
    private String sex;

    @Schema(description = "起运流派 (0:按天数和时辰数计算, 1:按分钟数计算)", example = "0")
    private int qiYunLiuPaiType;

    @Schema(description = "季节", example = "春")
    private String season;

    @Schema(description = "年干支纳音", example = "海中金")
    private String yearGanZhiNaYin;
}

