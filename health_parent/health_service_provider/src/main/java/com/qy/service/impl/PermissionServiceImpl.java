package com.qy.service.impl;

import com.qy.pojo.Permission;
import com.qy.mapper.PermissionMapper;
import com.qy.service.IOrdersettingService;
import com.qy.service.IPermissionService;
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
@DubboService(interfaceClass = IPermissionService.class)
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
