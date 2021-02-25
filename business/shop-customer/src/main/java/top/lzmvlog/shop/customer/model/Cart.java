package top.lzmvlog.shop.customer.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * cart 购物车
 *
 * @author zhang1591313226@163.com
 * @since 2021-02-25
 */
@Data
@TableName("cart")
public class Cart extends Model<Cart> {

    private static final long serialVersionUID = 1L;

    /**
     * 购物车id
     */
    @TableId("cart_id")
    private String cartId;

    /**
     * 用户id
     */
    @TableField("uid")
    private Integer uid;

    /**
     * 商品id
     */
    private String goodsId;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;
}
