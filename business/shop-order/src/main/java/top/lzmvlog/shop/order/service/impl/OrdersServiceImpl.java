package top.lzmvlog.shop.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.lzmvlog.shop.order.mapper.OrdersMapper;
import top.lzmvlog.common.model.order.Orders;
import top.lzmvlog.shop.order.service.OrdersService;

/**
 * order 服务实现类
 *
 * @author zhang1591313226@163.com
 * @since 2021-02-24
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

}
