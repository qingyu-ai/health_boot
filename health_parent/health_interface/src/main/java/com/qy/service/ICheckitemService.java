package com.qy.service;

import com.qy.entity.PageResult;
import com.qy.entity.QueryPageBean;
import com.qy.pojo.Checkitem;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qy
 * @since 2022-01-13
 */
public interface ICheckitemService extends IService<Checkitem> {

    // 分页查询
    PageResult pageQuery(QueryPageBean queryPageBean);

}
