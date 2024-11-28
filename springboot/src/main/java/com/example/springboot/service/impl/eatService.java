package com.example.springboot.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Eat;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.EatMapper;
import com.example.springboot.service.IEatService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class eatService implements IEatService {

    @Resource
    EatMapper eatMapper;


    @Override
    public List<Eat> list() {
        return eatMapper.list();
    }

    @Override
    public PageInfo<Eat> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        return new PageInfo<>(eatMapper.listByCondition(baseRequest));
    }

    @Override
    public void save(Eat obj) {
        try {
            obj.setEatCategory(eatCategory(obj.getEatCategories()));
            eatMapper.save(obj);
        } catch (Exception e) {
            throw new ServiceException("数据插入错误", e);
        }

    }

    @Override
    public Eat getById(Integer id) {
        return eatMapper.getById(id);
    }

    @Override
    public void update(Eat obj) {

        try {
//            obj.setCategory(eatCategory(obj.getCategories()));
            obj.setEatCategory(eatCategory(obj.getEatCategories()));
            obj.setUpdatetime(LocalDate.now());
            eatMapper.updateById(obj);
        } catch (Exception e) {
            throw new ServiceException("数据更新错误",e);
        }

    }

    @Override
    public void deleteById(Integer id) {
        eatMapper.deleteById(id);
    }

    private String eatCategory(List<String> eatCategories) {
        StringBuilder sb = new StringBuilder();
        if (CollUtil.isNotEmpty(eatCategories)) {
            eatCategories.forEach(v -> sb.append(v).append(" > "));
            return sb.substring(0, sb.lastIndexOf(" > "));
        }
        return sb.toString();
    }

}