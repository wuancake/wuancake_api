package com.wuan.weekly.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.wuan.weekly.entity.Version;
import com.wuan.weekly.mapper.VersionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wuan.weekly.entity.Leave;
import com.wuan.weekly.entity.Main;
import com.wuan.weekly.mapper.homePageMapper;
import com.wuan.weekly.util.Utils;

@Service
public class homePageService {

    @Autowired
    private homePageMapper mapper;
    @Autowired
    private VersionMapper versionMapper;

    /**
     * 向数据库提交请假周报
     */
    public void leaveWeekly(Leave[] leave) {
        for (int i = 0; i < leave.length; i++) {
            //请假
            mapper.leaveWeekly(leave[i], new Date());
        }
    }

    /**
     * 取消请假
     *
     * @param userId
     * @param thisWeek
     */
    public void cancelLeave(int userId, int groupId, int thisWeek) {
        mapper.cancelLeave(userId, groupId, thisWeek);
    }

    public Main m(int user_id) {
    	 //当前周数
        int thisWeek = (int) (((Calendar.getInstance(Locale.CHINA).getTime()).getTime() - Utils.FIRSTDAY.getTime()) / (7 * 24 * 60 * 60 * 1000));
        int status = 0;
        try {
            status = mapper.selectStatus(user_id, thisWeek);
        } catch (NullPointerException e) {
            status = 1;
        }
        System.out.println(status);
        Main ma = new Main();
        if (status == 1) {
            ma.setWeekNum(thisWeek);
            ma.setStatus(status);
            ma.setInfoText("未提交");
            ma.setInfoCode(200);
        } else {
            if (status == 2) {
                ma.setWeekNum(thisWeek);
                ma.setStatus(status);
                ma.setInfoText("已提交");
                ma.setInfoCode(200);
            } else if (status == 0) {
                ma.setInfoCode(500);
                ma.setInfoText("服务器错误");
            } else {
                ma.setWeekNum(thisWeek);
                ma.setStatus(status);
                ma.setInfoText("已请假");
                ma.setInfoCode(200);
            }
        }
        Version lateVersion = versionMapper.getLateVersion();
        ma.setVersion(lateVersion);
        return ma;
    }
}
