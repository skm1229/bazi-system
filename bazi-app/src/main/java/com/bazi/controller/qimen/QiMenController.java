package com.bazi.controller.qimen;

import com.bazi.common.result.Result;
import com.bazi.common.result.ResultEnum;
import com.bazi.dto.qimen.QiMenDto;
import com.bazi.service.QiMenService;
import com.bazi.vo.qimen.QiMenVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 奇门遁甲排盘控制器
 * 
 * <p>提供奇门遁甲排盘相关的REST API接口</p>
 *
 * @author skm1229
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/qimen")
@RequiredArgsConstructor
@Tag(name = "奇门遁甲", description = "奇门遁甲排盘相关接口")
public class QiMenController {

    private final QiMenService qiMenService;

    /**
     * 奇门遁甲排盘
     * 
     * <p>根据输入参数进行奇门遁甲排盘，返回完整的奇门遁甲分析结果</p>
     * 
     * @param dto 奇门遁甲排盘输入参数
     * @return 奇门遁甲排盘结果
     */
    @Operation(summary = "奇门遁甲排盘")
    @PostMapping("/paiPan")
    public Result<QiMenVo> paiPan(@Valid @RequestBody QiMenDto dto) {
        QiMenVo vo = qiMenService.paiPan(dto);
        return Result.result(ResultEnum.SUCCESS, "奇门遁甲排盘成功", vo);
    }
}
