package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.controller.request.EatReservePageRequest;
import com.example.springboot.entity.EatReserve;
import com.example.springboot.entity.Retur;
import com.example.springboot.service.IEatReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/eatReserve")
public class EatReserveController {

    @Autowired
    IEatReserveService eatReserveService;


    @PostMapping("/save")
    public Result save(@RequestBody EatReserve obj) {
        eatReserveService.save(obj);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody EatReserve obj) {
        eatReserveService.update(obj);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        eatReserveService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        EatReserve obj = eatReserveService.getById(id);
        return Result.success(obj);
    }

    @GetMapping("/list")
    public Result list() {
        List<EatReserve> list = eatReserveService.list();
        return Result.success(list);
    }

    @GetMapping("/page")
    public Result page(EatReservePageRequest pageRequest) {
        return Result.success(eatReserveService.page(pageRequest));
    }

    @GetMapping("/pageRetur")
    public Result pageRetur(EatReservePageRequest pageRequest) {
        return Result.success(eatReserveService.pageRetur(pageRequest));
    }

    @PostMapping("/saveRetur")
    public Result saveRetur(@RequestBody Retur obj) {
        eatReserveService.saveRetur(obj);
        return Result.success();
    }

    @DeleteMapping("/deleteRetur/{id}")
    public Result deleteRetur(@PathVariable Integer id) {
        eatReserveService.deleteReturById(id);
        return Result.success();
    }

//      timeRange: week month month2 month3
    @GetMapping("/lineCharts/{timeRange}")
    public Result lineCharts(@PathVariable String timeRange) {
        return Result.success(eatReserveService.getCountByTimeRange(timeRange));
    }

}
