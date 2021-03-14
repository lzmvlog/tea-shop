package top.lzmvlog.shop.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.lzmvlog.shop.customer.model.Customer;
import top.lzmvlog.shop.customer.model.vo.Login;
import top.lzmvlog.shop.customer.model.vo.Register;

/**
 * customer 服务类
 *
 * @author zhang1591313226@163.com
 * @since 2021-02-24
 */
public interface CustomerService extends IService<Customer> {

    /**
     * 用户注册
     *
     * @param register
     * @return
     * @see Register
     */
    Boolean registered(Register register);

    /**
     * 登录
     *
     * @param login
     * @return
     * @see Login
     */
    Customer login(Login login);
}
