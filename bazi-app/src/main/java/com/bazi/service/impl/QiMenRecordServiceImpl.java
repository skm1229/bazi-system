package com.bazi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bazi.common.exception.BusinessException;
import com.bazi.dto.qimen.QiMenDto;
import com.bazi.entity.QiMenRecord;
import com.bazi.mapper.QiMenRecordMapper;
import com.bazi.service.QiMenRecordService;
import com.bazi.vo.qimen.QiMenVo;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 奇门记录服务实现类
 *
 * @author skm1229
 * @version 1.0.0
 */
@Service
public class QiMenRecordServiceImpl implements QiMenRecordService {

    @Resource
    private QiMenRecordMapper qimenRecordMapper;

    @Override
    public QiMenRecord saveQimenRecord(Long userId, QiMenDto qiMenDto, QiMenVo qiMenVo) {
        QiMenRecord record = new QiMenRecord();
        
        // 拷贝输入参数
        BeanUtils.copyProperties(qiMenDto, record);
        record.setUserId(userId);

        // 从VO中提取奇门显示字段
        if (qiMenVo != null) {
            record.setJuShu(String.valueOf(qiMenVo.getJuShu())); // int转String
            record.setXunShou(qiMenVo.getXunShou());
            record.setZhiFu(qiMenVo.getZhiFu());
            record.setZhiShi(qiMenVo.getZhiShi());
        }
        
        record.setCreatedAt(LocalDateTime.now());
        record.setUpdatedAt(LocalDateTime.now());
        
        qimenRecordMapper.insert(record);
        return record;
    }

    @Override
    public QiMenRecord getQimenRecordById(Long id, Long userId) {
        LambdaQueryWrapper<QiMenRecord> queryWrapper = new LambdaQueryWrapper<QiMenRecord>()
                .eq(QiMenRecord::getId, id)
                .eq(QiMenRecord::getUserId, userId);
        
        QiMenRecord record = qimenRecordMapper.selectOne(queryWrapper);
        if (record == null) {
            throw BusinessException.recordNotFound();
        }
        return record;
    }

    @Override
    public List<QiMenRecord> getQimenRecordsByUserId(Long userId) {
        LambdaQueryWrapper<QiMenRecord> queryWrapper = new LambdaQueryWrapper<QiMenRecord>()
                .eq(QiMenRecord::getUserId, userId)
                .orderByDesc(QiMenRecord::getCreatedAt);
        
        return qimenRecordMapper.selectList(queryWrapper);
    }

    @Override
    public QiMenRecord updateQimenRecord(Long id, Long userId, QiMenDto qiMenDto, QiMenVo qiMenVo) {
        LambdaQueryWrapper<QiMenRecord> queryWrapper = new LambdaQueryWrapper<QiMenRecord>()
                .eq(QiMenRecord::getId, id)
                .eq(QiMenRecord::getUserId, userId);
        
        QiMenRecord record = qimenRecordMapper.selectOne(queryWrapper);
        if (record == null) {
            throw BusinessException.recordNotFound();
        }
        
        // 更新输入参数
        BeanUtils.copyProperties(qiMenDto, record);

        // 更新奇门显示字段
        if (qiMenVo != null) {
            record.setJuShu(String.valueOf(qiMenVo.getJuShu())); // int转String
            record.setXunShou(qiMenVo.getXunShou());
            record.setZhiFu(qiMenVo.getZhiFu());
            record.setZhiShi(qiMenVo.getZhiShi());
        }
        
        record.setUpdatedAt(LocalDateTime.now());
        qimenRecordMapper.updateById(record);
        
        return record;
    }

    @Override
    public void deleteQimenRecord(Long id, Long userId) {
        LambdaQueryWrapper<QiMenRecord> queryWrapper = new LambdaQueryWrapper<QiMenRecord>()
                .eq(QiMenRecord::getId, id)
                .eq(QiMenRecord::getUserId, userId);
        
        QiMenRecord record = qimenRecordMapper.selectOne(queryWrapper);
        if (record == null) {
            throw BusinessException.recordNotFound();
        }
        
        qimenRecordMapper.deleteById(id);
    }
}
