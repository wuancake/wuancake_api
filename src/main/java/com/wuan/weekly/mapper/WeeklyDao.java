package com.wuan.weekly.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.wuan.weekly.entity.User;
import com.wuan.weekly.entity.maggic.Report;

@Mapper
public interface WeeklyDao {


    @Insert("insert into report(week_num,user_id,group_id,text,status,reply_time) values (#{weekNum},#{userId},#{groupId},#{text},#{status},#{replyTime})")
    public void reportWeekly(Report saveReport);

    @Select("select * from report where user_id = #{userId} and group_id = #{groupId}  limit #{startReport},#{reportNum}")
    public List<Report> getReportByWeekNum(@Param("userId") int userId, @Param("groupId") int groupId, @Param("startReport") int startReport, @Param("reportNum") int reportNum);

    @Select("select count(*) from report where user_id = #{userId} and group_id = #{groupId}")
    public int getCountOfReport(@Param("userId") int userId, @Param("groupId") int groupId);

    @Select("SELECT * FROM user_group WHERE user_id = #{userId} and group_id = #{groupId} ")
    public User findUserByUserId(@Param("userId") int userId, @Param("groupId") int groupId);

    @Select("select distinct status " +
            "from report " +
            "where user_id = #{userId} " +
            "and week_num = #{weekNum} ")
    public Integer findStatusByUserIdAndMaxWeekNum(@Param("userId") Integer userId, @Param("weekNum") Integer weekNum);
}

