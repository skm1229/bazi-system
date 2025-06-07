package com.bazi.controller;

import com.bazi.common.result.Result;
import com.bazi.common.result.ResultEnum;
import com.bazi.dto.meihua.MeiHuaDto;
import com.bazi.entity.MeiHuaRecord;
import com.bazi.service.MeiHuaService;
import com.bazi.service.MeiHuaRecordService;
import com.bazi.vo.meihua.MeiHuaVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 梅花记录控制器
 *
 * @author skm1229
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/records/meihua")
@Tag(name = "记录管理 - 梅花", description = "梅花易数记录的保存、查询、更新、删除功能")
public class MeiHuaRecordController {

    @Resource
    private MeiHuaService meiHuaService;

    @Resource
    private MeiHuaRecordService meihuaRecordService;

    @PostMapping("/save")
    @Operation(summary = "保存梅花排盘记录", description = "排盘并保存梅花记录到数据库")
    public Result<MeiHuaRecord> saveMeihuaRecord(@RequestParam Long userId, @Valid @RequestBody MeiHuaDto meiHuaDto) {
        // 先进行梅花排盘
        MeiHuaVo meiHuaVo = meiHuaService.paiPan(meiHuaDto);

        // 保存排盘记录
        MeiHuaRecord record = meihuaRecordService.saveMeihuaRecord(userId, meiHuaDto, meiHuaVo);

        return Result.result(ResultEnum.CREATED, "梅花记录保存成功", record);
    }

    @GetMapping("/{id}")
    @Operation(summary = "查询梅花记录", description = "根据ID查询梅花排盘记录")
    public Result<MeiHuaRecord> getMeihuaRecord(@PathVariable Long id, @RequestParam Long userId) {
        MeiHuaRecord record = meihuaRecordService.getMeihuaRecordById(id, userId);
        return Result.result(ResultEnum.SUCCESS, "查询成功", record);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "查询用户的所有梅花记录", description = "根据用户ID查询其所有梅花排盘记录列表")
    public Result<List<MeiHuaRecord>> getMeihuaRecordsByUserId(@PathVariable Long userId) {
        List<MeiHuaRecord> records = meihuaRecordService.getMeihuaRecordsByUserId(userId);
        return Result.result(ResultEnum.SUCCESS, "查询成功", records);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新梅花记录", description = "更新指定的梅花排盘记录")
    public Result<MeiHuaRecord> updateMeihuaRecord(@PathVariable Long id, @RequestParam Long userId, @Valid @RequestBody MeiHuaDto meiHuaDto) {
        // 先进行梅花排盘
        MeiHuaVo meiHuaVo = meiHuaService.paiPan(meiHuaDto);

        // 更新排盘记录
        MeiHuaRecord record = meihuaRecordService.updateMeihuaRecord(id, userId, meiHuaDto, meiHuaVo);

        return Result.result(ResultEnum.UPDATED, "梅花记录更新成功", record);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除梅花记录", description = "删除指定的梅花排盘记录")
    public Result<String> deleteMeihuaRecord(@PathVariable Long id, @RequestParam Long userId) {
        meihuaRecordService.deleteMeihuaRecord(id, userId);
        return Result.result(ResultEnum.DELETED, "删除成功");
    }
}
