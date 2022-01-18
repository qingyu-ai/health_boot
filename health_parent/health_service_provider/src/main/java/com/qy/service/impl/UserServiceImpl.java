package com.qy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qy.mapper.PermissionMapper;
import com.qy.mapper.RoleMapper;
import com.qy.pojo.Permission;
import com.qy.pojo.Role;
import com.qy.pojo.User;
import com.qy.mapper.UserMapper;
import com.qy.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Set;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author qy
 * @since 2022-01-13
 */
@DubboService(interfaceClass = IUserService.class)
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private PermissionMapper permissionMapper;

    // 根据用户名查询数据库获取用户信息和关联的角色信息，同时需要查询角色关联的权限信息
    @Override
    public User findByUsername(String username) {

        User user = userMapper.findByUsername(username); // 查询用户基本信息，不包含用户角色

        if (user == null) {
            return null;
        }

        Integer userId = user.getId();
        // 根据用户id查询对应的角色
        Set<Role> roles = roleMapper.findByUserId(userId);

        roles.forEach(role -> {
            Integer roleId = role.getId();
            // 根据角色id查询关联权限
            Set<Permission> permissions = permissionMapper.findByRoleId(roleId);
            if (permissions != null && permissions.size() > 0) {
                role.setPermissions(permissions);
            }
        });

        user.setRoles(roles);

        return user;
    }
}
