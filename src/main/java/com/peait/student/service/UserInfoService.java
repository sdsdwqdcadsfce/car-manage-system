package com.peait.student.service;

import com.peait.student.entity.vo.RegistUserVO;
import com.peait.student.entity.vo.UserInfoVO;
import com.peait.student.result.Result;
import com.peait.student.result.TableResult;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户逻辑处理接口
 */
public interface UserInfoService {
    TableResult getUserList(String realName, int page, int limit);

    Result insertUser(RegistUserVO registVO, HttpServletRequest request);

    Result updateUser(UserInfoVO updateVO, HttpServletRequest request);

    Result selectUserById(String id);

    Result deleteUserById(String id, HttpServletRequest request);

    Result queryUser();

    Result loginUser(String userName, String password);
}
