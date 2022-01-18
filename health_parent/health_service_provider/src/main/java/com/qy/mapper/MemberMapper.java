package com.qy.mapper;

import com.qy.pojo.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author qy
 * @since 2022-01-13
 */
public interface MemberMapper extends BaseMapper<Member> {

    // 查询指定日期之前用户
    Integer findMemberCountBeforeDate(String date);

    // 通过日期查询用户
    Integer findMemberCountByDate(String date);

    // 查询指定日期之后用户
    Integer findMemberCountAfterDate(String date);

    // 查询用户总数
    Integer findMemberTotalCount();

}
