package com.peait.student.service;

import com.peait.student.entity.Books;
import com.peait.student.result.Result;
import com.peait.student.result.TableResult;

public interface BooksService {
    Result selectById(String id);

    Result deleteById(String id);

    Result update(Books books);

    Result insert(Books books);

    TableResult getList(String bookName, int page, int limit);
}
