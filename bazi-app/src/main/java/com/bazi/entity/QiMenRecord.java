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
 * 奇门遁甲记录实体
 *
 * @author skm1229
 * @version 1.0.0
 */
@Data
@TableName("qimen_records")
@Schema(description = "奇门遁甲记录")
public class QiMenRecord {

    @Schema(description = "奇门记录ID")
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

    @Schema(description = "排盘类型")
    private Integer paiPanType;

    // 奇门设置
    @Schema(description = "值使类型")
    private Integer zhiShiType;

    @Schema(description = "月家起局类型")
    private Integer yueJiaQiJuType;

    // 干支设置
    @Schema(description = "年干支类型")
    private Integer yearGanZhiType;

    @Schema(description = "月干支类型")
    private Integer monthGanZhiType;

    @Schema(description = "日干支类型")
    private Integer dayGanZhiType;

    @Schema(description = "时干支类型")
    private Integer hourGanZhiType;

    // 奇门信息（方便前端显示）
    @Schema(description = "局数", example = "阳遁一局")
    private String juShu;

    @Schema(description = "旬首", example = "甲子")
    private String xunShou;

    @Schema(description = "值符", example = "天心星")
    private String zhiFu;

    @Schema(description = "值使", example = "开门")
    private String zhiShi;

    @Schema(description = "记录创建时间")
    private LocalDateTime createdAt;

    @Schema(description = "记录最后更新时间")
    private LocalDateTime updatedAt;
}
