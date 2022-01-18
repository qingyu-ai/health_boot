package com.qy.controller;


import com.qy.constant.MessageConstant;
import com.qy.entity.PageResult;
import com.qy.entity.QueryPageBean;
import com.qy.entity.Result;
import com.qy.pojo.Checkitem;
import com.qy.service.ICheckitemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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
@RequestMapping("/checkitem")
@Api(tags = "CheckitemController")
public class CheckitemController {

    @DubboReference
    private ICheckitemService checkitemService;

    @ApiOperation(value = "分页查询检查项")
    @PostMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return checkitemService.pageQuery(queryPageBean);
    }

    @ApiOperation(value = "新增检查项")
    @PreAuthorize("hasAuthority('CHECKITEM_ADD')")//权限校验
    @PostMapping("/add")
    public Result add(@RequestBody Checkitem checkItem){
        try {
            checkitemService.save(checkItem);
        }catch (Exception e){
            e.printStackTrace();
            // 服务调用失败
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    @ApiOperation(value = "删除检查项")
    @PreAuthorize("hasAuthority('CHECKITEM_DELETE')")//权限校验
    @GetMapping("/delete")
    public Result delete(Integer id){
        try {
            // 删除
            checkitemService.removeById(id);
        }catch (RuntimeException e){
            // 运行中错误
            return new Result(false,e.getMessage());
        }catch (Exception e){
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
        }
        return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }

    @ApiOperation(value = "编辑检查项")
    @PreAuthorize("hasAuthority('CHECKITEM_EDIT')")//权限校验
    @PostMapping("/edit")
    public Result edit(@RequestBody Checkitem checkItem){
        try {
            checkitemService.updateById(checkItem);
        }catch (Exception e){
            return new Result(false,MessageConstant.EDIT_CHECKITEM_FAIL);
        }
        return new Result(true,MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }

    @ApiOperation(value = "根据id查询检查项，回显到编辑框")
    @GetMapping("/findById")
    public Result findById(Integer id) {
        try {
            Checkitem checkItem = checkitemService.getById(id);
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkItem);
        } catch (Exception e) {
            e.printStackTrace();
            //服务调用失败
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    @ApiOperation(value = "查询所有检查项")
    @GetMapping("/findAll")
    public Result findAll() {
        try {
            List<Checkitem> list = checkitemService.list();
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, list);
        } catch (Exception e) {
            e.printStackTrace();
            //服务调用失败
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

}
