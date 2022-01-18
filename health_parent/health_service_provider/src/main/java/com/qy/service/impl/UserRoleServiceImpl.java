package com.qy.service.impl;

import com.qy.pojo.UserRole;
import com.qy.mapper.UserRoleMapper;
import com.qy.service.IOrdersettingService;
import com.qy.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qy
 * @since 2022-01-13
 */
@DubboService(interfaceClass = IUserRoleService.class)
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
