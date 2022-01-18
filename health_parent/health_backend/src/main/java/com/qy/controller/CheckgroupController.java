package com.qy.controller;


import com.qy.constant.MessageConstant;
import com.qy.entity.PageResult;
import com.qy.entity.QueryPageBean;
import com.qy.entity.Result;
import com.qy.pojo.Checkgroup;
import com.qy.pojo.CheckgroupCheckitem;
import com.qy.pojo.Checkitem;
import com.qy.service.ICheckgroupCheckitemService;
import com.qy.service.ICheckgroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author qy
 * @since 2022-01-13
 */
@RestController
@RequestMapping("/checkgroup")
@Api(tags = "CheckgroupController")
public class CheckgroupController {

    @DubboReference
    private ICheckgroupService checkgroupService;

    @ApiOperation(value = "分页查询检查项")
    @PostMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return checkgroupService.pageQuery(queryPageBean);
    }


    @ApiOperation(value = "根据id查询检查组")
    @GetMapping("/findById")
    public Result findById(Integer id){
        Checkgroup checkGroup = checkgroupService.getById(id);
        if(checkGroup != null){
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, checkGroup);
        }
        return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
    }


    @ApiOperation(value = "根据检查组合id查询对应的所有检查项id")
    @GetMapping("/findCheckItemIdsByCheckGroupId")
    public Result findCheckItemIdsByCheckGroupId(Integer id){
        try{
            List<Integer> checkitemIds =
                    checkgroupService.findCheckItemIdsByCheckGroupId(id);
            return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkitemIds);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    @ApiOperation(value = "新增检查组")
    @PostMapping("/add")
    public Result add(@RequestBody Checkgroup checkGroup, Integer[] checkitemIds){
        try {
            checkgroupService.add(checkGroup,checkitemIds);
        }catch (Exception e){
            //新增失败
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
        //新增成功
        return new Result(true,MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }

    @ApiOperation(value = "编辑检查组")
    @PostMapping("/edit")
    public Result edit(@RequestBody Checkgroup checkGroup,Integer[] checkitemIds){
        try {
            checkgroupService.edit(checkGroup,checkitemIds);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
        return new Result(true,MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }

    @ApiOperation(value = "查询所有检查组")
    @GetMapping("/findAll")
    public Result findAll(){
        List<Checkgroup> checkGroupList = checkgroupService.list();
        if(checkGroupList != null && checkGroupList.size() > 0){
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, checkGroupList);
        }
        return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
    }



}
