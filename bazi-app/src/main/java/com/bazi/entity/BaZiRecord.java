package com.bazi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 八字排盘记录实体
 *
 * @author skm1229
 * @version 1.0.0
 */
@Data
@TableName("bazi_records")
@Schema(description = "八字排盘记录")
public class BaZiRecord {

    @Schema(description = "八字记录ID")
    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @Schema(description = "关联用户ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    // 基本信息
    @Schema(description = "姓名")
    private String name;

    @Schema(description = "性别（0:女。1:男）")
    private Integer sex;

    @Schema(description = "占事")
    private String occupy;

    @Schema(description = "地址")
    private String address;

    // 日期信息
    @Schema(description = "日期")
    private String date;

    @Schema(description = "日期类型（0:公历。1:农历）")
    private Integer dateType;

    @Schema(description = "闰月类型（0:不使用闰月。1:使用闰月）")
    private Integer leapMonthType;

    @Schema(description = "节气类型（0:按天计算。1:按分钟计算）")
    private Integer jieQiType;

    // 干支设置
    @Schema(description = "年干支类型")
    private Integer yearGanZhiType;

    @Schema(description = "月干支类型")
    private Integer monthGanZhiType;

    @Schema(description = "日干支类型")
    private Integer dayGanZhiType;

    @Schema(description = "时干支类型")
    private Integer hourGanZhiType;

    @Schema(description = "起运流派")
    private Integer qiYunLiuPaiType;

    // 四柱干支（分开存储，方便查询分析）
    @Schema(description = "年干", example = "壬")
    private String yearGan;

    @Schema(description = "年支", example = "午")
    private String yearZhi;

    @Schema(description = "月干", example = "癸")
    private String monthGan;

    @Schema(description = "月支", example = "丑")
    private String monthZhi;

    @Schema(description = "日干", example = "戊")
    private String dayGan;

    @Schema(description = "日支", example = "子")
    private String dayZhi;

    @Schema(description = "时干", example = "壬")
    private String hourGan;

    @Schema(description = "时支", example = "子")
    private String hourZhi;

    @Schema(description = "记录创建时间")
    private LocalDateTime createdAt;

    @Schema(description = "记录最后更新时间")
    private LocalDateTime updatedAt;
}
