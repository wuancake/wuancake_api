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
import java.util.Random;

/**
 * @author Date: 2018/3/23
 * Time: 22:44
 */
@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "regist")
    public @ResponseBody
    JsonBean save(@RequestBody User user, HttpServletResponse response) {
        //设置四个后台变量
        user.setCreateTime(new Date());
        user.setModifyTime(new Date());
        //wuanName被砍掉
        if (user.getWuanName() == null) {
            user.setWuanName(user.getUserName());
        }
        String infoText = "注册成功";
        JsonBean jsonBean = new JsonBean();

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
            e.printStackTrace();
            infoText = "注册失败";
            response.setStatus(500);
            jsonBean.setInfoCode("500");
        }
        jsonBean.setInfoText(infoText);
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

        if (request.getSession().getAttribute("9527") == null || groupId < 0) {
            jsonBean.setInfoCode("500");
            jsonBean.setInfoText("会话无效或请求数据错误");
            response.setStatus(500);
            return jsonBean;
        }
        //查找用户是否存在
        User user = userService.findUserByUserId(userId);
        //默认两个值
        String infoText = "分组选择成功";
        String infoCode = "200";

        if (user != null) {
            try {
                //分组选择
                userService.selectGroup(user, groupId);
                //要返回的jsonBean
                jsonBean.setUserId(userId);
                jsonBean.setGroupId(groupId);
            } catch (Exception e) {
                infoText = "分组选择失败";
                response.setStatus(500);
                infoCode = "500";
            }
        } else if (user == null) {
            infoText = "没有这个用户";
            response.setStatus(500);
            infoCode = "500";
            return jsonBean;
        } else {
            response.setStatus(200);
        }
        jsonBean.setInfoCode(infoCode);
        jsonBean.setInfoText(infoText);

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
                jsonBean.setInfoText(infoText);
            }
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
        Object attribute = request.getSession().getAttribute("9527");
        if (attribute == null) {
            jsonBean.setInfoText("会话无效");
            jsonBean.setInfoCode("500");
            response.setStatus(500);
            return jsonBean;
        }

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
