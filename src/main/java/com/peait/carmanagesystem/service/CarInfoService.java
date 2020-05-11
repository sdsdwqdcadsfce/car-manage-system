package com.peait.carmanagesystem.service;

import com.peait.carmanagesystem.entity.vo.RegistCarInfoVO;
import com.peait.carmanagesystem.result.Result;
import com.peait.carmanagesystem.result.TableResult;

public interface CarInfoService {
    Result insert(RegistCarInfoVO registCarInfoVO);

    Result update(RegistCarInfoVO registCarInfoVO);

    Result delete(String id);

    TableResult queryList(String carUserName, int page, int limit);

    Result queryById(String id);
}
