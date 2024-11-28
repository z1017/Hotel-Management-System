package com.example.springboot.mapper;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.EatReserve;
import com.example.springboot.entity.Retur;
import com.example.springboot.mapper.po.ReserveReturCountPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EatReserveMapper {
    List<EatReserve> list();

    List<EatReserve> listByCondition(BaseRequest baseRequest);
    List<Retur> listReturByCondition(BaseRequest baseRequest);

    void save(EatReserve obj);

    EatReserve getById(Integer id);

    void updateById(EatReserve user);

    void deleteById(Integer id);

    void saveRetur(Retur obj);

    void deleteReturById(Integer id);

    void updateStatus(String status, Integer id);

    List<ReserveReturCountPO> getCountByTimeRange(@Param("timeRange") String timeRange, @Param("type") int type);  // 1 borrow  2 return
}
