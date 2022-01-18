package com.qy.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * ?????ײ
 * </p>
 *
 * @author qy
 * @since 2022-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_setmeal")
@ApiModel(value="Setmeal对象", description="?????ײ")
public class Setmeal implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "套餐id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "套餐名")
    private String name;

    @ApiModelProperty(value = "套餐代码")
    private String code;

    @ApiModelProperty(value = "套餐助记码")
    private String helpCode;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "年龄")
    private String age;

    @ApiModelProperty(value = "价格")
    private Float price;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "注意事项")
    private String attention;

    @ApiModelProperty(value = "套餐图")
    private String img;


}
