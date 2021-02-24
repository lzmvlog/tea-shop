package top.lzmvlog.shop.customer.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.lzmvlog.common.result.R;
import top.lzmvlog.shop.customer.model.Customer;
import top.lzmvlog.shop.customer.service.CustomerService;

import java.time.LocalDateTime;

/**
 * customer 前端控制器
 *
 * @author zhang1591313226@163.com
 * @since 2021-02-24
 */
@RestController
@Slf4j
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    public CustomerService customerService;

    /**
     * 新增 customer
     *
     * @param customer
     * @return
     */
    @PutMapping("save")
    public R save(@RequestBody Customer customer) {
        customer.setCreateTime(LocalDateTime.now());
        return R.bool(customerService.save(customer));
    }

    /**
     * 删除 customer
     *
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public R delete(@PathVariable(value = "id") String id) {
        return R.bool(customerService.removeById(id));
    }

    /**
     * 修改 customer
     *
     * @param customer
     * @return
     */
    @PostMapping("update")
    public R update(@RequestBody Customer customer) {
        return R.bool(customerService.updateById(customer));
    }

    /**
     * 查询 orders
     *
     * @param customer
     * @return
     */
    @PostMapping("select")
    public R select(@RequestBody Customer customer, Page<Customer> page) {
        return R.ok(customerService.page(page, Wrappers.<Customer>lambdaQuery(customer)));
    }

}
