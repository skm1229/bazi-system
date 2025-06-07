package com.bazi.vo.bazi;

import com.bazi.vo.bazi.common.TimeDataVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 流时查询结果VO
 *
 * @author skm1229
 * @version 1.0.0
 */
@Data
@Schema(description = "流时查询结果")
public class LiuShiVo {

    // 流时基本信息
    @Schema(description = "流时干支")
    private String liuShiGanZhi;

    @Schema(description = "流时主星")
    private String liuShiZhuXing;

    @Schema(description = "流时藏干")
    private List<String> liuShiCangGan;

    @Schema(description = "流时副星")
    private List<String> liuShiFuXing;

    @Schema(description = "流时自坐")
    private String liuShiZiZuo;

    @Schema(description = "流时星运")
    private String liuShiXingYun;

    @Schema(description = "流时空亡")
    private String liuShiKongWang;

    @Schema(description = "流时纳音")
    private String liuShiNaYin;

    @Schema(description = "流时神煞")
    private List<String> liuShiShenSha;

    // 留意信息
    @Schema(description = "天干留意")
    private List<String> tianGanLiuYi;

    @Schema(description = "地支留意")
    private List<String> diZhiLiuYi;
}

