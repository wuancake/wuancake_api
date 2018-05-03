package com.wuan.weekly.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.wuan.weekly.entity.maggic.SaveReport;

@Mapper
public interface WeeklyDao {


	@Insert("insert into report(week_num,user_id,group_id,text,status,reply_time) values (#{weekNum},#{userId},#{groupId},#{text},#{status},#{replyTime})")
	public void reportWeekly(SaveReport report);

	@Select("select * from report where user_id = #{userId} and week_num = #{weekNum}")
	public SaveReport getReportByWeekNum(@Param("weekNum")int weekNum,@Param("userId")int userId);

}
