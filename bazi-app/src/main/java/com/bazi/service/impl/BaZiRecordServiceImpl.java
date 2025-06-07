package com.bazi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bazi.common.exception.BusinessException;
import com.bazi.dto.bazi.BaZiDto;
import com.bazi.entity.BaZiRecord;
import com.bazi.mapper.BaZiRecordMapper;
import com.bazi.service.BaZiRecordService;
import com.bazi.vo.bazi.BaZiVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 八字记录服务实现类
 *
 * @author skm1229
 * @version 1.0.0
 */
@Service
public class BaZiRecordServiceImpl implements BaZiRecordService {

    @Resource
    private BaZiRecordMapper baziRecordMapper;

    @Override
    public BaZiRecord saveBaziRecord(Long userId, BaZiDto baZiDto, BaZiVo baziVo) {
        BaZiRecord record = new BaZiRecord();

        // 🎯 使用BeanUtils一行代码完成属性拷贝！
        BeanUtils.copyProperties(baZiDto, record);

        // 设置特殊字段
        record.setUserId(userId);

        // 从排盘结果中提取四柱干支保存（分开存储）
        if (baziVo != null) {
            // 提取年柱干支
            if (baziVo.getYearGanZhi() != null && baziVo.getYearGanZhi().length() >= 2) {
                record.setYearGan(baziVo.getYearGanZhi().substring(0, 1));
                record.setYearZhi(baziVo.getYearGanZhi().substring(1));
            }
            // 提取月柱干支
            if (baziVo.getMonthGanZhi() != null && baziVo.getMonthGanZhi().length() >= 2) {
                record.setMonthGan(baziVo.getMonthGanZhi().substring(0, 1));
                record.setMonthZhi(baziVo.getMonthGanZhi().substring(1));
            }
            // 提取日柱干支
            if (baziVo.getDayGanZhi() != null && baziVo.getDayGanZhi().length() >= 2) {
                record.setDayGan(baziVo.getDayGanZhi().substring(0, 1));
                record.setDayZhi(baziVo.getDayGanZhi().substring(1));
            }
            // 提取时柱干支
            if (baziVo.getHourGanZhi() != null && baziVo.getHourGanZhi().length() >= 2) {
                record.setHourGan(baziVo.getHourGanZhi().substring(0, 1));
                record.setHourZhi(baziVo.getHourGanZhi().substring(1));
            }
        }

        // 设置时间
        record.setCreatedAt(LocalDateTime.now());
        record.setUpdatedAt(LocalDateTime.now());

        // 保存到数据库
        baziRecordMapper.insert(record);

        return record;
    }

    @Override
    public BaZiRecord getBaziRecordById(Long id, Long userId) {
        LambdaQueryWrapper<BaZiRecord> queryWrapper = new LambdaQueryWrapper<BaZiRecord>()
                .eq(BaZiRecord::getId, id)
                .eq(BaZiRecord::getUserId, userId);

        BaZiRecord record = baziRecordMapper.selectOne(queryWrapper);
        if (record == null) {
            throw BusinessException.recordNotFound();
        }
        return record;
    }

    @Override
    public List<BaZiRecord> getBaziRecordsByUserId(Long userId) {
        LambdaQueryWrapper<BaZiRecord> queryWrapper = new LambdaQueryWrapper<BaZiRecord>()
                .eq(BaZiRecord::getUserId, userId)
                .orderByDesc(BaZiRecord::getCreatedAt);

        return baziRecordMapper.selectList(queryWrapper);
    }

    @Override
    public BaZiRecord updateBaziRecord(Long id, Long userId, BaZiDto baZiDto, BaZiVo baziVo) {
        // 查询记录是否存在且属于当前用户
        LambdaQueryWrapper<BaZiRecord> queryWrapper = new LambdaQueryWrapper<BaZiRecord>()
                .eq(BaZiRecord::getId, id)
                .eq(BaZiRecord::getUserId, userId);

        BaZiRecord record = baziRecordMapper.selectOne(queryWrapper);
        if (record == null) {
            throw BusinessException.recordNotFound();
        }

        // 🎯 使用BeanUtils一行代码完成属性拷贝！
        BeanUtils.copyProperties(baZiDto, record);

        // 更新四柱干支（分开存储）
        if (baziVo != null) {
            // 更新年柱干支
            if (baziVo.getYearGanZhi() != null && baziVo.getYearGanZhi().length() >= 2) {
                record.setYearGan(baziVo.getYearGanZhi().substring(0, 1));
                record.setYearZhi(baziVo.getYearGanZhi().substring(1));
            }
            // 更新月柱干支
            if (baziVo.getMonthGanZhi() != null && baziVo.getMonthGanZhi().length() >= 2) {
                record.setMonthGan(baziVo.getMonthGanZhi().substring(0, 1));
                record.setMonthZhi(baziVo.getMonthGanZhi().substring(1));
            }
            // 更新日柱干支
            if (baziVo.getDayGanZhi() != null && baziVo.getDayGanZhi().length() >= 2) {
                record.setDayGan(baziVo.getDayGanZhi().substring(0, 1));
                record.setDayZhi(baziVo.getDayGanZhi().substring(1));
            }
            // 更新时柱干支
            if (baziVo.getHourGanZhi() != null && baziVo.getHourGanZhi().length() >= 2) {
                record.setHourGan(baziVo.getHourGanZhi().substring(0, 1));
                record.setHourZhi(baziVo.getHourGanZhi().substring(1));
            }
        }

        // 更新时间
        record.setUpdatedAt(LocalDateTime.now());

        // 保存到数据库
        baziRecordMapper.updateById(record);

        return record;
    }

    @Override
    public void deleteBaziRecord(Long id, Long userId) {
        // 查询记录是否存在且属于当前用户
        LambdaQueryWrapper<BaZiRecord> queryWrapper = new LambdaQueryWrapper<BaZiRecord>()
                .eq(BaZiRecord::getId, id)
                .eq(BaZiRecord::getUserId, userId);
        
        BaZiRecord record = baziRecordMapper.selectOne(queryWrapper);
        if (record == null) {
            throw BusinessException.recordNotFound();
        }
        
        // 删除记录
        baziRecordMapper.deleteById(id);
    }
}
