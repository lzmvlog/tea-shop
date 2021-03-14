package top.lzmvlog.shop.auth.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import top.lzmvlog.shop.customer.model.Customer;
import top.lzmvlog.shop.customer.model.vo.Login;

/**
 * @author ShaoJie zhang1591313226@163.com
 * @Date 2021年03月14日 14:29
 * @Description:
 */
@Service
@FeignClient(name = "shop-customer")
public interface CustomerService {

    /**
     * 查询用户信息
     *
     * @param login
     * @return
     * @see Login
     */
    @PostMapping("/customer/login")
    Customer selectCustomer(Login login);
}
