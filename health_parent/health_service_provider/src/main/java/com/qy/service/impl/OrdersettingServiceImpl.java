package com.qy.service.impl;

import com.qy.pojo.Ordersetting;
import com.qy.mapper.OrdersettingMapper;
import com.qy.service.IOrdersettingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author qy
 * @since 2022-01-13
 */
@DubboService(interfaceClass = IOrdersettingService.class)
@Transactional
public class OrdersettingServiceImpl extends ServiceImpl<OrdersettingMapper, Ordersetting> implements IOrdersettingService {

    @Resource
    private OrdersettingMapper ordersettingMapper;

    //批量添加
    @Override
    public void add(List<Ordersetting> list) {
        if(list != null && list.size() > 0){
            for (Ordersetting orderSetting : list) {
                //检查此数据（日期）是否存在
                long count = ordersettingMapper.findCountByOrderDate(orderSetting.getOrderDate());

                if(count > 0){
                    //已经存在，执行更新操作
                    ordersettingMapper.editNumberByOrderDate(orderSetting);
                }else{
                    //不存在，执行添加操作
                    ordersettingMapper.add(orderSetting);
                }
            }
        }
    }

    // 根据月份查询对应的预约设置数据
    @Override
    public List<Map> getOrderSettingByMonth(String date) { // 格式：yyyy-MM
        String dateBegin = date + "-1";//2019-3-1
        String dateEnd = date + "-31";//2019-3-31

        Map map = new HashMap();
        map.put("dateBegin",dateBegin);
        map.put("dateEnd",dateEnd);
        List<Ordersetting> list = ordersettingMapper.getOrderSettingByMonth(map);
        List<Map> data = new ArrayList<>();
        for (Ordersetting orderSetting : list) {
            Map orderSettingMap = new HashMap();
            orderSettingMap.put("date",orderSetting.getOrderDate().getDate());//获得日期（几号）
            // System.out.println(orderSetting.getOrderDate());
            orderSettingMap.put("number",orderSetting.getNumber());//可预约人数
            orderSettingMap.put("reservations",orderSetting.getReservations());//已预约人数
            data.add(orderSettingMap);
        }
        return data;
    }

    //根据日期修改可预约人数
    public void editNumberByDate(Ordersetting orderSetting) {

        long count = ordersettingMapper.findCountByOrderDate(orderSetting.getOrderDate());

        if(count > 0){
            //当前日期已经进行了预约设置，需要进行修改操作
            ordersettingMapper.editNumberByOrderDate(orderSetting);
        }else{
            //当前日期没有进行预约设置，进行添加操作
            ordersettingMapper.add(orderSetting);
        }
    }
}
