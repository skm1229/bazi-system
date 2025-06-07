package com.bazi.dto.liuyao;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 六爻设置接收类
 *
 * @author skm1229
 */
@Data
@Schema(description = "六爻设置接收类")
public class LiuYaoDto {

    // 基本信息
    @Schema(description = "姓名", example = "张三")
    @NotBlank(message = "姓名不能为空")
    @Size(max = 50, message = "姓名长度不能超过50位")
    private String name;

    @Schema(description = "占事", example = "求财运")
    @Size(max = 100, message = "占事长度不能超过100位")
    private String occupy;

    @Schema(description = "性别（0:女。1:男）", example = "1")
    @Min(value = 0, message = "性别只能是0或1")
    @Max(value = 1, message = "性别只能是0或1")
    private int sex;

    // 日期设置
    @Schema(description = "日期", example = "2003年1月15日00时00分")
    @NotBlank(message = "日期不能为空")
    private String date;

    @Schema(description = "日期类型（0:公历。1:农历）", example = "0")
    @Min(value = 0, message = "日期类型只能是0或1")
    @Max(value = 1, message = "日期类型只能是0或1")
    private int dateType;

    @Schema(description = "闰月类型（0:不使用闰月。1:使用闰月）", example = "0")
    @Min(value = 0, message = "闰月类型只能是0或1")
    @Max(value = 1, message = "闰月类型只能是0或1")
    private int leapMonthType;

    @Schema(description = "节气类型（0:按天计算。1:按分钟计算）", example = "1")
    @Min(value = 0, message = "节气类型只能是0或1")
    @Max(value = 1, message = "节气类型只能是0或1")
    private int jieQiType;

    // 排盘设置
    @Schema(description = "排盘类型（0:日期。1:自动。2:手动）", example = "0")
    @Min(value = 0, message = "排盘类型只能是0、1或2")
    @Max(value = 2, message = "排盘类型只能是0、1或2")
    private int paiPanType;

    // 六爻设置
    @Schema(description = "上爻（0:—（2正1背）。1:- -（1正2背）。2:— ○（0正3背）。3:- - ×（3正0背））", example = "0")
    @Min(value = 0, message = "上爻只能是0、1、2或3")
    @Max(value = 3, message = "上爻只能是0、1、2或3")
    private int liuYao;

    @Schema(description = "五爻（0:—（2正1背）。1:- -（1正2背）。2:— ○（0正3背）。3:- - ×（3正0背））", example = "0")
    @Min(value = 0, message = "五爻只能是0、1、2或3")
    @Max(value = 3, message = "五爻只能是0、1、2或3")
    private int wuYao;

    @Schema(description = "四爻（0:—（2正1背）。1:- -（1正2背）。2:— ○（0正3背）。3:- - ×（3正0背））", example = "0")
    @Min(value = 0, message = "四爻只能是0、1、2或3")
    @Max(value = 3, message = "四爻只能是0、1、2或3")
    private int siYao;

    @Schema(description = "三爻（0:—（2正1背）。1:- -（1正2背）。2:— ○（0正3背）。3:- - ×（3正0背））", example = "0")
    @Min(value = 0, message = "三爻只能是0、1、2或3")
    @Max(value = 3, message = "三爻只能是0、1、2或3")
    private int sanYao;

    @Schema(description = "二爻（0:—（2正1背）。1:- -（1正2背）。2:— ○（0正3背）。3:- - ×（3正0背））", example = "0")
    @Min(value = 0, message = "二爻只能是0、1、2或3")
    @Max(value = 3, message = "二爻只能是0、1、2或3")
    private int erYao;

    @Schema(description = "初爻（0:—（2正1背）。1:- -（1正2背）。2:— ○（0正3背）。3:- - ×（3正0背））", example = "0")
    @Min(value = 0, message = "初爻只能是0、1、2或3")
    @Max(value = 3, message = "初爻只能是0、1、2或3")
    private int yiYao;

    // 干支设置
    @Schema(description = "年干支类型（0:以正月初一作为新年的开始。1:以立春当天作为新年的开始。2:以立春交接时刻作为新年的开始）", example = "2")
    @Min(value = 0, message = "年干支类型只能是0、1或2")
    @Max(value = 2, message = "年干支类型只能是0、1或2")
    private int yearGanZhiType;

    @Schema(description = "月干支类型（0:以节交接当天起算。1:以节交接时刻起算）", example = "1")
    @Min(value = 0, message = "月干支类型只能是0或1")
    @Max(value = 1, message = "月干支类型只能是0或1")
    private int monthGanZhiType;

    @Schema(description = "日干支类型（0:晚子时日柱按明天。1:晚子时日柱按当天）", example = "0")
    @Min(value = 0, message = "日干支类型只能是0或1")
    @Max(value = 1, message = "日干支类型只能是0或1")
    private int dayGanZhiType;

    @Schema(description = "时干支类型（0:支持早子时和晚子时）", example = "0")
    @Min(value = 0, message = "时干支类型只能是0")
    @Max(value = 0, message = "时干支类型只能是0")
    private int hourGanZhiType;
}


