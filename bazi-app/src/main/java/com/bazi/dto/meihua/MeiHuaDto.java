package com.bazi.dto.meihua;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 梅花易数设置接收类
 *
 * @author skm1229
 */
@Data
@Schema(description = "梅花易数设置接收类")
public class MeiHuaDto {

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
    @Schema(description = "排盘类型（0:日期。1:自动。2:数字。3:单数。4:双数）", example = "0")
    @Min(value = 0, message = "排盘类型只能是0-4")
    @Max(value = 4, message = "排盘类型只能是0-4")
    private int paiPanType;

    // 数字设置
    @Schema(description = "排盘数", example = "123")
    @Min(value = 0, message = "排盘数不能为负数")
    private int paiPanShu;

    @Schema(description = "排盘单数", example = "111")
    @Min(value = 0, message = "排盘单数不能为负数")
    private int paiPanDanShu;

    @Schema(description = "排盘双数1", example = "111")
    @Min(value = 0, message = "排盘双数1不能为负数")
    private int paiPanShuangShuOne;

    @Schema(description = "排盘双数2", example = "222")
    @Min(value = 0, message = "排盘双数2不能为负数")
    private int paiPanShuangShuTwo;

    @Schema(description = "双数排盘时上下卦类型（0:双数求和计算上下卦。1:双数不求和计算上下卦）", example = "1")
    @Min(value = 0, message = "上下卦类型只能是0或1")
    @Max(value = 1, message = "上下卦类型只能是0或1")
    private int paiPanShuangShuShangXiaGuaType;

    @Schema(description = "双数排盘时动爻类型（0:双数求和计算动爻。1:双数求和加时辰数计算动爻）", example = "0")
    @Min(value = 0, message = "动爻类型只能是0或1")
    @Max(value = 1, message = "动爻类型只能是0或1")
    private int paiPanShuangShuDongYaoType;

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


