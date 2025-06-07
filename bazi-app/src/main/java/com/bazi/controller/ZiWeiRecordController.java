package com.bazi.controller;

import com.bazi.common.result.Result;
import com.bazi.common.result.ResultEnum;
import com.bazi.dto.ziwei.ZiWeiDto;
import com.bazi.entity.ZiWeiRecord;
import com.bazi.service.ZiWeiService;
import com.bazi.service.ZiWeiRecordService;
import com.bazi.vo.ziwei.ZiWeiVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 紫微记录控制器
 *
 * @author skm1229
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/records/ziwei")
@Tag(name = "记录管理 - 紫微", description = "紫微斗数记录的保存、查询、更新、删除功能")
public class ZiWeiRecordController {

    @Resource
    private ZiWeiService ziWeiService;

    @Resource
    private ZiWeiRecordService ziweiRecordService;

    @PostMapping("/save")
    @Operation(summary = "保存紫微排盘记录", description = "排盘并保存紫微记录到数据库")
    public Result<ZiWeiRecord> saveZiweiRecord(@RequestParam Long userId, @Valid @RequestBody ZiWeiDto ziWeiDto) {
        // 先进行紫微排盘
        ZiWeiVo ziWeiVo = ziWeiService.paiPan(ziWeiDto);
        
        // 保存排盘记录
        ZiWeiRecord record = ziweiRecordService.saveZiweiRecord(userId, ziWeiDto, ziWeiVo);
        
        return Result.result(ResultEnum.CREATED, "紫微记录保存成功", record);
    }

    @GetMapping("/{id}")
    @Operation(summary = "查询紫微记录", description = "根据ID查询紫微排盘记录")
    public Result<ZiWeiRecord> getZiweiRecord(@PathVariable Long id, @RequestParam Long userId) {
        ZiWeiRecord record = ziweiRecordService.getZiweiRecordById(id, userId);
        return Result.result(ResultEnum.SUCCESS, "查询成功", record);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "查询用户的所有紫微记录", description = "根据用户ID查询其所有紫微排盘记录列表")
    public Result<List<ZiWeiRecord>> getZiweiRecordsByUserId(@PathVariable Long userId) {
        List<ZiWeiRecord> records = ziweiRecordService.getZiweiRecordsByUserId(userId);
        return Result.result(ResultEnum.SUCCESS, "查询成功", records);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新紫微记录", description = "更新指定的紫微排盘记录")
    public Result<ZiWeiRecord> updateZiweiRecord(@PathVariable Long id, @RequestParam Long userId, @Valid @RequestBody ZiWeiDto ziWeiDto) {
        // 先进行紫微排盘
        ZiWeiVo ziWeiVo = ziWeiService.paiPan(ziWeiDto);
        
        // 更新排盘记录
        ZiWeiRecord record = ziweiRecordService.updateZiweiRecord(id, userId, ziWeiDto, ziWeiVo);
        
        return Result.result(ResultEnum.UPDATED, "紫微记录更新成功", record);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除紫微记录", description = "删除指定的紫微排盘记录")
    public Result<String> deleteZiweiRecord(@PathVariable Long id, @RequestParam Long userId) {
        ziweiRecordService.deleteZiweiRecord(id, userId);
        return Result.result(ResultEnum.DELETED, "删除成功");
    }
}
