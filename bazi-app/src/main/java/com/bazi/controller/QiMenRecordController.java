package com.bazi.controller;

import com.bazi.common.result.Result;
import com.bazi.common.result.ResultEnum;
import com.bazi.dto.qimen.QiMenDto;
import com.bazi.entity.QiMenRecord;
import com.bazi.service.QiMenService;
import com.bazi.service.QiMenRecordService;
import com.bazi.vo.qimen.QiMenVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 奇门记录控制器
 *
 * @author skm1229
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/records/qimen")
@Tag(name = "记录管理 - 奇门", description = "奇门遁甲记录的保存、查询、更新、删除功能")
public class QiMenRecordController {

    @Resource
    private QiMenService qiMenService;

    @Resource
    private QiMenRecordService qimenRecordService;

    @PostMapping("/save")
    @Operation(summary = "保存奇门记录")
    public Result<QiMenRecord> saveQimenRecord(@RequestParam Long userId, @Valid @RequestBody QiMenDto qiMenDto) {
        // 先进行奇门排盘
        QiMenVo qiMenVo = qiMenService.paiPan(qiMenDto);
        
        // 保存排盘记录
        QiMenRecord record = qimenRecordService.saveQimenRecord(userId, qiMenDto, qiMenVo);
        
        return Result.result(ResultEnum.CREATED, "奇门记录保存成功", record);
    }

    @GetMapping("/{id}")
    @Operation(summary = "查询奇门记录")
    public Result<QiMenRecord> getQimenRecord(@PathVariable Long id, @RequestParam Long userId) {
        QiMenRecord record = qimenRecordService.getQimenRecordById(id, userId);
        return Result.result(ResultEnum.SUCCESS, "查询成功", record);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "查询用户奇门记录")
    public Result<List<QiMenRecord>> getQimenRecordsByUserId(@PathVariable Long userId) {
        List<QiMenRecord> records = qimenRecordService.getQimenRecordsByUserId(userId);
        return Result.result(ResultEnum.SUCCESS, "查询成功", records);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新奇门记录")
    public Result<QiMenRecord> updateQimenRecord(@PathVariable Long id, @RequestParam Long userId, @Valid @RequestBody QiMenDto qiMenDto) {
        // 先进行奇门排盘
        QiMenVo qiMenVo = qiMenService.paiPan(qiMenDto);
        
        // 更新排盘记录
        QiMenRecord record = qimenRecordService.updateQimenRecord(id, userId, qiMenDto, qiMenVo);
        
        return Result.result(ResultEnum.UPDATED, "奇门记录更新成功", record);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除奇门记录")
    public Result<String> deleteQimenRecord(@PathVariable Long id, @RequestParam Long userId) {
        qimenRecordService.deleteQimenRecord(id, userId);
        return Result.result(ResultEnum.DELETED, "删除成功");
    }
}
