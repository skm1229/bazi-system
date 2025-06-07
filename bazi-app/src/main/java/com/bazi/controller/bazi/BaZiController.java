package com.bazi.controller.bazi;

import com.bazi.common.result.Result;
import com.bazi.common.result.ResultEnum;
import com.bazi.dto.bazi.*;
import com.bazi.service.BaZiService;
import com.bazi.vo.bazi.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 八字排盘控制器
 *
 * <p>提供八字排盘相关的REST API接口</p>
 *
 * @author skm1229
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/bazi")
@RequiredArgsConstructor
@Tag(name = "八字排盘", description = "八字排盘相关接口")
public class BaZiController {

    private final BaZiService baZiService;

    /**
     * 八字排盘
     *
     * <p>根据出生信息进行八字排盘，返回完整的八字分析结果</p>
     *
     * @param dto 八字排盘输入参数
     * @return 八字排盘结果
     */
    @Operation(summary = "八字排盘")
    @PostMapping("/paiPan")
    public Result<BaZiVo> paiPan(@Valid @RequestBody BaZiDto dto) {
        BaZiVo vo = baZiService.paiPan(dto);
        return Result.result(ResultEnum.SUCCESS, "八字排盘成功", vo);
    }

    /**
     * 大运初始化
     *
     * <p>获取大运、流年、流月信息的初始化数据</p>
     *
     * @param dto 大运初始化输入参数
     * @return 大运初始化结果
     */
    @Operation(summary = "大运初始化", description = "获取大运、流年、流月信息的初始化数据")
    @PostMapping("/daYunInit")
    public Result<DaYunInitializeVo> daYunInit(@Valid @RequestBody DaYunInitializeDto dto) {
        DaYunInitializeVo vo = baZiService.daYunInit(dto);
        return Result.result(ResultEnum.SUCCESS, "大运初始化成功", vo);
    }

    /**
     * 大运切换
     *
     * <p>根据选择的大运轮数获取对应的流年和流月信息</p>
     *
     * @param dto 大运切换输入参数
     * @return 大运切换结果
     */
    @Operation(summary = "大运切换", description = "根据选择的大运轮数获取对应的流年和流月信息")
    @PostMapping("/daYun")
    public Result<DaYunVo> daYun(@Valid @RequestBody DaYunDto dto) {
        DaYunVo vo = baZiService.daYun(dto);
        return Result.result(ResultEnum.SUCCESS, "大运切换成功", vo);
    }

    /**
     * 流年切换
     *
     * <p>根据选择的流年轮数获取对应的流月和流日信息</p>
     *
     * @param dto 流年切换输入参数
     * @return 流年切换结果
     */
    @Operation(summary = "流年切换", description = "根据选择的流年轮数获取对应的流月和流日信息")
    @PostMapping("/liuNian")
    public Result<LiuNianVo> liuNian(@Valid @RequestBody LiuNianDto dto) {
        LiuNianVo vo = baZiService.liuNian(dto);
        return Result.result(ResultEnum.SUCCESS, "流年切换成功", vo);
    }

    /**
     * 流月切换
     *
     * <p>根据选择的流月轮数获取对应的流日信息</p>
     *
     * @param dto 流月切换输入参数
     * @return 流月切换结果
     */
    @Operation(summary = "流月切换", description = "根据选择的流月轮数获取对应的流日信息")
    @PostMapping("/liuYue")
    public Result<LiuYueVo> liuYue(@Valid @RequestBody LiuYueDto dto) {
        LiuYueVo vo = baZiService.liuYue(dto);
        return Result.result(ResultEnum.SUCCESS, "流月切换成功", vo);
    }

    /**
     * 流日切换
     *
     * <p>根据选择的流日轮数获取对应的流时信息</p>
     *
     * @param dto 流日切换输入参数
     * @return 流日切换结果
     */
    @Operation(summary = "流日切换", description = "根据选择的流日轮数获取对应的流时信息")
    @PostMapping("/liuRi")
    public Result<LiuRiVo> liuRi(@Valid @RequestBody LiuRiDto dto) {
        LiuRiVo vo = baZiService.liuRi(dto);
        return Result.result(ResultEnum.SUCCESS, "流日切换成功", vo);
    }

    /**
     * 流时切换
     *
     * <p>根据选择的流时轮数获取详细信息</p>
     *
     * @param dto 流时切换输入参数
     * @return 流时切换结果
     */
    @Operation(summary = "流时切换", description = "根据选择的流时轮数获取详细信息")
    @PostMapping("/liuShi")
    public Result<LiuShiVo> liuShi(@Valid @RequestBody LiuShiDto dto) {
        LiuShiVo vo = baZiService.liuShi(dto);
        return Result.result(ResultEnum.SUCCESS, "流时切换成功", vo);
    }

    /**
     * 胎命身查询
     *
     * <p>获取胎元、胎息、命宫、身宫信息</p>
     *
     * @param dto 胎命身查询输入参数
     * @return 胎命身查询结果
     */
    @Operation(summary = "胎命身查询", description = "获取胎元、胎息、命宫、身宫信息")
    @PostMapping("/taiMingShen")
    public Result<TaiMingShenVo> taiMingShen(@Valid @RequestBody TaiMingShenDto dto) {
        TaiMingShenVo vo = baZiService.taiMingShen(dto);
        return Result.result(ResultEnum.SUCCESS, "胎命身查询成功", vo);
    }
}
