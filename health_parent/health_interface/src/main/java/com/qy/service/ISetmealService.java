package com.qy.service;

import com.qy.entity.PageResult;
import com.qy.entity.QueryPageBean;
import com.qy.pojo.Setmeal;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qy
 * @since 2022-01-13
 */
public interface ISetmealService extends IService<Setmeal> {

    // 新增套餐
    void add(Setmeal setmeal, Integer[] checkgroupIds);

    // 分页查询
    PageResult pageQuery(QueryPageBean queryPageBean);

    // 套餐占比统计
    List<Map<String, Object>> findSetmealCount();
}
