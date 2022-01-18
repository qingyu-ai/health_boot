package com.qy.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jdk.nashorn.internal.ir.annotations.Ignore;
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
@TableName("t_checkgroup")
@ApiModel(value="Checkgroup对象", description="")
public class Checkgroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "检查组id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "检查组代码")
    private String code;

    @ApiModelProperty(value = "检查组名")
    private String name;

    @ApiModelProperty(value = "检查组助记码")
    private String helpCode;

    @ApiModelProperty(value = "适用性别")
    private String sex;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "注意事项")
    private String attention;


}
