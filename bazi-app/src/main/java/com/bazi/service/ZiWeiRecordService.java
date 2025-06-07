package com.bazi.service;

import com.bazi.dto.ziwei.ZiWeiDto;
import com.bazi.entity.ZiWeiRecord;
import com.bazi.vo.ziwei.ZiWeiVo;

import java.util.List;

/**
 * 紫微记录服务接口
 *
 * @author skm1229
 * @version 1.0.0
 */
public interface ZiWeiRecordService {

    /**
     * 保存紫微排盘记录
     *
     * @param userId 用户ID
     * @param ziWeiDto 紫微排盘参数
     * @param ziWeiVo 紫微排盘结果
     * @return 保存的记录
     */
    ZiWeiRecord saveZiweiRecord(Long userId, ZiWeiDto ziWeiDto, ZiWeiVo ziWeiVo);

    /**
     * 根据ID查询紫微记录
     *
     * @param id 记录ID
     * @param userId 用户ID
     * @return 紫微记录
     */
    ZiWeiRecord getZiweiRecordById(Long id, Long userId);

    /**
     * 查询用户的所有紫微记录
     *
     * @param userId 用户ID
     * @return 紫微记录列表
     */
    List<ZiWeiRecord> getZiweiRecordsByUserId(Long userId);

    /**
     * 更新紫微记录
     *
     * @param id 记录ID
     * @param userId 用户ID
     * @param ziWeiDto 紫微排盘参数
     * @param ziWeiVo 紫微排盘结果
     * @return 更新后的记录
     */
    ZiWeiRecord updateZiweiRecord(Long id, Long userId, ZiWeiDto ziWeiDto, ZiWeiVo ziWeiVo);

    /**
     * 删除紫微记录
     *
     * @param id 记录ID
     * @param userId 用户ID
     */
    void deleteZiweiRecord(Long id, Long userId);
}
