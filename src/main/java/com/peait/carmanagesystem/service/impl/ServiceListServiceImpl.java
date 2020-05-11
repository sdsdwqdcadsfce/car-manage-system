package com.peait.carmanagesystem.service.impl;

import com.peait.carmanagesystem.entity.ServiceList;
import com.peait.carmanagesystem.mapper.ServiceListMapper;
import com.peait.carmanagesystem.result.Result;
import com.peait.carmanagesystem.service.ServiceListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ServiceListServiceImpl implements ServiceListService {
    @Resource
    private ServiceListMapper serviceListMapper;

    @Override
    public Result query() {
        List<ServiceList> serviceListLists = serviceListMapper.query();
        return Result.success(serviceListLists);
    }
}
