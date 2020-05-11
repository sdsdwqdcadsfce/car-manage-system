package com.peait.carmanagesystem.service;

import com.peait.carmanagesystem.result.Result;
import com.peait.carmanagesystem.result.TableResult;

public interface AuthoritySetService {
    Result queryByWorkerCode(String workerCode);

    Result updateWorkerAuthority(String loginCode, String workerCode,String authority);

    TableResult queryList(String workerName, Integer page, Integer limit);
}
