package com.springboot.shiroo.controller;

import com.springboot.shiroo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wangduo
 * @date 2018/8/14 - 17:09
 */
@Controller
public class UserController {
    @RequestMapping("/hello")
    public String hello(){
        return "test";
    }
    @RequestMapping("/add")
    public String add(){
        return "user/add";
    }
    @RequestMapping("/update")
    public String update(){
        return "user/update";
    }
    @RequestMapping("/tologin")
    public String tologin(){
        return "login";
    }
    @RequestMapping("/auth")
    public String unauth(){
        return "user/unauth";
    }


    @RequestMapping("/login")
    public String login(String username , String password, Model model) {
        /**
         * 使用shiro进行编写操作
         */
        //1.获取subject
        Subject subject = SecurityUtils.getSubject ();
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken (username, password);
        //3.执行登录方法(try catch方法快捷键)
        try {
            subject.login (token);
            System.out.println (token.getUsername ());
            model.addAttribute ("name", token.getUsername ());
            //登陆成功
            return "test";
        } catch (UnknownAccountException e) {
            // e.printStackTrace ();
            //登陆失败:用户名不存在
            model.addAttribute ("msg", "用户名不存在");
            return "login";
        } catch (IncorrectCredentialsException e) {
            // e.printStackTrace ();
            //登陆失败:密码错误
            model.addAttribute ("msg", "密码错误");
            return "login";
        }
    }


    }


