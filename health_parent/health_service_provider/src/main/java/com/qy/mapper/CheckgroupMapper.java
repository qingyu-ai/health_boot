package com.qy.mapper;

import com.qy.pojo.Checkgroup;
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
public interface CheckgroupMapper extends BaseMapper<Checkgroup> {

    // 根据检查组id查询关联检查项
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    // 插入检查组
    void add(Checkgroup checkGroup);

    // 插入检查组与检查项多对多关联
    void setCheckGroupAndCheckItem(Map map);

    // 删除原先检查组与检查项关联数据
    void deleteAssociation(Integer id);

    // 修改（更新）检查组
    void edit(Checkgroup checkGroup);

}
