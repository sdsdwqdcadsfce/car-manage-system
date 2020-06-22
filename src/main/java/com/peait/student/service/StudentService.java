package com.peait.student.service;

import com.peait.student.entity.Student;
import com.peait.student.result.Result;
import com.peait.student.result.TableResult;

public interface StudentService {
    TableResult getStudentList(String studentName, int page, int limit);

    Result insertUser(Student student);

    Result updateStudent(Student student);

    Result deleteUserById(String id);

    Result selectUserById(String id);
}
