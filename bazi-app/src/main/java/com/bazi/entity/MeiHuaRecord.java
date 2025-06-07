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
 * 梅花易数记录实体
 *
 * @author skm1229
 * @version 1.0.0
 */
@Data
@TableName("meihua_records")
@Schema(description = "梅花易数记录")
public class MeiHuaRecord {

    @Schema(description = "梅花记录ID")
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

    @Schema(description = "排盘类型")
    private Integer paiPanType;

    // 数字设置
    @Schema(description = "上卦数")
    private Integer shangGuaNum;

    @Schema(description = "下卦数")
    private Integer xiaGuaNum;

    @Schema(description = "动爻数")
    private Integer dongYaoNum;

    // 干支设置
    @Schema(description = "年干支类型")
    private Integer yearGanZhiType;

    @Schema(description = "月干支类型")
    private Integer monthGanZhiType;

    @Schema(description = "日干支类型")
    private Integer dayGanZhiType;

    @Schema(description = "时干支类型")
    private Integer hourGanZhiType;

    // 卦象信息（方便前端显示）
    @Schema(description = "本卦名称", example = "乾为天")
    private String benGuaName;

    @Schema(description = "变卦名称", example = "天风姤")
    private String bianGuaName;

    @Schema(description = "体卦", example = "乾")
    private String tiGua;

    @Schema(description = "用卦", example = "巽")
    private String yongGua;

    @Schema(description = "记录创建时间")
    private LocalDateTime createdAt;

    @Schema(description = "记录最后更新时间")
    private LocalDateTime updatedAt;
}
