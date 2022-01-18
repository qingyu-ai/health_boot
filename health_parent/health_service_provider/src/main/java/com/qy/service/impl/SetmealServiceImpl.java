package com.qy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qy.constant.RedisConstant;
import com.qy.entity.PageResult;
import com.qy.entity.QueryPageBean;
import com.qy.mapper.SetmealMapper;
import com.qy.pojo.Setmeal;
import com.qy.service.ISetmealService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
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
@DubboService(interfaceClass = ISetmealService.class)
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements ISetmealService {

    @Resource
    private SetmealMapper setmealMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Value("${out_put_path}")//从属性文件读取输出目录的路径
    private String outputpath ;

    //新增套餐
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        setmealMapper.add(setmeal);
        if(checkgroupIds != null && checkgroupIds.length > 0){
            //绑定套餐和检查组的多对多关系
            setSetmealAndCheckGroup(setmeal.getId(),checkgroupIds);
        }
        //将图片名称保存到Redis
        savePic2Redis(setmeal.getImg());

        // 当添加套餐后需要重新生成静态页面(套餐列表页面，套餐详情页面 )
        // generateMobileStaticHtml();

    }

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
        Page<Setmeal> setmealPage = new Page<>(currentPage, pageSize);
        QueryWrapper<Setmeal> wrapper = new QueryWrapper<>();
        wrapper.like("code", queryString).or().like("name", queryString);
        IPage<Setmeal> pages = null;
        // 判断查询参数是否为空
        if (StringUtils.isEmpty(queryString)) {
            // 查询数据库
            pages = setmealMapper.selectPage(setmealPage, null);
        } else {
            // 查询数据库
            pages = setmealMapper.selectPage(setmealPage, wrapper);
        }

        // 获取总记录数
        long total = pages.getTotal();
        // 当前页结果
        List<Setmeal> rows = pages.getRecords();

        return new PageResult(total, rows);
    }

    // 套餐占比统计
    @Override
    public List<Map<String, Object>> findSetmealCount() {
        return setmealMapper.findSetmealCount();
    }


    // 用于生成静态页面
    public void generateHtml(String templateName, String htmlPageName, Map map){
        Configuration configuration = freeMarkerConfigurer.getConfiguration();// 获取配置对象
        Writer out = null;
        try {
            Template template = configuration.getTemplate(templateName);
            // 构造输出流
            out = new FileWriter(new File(outputpath + "/" + htmlPageName));
            // 输出文件
            template.process(map,out);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (null != out) {
                    out.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    //生成当前方法所需的静态页面
    public void generateMobileStaticHtml() {
        // 在生成静态页面之前需要查询数据
        List<Setmeal> list = setmealMapper.selectList(null);

        // 需要生成套餐列表静态页面
        generateMobileSetmealListHtml(list);

        // 需要生成套餐详情静态页面
        generateMobileSetmealDetailHtml(list);

    }

    //生成套餐列表静态页面
    public void generateMobileSetmealListHtml(List<Setmeal> list) {
        Map map = new HashMap<>();
        // 为模板提供数据,用于生成静态页面
        map.put("setmealList", list);
        this.generateHtml("mobile_setmeal.ftl","m_setmeal.html",map);
    }

    //生成套餐详情静态页面（多个）
    public void generateMobileSetmealDetailHtml(List<Setmeal> list) {
        for (Setmeal setmeal : list) {
            Map map = new HashMap();
            map.put("setmeal", setmealMapper.selectById(setmeal.getId()));
            this.generateHtml("mobile_setmeal_detail.ftl",
                    "setmeal_detail_"+setmeal.getId()+".html",
                    map);
        }
    }

    //绑定套餐和检查组的多对多关系
    private void setSetmealAndCheckGroup(Integer id, Integer[] checkgroupIds) {
        for (Integer checkgroupId : checkgroupIds) {
            Map<String,Integer> map = new HashMap<>();
            map.put("setmeal_id",id);
            map.put("checkgroup_id",checkgroupId);
            setmealMapper.setSetmealAndCheckGroup(map);
        }
    }

    //将图片名称保存到Redis
    private void savePic2Redis(String pic){
        stringRedisTemplate.opsForValue().set(RedisConstant.SETMEAL_PIC_DB_RESOURCES,pic);
    }
}
