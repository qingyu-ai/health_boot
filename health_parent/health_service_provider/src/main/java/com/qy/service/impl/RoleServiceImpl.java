package com.qy.service.impl;

import com.qy.pojo.Role;
import com.qy.mapper.RoleMapper;
import com.qy.service.IOrdersettingService;
import com.qy.service.IRoleService;
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
@DubboService(interfaceClass = IRoleService.class)
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
