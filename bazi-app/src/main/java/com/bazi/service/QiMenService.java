package com.bazi.service;

import com.bazi.dto.qimen.QiMenDto;
import com.bazi.vo.qimen.QiMenVo;

/**
 * 奇门遁甲排盘服务接口
 *
 * @author skm1229
 * @version 1.0.0
 */
public interface QiMenService {

    /**
     * 奇门遁甲排盘
     *
     * @param dto 奇门遁甲排盘输入参数
     * @return 奇门遁甲排盘结果
     */
    QiMenVo paiPan(QiMenDto dto);
}
