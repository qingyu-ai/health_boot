package com.qy.service;

import com.qy.entity.PageResult;
import com.qy.entity.QueryPageBean;
import com.qy.pojo.Checkgroup;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qy
 * @since 2022-01-13
 */
public interface ICheckgroupService extends IService<Checkgroup> {

    // 分页查询
    PageResult pageQuery(QueryPageBean queryPageBean);

    // 根据检查组id查询关联检查项
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    // 新增检查组
    void add(Checkgroup checkGroup, Integer[] checkitemIds);

    // 编辑检查组
    void edit(Checkgroup checkGroup, Integer[] checkitemIds);
}
