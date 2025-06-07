package com.bazi.dto.qimen;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 奇门遁甲（转盘）设置接收类
 *
 * @author skm1229
 */
@Data
@Schema(description = "奇门遁甲（转盘）设置接收类")
public class QiMenDto {

    // 基本信息
    @Schema(description = "姓名", example = "张三")
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
    private int jieQiType;

    // 奇门设置
    @Schema(description = "排盘类型（0:年家奇门。1:月家奇门。2:日家奇门。3:时家奇门）", example = "3")
    private int paiPanType;

    @Schema(description = "值使类型（0:天禽星为值符时，一律用[死门]为值使。1:天禽星为值符时，根据阴阳遁判断。2:天禽星为值符时，根据节气判断）", example = "0")
    private int zhiShiType;

    @Schema(description = "月家奇门起局类型（0:使用年支起局。1:使用年干支的符头地支起局）", example = "1")
    private int yueJiaQiJuType;

    // 干支设置
    @Schema(description = "年干支类型（0:以正月初一作为新年的开始。1:以立春当天作为新年的开始。2:以立春交接时刻作为新年的开始）", example = "2")
    private int yearGanZhiType;

    @Schema(description = "月干支类型（0:以节交接当天起算。1:以节交接时刻起算）", example = "1")
    private int monthGanZhiType;

    @Schema(description = "日干支类型（0:晚子时日柱按明天。1:晚子时日柱按当天）", example = "0")
    private int dayGanZhiType;

    @Schema(description = "时干支类型（0:支持早子时和晚子时）", example = "0")
    private int hourGanZhiType;
}


