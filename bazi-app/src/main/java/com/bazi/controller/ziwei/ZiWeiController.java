package com.bazi.controller.ziwei;

import com.bazi.common.result.Result;
import com.bazi.common.result.ResultEnum;
import com.bazi.dto.ziwei.ZiWeiDto;
import com.bazi.service.ZiWeiService;
import com.bazi.vo.ziwei.ZiWeiVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 紫微斗数排盘控制器
 * 
 * <p>提供紫微斗数排盘相关的REST API接口</p>
 *
 * @author skm1229
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/ziwei")
@RequiredArgsConstructor
@Tag(name = "紫微斗数", description = "紫微斗数排盘相关接口")
public class ZiWeiController {

    private final ZiWeiService ziWeiService;

    /**
     * 紫微斗数排盘
     * 
     * <p>根据输入参数进行紫微斗数排盘，返回完整的紫微斗数分析结果</p>
     * 
     * @param dto 紫微斗数排盘输入参数
     * @return 紫微斗数排盘结果
     */
    @Operation(summary = "紫微斗数排盘")
    @PostMapping("/paiPan")
    public Result<ZiWeiVo> paiPan(@Valid @RequestBody ZiWeiDto dto) {
        ZiWeiVo vo = ziWeiService.paiPan(dto);
        return Result.result(ResultEnum.SUCCESS, "紫微斗数排盘成功", vo);
    }
}
