package com.qy.mapper;

import com.qy.pojo.Permission;
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
public interface PermissionMapper extends BaseMapper<Permission> {

    // 提供角色id查询权限
    Set<Permission> findByRoleId(int roleId);

}
