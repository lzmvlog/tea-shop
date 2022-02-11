package top.lzmvlog.shop.auth.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import top.lzmvlog.common.model.customer.Customer;
import top.lzmvlog.common.model.customer.vo.Login;

/**
 * @author ShaoJie zhang1591313226@163.com
 * @Date 2021年03月14日 14:29
 * @Description:
 */
@FeignClient(name = "shop-customer")
public interface CustomerFeignService {

    /**
     * 查询用户信息
     *
     * @param login
     * @return
     * @see Login
     */
    @PostMapping("/customer/login")
    Customer selectCustomer(@RequestBody Login login);
}
