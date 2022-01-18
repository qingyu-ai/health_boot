package com.qy.service.impl;

import com.qy.pojo.Member;
import com.qy.mapper.MemberMapper;
import com.qy.service.IMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qy.service.IOrdersettingService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qy
 * @since 2022-01-13
 */
@DubboService(interfaceClass = IMemberService.class)
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

    @Resource
    private MemberMapper memberMapper;
    // 通过月份查询用户
    @Override
    public List<Integer> findMemberCountByMonth(List<String> months) {
        List<Integer> memberCount  = new ArrayList<>();
        for(String month : months){
            month = month + "-31";//格式：2019.04.31
            Integer count = memberMapper.findMemberCountBeforeDate(month);
            memberCount .add(count);
        }
        return memberCount;
    }

}
