package com.bazi.service;

import com.bazi.dto.liuyao.LiuYaoDto;
import com.bazi.vo.liuyao.LiuYaoVo;

/**
 * 六爻排盘服务接口
 *
 * @author skm1229
 * @version 1.0.0
 */
public interface LiuYaoService {

    /**
     * 六爻排盘
     *
     * @param dto 六爻排盘输入参数
     * @return 六爻排盘结果
     */
    LiuYaoVo paiPan(LiuYaoDto dto);
}
