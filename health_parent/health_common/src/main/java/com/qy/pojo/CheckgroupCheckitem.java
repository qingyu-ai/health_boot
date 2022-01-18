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
@TableName("t_checkgroup_checkitem")
@ApiModel(value="CheckgroupCheckitem对象", description="")
public class CheckgroupCheckitem implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "检查组id")
    @TableId("checkgroup_id")
    private Integer checkgroupId;

    @ApiModelProperty(value = "检查项id")
    @TableField("checkitem_id")
    private Integer checkitemId;


}
