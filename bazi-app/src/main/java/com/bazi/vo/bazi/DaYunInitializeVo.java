package com.bazi.vo.bazi;

import com.bazi.vo.bazi.common.TimeDataVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 大运流年初始化数据返回类 - 参照BaZiInitializeDaYunVo结构
 *
 * @author skm1229
 * @version 1.0.0
 */
@Data
@Schema(description = "大运流年初始化结果")
public class DaYunInitializeVo {

    // 时间数据信息
    @Schema(description = "大运")
    private List<TimeDataVo> daYun;

    @Schema(description = "小运")
    private List<TimeDataVo> xiaoYun;

    @Schema(description = "流年")
    private List<TimeDataVo> liuNian;

    @Schema(description = "流月")
    private List<TimeDataVo> liuYue;

    // 干支信息
    @Schema(description = "大运干支", example = "甲寅")
    private String daYunGanZhi;

    @Schema(description = "小运干支", example = "乙卯")
    private String xiaoYunGanZhi;

    @Schema(description = "流年干支", example = "癸卯")
    private String liuNianGanZhi;

    // 主星信息
    @Schema(description = "大运主星", example = "正财")
    private String daYunZhuXing;

    @Schema(description = "小运主星", example = "偏财")
    private String xiaoYunZhuXing;

    @Schema(description = "流年主星", example = "偏印")
    private String liuNianZhuXing;

    // 藏干信息
    @Schema(description = "大运藏干", example = "[\"甲\", \"丙\", \"戊\"]")
    private List<String> daYunCangGan;

    @Schema(description = "小运藏干", example = "[\"乙\", \"丁\"]")
    private List<String> xiaoYunCangGan;

    @Schema(description = "流年藏干", example = "[\"癸\", \"乙\"]")
    private List<String> liuNianCangGan;

    // 副星信息
    @Schema(description = "大运副星", example = "[\"劫财\", \"食神\"]")
    private List<String> daYunFuXing;

    @Schema(description = "小运副星", example = "[\"比肩\", \"伤官\"]")
    private List<String> xiaoYunFuXing;

    @Schema(description = "流年副星", example = "[\"正官\", \"七杀\"]")
    private List<String> liuNianFuXing;

    // 自坐信息
    @Schema(description = "大运自坐")
    private String daYunZiZuo;

    @Schema(description = "小运自坐")
    private String xiaoYunZiZuo;

    @Schema(description = "流年自坐")
    private String liuNianZiZuo;

    // 星运信息
    @Schema(description = "大运星运")
    private String daYunXingYun;

    @Schema(description = "小运星运")
    private String xiaoYunXingYun;

    @Schema(description = "流年星运")
    private String liuNianXingYun;

    // 空亡信息
    @Schema(description = "大运空亡")
    private String daYunKongWang;

    @Schema(description = "小运空亡")
    private String xiaoYunKongWang;

    @Schema(description = "流年空亡")
    private String liuNianKongWang;

    // 纳音信息
    @Schema(description = "大运纳音")
    private String daYunNaYin;

    @Schema(description = "小运纳音")
    private String xiaoYunNaYin;

    @Schema(description = "流年纳音")
    private String liuNianNaYin;

    // 神煞信息
    @Schema(description = "大运神煞")
    private List<String> daYunShenSha;

    @Schema(description = "小运神煞")
    private List<String> xiaoYunShenSha;

    @Schema(description = "流年神煞")
    private List<String> liuNianShenSha;

    // 留意信息
    @Schema(description = "天干留意")
    private List<String> tianGanLiuYi;

    @Schema(description = "地支留意")
    private List<String> diZhiLiuYi;
}

