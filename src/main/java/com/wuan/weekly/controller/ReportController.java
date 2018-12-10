package com.wuan.weekly.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wuan.weekly.entity.maggic.Msg;
import com.wuan.weekly.entity.maggic.Reports;
import com.wuan.weekly.service.imple.WeeklyServiceImple;

@RestController
public class ReportController {

	@Autowired
	private WeeklyServiceImple weeklyServiceImple;

	/**
	 * 查看周报
	 */
	@ResponseBody
	@RequestMapping(value = "myweekly", method = RequestMethod.POST)
	public Object getWeekly(@RequestBody Map<String, Object> param) {
		// 用户ID
		int userId = -1;
		// 分组ID
		int groupId = -1;
		// 当前第几页
		int pageNum = -1;
		// 一页有几条周报记录
		int weekNum = -1;

		// 获得数据
		try {
			if (null != param.get("userId")) {
				userId = (int) param.get("userId");
			}
			if (null != param.get("groupId")) {
				groupId = (int) param.get("groupId");
			}
			if (null != param.get("pageNum")) {
				pageNum = (int) param.get("pageNum");
			}
			if (null != param.get("weekNum")) {
				weekNum = (int) param.get("weekNum");
			}
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
		if (pageNum < 0) {
			Msg msg = new Msg("请求页数不正确！", 500);
			return msg;
		}
		if (weekNum < 0) {
			Msg msg = new Msg("请求每页的周报数不正确！", 500);
			return msg;
		}

		try {
			Reports report = weeklyServiceImple.getWeekly(userId, groupId, pageNum, weekNum);
			return report;
		} catch (Exception e) {
			Msg msg = new Msg("查看周报失败！", 500);
			return msg;
		}

	}

	/**
	 * 提交周报
	 */
	@ResponseBody
	@RequestMapping(value = "submit", method = RequestMethod.POST)
	public Object reportWeekly(@RequestBody Map<String, Object> param) {
		// 用户ID
		int userId = -1;
		// 分组ID
		int groupId = -1;
		// 本周完成的任务
		String complete = null;
		// 本周遇到的困难
		String trouble = null;
		// 下周的计划
		String plan = null;
		// 作品链接（选填）
		String url = null;

		// 获得数据
		try {
			if (null != param.get("userId")) {
				userId = (int) param.get("userId");
			}
			if (null != param.get("groupId")) {
				groupId = (int) param.get("groupId");
			}
			if (null != param.get("complete")) {
				complete = (String) param.get("complete");
			}
			if (null != param.get("trouble")) {
				trouble = (String) param.get("trouble");
			}
			if (null != param.get("plane")) {
				plan = (String) param.get("plane");
			}
			if (null != param.get("url")) {
				url = (String) param.get("url");
			}
		} catch (NumberFormatException e) {
			Msg msg = new Msg("参数错误！",500);
			return msg;
		} catch (ClassCastException e) {
			Msg msg = new Msg("参数错误！",500);
			return msg;
		}

		// 检查参数合法性
		if (userId < 0) {
			Msg msg = new Msg("用户id不正确！",500);
			return msg;
		}
		if (groupId < 0) {
			Msg msg = new Msg("分组id不正确！",500);
			return msg;
		}
		if (null == complete || "".equals(complete)) {
			Msg msg = new Msg("本周完成的任务不能为空！",500);
			return msg;
		}
		if (null == trouble || "".equals(trouble)) {
			Msg msg = new Msg("本周所遇困难不能为空！",500);
			return msg;
		}
		if (null == plan || "".equals(plan)) {
			Msg msg = new Msg("下周计划不能为空！",500);
			return msg;
		}
		if (null == url) {
			Msg msg = new Msg("url参数错误！不能为null!",500);
			return msg;
		}

		Boolean result = false;
		try
		{
		// 传给服务层
		result = weeklyServiceImple.reportWeekly(userId, groupId, complete, trouble, plan, url);		
		}
		catch(Exception e)
		{
			Msg msg = new Msg("提交周报失败！",500);
			return msg;		
		}
		// 周报提交成功
		if (result) {
			Msg msg = new Msg("提交周报成功！",200);
			return msg;
		}
		// 周报提交失败
		else {
			Msg msg = new Msg("提交周报失败！",500);
			return msg;
		}
	}

}
