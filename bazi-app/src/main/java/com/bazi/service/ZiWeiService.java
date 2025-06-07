package com.bazi.service;

import com.bazi.dto.ziwei.ZiWeiDto;
import com.bazi.vo.ziwei.ZiWeiVo;

/**
 * 紫微斗数排盘服务接口
 *
 * @author skm1229
 * @version 1.0.0
 */
public interface ZiWeiService {

    /**
     * 紫微斗数排盘
     *
     * @param dto 紫微斗数排盘输入参数
     * @return 紫微斗数排盘结果
     */
    ZiWeiVo paiPan(ZiWeiDto dto);
}
