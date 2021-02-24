package top.lzmvlog.common.enumutil;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单枚举
 *
 * @author zhangweijian@gridsum.com
 * @since 2021-02-25 11:39
 */
@Getter
@AllArgsConstructor
public enum OrdersPayEnum {

    PLACEORDER("下单未支付", 0),

    CANCEL("取消订单", 1),

    PAID("已支付", 2);

    private String description;

    private Integer value;


}
