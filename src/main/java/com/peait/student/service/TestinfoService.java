package com.peait.student.service;

import com.peait.student.entity.Testinfo;
import com.peait.student.result.Result;
import com.peait.student.result.TableResult;

public interface TestinfoService {
    TableResult getList(String subjectName, int page, int limit);

    Result insert(Testinfo testinfo);

    Result update(Testinfo testinfo);

    Result deleteById(String id);

    Result selectById(String id);
}
