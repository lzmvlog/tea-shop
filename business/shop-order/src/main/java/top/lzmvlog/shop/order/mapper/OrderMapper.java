package top.lzmvlog.shop.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.lzmvlog.shop.order.model.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* order Mapper 接口
*
* @author zhang1591313226@163.com
* @since 2021-02-24
*/
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
