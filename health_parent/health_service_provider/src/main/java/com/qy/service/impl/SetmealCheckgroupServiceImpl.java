package com.qy.service.impl;

import com.qy.pojo.SetmealCheckgroup;
import com.qy.mapper.SetmealCheckgroupMapper;
import com.qy.service.IOrdersettingService;
import com.qy.service.ISetmealCheckgroupService;
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
@DubboService(interfaceClass = ISetmealCheckgroupService.class)
public class SetmealCheckgroupServiceImpl extends ServiceImpl<SetmealCheckgroupMapper, SetmealCheckgroup> implements ISetmealCheckgroupService {

}
