package com.qy.mapper;

import com.qy.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author qy
 * @since 2022-01-13
 */
public interface RoleMapper extends BaseMapper<Role> {

    // 提供id查询角色
    Set<Role> findByUserId(Integer userId);
}
