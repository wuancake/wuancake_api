package com.wuan.weekly.controller;


import com.wuan.weekly.entity.JsonBean;
import com.wuan.weekly.entity.User;
import com.wuan.weekly.entity.UserGroup;
import com.wuan.weekly.service.imple.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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
    JsonBean save(User user) {

        user.setAuth(1);
        user.setDeleteFlg(0);
        user.setCreate_time(new Date());
        user.setModify_time(new Date());
		
		if(user.getWuan_name==null){
			user.setWuan_name(user.getUser_name);
		}
		
        String infoText = "注册成功";
        String infoCode = "301";
        String url = "/login";
        JsonBean jsonBean = new JsonBean();
        try {
            userService.saveUser(user);
            jsonBean.setUser_id(userService.findUserByEmailAndPassword(user.getEmail(), user.getPassword()).getId());
            jsonBean.setGroup_id(0);
        } catch (Exception e) {
            e.printStackTrace();
            infoText = "用户名或邮箱已占用";
            infoCode = "500";
            url = null;
        }
        jsonBean.setInfoText(infoText);
        jsonBean.setInfoCode(infoCode);
        jsonBean.setUrl(url);

        return jsonBean;
    }

    @RequestMapping(value = "group")
    public @ResponseBody
    JsonBean selectGroup(@RequestParam("user_id") Integer user_id, @RequestParam("group_id") Integer group_id) {
        User user = userService.findUserByUserId(user_id);
        String infoText = "分组选择成功";
        String infoCode = "301";
        String url = "/main";
        JsonBean jsonBean = new JsonBean();
        if (user != null) {
            try {
                userService.selectGroup(user, group_id);
                jsonBean.setUser_id(userService.findUserByUserId(user_id).getId());
                jsonBean.setGroup_id(group_id);
            } catch (Exception e) {
                e.printStackTrace();
                infoText = "分组选择失败";
                infoCode = "500";
                url = null;
            }
        }
        jsonBean.setInfoText(infoText);
        jsonBean.setInfoCode(infoCode);
        jsonBean.setUrl(url);

        return jsonBean;
    }

    @RequestMapping(value = "login")
    public @ResponseBody
    JsonBean findUserByEmailAndPassword(@RequestParam("email") String email, @RequestParam("password") String password) {
        User user = userService.findUserByEmailAndPassword(email, password);
        String infoText = "登录成功已选择分组";
        String infoCode = "301";
        String url = "/main";
        JsonBean jsonBean = new JsonBean();

        if (user != null) {
            UserGroup userGroup = userService.findUserGroupByUserId(user.getId());
            jsonBean.setInfoText(infoText);
            jsonBean.setInfoCode(infoCode);
            jsonBean.setUser_id(user.getId());
            jsonBean.setGroup_id(userGroup.getGroup_id());

            if (userGroup.getGroup_id() != 0) {
                jsonBean.setUrl(url);
                return jsonBean;
            } else {
                infoText = "登录成功未选择分组";
                url = "/group";
                jsonBean.setInfoText(infoText);
                jsonBean.setUrl(url);
                return jsonBean;
            }
        } else {
            infoText = "邮箱或密码错误";
            infoCode = "500";
            jsonBean.setInfoText(infoText);
            jsonBean.setInfoCode(infoCode);
            return jsonBean;
        }
    }
}
