package com.wuan.weekly.controller;


import com.wuan.weekly.entity.JsonBean;
import com.wuan.weekly.entity.JsonRequestBody;
import com.wuan.weekly.entity.User;
import com.wuan.weekly.entity.WaGroup;
import com.wuan.weekly.mapper.WaGroupMapper;
import com.wuan.weekly.service.WeeklyService;
import com.wuan.weekly.service.imple.UserServiceImpl;
import com.wuan.weekly.util.MD5Utils;
import com.wuan.weekly.util.Utils;
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
    public static final String SESSION_NAME = "9527";
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private WaGroupMapper waGroupMapper;

    @Autowired
    private WeeklyService weeklyService;

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
        //MD5加盐
        String password = MD5Utils.generate(user.getPassword());
        user.setPassword(password);

        try {
            userService.saveUser(user);
            User userByEmailAndPassword = userService.findUserByEmail(user.getEmail());
            //设置用户id
            jsonBean.setUserId(userByEmailAndPassword.getId());
            //设置注册后的分组
            jsonBean.setGroupId(0);
            //设置用户名
            jsonBean.setUserName(user.getUserName());
        } catch (Exception e) {
            jsonBean.setInfoText(e.getMessage());
            jsonBean.setInfoCode("500");
            return jsonBean;
        }
        jsonBean.setInfoText("注册成功");
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
                jsonBean.setInfoText(e.getMessage());
                jsonBean.setInfoCode("500");
                return jsonBean;
            }
        } else {
            jsonBean.setInfoText("没有这个用户");
            jsonBean.setInfoCode("500");
            return jsonBean;
        }
        jsonBean.setInfoCode("200");
        jsonBean.setInfoText("分组选择成功");

        return jsonBean;
    }

    @RequestMapping(value = "login")
    public @ResponseBody
    JsonBean findUserByEmailAndPassword(@RequestBody JsonRequestBody jsonRequestBody, HttpServletResponse response, HttpServletRequest request) {

        String email = jsonRequestBody.getEmail();
        String password = jsonRequestBody.getPassword();

        JsonBean jsonBean = new JsonBean();

        User user = userService.findUserByEmail(jsonRequestBody.getEmail());

        if (user == null) {
            jsonBean.setInfoText("邮箱错误");
            jsonBean.setInfoCode("500");
            return jsonBean;
        } else {
            boolean verify = MD5Utils.verify(password, user.getPassword());
            if (!verify) {
                jsonBean.setInfoText("密码错误");
                jsonBean.setInfoCode("500");
                return jsonBean;
            } else {
                Integer userId = user.getId();
                Integer groupId = userService.findUserGroupByUserId(userId);
                jsonBean.setUserId(user.getId());
                jsonBean.setGroupId(groupId);
                jsonBean.setUserName(user.getUserName());
                jsonBean.setInfoText("登录成功已选择分组");
                if (groupId == 0) {
                    jsonBean.setInfoText("登录成功未选择分组");
                }
                //微信小程序需要，返回当前周数
                jsonBean.setCurrWeek(Utils.getMaxWeekNum());
                //微信小程序需要，返回当前周数的周报状态
                Integer statusByUserIdAndMaxWeekNum = weeklyService.findStatusByUserIdAndMaxWeekNum(userId, Utils.getMaxWeekNum());
                if (statusByUserIdAndMaxWeekNum == null) {
                    statusByUserIdAndMaxWeekNum = 1;
                }
                jsonBean.setStatus(statusByUserIdAndMaxWeekNum);
                jsonBean.setGroupName(waGroupMapper.getGroupNameByGroupId(groupId));
                jsonBean.setInfoCode("200");
                return jsonBean;
            }
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
            return jsonBean;
        }
        jsonBean.setInfoCode("200");
        return jsonBean;
    }
}
