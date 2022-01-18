package com.qy.mapper;

import com.qy.pojo.Ordersetting;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author qy
 * @since 2022-01-13
 */
public interface OrdersettingMapper extends BaseMapper<Ordersetting> {

    // 插入预约信息
    void add(Ordersetting orderSetting);

    // 根据日期更新可预约人数
    void editNumberByOrderDate(Ordersetting orderSetting);

    // 通过日期查询数据
    long findCountByOrderDate(Date orderDate);

    // 根据日期范围查询预约设置信息
    List<Ordersetting> getOrderSettingByMonth(Map map);


    //更新已预约人数
    void editReservationsByOrderDate(Ordersetting orderSetting);

    //根据预约日期查询预约设置信息
    Ordersetting findByOrderDate(Date orderDate);

}
