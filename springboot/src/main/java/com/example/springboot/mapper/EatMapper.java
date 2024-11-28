package com.example.springboot.mapper;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Eat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EatMapper {

    List<Eat> list();

    List<Eat> listByCondition(BaseRequest baseRequest);

    void save(Eat obj);

    Eat getById(Integer id);

    void updateById(Eat user);

    void deleteById(Integer id);

    Eat getByNo(String eatNo);

    void updateNumByNo(String eatNo);


}
