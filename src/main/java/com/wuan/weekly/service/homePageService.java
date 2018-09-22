package com.wuan.weekly.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.wuan.weekly.entity.Version;
import com.wuan.weekly.mapper.VersionMapper;
import com.wuan.weekly.mapper.WeeklyDao;
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

    @Autowired
    private WeeklyDao weeklyDao;

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

    public Main m(int user_id, int groupId) {
        //当前周数
        Integer maxWeekNum = Utils.getMaxWeekNum();

        Integer status = weeklyDao.findStatusByUserIdAndMaxWeekNumAndGroupId(user_id, maxWeekNum, groupId);

        if (null == status) {
            status = 1;
        }

        Main ma = new Main();
        ma.setWeekNum(maxWeekNum);
        ma.setStatus(status);
        ma.setInfoCode(200);
        switch (status) {
            case 1:
                ma.setInfoText("未提交");
                break;
            case 2:
                ma.setInfoText("已提交");
                break;
            case 3:
                ma.setInfoText("已请假");
                break;
            default:
        }
        Version lateVersion = versionMapper.getLateVersion();
        ma.setVersion(lateVersion);
        return ma;
    }
}
