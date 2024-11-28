package com.example.springboot.service;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Eat;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IEatService {

    List<Eat> list();

    PageInfo<Eat> page(BaseRequest baseRequest);

    void save(Eat obj);

    Eat getById(Integer id);

    void update(Eat obj);

    void deleteById(Integer id);

}
