package com.peait.student.service;

import com.peait.student.entity.Student;
import com.peait.student.entity.Subject;
import com.peait.student.result.Result;
import com.peait.student.result.TableResult;

public interface SubjectService {
    TableResult getStudentList(String studentName, int page, int limit);

    Result insertUser(Subject student);

    Result updateStudent(Subject student);

    Result deleteUserById(String id);

    Result selectUserById(String id);
}
