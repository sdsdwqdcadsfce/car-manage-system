package com.peait.carmanagesystem.service;

import com.peait.carmanagesystem.entity.vo.RegistProjectBeautyManageVO;
import com.peait.carmanagesystem.result.Result;
import com.peait.carmanagesystem.result.TableResult;

public interface ProjectManageService {
    Result insert(RegistProjectBeautyManageVO registProjectBeautyManageVO);

    Result query(String id);

    Result update(RegistProjectBeautyManageVO registProjectBeautyManageVO);

    Result delete(String id);

    TableResult queryList(String projectname, int page, int limit);

    Result queryName();
}
