package com.peait.student.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peait.student.entity.Student;
import com.peait.student.entity.UserInfo;
import com.peait.student.entity.vo.RegistUserVO;
import com.peait.student.entity.vo.UserInfoVO;
import com.peait.student.exception.GlobalException;
import com.peait.student.mapper.StudentMapper;
import com.peait.student.mapper.UserInfoMapper;
import com.peait.student.result.CodeMsg;
import com.peait.student.result.Result;
import com.peait.student.result.TableResult;
import com.peait.student.service.UserInfoService;
import com.peait.student.util.UUIDUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 用户处理逻辑实现类
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private StudentMapper studentMapper;


    /**
     * 获取学生列表
     * @param page
     * @param limit
     * @return
     */
    @Override
    public TableResult getUserList(String studentName, int page, int limit) throws GlobalException {
        //使用pagehelper分页
        PageHelper.startPage(page, limit);
        List<UserInfo> result = userInfoMapper.getUserList(studentName);
        PageInfo<UserInfo> userPageInfo = new PageInfo<>(result);
        return new TableResult(userPageInfo.getTotal(), userPageInfo.getList());
    }

    /**
     * 新增用户信息
     *
     * @param registVO
     * @param request
     * @return
     */
    @Override
    public Result insertUser(RegistUserVO registVO, HttpServletRequest request) throws GlobalException {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(registVO.getPassword());
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(registVO, userInfo);
        userInfo.setId(UUIDUtil.uuid());
        userInfo.setUsercode(UUIDUtil.getSixNum());
        userInfo.setPassword(encode);
        userInfo.setUsercode(UUIDUtil.getSixNum());
        userInfo.setInsertime(new Date());
        userInfo.setUpdatetime(new Date());
        userInfoMapper.insertSelective(userInfo);
        return Result.success("新增成功");
    }

    /**
     * 通过id修改用户
     *
     * @param updateVO
     * @return
     */
    @Override
    public Result updateUser(UserInfoVO updateVO,HttpServletRequest request) throws GlobalException {
        //通过id查询用户
        //判断用户名是否重复
        int count = userInfoMapper.IsExistValidataNotId("user_info", "userName", updateVO.getUsername(), updateVO.getId());
        if (count > 0) {
            throw new GlobalException(CodeMsg.NAME_EXIXT_ERROR);
        }
        //判断手机号是否有重复
        int phoneCount = userInfoMapper.IsExistValidataNotId("user_info", "userPhone", updateVO.getUserphone(), updateVO.getId());
        if (phoneCount > 0) {
            throw new GlobalException(CodeMsg.MOBILE_EXIST);
        }
        //进行修改
        UserInfo user = new UserInfo();
        BeanUtils.copyProperties(updateVO, user);
        user.setUpdatetime(new Date());
        //密码加密
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        String encode = bCrypt.encode(updateVO.getPassword());
        user.setPassword(encode);
        userInfoMapper.updateByPrimaryKeySelective(user);
        return Result.success("");
    }

    /**
     * 通过用户名id查询用户信息
     *
     * @param id
     * @return
     */
    @Override
    public Result selectUserById(String id) throws GlobalException {
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
        return Result.success(userInfo);
    }

    /**
     * 通过用户名id查询用户信息
     *
     * @param id
     * @return
     */
    @Override
    public Result queryUser() throws GlobalException {
        List<UserInfo> userInfo = userInfoMapper.queryUser();
        return Result.success(userInfo);
    }

    /**
     *  用户登陆
     * @param userName
     * @param password
     * @return
     */
    @Override
    public Result loginUser(String userName, String password) {
        UserInfo user = userInfoMapper.selectByUserName(userName);
        if(user==null){
            throw new GlobalException(CodeMsg.USERNAME_EMPTY);
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if(!bCryptPasswordEncoder.matches(password,user.getPassword())){
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        return Result.success(user);
    }

    /**
     * 通过用户id删除用户信息
     *
     * @param id
     * @param request
     * @return
     */
    @Override
    public Result deleteUserById(String id, HttpServletRequest request) throws GlobalException {
        String workCode = request.getHeader("token");
        userInfoMapper.deleteByPrimaryKey(id);
        return Result.success("");
    }
}
