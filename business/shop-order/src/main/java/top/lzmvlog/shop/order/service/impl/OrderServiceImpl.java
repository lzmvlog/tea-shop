package top.lzmvlog.shop.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.lzmvlog.shop.order.mapper.OrderMapper;
import top.lzmvlog.shop.order.model.Order;
import top.lzmvlog.shop.order.service.OrderService;

/**
 * order 服务实现类
 *
 * @author zhang1591313226@163.com
 * @since 2021-02-24
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
