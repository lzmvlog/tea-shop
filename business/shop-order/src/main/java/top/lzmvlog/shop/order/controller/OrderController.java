package top.lzmvlog.shop.order.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.lzmvlog.common.result.R;
import top.lzmvlog.shop.order.model.Order;
import top.lzmvlog.shop.order.service.OrderService;

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
public class OrderController {

    @Autowired
    public OrderService orderservice;

    /**
     * 新增 order
     *
     * @param order
     * @return
     */
    @PutMapping("save")
    public R save(@RequestBody Order order) {
        return R.bool(orderservice.save(order));
    }

    /**
     * 删除 order
     *
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public R delete(@PathVariable(value = "id") String id) {
        return R.bool(orderservice.removeById(id));
    }

    /**
     * 修改 order
     *
     * @param order
     * @return
     */
    @PostMapping("update")
    public R update(@RequestBody Order order) {
        return R.bool(orderservice.updateById(order));
    }

    /**
     * 查询 order
     *
     * @param order
     * @return
     */
    @PostMapping("select")
    public R select(@RequestBody Order order, Page<Order> page) {
        return R.ok(orderservice.page(page, Wrappers.<Order>query(order)));
    }

}
