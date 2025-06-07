package com.bazi.service;

import com.bazi.dto.qimen.QiMenDto;
import com.bazi.entity.QiMenRecord;
import com.bazi.vo.qimen.QiMenVo;

import java.util.List;

/**
 * 奇门记录服务接口
 *
 * @author skm1229
 * @version 1.0.0
 */
public interface QiMenRecordService {

    /**
     * 保存奇门排盘记录
     *
     * @param userId 用户ID
     * @param qiMenDto 奇门排盘参数
     * @param qiMenVo 奇门排盘结果
     * @return 保存的记录
     */
    QiMenRecord saveQimenRecord(Long userId, QiMenDto qiMenDto, QiMenVo qiMenVo);

    /**
     * 根据ID查询奇门记录
     *
     * @param id 记录ID
     * @param userId 用户ID
     * @return 奇门记录
     */
    QiMenRecord getQimenRecordById(Long id, Long userId);

    /**
     * 查询用户的所有奇门记录
     *
     * @param userId 用户ID
     * @return 奇门记录列表
     */
    List<QiMenRecord> getQimenRecordsByUserId(Long userId);

    /**
     * 更新奇门记录
     *
     * @param id 记录ID
     * @param userId 用户ID
     * @param qiMenDto 奇门排盘参数
     * @param qiMenVo 奇门排盘结果
     * @return 更新后的记录
     */
    QiMenRecord updateQimenRecord(Long id, Long userId, QiMenDto qiMenDto, QiMenVo qiMenVo);

    /**
     * 删除奇门记录
     *
     * @param id 记录ID
     * @param userId 用户ID
     */
    void deleteQimenRecord(Long id, Long userId);
}
