package com.demo.userLogin.api;

import com.demo.userLogin.model.Result;
import com.demo.userLogin.model.User;
import com.demo.userLogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.util.Objects;

/**
 * @author joe
 */
@Controller
public class UserAPI {

    @Autowired
    private UserService userService;

    @PostMapping("/api/user/reg")
    @ResponseBody
    public Result<User> reg(@RequestParam("name") String name, @RequestParam("password") String pwd, @RequestParam("passwordCheck") String pwdCheck) {
        Result result = new Result();
        if(!Objects.equals(pwd, pwdCheck)){
            result.setSuccess(false);
            result.setMessage("两次密码不一致");
            result.setCode("401");
            return result;
        }

        return userService.register(name, pwd);
    }

    @PostMapping("/api/user/login")
    @ResponseBody
    public Result<User> login(@RequestParam("userName") String userName, @RequestParam("pwd") String pwd, HttpServletRequest request) {
        Result<User> result = userService.login(userName, pwd);

        if (result.isSuccess()) {
            request.getSession().setAttribute("userId", result.getData().getId());
        }

        return result;
    }

    @GetMapping("/api/user/logout")
    @ResponseBody
    public Result logout(HttpServletRequest request) {
        Result result = new Result();
        request.getSession().removeAttribute("userId");

        result.setSuccess(true);
        return result;
    }


}
