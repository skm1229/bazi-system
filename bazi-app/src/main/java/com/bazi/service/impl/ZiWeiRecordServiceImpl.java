package com.bazi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bazi.common.exception.BusinessException;
import com.bazi.dto.ziwei.ZiWeiDto;
import com.bazi.entity.ZiWeiRecord;
import com.bazi.mapper.ZiWeiRecordMapper;
import com.bazi.service.ZiWeiRecordService;
import com.bazi.vo.ziwei.ZiWeiVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 紫微记录服务实现类
 *
 * @author skm1229
 * @version 1.0.0
 */
@Service
public class ZiWeiRecordServiceImpl implements ZiWeiRecordService {

    @Resource
    private ZiWeiRecordMapper ziweiRecordMapper;

    @Override
    public ZiWeiRecord saveZiweiRecord(Long userId, ZiWeiDto ziWeiDto, ZiWeiVo ziWeiVo) {
        ZiWeiRecord record = new ZiWeiRecord();

        // 拷贝输入参数
        BeanUtils.copyProperties(ziWeiDto, record);
        record.setUserId(userId);

        // 从VO中提取紫微显示字段
        if (ziWeiVo != null) {
            record.setMingGong(ziWeiVo.getMingGongDiZhi()); // 命宫地支
            record.setShenGong(ziWeiVo.getShenGongDiZhi()); // 身宫地支
            record.setWuXingJu(ziWeiVo.getWuXingJu());      // 五行局
            record.setZhuXing(ziWeiVo.getMingZhu());        // 命主作为主星
        }

        record.setCreatedAt(LocalDateTime.now());
        record.setUpdatedAt(LocalDateTime.now());

        ziweiRecordMapper.insert(record);
        return record;
    }

    @Override
    public ZiWeiRecord getZiweiRecordById(Long id, Long userId) {
        LambdaQueryWrapper<ZiWeiRecord> queryWrapper = new LambdaQueryWrapper<ZiWeiRecord>()
                .eq(ZiWeiRecord::getId, id)
                .eq(ZiWeiRecord::getUserId, userId);

        ZiWeiRecord record = ziweiRecordMapper.selectOne(queryWrapper);
        if (record == null) {
            throw BusinessException.recordNotFound();
        }
        return record;
    }

    @Override
    public List<ZiWeiRecord> getZiweiRecordsByUserId(Long userId) {
        LambdaQueryWrapper<ZiWeiRecord> queryWrapper = new LambdaQueryWrapper<ZiWeiRecord>()
                .eq(ZiWeiRecord::getUserId, userId)
                .orderByDesc(ZiWeiRecord::getCreatedAt);
        
        return ziweiRecordMapper.selectList(queryWrapper);
    }

    @Override
    public ZiWeiRecord updateZiweiRecord(Long id, Long userId, ZiWeiDto ziWeiDto, ZiWeiVo ziWeiVo) {
        LambdaQueryWrapper<ZiWeiRecord> queryWrapper = new LambdaQueryWrapper<ZiWeiRecord>()
                .eq(ZiWeiRecord::getId, id)
                .eq(ZiWeiRecord::getUserId, userId);

        ZiWeiRecord record = ziweiRecordMapper.selectOne(queryWrapper);
        if (record == null) {
            throw BusinessException.recordNotFound();
        }

        // 更新输入参数
        BeanUtils.copyProperties(ziWeiDto, record);

        // 更新紫微显示字段
        if (ziWeiVo != null) {
            record.setMingGong(ziWeiVo.getMingGongDiZhi()); // 命宫地支
            record.setShenGong(ziWeiVo.getShenGongDiZhi()); // 身宫地支
            record.setWuXingJu(ziWeiVo.getWuXingJu());      // 五行局
            record.setZhuXing(ziWeiVo.getMingZhu());        // 命主作为主星
        }

        record.setUpdatedAt(LocalDateTime.now());
        ziweiRecordMapper.updateById(record);

        return record;
    }

    @Override
    public void deleteZiweiRecord(Long id, Long userId) {
        LambdaQueryWrapper<ZiWeiRecord> queryWrapper = new LambdaQueryWrapper<ZiWeiRecord>()
                .eq(ZiWeiRecord::getId, id)
                .eq(ZiWeiRecord::getUserId, userId);
        
        ZiWeiRecord record = ziweiRecordMapper.selectOne(queryWrapper);
        if (record == null) {
            throw BusinessException.recordNotFound();
        }
        
        ziweiRecordMapper.deleteById(id);
    }
}
