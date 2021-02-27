package top.lzmvlog.shop.order.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单
 *
 * @author zhang1591313226@163.com
 * @since 2021-02-24 10:14
 */
@Data
@TableName("orders")
public class Orders extends Model<Orders> {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String orderId;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 订单商品id
     */
    private String goodsId;

    /**
     * 订单金额
     */
    private BigDecimal orderMoney;

    /**
     * 订单创建时间
     */
    private LocalDateTime createTime;

    /**
     * 订单是否支付、订单状态
     *
     * @see top.lzmvlog.common.enums.OrdersPayEnum
     */
    private Integer isPay;
}
