package com.bazi.vo.bazi;

import com.bazi.vo.bazi.common.TimeDataVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 大运切换数据返回类 - 与父模块BaZiDaYunVo保持一致
 *
 * @author skm1229
 * @version 1.0.0
 */
@Data
@Schema(description = "大运切换结果")
public class DaYunVo {

    // 时间数据信息
    @Schema(description = "小运信息")
    private List<TimeDataVo> xiaoYun;

    @Schema(description = "流年信息")
    private List<TimeDataVo> liuNian;

    @Schema(description = "流月信息")
    private List<TimeDataVo> liuYue;

    // 干支信息
    @Schema(description = "大运干支", example = "甲寅")
    private String daYunGanZhi;

    @Schema(description = "流年干支", example = "癸卯")
    private String liuNianGanZhi;

    // 主星信息
    @Schema(description = "大运主星", example = "正财")
    private String daYunZhuXing;

    @Schema(description = "流年主星", example = "偏印")
    private String liuNianZhuXing;

    // 藏干信息
    @Schema(description = "大运藏干", example = "[\"甲\", \"丙\", \"戊\"]")
    private List<String> daYunCangGan;

    @Schema(description = "流年藏干", example = "[\"癸\", \"乙\"]")
    private List<String> liuNianCangGan;

    // 副星信息
    @Schema(description = "大运副星", example = "[\"劫财\", \"食神\"]")
    private List<String> daYunFuXing;

    @Schema(description = "流年副星", example = "[\"正官\", \"七杀\"]")
    private List<String> liuNianFuXing;

    // 自坐信息
    @Schema(description = "大运自坐", example = "长生")
    private String daYunZiZuo;

    @Schema(description = "流年自坐", example = "沐浴")
    private String liuNianZiZuo;

    // 星运信息
    @Schema(description = "大运星运", example = "帝旺")
    private String daYunXingYun;

    @Schema(description = "流年星运", example = "衰")
    private String liuNianXingYun;

    // 空亡信息
    @Schema(description = "大运空亡", example = "戌亥")
    private String daYunKongWang;

    @Schema(description = "流年空亡", example = "申酉")
    private String liuNianKongWang;

    // 纳音信息
    @Schema(description = "大运纳音", example = "大溪水")
    private String daYunNaYin;

    @Schema(description = "流年纳音", example = "金箔金")
    private String liuNianNaYin;

    // 神煞信息
    @Schema(description = "大运神煞", example = "[\"天乙贵人\", \"文昌\"]")
    private List<String> daYunShenSha;

    @Schema(description = "流年神煞", example = "[\"桃花\", \"驿马\"]")
    private List<String> liuNianShenSha;

    // 留意信息
    @Schema(description = "天干留意", example = "[\"甲木逢金\", \"癸水遇土\"]")
    private List<String> tianGanLiuYi;

    @Schema(description = "地支留意", example = "[\"寅申冲\", \"卯酉冲\"]")
    private List<String> diZhiLiuYi;
}
