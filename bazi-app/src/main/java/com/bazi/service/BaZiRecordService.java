package com.bazi.service;

import com.bazi.dto.bazi.BaZiDto;
import com.bazi.entity.BaZiRecord;
import com.bazi.vo.bazi.BaZiVo;

import java.util.List;

/**
 * 八字记录服务接口
 *
 * @author skm1229
 * @version 1.0.0
 */
public interface BaZiRecordService {

    /**
     * 保存八字排盘记录
     *
     * @param userId 用户ID
     * @param baZiDto 八字排盘参数
     * @param baziVo 八字排盘结果
     * @return 保存的记录
     */
    BaZiRecord saveBaziRecord(Long userId, BaZiDto baZiDto, BaZiVo baziVo);

    /**
     * 根据ID查询八字记录
     *
     * @param id 记录ID
     * @param userId 用户ID
     * @return 八字记录
     */
    BaZiRecord getBaziRecordById(Long id, Long userId);

    /**
     * 查询用户的所有八字记录
     *
     * @param userId 用户ID
     * @return 八字记录列表
     */
    List<BaZiRecord> getBaziRecordsByUserId(Long userId);

    /**
     * 更新八字记录
     *
     * @param id 记录ID
     * @param userId 用户ID
     * @param baZiDto 八字排盘参数
     * @param baziVo 八字排盘结果
     * @return 更新后的记录
     */
    BaZiRecord updateBaziRecord(Long id, Long userId, BaZiDto baZiDto, BaZiVo baziVo);

    /**
     * 删除八字记录
     *
     * @param id 记录ID
     * @param userId 用户ID
     */
    void deleteBaziRecord(Long id, Long userId);
}
