package com.qy.service;

import com.qy.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qy
 * @since 2022-01-13
 */
public interface IUserService extends IService<User> {

    // 通过用户名查询信息
    User findByUsername(String username);
}
