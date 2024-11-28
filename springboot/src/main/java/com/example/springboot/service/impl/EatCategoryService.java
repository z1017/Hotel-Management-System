package com.example.springboot.service.impl;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.EatCategory;
import com.example.springboot.mapper.EatCategoryMapper;
import com.example.springboot.service.IEatCategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class EatCategoryService implements IEatCategoryService {

    @Resource
    EatCategoryMapper eatCategoryMapper;


    @Override
    public List<EatCategory> list() {
        return eatCategoryMapper.list();
    }

    @Override
    public PageInfo<EatCategory> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        // 自关联查询
        List<EatCategory> eatcategories = eatCategoryMapper.listByCondition(baseRequest);
        return new PageInfo<>(eatcategories);
    }

    @Override
    public void save(EatCategory obj) {
        eatCategoryMapper.save(obj);
    }

    @Override
    public EatCategory getById(Integer id) {
        return eatCategoryMapper.getById(id);
    }

    @Override
    public void update(EatCategory obj) {
        obj.setUpdatetime(LocalDate.now());
        eatCategoryMapper.updateById(obj);
    }

    @Override
    public void deleteById(Integer id) {
        eatCategoryMapper.deleteById(id);
    }

}