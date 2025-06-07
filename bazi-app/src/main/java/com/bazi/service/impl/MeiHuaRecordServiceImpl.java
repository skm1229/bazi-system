package com.bazi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bazi.common.exception.BusinessException;
import com.bazi.common.result.ResultEnum;
import com.bazi.dto.meihua.MeiHuaDto;
import com.bazi.entity.MeiHuaRecord;
import com.bazi.mapper.MeiHuaRecordMapper;
import com.bazi.service.MeiHuaRecordService;
import com.bazi.vo.meihua.MeiHuaVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 梅花记录服务实现类
 *
 * @author skm1229
 * @version 1.0.0
 */
@Service
public class MeiHuaRecordServiceImpl implements MeiHuaRecordService {

    @Resource
    private MeiHuaRecordMapper meihuaRecordMapper;

    @Override
    public MeiHuaRecord saveMeihuaRecord(Long userId, MeiHuaDto meiHuaDto, MeiHuaVo meiHuaVo) {
        MeiHuaRecord record = new MeiHuaRecord();

        // 拷贝输入参数
        BeanUtils.copyProperties(meiHuaDto, record);
        record.setUserId(userId);

        // 从VO中提取卦象显示字段
        if (meiHuaVo != null) {
            record.setBenGuaName(meiHuaVo.getBenGua());
            record.setBianGuaName(meiHuaVo.getBianGua());
            record.setTiGua(meiHuaVo.getShangGua()); // 上卦作为体卦
            record.setYongGua(meiHuaVo.getXiaGua()); // 下卦作为用卦
        }

        record.setCreatedAt(LocalDateTime.now());
        record.setUpdatedAt(LocalDateTime.now());

        meihuaRecordMapper.insert(record);
        return record;
    }

    @Override
    public MeiHuaRecord getMeihuaRecordById(Long id, Long userId) {
        LambdaQueryWrapper<MeiHuaRecord> queryWrapper = new LambdaQueryWrapper<MeiHuaRecord>()
                .eq(MeiHuaRecord::getId, id)
                .eq(MeiHuaRecord::getUserId, userId);
        
        MeiHuaRecord record = meihuaRecordMapper.selectOne(queryWrapper);
        if (record == null) {
            throw BusinessException.recordNotFound();
        }
        return record;
    }

    @Override
    public List<MeiHuaRecord> getMeihuaRecordsByUserId(Long userId) {
        LambdaQueryWrapper<MeiHuaRecord> queryWrapper = new LambdaQueryWrapper<MeiHuaRecord>()
                .eq(MeiHuaRecord::getUserId, userId)
                .orderByDesc(MeiHuaRecord::getCreatedAt);
        
        return meihuaRecordMapper.selectList(queryWrapper);
    }

    @Override
    public MeiHuaRecord updateMeihuaRecord(Long id, Long userId, MeiHuaDto meiHuaDto, MeiHuaVo meiHuaVo) {
        LambdaQueryWrapper<MeiHuaRecord> queryWrapper = new LambdaQueryWrapper<MeiHuaRecord>()
                .eq(MeiHuaRecord::getId, id)
                .eq(MeiHuaRecord::getUserId, userId);

        MeiHuaRecord record = meihuaRecordMapper.selectOne(queryWrapper);
        if (record == null) {
            throw BusinessException.recordNotFound();
        }

        // 更新输入参数
        BeanUtils.copyProperties(meiHuaDto, record);

        // 更新卦象显示字段
        if (meiHuaVo != null) {
            record.setBenGuaName(meiHuaVo.getBenGua());
            record.setBianGuaName(meiHuaVo.getBianGua());
            record.setTiGua(meiHuaVo.getShangGua()); // 上卦作为体卦
            record.setYongGua(meiHuaVo.getXiaGua()); // 下卦作为用卦
        }

        record.setUpdatedAt(LocalDateTime.now());
        meihuaRecordMapper.updateById(record);

        return record;
    }

    @Override
    public void deleteMeihuaRecord(Long id, Long userId) {
        LambdaQueryWrapper<MeiHuaRecord> queryWrapper = new LambdaQueryWrapper<MeiHuaRecord>()
                .eq(MeiHuaRecord::getId, id)
                .eq(MeiHuaRecord::getUserId, userId);
        
        MeiHuaRecord record = meihuaRecordMapper.selectOne(queryWrapper);
        if (record == null) {
            throw new BusinessException(ResultEnum.DATA_NOT_FOUND);
        }
        
        meihuaRecordMapper.deleteById(id);
    }
}
