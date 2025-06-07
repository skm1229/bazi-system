package com.bazi.controller;

import com.bazi.common.result.Result;
import com.bazi.common.result.ResultEnum;
import com.bazi.dto.liuyao.LiuYaoDto;
import com.bazi.entity.LiuYaoRecord;
import com.bazi.service.LiuYaoService;
import com.bazi.service.LiuYaoRecordService;
import com.bazi.vo.liuyao.LiuYaoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 六爻记录控制器
 *
 * @author skm1229
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/records/liuyao")
@Tag(name = "记录管理 - 六爻", description = "六爻排盘记录的保存、查询、更新、删除功能")
public class LiuYaoRecordController {

    @Resource
    private LiuYaoService liuYaoService;

    @Resource
    private LiuYaoRecordService liuyaoRecordService;

    @PostMapping("/save")
    @Operation(summary = "保存六爻排盘记录", description = "排盘并保存六爻记录到数据库")
    public Result<LiuYaoRecord> saveLiuyaoRecord(@RequestParam Long userId, @Valid @RequestBody LiuYaoDto liuYaoDto) {
        // 先进行六爻排盘
        LiuYaoVo liuYaoVo = liuYaoService.paiPan(liuYaoDto);
        
        // 保存排盘记录
        LiuYaoRecord record = liuyaoRecordService.saveLiuyaoRecord(userId, liuYaoDto, liuYaoVo);
        
        return Result.result(ResultEnum.CREATED, "六爻记录保存成功", record);
    }

    @GetMapping("/{id}")
    @Operation(summary = "查询六爻记录", description = "根据ID查询六爻排盘记录")
    public Result<LiuYaoRecord> getLiuyaoRecord(@PathVariable Long id, @RequestParam Long userId) {
        LiuYaoRecord record = liuyaoRecordService.getLiuyaoRecordById(id, userId);
        return Result.result(ResultEnum.SUCCESS, "查询成功", record);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "查询用户的所有六爻记录", description = "根据用户ID查询其所有六爻排盘记录列表")
    public Result<List<LiuYaoRecord>> getLiuyaoRecordsByUserId(@PathVariable Long userId) {
        List<LiuYaoRecord> records = liuyaoRecordService.getLiuyaoRecordsByUserId(userId);
        return Result.result(ResultEnum.SUCCESS, "查询成功", records);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新六爻记录", description = "更新指定的六爻排盘记录")
    public Result<LiuYaoRecord> updateLiuyaoRecord(@PathVariable Long id, @RequestParam Long userId, @Valid @RequestBody LiuYaoDto liuYaoDto) {
        // 先进行六爻排盘
        LiuYaoVo liuYaoVo = liuYaoService.paiPan(liuYaoDto);
        
        // 更新排盘记录
        LiuYaoRecord record = liuyaoRecordService.updateLiuyaoRecord(id, userId, liuYaoDto, liuYaoVo);
        
        return Result.result(ResultEnum.UPDATED, "六爻记录更新成功", record);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除六爻记录", description = "删除指定的六爻排盘记录")
    public Result<String> deleteLiuyaoRecord(@PathVariable Long id, @RequestParam Long userId) {
        liuyaoRecordService.deleteLiuyaoRecord(id, userId);
        return Result.result(ResultEnum.DELETED, "删除成功");
    }
}
