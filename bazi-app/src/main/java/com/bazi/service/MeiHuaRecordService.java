package com.bazi.service;

import com.bazi.dto.meihua.MeiHuaDto;
import com.bazi.entity.MeiHuaRecord;
import com.bazi.vo.meihua.MeiHuaVo;

import java.util.List;

/**
 * 梅花记录服务接口
 *
 * @author skm1229
 * @version 1.0.0
 */
public interface MeiHuaRecordService {

    /**
     * 保存梅花排盘记录
     *
     * @param userId 用户ID
     * @param meiHuaDto 梅花排盘参数
     * @param meiHuaVo 梅花排盘结果
     * @return 保存的记录
     */
    MeiHuaRecord saveMeihuaRecord(Long userId, MeiHuaDto meiHuaDto, MeiHuaVo meiHuaVo);

    /**
     * 根据ID查询梅花记录
     *
     * @param id 记录ID
     * @param userId 用户ID
     * @return 梅花记录
     */
    MeiHuaRecord getMeihuaRecordById(Long id, Long userId);

    /**
     * 查询用户的所有梅花记录
     *
     * @param userId 用户ID
     * @return 梅花记录列表
     */
    List<MeiHuaRecord> getMeihuaRecordsByUserId(Long userId);

    /**
     * 更新梅花记录
     *
     * @param id 记录ID
     * @param userId 用户ID
     * @param meiHuaDto 梅花排盘参数
     * @param meiHuaVo 梅花排盘结果
     * @return 更新后的记录
     */
    MeiHuaRecord updateMeihuaRecord(Long id, Long userId, MeiHuaDto meiHuaDto, MeiHuaVo meiHuaVo);

    /**
     * 删除梅花记录
     *
     * @param id 记录ID
     * @param userId 用户ID
     */
    void deleteMeihuaRecord(Long id, Long userId);
}
