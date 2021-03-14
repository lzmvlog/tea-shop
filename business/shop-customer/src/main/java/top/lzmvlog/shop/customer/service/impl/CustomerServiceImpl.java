package top.lzmvlog.shop.customer.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import top.lzmvlog.shop.customer.mapper.CustomerMapper;
import top.lzmvlog.shop.customer.model.Customer;
import top.lzmvlog.shop.customer.model.vo.Login;
import top.lzmvlog.shop.customer.model.vo.Register;
import top.lzmvlog.shop.customer.service.CustomerService;

/**
 * customer 服务实现类
 *
 * @author zhang1591313226@163.com
 * @since 2021-02-24
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

    /**
     * 密码加密
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CustomerMapper customerMapper;

    /**
     * 用户注册
     *
     * @param register
     * @return
     * @see Register
     */
    @Override
    public Boolean registered(Register register) {
        Customer customer = new Customer();
        customer.setAccount(register.getAccount());
        customer.setPassword(bCryptPasswordEncoder.encode(register.getPassword()));
        return customerMapper.insert(customer) == 1 ? true : false;
    }

    /**
     * 登录
     *
     * @param login
     * @return
     * @see Login
     */
    @Override
    public Customer login(Login login) {
        Customer customer = customerMapper.selectOne(Wrappers.<Customer>lambdaQuery()
                .eq(Customer::getAccount, login.getAccount()));
        boolean matches = bCryptPasswordEncoder.matches(login.getPassword(), customer.getPassword());
        if (matches) {
            return customer;
        }
        return null;
    }
}
