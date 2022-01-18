package com.qy.controller;


import com.qy.constant.MessageConstant;
import com.qy.entity.Result;
import com.qy.pojo.Ordersetting;
import com.qy.service.IOrdersettingService;
import com.qy.utils.POIUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author qy
 * @since 2022-01-13
 */
@RestController
@RequestMapping("/ordersetting")
@Api(tags = "OrdersettingController")
public class OrdersettingController {

    @DubboReference
    private IOrdersettingService ordersettingService;

    @ApiOperation(value = "文件上传，实现预约设置数据批量导入")
    @PostMapping("/upload")
    public Result upload(@RequestParam("excelFile") MultipartFile excelFile){

        try {
            // 使用POI解析表格数据
            List<String[]> list = POIUtils.readExcel(excelFile);
            // 重写，可读性高
            List<Ordersetting> data = new ArrayList<>();
            // 遍历
            for (String[] strings : list){
                // 预约日期
                String orderDate = strings[0];
                // 预约人数
                String number = strings[1];

                // 将数据转成OrderSetting对象
                Ordersetting orderSetting = new Ordersetting(new Date(orderDate),Integer.parseInt(number));
                // 添加进集合
                data.add(orderSetting);
            }
            // 通过dubbo远程调用服务实现数据批量导入到数据库
            ordersettingService.add(data);
            return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);

        } catch (IOException e) {
            e.printStackTrace();
            // 文件解析失败
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }

    }

    @ApiOperation(value = "根据指定日期修改可预约人数")
    @PostMapping("/editNumberByDate")
    public Result editNumberByDate(@RequestBody Ordersetting orderSetting){
        try{
            ordersettingService.editNumberByDate(orderSetting);
            //预约设置成功
            return new Result(true,MessageConstant.ORDERSETTING_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            //预约设置失败
            return new Result(false,MessageConstant.ORDERSETTING_FAIL);
        }
    }


    @ApiOperation(value = "获取指定日期所在月份的预约设置数据")
    @PostMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String date){//参数格式为：2019-03
        try{
            List<Map> list = ordersettingService.getOrderSettingByMonth(date);
            //获取预约设置数据成功
            return new Result(true,MessageConstant.GET_ORDERSETTING_SUCCESS,list);
        }catch (Exception e){
            e.printStackTrace();
            //获取预约设置数据失败
            return new Result(false, MessageConstant.GET_ORDERSETTING_FAIL);
        }
    }

}
