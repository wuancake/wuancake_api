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

import com.wuan.weekly.entity.Main;
import com.wuan.weekly.entity.maggic.Msg;
import com.wuan.weekly.exception.ParamFormatException;
import com.wuan.weekly.service.homePageService;
import com.wuan.weekly.util.Utils;

@RestController
public class homePageController {

    @Autowired
    private homePageService hps;

//    @SuppressWarnings("unused")
//	@Autowired
//    private IUserService iUserService;

   /**
    * 我的主页
    */
	@ResponseBody
	@RequestMapping(value = "/main", method = RequestMethod.POST)
	public Object home(@RequestBody Map<String, Object> map) {
		// 用户ID
		int userId = -1;
		
		// 获得数据
		try {
			userId = (int) map.get("userId");
		} catch (NumberFormatException e) {
			Msg msg = new Msg("参数错误！", 500);
			return msg;
		}

		// 检查参数合法性
		if(userId < 0) {
			Msg msg = new Msg("用户id不正确！", 500);
			return msg;		
		}
		
		// 传到业务层处理
		try
		{
			Main myInfo = hps.GetMyInfo(userId);
			return myInfo;
		}
		catch(Exception e)
		{
			Msg msg = new Msg("查看周报失败！",500);
			return msg;
		}
	}
	
	/**
	 * 请假
	 */
	@ResponseBody
	@RequestMapping(value = "leave", method = RequestMethod.POST)
	public Object leave(@RequestBody Map<String, Object> param) {
		// 用户id
		int userId = -1;
		// 请假周数
		int offNum = -1;
		// 理由
		String reason = null;
		// 分组id
		int groupId = -1;

		// 获取数据
		try {
			userId = (int) param.get("userId");
			groupId = (int) param.get("groupId");
			offNum = (int) param.get("weekNum");
			reason = param.get("reason").toString();
		} catch (NumberFormatException e) {
			Msg msg = new Msg("参数错误！", 500);
			return msg;
		} catch (ClassCastException e) {
			Msg msg = new Msg("参数错误！", 500);
			return msg;
		}

		// 检查参数合法性
		if (userId < 0) {
			Msg msg = new Msg("用户id不正确！", 500);
			return msg;
		}
		if (groupId < 0) {
			Msg msg = new Msg("分组id不正确！", 500);
			return msg;
		}
		if (offNum <= 0 || offNum > 3) {
			Msg msg = new Msg("请假周数不正确！", 500);
			return msg;
		}
		if (null == reason || "".equals(reason)) {
			Msg msg = new Msg("请假理由不能为空！", 500);
			return msg;
		}

		// 传给服务层
		Boolean result = false;
		try {
			result = hps.Leave(userId, groupId, offNum, reason);
		} catch (Exception e) {
			Msg msg = new Msg("请假失败！", 500);
			return msg;
		}

		// 周报提交成功
		if (result) {
			Msg msg = new Msg("请假成功！", 200);
			return msg;
		}
		// 周报提交失败
		else {
			Msg msg = new Msg("请假失败！", 500);
			return msg;
		}
	}
	
	/**
	 * 取消请假
	 */
	@ResponseBody
	@RequestMapping(value = "/cancelLeave", method = RequestMethod.POST)
	public Object cancelLeave(@RequestBody Map<String, Object> param) throws ParamFormatException {
		// 用户Id
		int userId = -1;
		// 当前周数
		int currentWeek = -1;

		try {
			userId = (int) param.get("userId");
			// 当前周数
			currentWeek = (int) (((Calendar.getInstance(Locale.CHINA).getTime()).getTime() - Utils.FIRSTDAY.getTime())
					/ (7 * 24 * 60 * 60 * 1000));
		} catch (NumberFormatException e) {
			Msg msg = new Msg("参数错误！", 500);
			return msg;
		} catch (ClassCastException e) {
			Msg msg = new Msg("参数错误！", 500);
			return msg;
		}

		if (userId < 0) {
			Msg msg = new Msg("用户id不正确！", 500);
			return msg;
		}

		boolean Result = false;
		try {
			Result = hps.cancelLeave(userId, currentWeek);
		} catch (Exception e) {
			Msg msg = new Msg("取消请假失败！", 500);
			return msg;
		}

		if (Result) {
			Msg msg = new Msg("取消请假成功！", 500);
			return msg;
		} else {
			Msg msg = new Msg("取消请假失败！", 500);
			return msg;
		}
	}

}
