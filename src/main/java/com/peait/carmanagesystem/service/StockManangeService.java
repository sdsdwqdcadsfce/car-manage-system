package com.peait.carmanagesystem.service;

import com.peait.carmanagesystem.entity.StockManange;
import com.peait.carmanagesystem.result.Result;
import com.peait.carmanagesystem.result.TableResult;

public interface StockManangeService {
    Result insert(StockManange stockManange);

    Result update(StockManange stockManange);

    Result query(String id);

    TableResult queryList(String goodsName, int page, int limit);

    Result delete(String id);
}
