package com.bazi.vo.bazi;

import com.bazi.vo.bazi.common.TimeDataVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 流日查询结果VO
 *
 * @author skm1229
 * @version 1.0.0
 */
@Data
@Schema(description = "流日查询结果")
public class LiuRiVo {

    // 流时信息
    @Schema(description = "流时列表")
    private List<TimeDataVo> liuShiList;

    // 流日基本信息
    @Schema(description = "流日干支")
    private String liuRiGanZhi;

    @Schema(description = "流日主星")
    private String liuRiZhuXing;

    @Schema(description = "流日藏干")
    private List<String> liuRiCangGan;

    @Schema(description = "流日副星")
    private List<String> liuRiFuXing;

    @Schema(description = "流日自坐")
    private String liuRiZiZuo;

    @Schema(description = "流日星运")
    private String liuRiXingYun;

    @Schema(description = "流日空亡")
    private String liuRiKongWang;

    @Schema(description = "流日纳音")
    private String liuRiNaYin;

    @Schema(description = "流日神煞")
    private List<String> liuRiShenSha;

    // 留意信息
    @Schema(description = "天干留意")
    private List<String> tianGanLiuYi;

    @Schema(description = "地支留意")
    private List<String> diZhiLiuYi;
}
