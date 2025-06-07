package com.bazi.dto.bazi;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 八字设置接收类 - 与父模块保持一致
 *
 * @author skm1229
 * @version 1.0.0
 */
@Data
@Schema(description = "八字排盘设置参数")
public class BaZiDto {

    @Schema(description = "姓名", example = "张三")
    @Size(max = 50, message = "姓名长度不能超过50位")
    private String name;

    @Schema(description = "占事", example = "事业")
    @Size(max = 100, message = "占事长度不能超过100位")
    private String occupy;

    @Schema(description = "日期 (支持格式: yyyy年M月d日HH时mm分 或 yyyy-MM-dd HH:mm:ss)", example = "2003年1月15日00时00分")
    private String date;

    @Schema(description = "地区", example = "北京市")
    @Size(max = 100, message = "地区长度不能超过100位")
    private String address;

    @Schema(description = "是否闰月 (0:非闰月, 1:闰月)", example = "0")
    @Min(value = 0, message = "闰月类型只能是0或1")
    @Max(value = 1, message = "闰月类型只能是0或1")
    private int leapMonthType;

    @Schema(description = "日期类型 (0:公历, 1:农历)", example = "0")
    @Min(value = 0, message = "日期类型只能是0或1")
    @Max(value = 1, message = "日期类型只能是0或1")
    private int dateType;

    @Schema(description = "性别 (0:女, 1:男)", example = "1")
    @Min(value = 0, message = "性别只能是0或1")
    @Max(value = 1, message = "性别只能是0或1")
    private int sex;

    @Schema(description = "起运流派 (0:按天和时辰计算, 1:按分钟计算)", example = "1")
    private int qiYunLiuPaiType = 1;

    @Schema(description = "节气排法 (0:按天计算, 1:按分钟计算)", example = "1")
    private int jieQiType = 1;

    @Schema(description = "人元司令分野类型", example = "0")
    private int renYuanType = 0;

    @Schema(description = "年干支设置", example = "2")
    private int yearGanZhiType = 2;

    @Schema(description = "月干支设置", example = "1")
    private int monthGanZhiType = 1;

    @Schema(description = "日干支设置", example = "0")
    private int dayGanZhiType = 0;

    @Schema(description = "时干支设置", example = "0")
    private int hourGanZhiType = 0;
}
