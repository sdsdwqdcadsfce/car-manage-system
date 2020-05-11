package com.peait.carmanagesystem.service;

import com.peait.carmanagesystem.entity.vo.CarBeautyManageVO;
import com.peait.carmanagesystem.result.Result;
import com.peait.carmanagesystem.result.TableResult;
import org.apache.ibatis.annotations.Param;

public interface CarBeautyManageService {
    Result insert(CarBeautyManageVO carBeautyManageVO);

    Result update(CarBeautyManageVO carBeautyManageVO);

    Result delete(String id);

    Result query(String id);

    TableResult queryList(String carCode, int page, int limit);
}
