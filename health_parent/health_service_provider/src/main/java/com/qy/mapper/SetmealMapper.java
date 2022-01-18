package com.qy.mapper;

import com.qy.pojo.Setmeal;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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
public interface SetmealMapper extends BaseMapper<Setmeal> {

    // 添加套餐
    void add(Setmeal setmeal);

    // 绑定套餐和检查组多对多关系
    void setSetmealAndCheckGroup(Map<String, Integer> map);

    // 查询套餐总数
    List<Map<String, Object>> findSetmealCount();
}
