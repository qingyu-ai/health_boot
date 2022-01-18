package com.qy.service.impl;

import com.qy.pojo.Order;
import com.qy.mapper.OrderMapper;
import com.qy.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qy.service.IOrdersettingService;
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
@DubboService(interfaceClass = IOrderService.class)
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
