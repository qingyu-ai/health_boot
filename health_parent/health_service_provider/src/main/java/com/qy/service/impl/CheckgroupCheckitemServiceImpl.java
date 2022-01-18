package com.qy.service.impl;

import com.qy.pojo.CheckgroupCheckitem;
import com.qy.mapper.CheckgroupCheckitemMapper;
import com.qy.service.ICheckgroupCheckitemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qy.service.ICheckgroupService;
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
@DubboService(interfaceClass = ICheckgroupCheckitemService.class)
public class CheckgroupCheckitemServiceImpl extends ServiceImpl<CheckgroupCheckitemMapper, CheckgroupCheckitem> implements ICheckgroupCheckitemService {

}
