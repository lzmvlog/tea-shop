package top.lzmvlog.shop.files.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.lzmvlog.common.result.R;
import top.lzmvlog.shop.files.model.Resource;
import top.lzmvlog.shop.files.service.ResourceService;

import java.time.LocalDateTime;

/**
  * resource 前端控制器
  *
  * @author zhang1591313226@163.com
  * @since 2021-03-06
  */
@RestController
@Slf4j
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    public ResourceService resourceService;

    /**
     * 新增 resource
     *
     * @param resource
     * @return
     */
    @PutMapping("save")
    public R save(@RequestBody Resource resource) {
        resource.setCreateTime(LocalDateTime.now());
        return R.bool(resourceService.save(resource));
    }

    /**
     * 删除 resource
     *
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public R delete(@PathVariable(value = "id") String id) {
        return R.bool(resourceService.removeById(id));
    }

    /**
     * 修改 resource
     *
     * @param resource
     * @return
     */
    @PostMapping("update")
    public R update(@RequestBody Resource resource) {
        return R.bool(resourceService.updateById(resource));
    }

    /**
     * 查询 orders
     *
     * @param resource
     * @return
     */
    @PostMapping("select")
    public R select(@RequestBody Resource resource, Page<Resource> page) {
        return R.ok(resourceService.page(page, Wrappers.<Resource>lambdaQuery(resource)));
    }

}
