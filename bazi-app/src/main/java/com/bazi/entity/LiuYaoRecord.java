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
 * 六爻排盘记录实体
 *
 * @author skm1229
 * @version 1.0.0
 */
@Data
@TableName("liuyao_records")
@Schema(description = "六爻排盘记录")
public class LiuYaoRecord {

    @Schema(description = "六爻记录ID")
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

    @Schema(description = "排盘类型")
    private Integer paiPanType;

    // 六爻设置
    @Schema(description = "初爻")
    private Integer yao1;

    @Schema(description = "二爻")
    private Integer yao2;

    @Schema(description = "三爻")
    private Integer yao3;

    @Schema(description = "四爻")
    private Integer yao4;

    @Schema(description = "五爻")
    private Integer yao5;

    @Schema(description = "上爻")
    private Integer yao6;

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

    @Schema(description = "本卦卦象", example = "111111")
    private String benGuaXiang;

    @Schema(description = "变卦卦象", example = "111011")
    private String bianGuaXiang;

    @Schema(description = "记录创建时间")
    private LocalDateTime createdAt;

    @Schema(description = "记录最后更新时间")
    private LocalDateTime updatedAt;
}
