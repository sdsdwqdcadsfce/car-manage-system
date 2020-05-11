package com.peait.carmanagesystem.service;

import com.peait.carmanagesystem.entity.OrderManage;
import com.peait.carmanagesystem.result.Result;
import com.peait.carmanagesystem.result.TableResult;

public interface OrderManageService {
    Result insert(OrderManage orderManage);

    Result update(OrderManage orderManage);

    Result query(String orderManage);

    Result delete(String id);

    TableResult queryList(String ordercode, int page, int limit);

    Result webInsert(OrderManage orderManage);

    TableResult queryByUserName(String userName);
}
