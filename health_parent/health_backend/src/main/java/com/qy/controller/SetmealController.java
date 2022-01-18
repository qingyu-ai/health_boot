package com.qy.controller;


import com.qy.constant.MessageConstant;
import com.qy.constant.RedisConstant;
import com.qy.entity.PageResult;
import com.qy.entity.QueryPageBean;
import com.qy.entity.Result;
import com.qy.pojo.Setmeal;
import com.qy.service.ISetmealService;
import com.qy.utils.QiniuUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author qy
 * @since 2022-01-13
 */
@RestController
@RequestMapping("/setmeal")
@Api(tags = "SetmealController")
public class SetmealController {

    @DubboReference
    private ISetmealService setmealService;

    //使用JedisPool操作Redis服务
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @ApiOperation(value = "文件上传")
    @PostMapping("/upload")
    public Result upload(@RequestParam("imgFile") MultipartFile imgFile){

        try {
            //获取原始文件名
            String originalFilename = imgFile.getOriginalFilename();
            int lastIndexOf = originalFilename.lastIndexOf(".");
            //获取文件后缀
            String suffix = originalFilename.substring(lastIndexOf - 1);
            //使用UUID随机产生文件名称，防止同名文件覆盖
            String fileName = UUID.randomUUID().toString() + suffix;
            // 上传图片
            QiniuUtils.upload2Qiniu(imgFile.getBytes(),fileName);
            // 将图片名字存入redis
            stringRedisTemplate.opsForValue().set(RedisConstant.SETMEAL_PIC_RESOURCES,fileName);
            //图片上传成功
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,fileName);
        }catch (IOException e){
            e.printStackTrace();
            //图片上传失败
            return new Result(false,MessageConstant.PIC_UPLOAD_FAIL);
        }

    }

    @ApiOperation(value = "新增套餐")
    @PostMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, Integer[] checkgroupIds){
        try {
            setmealService.add(setmeal,checkgroupIds);
        }catch (Exception e){
            e.printStackTrace();
            //新增套餐失败
            return new Result(false,MessageConstant.ADD_SETMEAL_FAIL);
        }
        //新增套餐成功
        return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);
    }

    @ApiOperation(value = "分页查询")
    @PostMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return setmealService.pageQuery(queryPageBean);
    }

}
