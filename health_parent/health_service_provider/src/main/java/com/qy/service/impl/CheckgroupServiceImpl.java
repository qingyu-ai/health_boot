package com.qy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qy.entity.PageResult;
import com.qy.entity.QueryPageBean;
import com.qy.mapper.CheckgroupMapper;
import com.qy.pojo.Checkgroup;
import com.qy.service.ICheckgroupService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author qy
 * @since 2022-01-13
 */
@DubboService(interfaceClass = ICheckgroupService.class)
@Transactional
public class CheckgroupServiceImpl extends ServiceImpl<CheckgroupMapper, Checkgroup> implements ICheckgroupService {

    @Resource
    private CheckgroupMapper checkgroupMapper;

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
        Page<Checkgroup> userPage = new Page<>(currentPage, pageSize);
        QueryWrapper<Checkgroup> wrapper = new QueryWrapper<>();
        wrapper.like("code", queryString).or().like("name", queryString);
        IPage<Checkgroup> pages = null;
        // 判断查询参数是否为空
        if (StringUtils.isEmpty(queryString)) {
            // 查询数据库
            pages = checkgroupMapper.selectPage(userPage, null);
        } else {
            // 查询数据库
            pages = checkgroupMapper.selectPage(userPage, wrapper);
        }

        // 获取总记录数
        long total = pages.getTotal();
        // 当前页结果
        List<Checkgroup> rows = pages.getRecords();

        return new PageResult(total, rows);
    }

    // 根据检查组id查询关联检查项
    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
        return checkgroupMapper.findCheckItemIdsByCheckGroupId(id);
    }

    // 新增检查组
    @Override
    public void add(Checkgroup checkGroup, Integer[] checkitemIds) {
        // 新增检查组，操作t_checkgroup表
        checkgroupMapper.add(checkGroup);
        // 设置检查组和检查项的多对多的关联关系，操作t_checkgroup_checkitem表
        setCheckGroupAndCheckItem(checkGroup.getId(),checkitemIds);
    }

    // 编辑检查组
    @Override
    public void edit(Checkgroup checkGroup, Integer[] checkitemIds) {
       //根据检查组id删除中间表数据（清理原有关联关系）
        checkgroupMapper.deleteAssociation(checkGroup.getId());
        //向中间表(t_checkgroup_checkitem)插入数据（建立检查组和检查项关联关系）
        setCheckGroupAndCheckItem(checkGroup.getId(),checkitemIds);
        //更新检查组基本信息
        checkgroupMapper.edit(checkGroup);
    }

    //向中间表(t_checkgroup_checkitem)插入数据（建立检查组和检查项关联关系）
    public void setCheckGroupAndCheckItem(Integer checkGroupId,Integer[] checkitemIds){
        if(checkitemIds != null && checkitemIds.length > 0){
            for (Integer checkitemId : checkitemIds) {
                Map<String,Integer> map = new HashMap<>();
                map.put("checkgroup_id",checkGroupId);
                map.put("checkitem_id",checkitemId);
                checkgroupMapper.setCheckGroupAndCheckItem(map);
            }
        }
    }
}
