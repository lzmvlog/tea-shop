package top.lzmvlog.shop.order.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.lzmvlog.common.result.R;
import top.lzmvlog.shop.order.model.Orders;
import top.lzmvlog.shop.order.service.OrdersService;

/**
 * order 前端控制器
 *
 * @author zhang1591313226@163.com
 * @since 2021-02-24
 */
@RestController
@Slf4j
//@AllArgsConstructor
@RequestMapping("/order")
public class OrdersController {

    @Autowired
    public OrdersService orderservice;

    /**
     * 新增 orders
     *
     * @param orders
     * @return
     */
    @PutMapping("save")
    public R save(@RequestBody Orders orders) {
        return R.bool(orderservice.save(orders));
    }

    /**
     * 删除 orders
     *
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public R delete(@PathVariable(value = "id") String id) {
        return R.bool(orderservice.removeById(id));
    }

    /**
     * 修改 orders
     *
     * @param orders
     * @return
     */
    @PostMapping("update")
    public R update(@RequestBody Orders orders) {
        return R.bool(orderservice.updateById(orders));
    }

    /**
     * 查询 orders
     *
     * @param orders
     * @return
     */
    @PostMapping("select")
    public R select(@RequestBody Orders orders, Page<Orders> page) {
        return R.ok(orderservice.page(page, Wrappers.<Orders>query(orders)));
    }

}
