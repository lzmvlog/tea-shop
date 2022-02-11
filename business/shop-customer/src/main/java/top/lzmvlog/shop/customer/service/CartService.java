package top.lzmvlog.shop.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.lzmvlog.common.model.customer.Cart;

/**
 * cart 服务类
 *
 * @author zhang1591313226@163.com
 * @since 2021-02-25
 */
public interface CartService extends IService<Cart> {

    /**
     * 保存购物车
     *
     * @param goodsId 商品id
     * @return
     */
    Boolean saveCart(String goodsId);
}
