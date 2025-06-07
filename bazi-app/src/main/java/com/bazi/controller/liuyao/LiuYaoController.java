package com.bazi.controller.liuyao;

import com.bazi.common.result.Result;
import com.bazi.common.result.ResultEnum;
import com.bazi.dto.liuyao.LiuYaoDto;
import com.bazi.service.LiuYaoService;
import com.bazi.vo.liuyao.LiuYaoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 六爻排盘控制器
 * 
 * <p>提供六爻排盘相关的REST API接口</p>
 *
 * @author skm1229
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/liuyao")
@RequiredArgsConstructor
@Tag(name = "六爻排盘", description = "六爻排盘相关接口")
public class LiuYaoController {

    private final LiuYaoService liuYaoService;

    /**
     * 六爻排盘
     * 
     * <p>根据输入参数进行六爻排盘，返回完整的六爻分析结果</p>
     * 
     * @param dto 六爻排盘输入参数
     * @return 六爻排盘结果
     */
    @Operation(summary = "六爻排盘")
    @PostMapping("/paiPan")
    public Result<LiuYaoVo> paiPan(@Valid @RequestBody LiuYaoDto dto) {
        LiuYaoVo vo = liuYaoService.paiPan(dto);
        return Result.result(ResultEnum.SUCCESS, "六爻排盘成功", vo);
    }
}
