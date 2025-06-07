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
 * 紫微斗数记录实体
 *
 * @author skm1229
 * @version 1.0.0
 */
@Data
@TableName("ziwei_records")
@Schema(description = "紫微斗数记录")
public class ZiWeiRecord {

    @Schema(description = "紫微记录ID")
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

    // 日期信息
    @Schema(description = "日期")
    private String date;

    @Schema(description = "日期类型（0:公历。1:农历）")
    private Integer dateType;

    @Schema(description = "闰月类型（0:不使用闰月。1:使用闰月）")
    private Integer leapMonthType;

    @Schema(description = "节气类型（0:按天计算。1:按分钟计算）")
    private Integer jieQiType;

    // 紫微设置
    @Schema(description = "五行局类型")
    private Integer wuXingJuType;

    @Schema(description = "流年支")
    private String liuNianZhi;

    // 干支设置
    @Schema(description = "年干支类型")
    private Integer yearGanZhiType;

    @Schema(description = "月干支类型")
    private Integer monthGanZhiType;

    @Schema(description = "日干支类型")
    private Integer dayGanZhiType;

    @Schema(description = "时干支类型")
    private Integer hourGanZhiType;

    // 紫微信息（方便前端显示）
    @Schema(description = "命宫", example = "子宫")
    private String mingGong;

    @Schema(description = "身宫", example = "午宫")
    private String shenGong;

    @Schema(description = "五行局", example = "水二局")
    private String wuXingJu;

    @Schema(description = "主星", example = "紫微星")
    private String zhuXing;

    @Schema(description = "记录创建时间")
    private LocalDateTime createdAt;

    @Schema(description = "记录最后更新时间")
    private LocalDateTime updatedAt;
}
