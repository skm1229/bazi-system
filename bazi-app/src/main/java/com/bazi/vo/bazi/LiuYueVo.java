package com.bazi.vo.bazi;

import com.bazi.vo.bazi.common.TimeDataVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 流月查询结果VO
 *
 * @author skm1229
 * @version 1.0.0
 */
@Data
@Schema(description = "流月查询结果")
public class LiuYueVo {

    // 流日信息
    @Schema(description = "流日列表")
    private List<TimeDataVo> liuRiList;

    // 流月基本信息
    @Schema(description = "流月干支", example = "甲寅")
    private String liuYueGanZhi;

    @Schema(description = "流月主星", example = "正财")
    private String liuYueZhuXing;

    @Schema(description = "流月藏干", example = "[\"甲\", \"丙\", \"戊\"]")
    private List<String> liuYueCangGan;

    @Schema(description = "流月副星", example = "[\"劫财\", \"食神\"]")
    private List<String> liuYueFuXing;

    @Schema(description = "流月自坐", example = "长生")
    private String liuYueZiZuo;

    @Schema(description = "流月星运", example = "帝旺")
    private String liuYueXingYun;

    @Schema(description = "流月空亡", example = "戌亥")
    private String liuYueKongWang;

    @Schema(description = "流月纳音", example = "大溪水")
    private String liuYueNaYin;

    @Schema(description = "流月神煞", example = "[\"天乙贵人\", \"文昌\"]")
    private List<String> liuYueShenSha;

    // 留意信息
    @Schema(description = "天干留意", example = "[\"甲木逢金\", \"癸水遇土\"]")
    private List<String> tianGanLiuYi;

    @Schema(description = "地支留意", example = "[\"寅申冲\", \"卯酉冲\"]")
    private List<String> diZhiLiuYi;
}
