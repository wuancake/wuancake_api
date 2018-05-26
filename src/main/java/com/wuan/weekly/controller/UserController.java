package com.wuan.weekly.controller;


import com.wuan.weekly.entity.JsonBean;
import com.wuan.weekly.entity.JsonRequestBody;
import com.wuan.weekly.entity.User;
import com.wuan.weekly.entity.WaGroup;
import com.wuan.weekly.service.imple.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * @author Date: 2018/3/23
 * Time: 22:44
 */
@RestController
public class UserController {
    private static final String SESSION_NAME = "9527";
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "regist")
    public @ResponseBody
    JsonBean save(@RequestBody User user, HttpServletResponse response, HttpServletRequest request) {

        JsonBean jsonBean = new JsonBean();
        //设置四个后台变量
        user.setCreateTime(new Date());
        user.setModifyTime(new Date());
        //wuanName被砍掉
        if (user.getWuanName() == null) {
            user.setWuanName(user.getUserName());
        }

        try {
            userService.saveUser(user);
            User userByEmailAndPassword = userService.findUserByEmailAndPassword(user.getEmail(), user.getPassword());
            //设置用户id
            jsonBean.setUserId(userByEmailAndPassword.getId());
            //设置注册后的分组
            jsonBean.setGroupId(0);
            //设置用户名
            jsonBean.setUserName(user.getUserName());
        } catch (Exception e) {
            jsonBean.setInfoText(e.getMessage());
            response.setStatus(500);
            jsonBean.setInfoCode("500");
            return jsonBean;
        }
        jsonBean.setInfoText("注册成功");
        response.setStatus(200);
        jsonBean.setInfoCode("200");
        return jsonBean;
    }

    @RequestMapping(value = "group")
    public @ResponseBody
    JsonBean selectGroup(@RequestBody JsonRequestBody jsonRequestBody, HttpServletResponse response, HttpServletRequest request) {
        JsonBean jsonBean = new JsonBean();

        //获取json参数

        Integer userId = jsonRequestBody.getUserId();
        Integer groupId = jsonRequestBody.getGroupId();

        //查找用户是否存在
        User user = userService.findUserByUserId(userId);

        if (user != null) {
            try {
                //分组选择
                userService.selectGroup(user, groupId);
                //要返回的jsonBean
                jsonBean.setUserId(userId);
                jsonBean.setGroupId(groupId);
            } catch (Exception e) {
                response.setStatus(500);
                jsonBean.setInfoText(e.getMessage());
                jsonBean.setInfoCode("500");
                return jsonBean;
            }
        } else {
            response.setStatus(500);
            jsonBean.setInfoText("没有这个用户");
            jsonBean.setInfoCode("500");
            return jsonBean;
        }
        response.setStatus(200);
        jsonBean.setInfoCode("200");
        jsonBean.setInfoText("分组选择成功");

        return jsonBean;
    }

    @RequestMapping(value = "login")
    public @ResponseBody
    JsonBean findUserByEmailAndPassword(@RequestBody JsonRequestBody jsonRequestBody, HttpServletResponse response, HttpServletRequest request) {

        String email = jsonRequestBody.getEmail();
        String password = jsonRequestBody.getPassword();

        User user = userService.findUserByEmailAndPassword(email, password);
        String infoText = "登录成功已选择分组";

        JsonBean jsonBean = new JsonBean();
        if (user != null) {
            Integer userId = user.getId();
            Integer groupId = userService.findUserGroupByUserId(userId);
            jsonBean.setInfoText(infoText);
            jsonBean.setUserId(user.getId());
            jsonBean.setGroupId(groupId);

            if (groupId == 0) {
                infoText = "登录成功未选择分组";
            }
            jsonBean.setInfoText(infoText);
            response.setStatus(200);
            jsonBean.setInfoCode("200");
            jsonBean.setUserName(user.getUserName());
            request.getSession().setAttribute("9527", user);
            return jsonBean;
        } else {
            infoText = "邮箱或密码错误";
            response.setStatus(500);
            jsonBean.setInfoCode("500");
            jsonBean.setInfoText(infoText);
            return jsonBean;
        }
    }

    @RequestMapping(value = "findAllGroupInfo")
    public @ResponseBody
    JsonBean findAllGroupInfo(HttpServletResponse response, HttpServletRequest request) {
        JsonBean jsonBean = new JsonBean();

        try {
            List<WaGroup> allGroupInfo = userService.findAllGroupInfo();
            System.out.println(allGroupInfo);
            jsonBean.setGroups(allGroupInfo);
        } catch (Exception e) {
            jsonBean.setInfoCode("500");
            jsonBean.setInfoText("请求失败");
            response.setStatus(500);
            return jsonBean;
        }
        response.setStatus(200);
        jsonBean.setInfoCode("200");
        return jsonBean;
    }
}
