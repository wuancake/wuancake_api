package com.wuan.weekly.service.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wuan.weekly.entity.User;
import com.wuan.weekly.entity.maggic.Report;
import com.wuan.weekly.exception.ReportFailException;
import com.wuan.weekly.mapper.WeeklyDao;
import com.wuan.weekly.service.WeeklyService;

@Service
public class WeeklyServiceImple implements WeeklyService {

    @Autowired
    private WeeklyDao weeklyDao;


    @Override
    public void reportWeekly(Report saveReport) throws ReportFailException {
        //查找用户是否存在
        User user = weeklyDao.findUserByUserId(saveReport.getUserId(), saveReport.getGroupId());
        if (user == null) {
            throw new ReportFailException("没有这个用户！");
        }
        weeklyDao.reportWeekly(saveReport);
    }

    @Override
    public Integer findStatusByUserIdAndMaxWeekNum(Integer userId, Integer maxWeekNum) {
        return weeklyDao.findStatusByUserIdAndMaxWeekNum(userId, maxWeekNum);
    }

    @Override
    public List<Report> getReportByWeekNum(int userId, int groupId, int startReport, int reportNum) {
        // TODO 自动生成的方法存根
        List<Report> report = weeklyDao.getReportByWeekNum(userId, groupId, startReport, reportNum);
        return report;
    }

    public int getCountOfReport(int userId, int groupId) {
        return weeklyDao.getCountOfReport(userId, groupId);
    }

}
