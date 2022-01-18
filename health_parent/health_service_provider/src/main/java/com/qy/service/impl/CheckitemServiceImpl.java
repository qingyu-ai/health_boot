package com.qy.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qy.entity.PageResult;
import com.qy.entity.QueryPageBean;
import com.qy.pojo.Checkitem;
import com.qy.mapper.CheckitemMapper;
import com.qy.service.ICheckitemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.qy.service.IUserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qy
 * @since 2022-01-13
 */
@DubboService(interfaceClass = ICheckitemService.class)
@Transactional // 事务注解，加了之后，上面service注解必须加括号中的内容
public class CheckitemServiceImpl extends ServiceImpl<CheckitemMapper, Checkitem> implements ICheckitemService {

    @Resource
    private CheckitemMapper checkitemMapper;

    // 分页查询
    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        //页码
        Integer currentPage = queryPageBean.getCurrentPage();
        //每页记录数
        Integer pageSize = queryPageBean.getPageSize();
        //查询条件
        String queryString = queryPageBean.getQueryString();

        // 完成分页查询,基于mybatisplus框架提供的分页助手插件完成,限制每页10条
        //参数一是当前页，参数二是每页个数
        Page<Checkitem> itemPage = new Page<>(currentPage, pageSize);
        QueryWrapper<Checkitem> wrapper = new QueryWrapper<>();
        wrapper.like("code", queryString).or().like("name", queryString);
        IPage<Checkitem> pages = null;
        // 判断查询参数是否为空
        if (StringUtils.isEmpty(queryString)) {
            // 查询数据库
            pages = checkitemMapper.selectPage(itemPage, null);
        } else {
            // 查询数据库
            pages = checkitemMapper.selectPage(itemPage, wrapper);
        }

        // 获取总记录数
        long total = pages.getTotal();
        // 当前页结果
        List<Checkitem> rows = pages.getRecords();

        return new PageResult(total, rows);
    }

}
