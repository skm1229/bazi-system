package com.bazi.controller.meihua;

import com.bazi.common.result.Result;
import com.bazi.common.result.ResultEnum;
import com.bazi.dto.meihua.MeiHuaDto;
import com.bazi.service.MeiHuaService;
import com.bazi.vo.meihua.MeiHuaVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 梅花易数排盘控制器
 * 
 * <p>提供梅花易数排盘相关的REST API接口</p>
 *
 * @author skm1229
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/meihua")
@RequiredArgsConstructor
@Tag(name = "梅花易数", description = "梅花易数排盘相关接口")
public class MeiHuaController {

    private final MeiHuaService meiHuaService;

    /**
     * 梅花易数排盘
     * 
     * <p>根据输入参数进行梅花易数排盘，返回完整的梅花易数分析结果</p>
     * 
     * @param dto 梅花易数排盘输入参数
     * @return 梅花易数排盘结果
     */
    @Operation(summary = "梅花易数排盘")
    @PostMapping("/paiPan")
    public Result<MeiHuaVo> paiPan(@Valid @RequestBody MeiHuaDto dto) {
        MeiHuaVo vo = meiHuaService.paiPan(dto);
        return Result.result(ResultEnum.SUCCESS, "梅花易数排盘成功", vo);
    }
}
