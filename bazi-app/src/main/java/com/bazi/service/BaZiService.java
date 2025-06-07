package com.bazi.service;

import com.bazi.dto.bazi.*;
import com.bazi.vo.bazi.*;

/**
 * 八字排盘服务接口
 *
 * @author skm1229
 * @version 1.0.0
 */
public interface BaZiService {
    /**
     * 八字排盘
     *
     * @param dto 八字设置参数
     * @return 八字排盘结果
     */
    BaZiVo paiPan(BaZiDto dto);

    /**
     * 大运初始化 - 获取大运、流年、流月信息
     *
     * @param dto 大运初始化参数
     * @return 大运初始化结果
     */
    DaYunInitializeVo daYunInit(DaYunInitializeDto dto);

    /**
     * 大运切换 - 根据选择的大运轮数获取对应的流年和流月信息
     *
     * @param dto 大运参数
     * @return 大运切换结果
     */
    DaYunVo daYun(DaYunDto dto);

    /**
     * 流年切换 - 根据选择的流年轮数获取对应的流月和流日信息
     *
     * @param dto 流年参数
     * @return 流年切换结果
     */
    LiuNianVo liuNian(LiuNianDto dto);

    /**
     * 流月切换 - 根据选择的流月轮数获取对应的流日信息
     *
     * @param dto 流月参数
     * @return 流月切换结果
     */
    LiuYueVo liuYue(LiuYueDto dto);

    /**
     * 流日切换 - 根据选择的流日轮数获取对应的流时信息
     *
     * @param dto 流日参数
     * @return 流日切换结果
     */
    LiuRiVo liuRi(LiuRiDto dto);

    /**
     * 流时切换 - 根据选择的流时轮数获取详细信息
     *
     * @param dto 流时参数
     * @return 流时切换结果
     */
    LiuShiVo liuShi(LiuShiDto dto);

    /**
     * 胎命身查询 - 获取胎元、胎息、命宫、身宫信息
     *
     * @param dto 胎命身参数
     * @return 胎命身查询结果
     */
    TaiMingShenVo taiMingShen(TaiMingShenDto dto);
}
