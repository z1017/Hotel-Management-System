package com.example.springboot.service;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.EatReserve;
import com.example.springboot.entity.Retur;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface IEatReserveService {

    List<EatReserve> list();

    PageInfo<EatReserve> page(BaseRequest baseRequest);

    void save(EatReserve obj);

    PageInfo<Retur> pageRetur(BaseRequest baseRequest);

    void saveRetur(Retur obj);

    EatReserve getById(Integer id);

    void update(EatReserve obj);

    void deleteById(Integer id);

    void deleteReturById(Integer id);

    Map<String, Object> getCountByTimeRange(String timeRange);
}
