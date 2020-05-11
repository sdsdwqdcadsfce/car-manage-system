package com.peait.carmanagesystem.service;

import com.peait.carmanagesystem.entity.vo.LoginVO;
import com.peait.carmanagesystem.entity.vo.RegistWorkVO;
import com.peait.carmanagesystem.entity.vo.WorkerInfoVO;
import com.peait.carmanagesystem.result.Result;
import com.peait.carmanagesystem.result.TableResult;

import javax.servlet.http.HttpServletRequest;

public interface WorkerInfoService {

    TableResult getWorkList(String realName, int page, int limit);

    Result insertWorker(RegistWorkVO registVO, HttpServletRequest request);

    Result updateWorker(WorkerInfoVO workerInfo);

    Result selectWorkerById(String id);

    Result deleteWorkerById(String id, String loginWorker);

    Result login(LoginVO loginVO);
}
