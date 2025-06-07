package com.bazi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bazi.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户Mapper接口
 *
 * @author skm1229
 * @version 1.0.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
