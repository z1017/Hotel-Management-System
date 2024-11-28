package com.example.springboot.mapper;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.EatCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EatCategoryMapper {

    List<EatCategory> list();

    List<EatCategory> listByCondition(BaseRequest baseRequest);

    void save(EatCategory obj);

    EatCategory getById(Integer id);

    void updateById(EatCategory user);

    void deleteById(Integer id);


}
