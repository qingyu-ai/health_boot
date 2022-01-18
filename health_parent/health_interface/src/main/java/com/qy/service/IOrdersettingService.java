package com.qy.service;

import com.qy.pojo.Ordersetting;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author qy
 * @since 2022-01-13
 */
public interface IOrdersettingService extends IService<Ordersetting> {

    // 批量导入数据
    void add(List<Ordersetting> data);

    // 根据日期获取数据
    List<Map> getOrderSettingByMonth(String date);

    // 通过日期修改数据
    void editNumberByDate(Ordersetting orderSetting);
}
