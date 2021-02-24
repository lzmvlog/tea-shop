package top.lzmvlog.shop.goods.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import top.lzmvlog.common.result.R;
import top.lzmvlog.shop.goods.model.Goods;
import top.lzmvlog.shop.goods.service.GoodsService;

import java.time.LocalDateTime;

/**
  * goods 前端控制器
  *
  * @author zhang1591313226@163.com
  * @since 2021-02-24
  */
@RestController
@Slf4j
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    public GoodsService goodsService;

    /**
     * 新增 goods
     *
     * @param goods
     * @return
     */
    @PutMapping("save")
    public R save(@RequestBody Goods goods) {
        goods.setCreateTime(LocalDateTime.now());
        return R.bool(goodsService.save(goods));
    }

    /**
     * 删除 goods
     *
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public R delete(@PathVariable(value = "id") String id) {
        return R.bool(goodsService.removeById(id));
    }

    /**
     * 修改 goods
     *
     * @param goods
     * @return
     */
    @PostMapping("update")
    public R update(@RequestBody Goods goods) {
        return R.bool(goodsService.updateById(goods));
    }

    /**
     * 查询 orders
     *
     * @param goods
     * @return
     */
    @PostMapping("select")
    public R select(@RequestBody Goods goods, Page<Goods> page) {
        return R.ok(goodsService.page(page, Wrappers.<Goods>lambdaQuery(goods)));
    }

}
