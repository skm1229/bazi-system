package com.bazi.service;

import com.bazi.dto.liuyao.LiuYaoDto;
import com.bazi.entity.LiuYaoRecord;
import com.bazi.vo.liuyao.LiuYaoVo;

import java.util.List;

/**
 * 六爻记录服务接口
 *
 * @author skm1229
 * @version 1.0.0
 */
public interface LiuYaoRecordService {

    /**
     * 保存六爻排盘记录
     *
     * @param userId 用户ID
     * @param liuYaoDto 六爻排盘参数
     * @param liuYaoVo 六爻排盘结果
     * @return 保存的记录
     */
    LiuYaoRecord saveLiuyaoRecord(Long userId, LiuYaoDto liuYaoDto, LiuYaoVo liuYaoVo);

    /**
     * 根据ID查询六爻记录
     *
     * @param id 记录ID
     * @param userId 用户ID
     * @return 六爻记录
     */
    LiuYaoRecord getLiuyaoRecordById(Long id, Long userId);

    /**
     * 查询用户的所有六爻记录
     *
     * @param userId 用户ID
     * @return 六爻记录列表
     */
    List<LiuYaoRecord> getLiuyaoRecordsByUserId(Long userId);

    /**
     * 更新六爻记录
     *
     * @param id 记录ID
     * @param userId 用户ID
     * @param liuYaoDto 六爻排盘参数
     * @param liuYaoVo 六爻排盘结果
     * @return 更新后的记录
     */
    LiuYaoRecord updateLiuyaoRecord(Long id, Long userId, LiuYaoDto liuYaoDto, LiuYaoVo liuYaoVo);

    /**
     * 删除六爻记录
     *
     * @param id 记录ID
     * @param userId 用户ID
     */
    void deleteLiuyaoRecord(Long id, Long userId);
}
