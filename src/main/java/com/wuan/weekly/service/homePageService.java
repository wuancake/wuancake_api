package com.wuan.weekly.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
	 * 请假
	 * 
	 * @param userId
	 *            用户Id
	 * @param groupId
	 *            分组Id
	 * @param offNum
	 *            请假周数
	 * @param reason
	 *            请假理由
	 */
    public boolean Leave(int userId,int groupId,int offNum,String reason)
    {
    	List<Leave> leaves = CreateLeaveReports(userId,groupId,offNum,reason);
    	return mapper.insertLeaves(leaves,new Date());
    }
    
	/**
	 * 根据请假周数生成请假的周报
	 * 
	 * @param userId
	 *            用户Id
	 * @param groupId
	 *            分组Id
	 * @param offNum
	 *            请假周数
	 * @param reason
	 *            请假理由
	 */
    private List<Leave> CreateLeaveReports(int userId,int groupId,int offNum,String reason)
    {
    	List<Leave> leaves = new ArrayList<Leave>();
    	// 状态3：请假
    	int status = 3;
    	// 当前周
    	int currentWeek = (int) (((Calendar.getInstance(Locale.CHINA).getTime()).getTime() - Utils.FIRSTDAY.getTime()) / (7 * 24 * 60 * 60 * 1000));
    	for(int i = 0; i < offNum; i++)
    	{
    		Leave leave = new Leave(currentWeek, userId, groupId, status, reason);
    		leaves.add(leave);
    		currentWeek++;
    	}
    	return leaves;
    }
    
	/**
	 * 获得用户主页信息
	 * 
	 * @param userId
	 *            用户Id
	 */
	public Main GetMyInfo(int userId) {
		// 当前周
		Integer thisWeek = Utils.getMaxWeekNum();
		// 周报状态
		Integer status = weeklyDao.findStatusByUserIdAndCurrentWeek(userId, thisWeek);

		if (status == null) {
			status = 1;
		}

		// 考勤app版本及更新信息
		Version lateVersion = null;
		try {
			lateVersion = versionMapper.getLateVersion();
		} catch (Exception e) {
			// 获取版本更新出错
		}
		Main myInfo = new Main(thisWeek, status, lateVersion);
		myInfo.setInfoCode(200);
		switch (status) {
		case 1:
			myInfo.setInfoText("未提交");
			break;
		case 2:
			myInfo.setInfoText("已提交");
			break;
		case 3:
			myInfo.setInfoText("已请假");
			break;
		}
		return myInfo;
	}
    
    /**
     * 取消请假
     */
    public boolean cancelLeave(int userId, int thisWeek) {
        return mapper.cancelLeave(userId, thisWeek);
    }
}
