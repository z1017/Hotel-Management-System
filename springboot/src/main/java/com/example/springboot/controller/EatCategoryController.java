package com.example.springboot.controller;

import cn.hutool.core.collection.CollUtil;
import com.example.springboot.common.Result;
import com.example.springboot.controller.request.EatCategoryPageRequest;
import com.example.springboot.entity.EatCategory;
import com.example.springboot.service.IEatCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/eatCategory")
public class EatCategoryController {

    @Autowired
    IEatCategoryService eatCategoryService;

    @PostMapping("/save")
    public Result save(@RequestBody EatCategory obj) {
        eatCategoryService.save(obj);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody EatCategory obj) {
        eatCategoryService.update(obj);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        eatCategoryService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        EatCategory obj = eatCategoryService.getById(id);
        return Result.success(obj);
    }

    @GetMapping("/list")
    public Result list() {
        List<EatCategory> list = eatCategoryService.list();
        return Result.success(list);
    }

    @GetMapping("/tree")
    public Result tree() {
        List<EatCategory> list = eatCategoryService.list();

        // 对list操作即可
        List<EatCategory> treeList = list.stream().filter(v -> v.getPid() == null).collect(Collectors.toList());  // 第一层
        // 比如 递归实现 children列表的查询

        return Result.success(createTree(null, list));   //  null 表示从第一级开始递归
    }

    // 完全递归的方法来实现递归树
    private List<EatCategory> createTree(Integer pid, List<EatCategory> categories) {
        List<EatCategory> treeList = new ArrayList<>();
        for (EatCategory category : categories) {
            if (pid == null) {
                if (category.getPid() == null) {  // 那这就是第一级节点
                    treeList.add(category);
                    category.setChildren(createTree(category.getId(), categories));
                }
            } else {
                if (pid.equals(category.getPid())) {
                    treeList.add(category);
                    category.setChildren(createTree(category.getId(), categories));
                }
            }
            if (CollUtil.isEmpty(category.getChildren())) {
                category.setChildren(null);
            }
        }
        return treeList;
    }

    @GetMapping("/page")
    public Result page(EatCategoryPageRequest pageRequest) {
        return Result.success(eatCategoryService.page(pageRequest));
    }

}
