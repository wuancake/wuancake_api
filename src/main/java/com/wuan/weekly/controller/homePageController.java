package com.wuan.weekly.controller;

import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wuan.weekly.entity.Info;
import com.wuan.weekly.entity.Leave;
import com.wuan.weekly.entity.Main;
import com.wuan.weekly.exception.NullTextException;
import com.wuan.weekly.exception.ParamFormatException;
import com.wuan.weekly.service.homePageService;
import com.wuan.weekly.util.Utils;

@RestController
public class homePageController {

    @Autowired
    private homePageService hps;



    @ResponseBody
    @RequestMapping(value = "/main", method = RequestMethod.POST)
    public Main home(@RequestBody Map<String, Object> map) throws ParamFormatException {
        int userId = (int) map.get("userId");
        if (userId < 0) {
            throw new ParamFormatException("用户ID不正确！");
        }

        return hps.m(userId);
    }

    @ResponseBody
    @RequestMapping(value = "/leave", method = RequestMethod.POST)
    public Info leave(@RequestBody Leave li) throws ParamFormatException, NullTextException {
        //请了多少周假
        int weekNum = li.getWeekNum();
        //请假理由
        String reason = li.getReason();
        //用户id
        int userId = li.getUserId();
        //分组id
        int groupId = li.getGroupId();
        if (weekNum <= 0 || userId < 0 || groupId < 0) {
            throw new ParamFormatException("用户ID或分组ID不正确或请假周数不正确！");
        }
        if (reason == null || "".equals(reason)) {
            throw new NullTextException("必填项不能为空！");
        }
        //生成请假周报
        Leave[] leaveReport = createLeaveReport(weekNum, reason, userId, groupId);
        try {
            hps.leaveWeekly(leaveReport);
        } catch (Exception e) {
            //如果在往数据库里更新请假出现问题，事务回滚
            //当前周数
            e.printStackTrace();
            //当前周数
            int thisWeek = (int) (((Calendar.getInstance(Locale.CHINA).getTime()).getTime() - Utils.FIRSTDAY.getTime()) / (7 * 24 * 60 * 60 * 1000));
            hps.cancelLeave(userId, groupId, thisWeek);
            return new Info("请假失败", 500);
        }
        //成功请假
        return new Info("请假成功", 200);
    }

    @ResponseBody
    @RequestMapping(value = "/cancelLeave", method = RequestMethod.POST)
    public Info cancelLeave(@RequestBody Map<String, Object> param) throws ParamFormatException {
        int userId = (int) param.get("userId");
        int groupId = (int) param.get("groupId");
        checkParam(userId, groupId);
        //当前周数
        int thisWeek = (int) (((Calendar.getInstance(Locale.CHINA).getTime()).getTime() - Utils.FIRSTDAY.getTime()) / (7 * 24 * 60 * 60 * 1000));
        try {
            hps.cancelLeave(userId, groupId, thisWeek);
        } catch (Exception e) {
            e.printStackTrace();
            return new Info("取消请假失败", 500);
        }
        return new Info("取消请假成功", 200);
    }


    private void checkParam(Integer... integers) throws ParamFormatException {
        for (Integer i : integers) {
            if (i == null || i <= 0) {
                throw new ParamFormatException("用户ID或分组ID不正确！");
            }
        }

    }

    private Leave[] createLeaveReport(int weekNum, String reason, int userId, int groupId) {
        Leave[] leaveReport = new Leave[weekNum];
        //周报状态  3为请假
        int status = 3;
        //当前周数
        int thisWeek = (int) (((Calendar.getInstance(Locale.CHINA).getTime()).getTime() - Utils.FIRSTDAY.getTime()) / (7 * 24 * 60 * 60 * 1000));
        for (int i = 0; i < weekNum; i++) {
            leaveReport[i] = new Leave(thisWeek, userId, groupId, status, reason);
            thisWeek++;
        }
        return leaveReport;
    }
}
