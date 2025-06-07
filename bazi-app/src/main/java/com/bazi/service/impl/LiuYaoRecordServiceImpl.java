package com.bazi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bazi.common.exception.BusinessException;
import com.bazi.dto.liuyao.LiuYaoDto;
import com.bazi.entity.LiuYaoRecord;
import com.bazi.mapper.LiuYaoRecordMapper;
import com.bazi.service.LiuYaoRecordService;
import com.bazi.vo.liuyao.LiuYaoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 六爻记录服务实现类
 *
 * @author skm1229
 * @version 1.0.0
 */
@Service
public class LiuYaoRecordServiceImpl implements LiuYaoRecordService {

    @Resource
    private LiuYaoRecordMapper liuyaoRecordMapper;

    @Override
    public LiuYaoRecord saveLiuyaoRecord(Long userId, LiuYaoDto liuYaoDto, LiuYaoVo liuYaoVo) {
        LiuYaoRecord record = new LiuYaoRecord();

        // 拷贝输入参数
        BeanUtils.copyProperties(liuYaoDto, record);
        record.setUserId(userId);

        // 从VO中提取卦象显示字段
        if (liuYaoVo != null) {
            record.setBenGuaName(liuYaoVo.getBenGua());
            record.setBianGuaName(null); // 六爻VO中暂无变卦信息，先设为null
            record.setBenGuaXiang(liuYaoVo.getBenGuaAs());
            record.setBianGuaXiang(null); // 六爻VO中暂无变卦卦象，先设为null
        }

        record.setCreatedAt(LocalDateTime.now());
        record.setUpdatedAt(LocalDateTime.now());

        liuyaoRecordMapper.insert(record);
        return record;
    }

    @Override
    public LiuYaoRecord getLiuyaoRecordById(Long id, Long userId) {
        LambdaQueryWrapper<LiuYaoRecord> queryWrapper = new LambdaQueryWrapper<LiuYaoRecord>()
                .eq(LiuYaoRecord::getId, id)
                .eq(LiuYaoRecord::getUserId, userId);
        
        LiuYaoRecord record = liuyaoRecordMapper.selectOne(queryWrapper);
        if (record == null) {
            throw BusinessException.recordNotFound();
        }
        return record;
    }

    @Override
    public List<LiuYaoRecord> getLiuyaoRecordsByUserId(Long userId) {
        LambdaQueryWrapper<LiuYaoRecord> queryWrapper = new LambdaQueryWrapper<LiuYaoRecord>()
                .eq(LiuYaoRecord::getUserId, userId)
                .orderByDesc(LiuYaoRecord::getCreatedAt);
        
        return liuyaoRecordMapper.selectList(queryWrapper);
    }

    @Override
    public LiuYaoRecord updateLiuyaoRecord(Long id, Long userId, LiuYaoDto liuYaoDto, LiuYaoVo liuYaoVo) {
        LambdaQueryWrapper<LiuYaoRecord> queryWrapper = new LambdaQueryWrapper<LiuYaoRecord>()
                .eq(LiuYaoRecord::getId, id)
                .eq(LiuYaoRecord::getUserId, userId);

        LiuYaoRecord record = liuyaoRecordMapper.selectOne(queryWrapper);
        if (record == null) {
            throw BusinessException.recordNotFound();
        }

        // 更新输入参数
        BeanUtils.copyProperties(liuYaoDto, record);

        // 更新卦象显示字段
        if (liuYaoVo != null) {
            record.setBenGuaName(liuYaoVo.getBenGua());
            record.setBianGuaName(null); // 六爻VO中暂无变卦信息，先设为null
            record.setBenGuaXiang(liuYaoVo.getBenGuaAs());
            record.setBianGuaXiang(null); // 六爻VO中暂无变卦卦象，先设为null
        }

        record.setUpdatedAt(LocalDateTime.now());
        liuyaoRecordMapper.updateById(record);

        return record;
    }

    @Override
    public void deleteLiuyaoRecord(Long id, Long userId) {
        LambdaQueryWrapper<LiuYaoRecord> queryWrapper = new LambdaQueryWrapper<LiuYaoRecord>()
                .eq(LiuYaoRecord::getId, id)
                .eq(LiuYaoRecord::getUserId, userId);
        
        LiuYaoRecord record = liuyaoRecordMapper.selectOne(queryWrapper);
        if (record == null) {
            throw BusinessException.recordNotFound();
        }
        
        liuyaoRecordMapper.deleteById(id);
    }
}
