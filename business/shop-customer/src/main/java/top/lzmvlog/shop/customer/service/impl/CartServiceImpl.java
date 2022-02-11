package top.lzmvlog.shop.customer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.lzmvlog.common.model.customer.Cart;
import top.lzmvlog.shop.customer.mapper.CartMapper;
import top.lzmvlog.shop.customer.service.CartService;

/**
 * cart 服务实现类
 *
 * @author zhang1591313226@163.com
 * @since 2021-02-25
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

    /**
     * 保存购物车
     *
     * @param goodsId 商品id
     * @return
     */
    @Override
    public Boolean saveCart(String goodsId) {
        // TODO:缺少获取用户信息
        return null;
    }
}
