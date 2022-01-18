package com.qy.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author qy
 * @since 2022-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_setmeal_checkgroup")
@ApiModel(value="SetmealCheckgroup对象", description="")
public class SetmealCheckgroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "套餐id")
    @TableId("setmeal_id")
    private Integer setmealId;

    @ApiModelProperty(value = "检查组id")
    @TableField("checkgroup_id")
    private Integer checkgroupId;


}
