package com.qy.mapper;

import com.qy.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author qy
 * @since 2022-01-13
 */
public interface UserMapper extends BaseMapper<User> {

    // 根据用户名查询数据库获取用户信息和关联的角色信息
    User findByUsername(String username);

}
