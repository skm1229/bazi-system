package com.bazi.dto.ziwei;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 紫微斗数排盘输入DTO
 *
 * @author skm1229
 * @version 1.0.0
 */
@Data
@Schema(description = "紫微斗数排盘输入参数")
public class ZiWeiDto {

    // 基本信息
    @Schema(description = "姓名", example = "张三")
    @NotBlank(message = "姓名不能为空")
    @Size(max = 50, message = "姓名长度不能超过50位")
    private String name;

    @Schema(description = "性别", example = "1", allowableValues = {"0", "1"})
    @Min(value = 0, message = "性别只能是0或1")
    @Max(value = 1, message = "性别只能是0或1")
    private int sex;

    @Schema(description = "占事", example = "求财")
    @Size(max = 100, message = "占事长度不能超过100位")
    private String occupy;

    // 日期设置
    @Schema(description = "日期", example = "2003年1月15日00时00分")
    @NotBlank(message = "日期不能为空")
    private String date;

    @Schema(description = "日期类型", example = "0", allowableValues = {"0", "1"})
    @Min(value = 0, message = "日期类型只能是0或1")
    @Max(value = 1, message = "日期类型只能是0或1")
    private int dateType;

    @Schema(description = "闰月类型", example = "0", allowableValues = {"0", "1"})
    @Min(value = 0, message = "闰月类型只能是0或1")
    @Max(value = 1, message = "闰月类型只能是0或1")
    private int leapMonthType;

    @Schema(description = "节气类型", example = "1", allowableValues = {"0", "1"})
    private int jieQiType;

    // 紫微设置
    @Schema(description = "五行局类型", example = "0", allowableValues = {"0", "1"})
    private int wuXingJuType;

    @Schema(description = "流年支", example = "子")
    private String liuNianZhi;

    // 干支设置
    @Schema(description = "年干支类型", example = "2", allowableValues = {"0", "1", "2"})
    private int yearGanZhiType;

    @Schema(description = "月干支类型", example = "1", allowableValues = {"0", "1"})
    private int monthGanZhiType;

    @Schema(description = "日干支类型", example = "0", allowableValues = {"0", "1"})
    private int dayGanZhiType;

    @Schema(description = "时干支类型", example = "0")
    private int hourGanZhiType;
}
