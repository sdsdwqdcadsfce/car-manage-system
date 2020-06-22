package com.peait.student.service;

import com.peait.student.entity.StudentSorce;
import com.peait.student.result.Result;
import com.peait.student.result.TableResult;

public interface StudentSorceServiice {
    TableResult getList(String subjectName, int page, int limit);

    Result insert(StudentSorce studentSorce);

    Result update(StudentSorce studentSorce);

    Result deleteById(String id);

    Result selectById(String id);
}
