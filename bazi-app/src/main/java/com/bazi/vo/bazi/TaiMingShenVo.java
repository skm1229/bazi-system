package com.bazi.vo.bazi;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 胎命身查询结果VO - 胎元、胎息、命宫、身宫
 *
 * @author skm1229
 * @version 1.0.0
 */
@Data
@Schema(description = "胎命身查询结果")
public class TaiMingShenVo {

    // 干支信息
    @Schema(description = "胎元干支")
    private String taiYuanGanZhi;

    @Schema(description = "胎息干支")
    private String taiXiGanZhi;

    @Schema(description = "命宫干支")
    private String mingGongGanZhi;

    @Schema(description = "身宫干支")
    private String shenGongGanZhi;

    // 主星信息
    @Schema(description = "胎元主星")
    private String taiYuanZhuXing;

    @Schema(description = "胎息主星")
    private String taiXiZhuXing;

    @Schema(description = "命宫主星")
    private String mingGongZhuXing;

    @Schema(description = "身宫主星")
    private String shenGongZhuXing;

    // 藏干信息
    @Schema(description = "胎元藏干")
    private List<String> taiYuanCangGan;

    @Schema(description = "胎息藏干")
    private List<String> taiXiCangGan;

    @Schema(description = "命宫藏干")
    private List<String> mingGongCangGan;

    @Schema(description = "身宫藏干")
    private List<String> shenGongCangGan;

    // 副星信息
    @Schema(description = "胎元副星")
    private List<String> taiYuanFuXing;

    @Schema(description = "胎息副星")
    private List<String> taiXiFuXing;

    @Schema(description = "命宫副星")
    private List<String> mingGongFuXing;

    @Schema(description = "身宫副星")
    private List<String> shenGongFuXing;

    // 自坐信息
    @Schema(description = "胎元自坐")
    private String taiYuanZiZuo;

    @Schema(description = "胎息自坐")
    private String taiXiZiZuo;

    @Schema(description = "命宫自坐")
    private String mingGongZiZuo;

    @Schema(description = "身宫自坐")
    private String shenGongZiZuo;

    // 星运信息
    @Schema(description = "胎元星运")
    private String taiYuanXingYun;

    @Schema(description = "胎息星运")
    private String taiXiXingYun;

    @Schema(description = "命宫星运")
    private String mingGongXingYun;

    @Schema(description = "身宫星运")
    private String shenGongXingYun;

    // 空亡信息
    @Schema(description = "胎元空亡")
    private String taiYuanKongWang;

    @Schema(description = "胎息空亡")
    private String taiXiKongWang;

    @Schema(description = "命宫空亡")
    private String mingGongKongWang;

    @Schema(description = "身宫空亡")
    private String shenGongKongWang;

    // 纳音信息
    @Schema(description = "胎元纳音")
    private String taiYuanNaYin;

    @Schema(description = "胎息纳音")
    private String taiXiNaYin;

    @Schema(description = "命宫纳音")
    private String mingGongNaYin;

    @Schema(description = "身宫纳音")
    private String shenGongNaYin;

    // 神煞信息
    @Schema(description = "胎元神煞")
    private List<String> taiYuanShenSha;

    @Schema(description = "胎息神煞")
    private List<String> taiXiShenSha;

    @Schema(description = "命宫神煞")
    private List<String> mingGongShenSha;

    @Schema(description = "身宫神煞")
    private List<String> shenGongShenSha;

    // 留意信息
    @Schema(description = "天干留意")
    private List<String> tianGanLiuYi;

    @Schema(description = "地支留意")
    private List<String> diZhiLiuYi;
}

