package com.bazi.controller;

import com.bazi.common.result.Result;
import com.bazi.common.result.ResultEnum;
import com.bazi.dto.bazi.BaZiDto;
import com.bazi.entity.BaZiRecord;
import com.bazi.service.BaZiService;
import com.bazi.service.BaZiRecordService;
import com.bazi.vo.bazi.BaZiVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 八字记录控制器
 *
 * @author skm1229
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/records/bazi")
@Tag(name = "记录管理 - 八字", description = "八字排盘记录的保存、查询、更新、删除功能")
public class BaZiRecordController {

    @Resource
    private BaZiService baZiService;

    @Resource
    private BaZiRecordService baziRecordService;

    @PostMapping("/save")
    @Operation(summary = "保存八字记录")
    public Result<BaZiRecord> saveBaziRecord(@RequestParam Long userId, @Valid @RequestBody BaZiDto baZiDto) {
        // 先进行八字排盘
        BaZiVo baziVo = baZiService.paiPan(baZiDto);

        // 保存排盘记录
        BaZiRecord record = baziRecordService.saveBaziRecord(userId, baZiDto, baziVo);

        return Result.result(ResultEnum.CREATED, "八字记录保存成功", record);
    }

    @GetMapping("/{id}")
    @Operation(summary = "查询八字记录")
    public Result<BaZiRecord> getBaziRecord(@PathVariable Long id, @RequestParam Long userId) {
        BaZiRecord record = baziRecordService.getBaziRecordById(id, userId);
        return Result.result(ResultEnum.SUCCESS, "查询成功", record);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "查询用户八字记录")
    public Result<List<BaZiRecord>> getBaziRecordsByUserId(@PathVariable Long userId) {
        java.util.List<BaZiRecord> records = baziRecordService.getBaziRecordsByUserId(userId);
        return Result.result(ResultEnum.SUCCESS, "查询成功", records);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新八字记录")
    public Result<BaZiRecord> updateBaziRecord(@PathVariable Long id, @RequestParam Long userId, @Valid @RequestBody BaZiDto baZiDto) {
        // 先进行八字排盘
        BaZiVo baziVo = baZiService.paiPan(baZiDto);

        // 更新排盘记录
        BaZiRecord record = baziRecordService.updateBaziRecord(id, userId, baZiDto, baziVo);

        return Result.result(ResultEnum.UPDATED, "八字记录更新成功", record);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除八字记录")
    public Result<String> deleteBaziRecord(@PathVariable Long id, @RequestParam Long userId) {
        baziRecordService.deleteBaziRecord(id, userId);
        return Result.result(ResultEnum.DELETED, "删除成功");
    }
}
