package com.wuan.weekly.controller;


import com.wuan.weekly.entity.JsonBean;
import com.wuan.weekly.entity.User;
import com.wuan.weekly.entity.WaGroup;
import com.wuan.weekly.service.imple.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * @author Nobody
 * Date: 2018/3/23
 * Time: 22:44
 */
@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "regist")
    public @ResponseBody
    JsonBean save(User user, HttpServletResponse response) {
        //设置四个后台变量
        user.setCreate_time(new Date());
        user.setModify_time(new Date());
        //wuan_name被砍掉
        if (user.getWuan_name() == null) {
            user.setWuan_name(user.getUser_name());
        }
        String infoText = "注册成功";
        JsonBean jsonBean = new JsonBean();

        try {
            userService.saveUser(user);
            jsonBean.setUser_id(userService.findUserByEmailAndPassword(user.getEmail(), user.getPassword()).getId());
            jsonBean.setGroup_id(0);
        } catch (Exception e) {
            infoText = "用户名或邮箱已占用";
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
    JsonBean selectGroup(@RequestParam("user_id") Integer user_id, @RequestParam("group_id") Integer group_id, HttpServletResponse response) {
        User user = userService.findUserByUserId(user_id);
        String infoText = "分组选择成功";

        JsonBean jsonBean = new JsonBean();
        if (user != null) {
            try {
                userService.selectGroup(user, group_id);

                jsonBean.setUser_id(userService.findUserByUserId(user_id).getId());
                jsonBean.setGroup_id(group_id);
            } catch (Exception e) {
                infoText = "分组选择失败";
                response.setStatus(500);
                jsonBean.setInfoCode("500");
            }
        }
        jsonBean.setInfoText(infoText);
        response.setStatus(200);
        jsonBean.setInfoCode("200");

        return jsonBean;
    }

    @RequestMapping(value = "login")
    public @ResponseBody
    JsonBean findUserByEmailAndPassword(@RequestParam("email") String email, @RequestParam("password") String password, HttpServletResponse response) {
        User user = userService.findUserByEmailAndPassword(email, password);
        String infoText = "登录成功已选择分组";

        JsonBean jsonBean = new JsonBean();
        if (user != null) {
            Integer user_id = user.getId();
            Integer group_id = userService.findUserGroupByUserId(user_id);
            jsonBean.setInfoText(infoText);
            jsonBean.setUser_id(user.getId());
            jsonBean.setGroup_id(group_id);

            if (group_id != 0) {
                response.setStatus(200);
                jsonBean.setInfoCode("200");
                return jsonBean;
            } else {
                infoText = "登录成功未选择分组";
                jsonBean.setInfoText(infoText);
                response.setStatus(200);
                jsonBean.setInfoCode("200");
                return jsonBean;
            }
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
    JsonBean findAllGroupInfo(HttpServletResponse response) {
        JsonBean jsonBean = new JsonBean();
        try {
            List<WaGroup> allGroupInfo = userService.findAllGroupInfo();
            jsonBean.setGroups(allGroupInfo);
        } catch (Exception e) {
            jsonBean.setInfoCode("500");
            response.setStatus(500);
        }
        response.setStatus(200);
        jsonBean.setInfoCode("200");
        return jsonBean;
    }
}
