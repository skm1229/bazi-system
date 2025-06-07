package com.bazi.service;

import com.bazi.dto.meihua.MeiHuaDto;
import com.bazi.vo.meihua.MeiHuaVo;

/**
 * 梅花易数排盘服务接口
 *
 * @author skm1229
 * @version 1.0.0
 */
public interface MeiHuaService {

    /**
     * 梅花易数排盘
     *
     * @param dto 梅花易数排盘输入参数
     * @return 梅花易数排盘结果
     */
    MeiHuaVo paiPan(MeiHuaDto dto);
}
