package com.qy.mapper;

import com.qy.pojo.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author qy
 * @since 2022-01-13
 */
public interface OrderMapper extends BaseMapper<Order> {

    // 通过日期查询预约数
    Integer findOrderCountByDate(String date);

    // 查询日期之后的预约数
    Integer findOrderCountAfterDate(String date);

    // 通过日期查询到诊数
    Integer findVisitsCountByDate(String date);

    // 查询日期之后的到诊数
    Integer findVisitsCountAfterDate(String date);

    // 热门套餐
    List<Map> findHotSetmeal();

}
