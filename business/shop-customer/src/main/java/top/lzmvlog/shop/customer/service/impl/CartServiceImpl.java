package top.lzmvlog.shop.customer.service.impl;

import top.lzmvlog.shop.customer.model.Cart;
import top.lzmvlog.shop.customer.mapper.CartMapper;
import top.lzmvlog.shop.customer.service.CartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* cart 服务实现类
*
* @author zhang1591313226@163.com
* @since 2021-02-25
*/
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

}
