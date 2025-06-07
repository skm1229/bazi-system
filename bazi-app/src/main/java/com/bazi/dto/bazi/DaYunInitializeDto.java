package com.bazi.dto.bazi;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 大运流年初始化设置接收类 - 参照BaZiDaYunInitializeDto结构
 *
 * @author skm1229
 * @version 1.0.0
 */
@Data
@Schema(description = "大运流年初始化参数")
public class DaYunInitializeDto {

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

    // 基本信息
    @Schema(description = "公历日期")
    private Date solarDate;

    @Schema(description = "性别", example = "男")
    private String sex;

    @Schema(description = "起运流派 (0:按天数和时辰数计算, 1:按分钟数计算)", example = "0")
    private int qiYunLiuPai;

    @Schema(description = "季节", example = "春")
    private String season;

    @Schema(description = "年干支纳音", example = "海中金")
    private String yearGanZhiNaYin;
}

