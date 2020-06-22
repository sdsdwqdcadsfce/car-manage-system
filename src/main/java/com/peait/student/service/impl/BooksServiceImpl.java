package com.peait.student.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peait.student.entity.Books;
import com.peait.student.entity.Student;
import com.peait.student.mapper.BooksMapper;
import com.peait.student.result.Result;
import com.peait.student.result.TableResult;
import com.peait.student.service.BooksService;
import com.peait.student.util.UUIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BooksServiceImpl implements BooksService {
    @Resource
    private BooksMapper booksMapper;
    @Override
    public Result selectById(String id) {
        Books books = booksMapper.selectByPrimaryKey(id);
        return Result.success(books);
    }

    @Override
    public Result deleteById(String id) {
        int i = booksMapper.deleteByPrimaryKey(id);
        return Result.success("删除成功");
    }

    @Override
    public Result update(Books books) {
        booksMapper.updateByPrimaryKeySelective(books);
        return Result.success("修改成功");
    }

    @Override
    public Result insert(Books books) {
        books.setId(UUIDUtil.uuid());
        booksMapper.insertSelective(books);
        return Result.success("新增成功");
    }

    @Override
    public TableResult getList(String bookName, int page, int limit) {
        //使用pagehelper分页
        PageHelper.startPage(page, limit);
        List<Books> result = booksMapper.getList(bookName);
        PageInfo<Books> userPageInfo = new PageInfo<>(result);
        return new TableResult(userPageInfo.getTotal(), userPageInfo.getList());
    }
}
