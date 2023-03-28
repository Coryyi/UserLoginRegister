package com.demo.userLogin.service.impl;

import com.demo.userLogin.dataobject.UserDO;
import com.demo.userLogin.dao.UserDAO;
import com.demo.userLogin.model.Result;
import com.demo.userLogin.model.User;
import com.demo.userLogin.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public Result<User> register(String userName, String pwd) {
        Result<User> result = new Result<>();

        if (StringUtils.isEmpty(userName)) {
            result.setCode("600");
            result.setMessage("用户名不能为空");
            return result;
        }
        if (StringUtils.isEmpty(pwd)) {
            result.setCode("601");
            result.setMessage("密码不能为空");
            return result;
        }

        UserDO userDO = userDAO.findByUserName(userName);
        if (userDO!=null){
            result.setCode("602");
            result.setMessage("用户名已经存在");
            return result;
        }

        // 密码加自定义盐值，确保密码安全
        String saltPwd = pwd + "_2050";
        // 生成md5值，并转为大写字母
        String md5Pwd = DigestUtils.md5Hex(saltPwd).toUpperCase();

        UserDO userDO1 = new UserDO();
        userDO1.setUserName(userName);
        userDO1.setPwd(md5Pwd);

        userDAO.add(userDO1);

        result.setSuccess(true);

        // 将 UserDO 对象转化为 User 对象
        User user = new User();
        user.setId(userDO1.getId());
        user.setUserName(userDO1.getUserName());

        result.setData(user);
        result.setMessage("注册成功");
        return result;
    }

    @Override
    public Result<User> login(String userName, String pwd) {

        Result<User> result = new Result<>();

        if (StringUtils.isEmpty(userName)) {
            result.setCode("600");
            result.setMessage("用户名不能为空");
            return result;
        }
        if (StringUtils.isEmpty(pwd)) {
            result.setCode("601");
            result.setMessage("密码不能为空");
            return result;
        }

        UserDO userDO = userDAO.findByUserName(userName);
        if (userDO==null){
            result.setCode("602");
            result.setMessage("用户名不存在");
            return result;
        }

        // 密码加自定义盐值，确保密码安全
        String saltPwd = pwd + "_2050";
        // 生成md5值，并转为大写字母
        String md5Pwd = DigestUtils.md5Hex(saltPwd).toUpperCase();

        if (!StringUtils.equals(md5Pwd,userDO.getPwd())){
            result.setCode("603");
            result.setMessage("密码不对");
            return result;
        }


        result.setSuccess(true);

        // 将 UserDO 对象转化为 User 对象
        User user = new User();
        user.setId(userDO.getId());
        user.setUserName(userDO.getUserName());
        user.setAvatar(userDO.getAvatar());
        user.setGmtCreated(userDO.getGmtCreated());
        user.setGmtModified(userDO.getGmtModified());

        result.setData(user);

        return result;
    }
}
