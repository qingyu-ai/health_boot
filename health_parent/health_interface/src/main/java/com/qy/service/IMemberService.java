package com.qy.service;

import com.qy.pojo.Member;
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
public interface IMemberService extends IService<Member> {

    // 会员数量统计
    List<Integer> findMemberCountByMonth(List<String> months);
}
