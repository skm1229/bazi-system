package com.bazi.dto.bazi;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 流月查询DTO - 基于参考代码设计
 *
 * @author skm1229
 * @version 1.0.0
 */
@Data
@Schema(description = "流月切换参数")
public class LiuYueDto {

    // 四柱干支信息
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

    // 运势干支信息
    @Schema(description = "大运干", example = "甲")
    private String daYunGan;

    @Schema(description = "流年干", example = "己")
    private String liuNianGan;

    @Schema(description = "流月干", example = "壬")
    private String liuYueGan;

    @Schema(description = "大运支", example = "寅")
    private String daYunZhi;

    @Schema(description = "流年支", example = "丑")
    private String liuNianZhi;

    @Schema(description = "流月支", example = "寅")
    private String liuYueZhi;

    // 轮数信息
    @Schema(description = "大运轮数", example = "1")
    private int daYunLun;

    @Schema(description = "流年轮数", example = "1")
    private int liuNianLun;

    @Schema(description = "流月轮数", example = "1")
    private int liuYueLun;

    @Schema(description = "流月公历日期", example = "2023-01-01")
    private String liuYueSolarDate;

    // 基本信息
    @Schema(description = "公历日期")
    private Date solarDate;

    @Schema(description = "性别", example = "男")
    private String sex;

    @Schema(description = "起运流派 (0:按天数和时辰数计算, 1:按分钟数计算)", example = "0")
    private int qiYunLiuPai;

    @Schema(description = "节气排法 (0:按天计算, 1:按分钟计算)", example = "1")
    private int jieQiSet;

    @Schema(description = "季节", example = "春")
    private String season;

    @Schema(description = "年干支纳音", example = "海中金")
    private String yearGanZhiNaYin;
}

