package com.example.springboot.service;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.EatCategory;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IEatCategoryService {

    List<EatCategory> list();

    PageInfo<EatCategory> page(BaseRequest baseRequest);

    void save(EatCategory obj);

    EatCategory getById(Integer id);

    void update(EatCategory obj);

    void deleteById(Integer id);

}
