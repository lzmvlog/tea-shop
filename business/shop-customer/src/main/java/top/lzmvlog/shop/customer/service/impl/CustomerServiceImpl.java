package top.lzmvlog.shop.customer.service.impl;

import top.lzmvlog.shop.customer.model.Customer;
import top.lzmvlog.shop.customer.mapper.CustomerMapper;
import top.lzmvlog.shop.customer.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* customer 服务实现类
*
* @author zhang1591313226@163.com
* @since 2021-02-24
*/
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

}
