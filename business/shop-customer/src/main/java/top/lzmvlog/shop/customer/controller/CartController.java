package top.lzmvlog.shop.customer.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.lzmvlog.common.result.R;
import top.lzmvlog.shop.customer.model.Cart;
import top.lzmvlog.shop.customer.service.CartService;

import java.time.LocalDateTime;

/**
 * cart 前端控制器
 *
 * @author zhang1591313226@163.com
 * @since 2021-02-25
 */
@RestController
@Slf4j
@RequestMapping("/cart")
public class CartController {

    @Autowired
    public CartService cartService;

    /**
     * 新增 cart
     *
     * @param cart
     * @return
     */
    @PutMapping("save")
    public R save(@RequestBody Cart cart) {
        cart.setCreateTime(LocalDateTime.now());
        return R.bool(cartService.save(cart));
    }

    /**
     * 删除 cart
     *
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public R delete(@PathVariable(value = "id") String id) {
        return R.bool(cartService.removeById(id));
    }

    /**
     * 修改 cart
     *
     * @param cart
     * @return
     */
    @PostMapping("update")
    public R update(@RequestBody Cart cart) {
        return R.bool(cartService.updateById(cart));
    }

    /**
     * 查询 orders
     *
     * @param cart
     * @return
     */
    @PostMapping("select")
    public R select(@RequestBody Cart cart, Page<Cart> page) {
        return R.ok(cartService.page(page, Wrappers.<Cart>lambdaQuery(cart)));
    }

    /**
     * 新增购物车商品
     *
     * @param goodsId 商品id
     * @return
     */
    @PostMapping("saveCart")
    public R saveCart(String goodsId) {
        return R.ok(cartService.saveCart(goodsId));
    }

    /**
     * 查询 orders
     *
     * @param cart
     * @return
     */
    @PostMapping("selectMine")
    public R selectMine(@RequestBody Cart cart, Page<Cart> page) {
        // TODO:缺少用户信息
        return R.ok(cartService.page(page, Wrappers.<Cart>lambdaQuery(cart)));
    }
}
