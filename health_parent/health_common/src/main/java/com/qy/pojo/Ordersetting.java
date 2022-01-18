package com.qy.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@TableName("t_ordersetting")
@ApiModel(value="Ordersetting对象", description="")
public class Ordersetting implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "预约设置id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "预约设置日期")
    private Date orderDate;

    @ApiModelProperty(value = "预约设置人数")
    private Integer number;

    @ApiModelProperty(value = "预订")
    private Integer reservations;

    public Ordersetting(Date orderDate, int number) {
        this.orderDate = orderDate;
        this.number = number;
    }

}
