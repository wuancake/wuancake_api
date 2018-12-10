package com.wuan.weekly.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.wuan.weekly.entity.Report;
import com.wuan.weekly.entity.User;

/**
 * @author Magic
 *
 */
@Mapper
public interface WeeklyDao {
	
	/**
     * 提交周报
     */
	@Insert("insert into " +
			"report (week_num, user_id, group_id, text, status, reply_time) " +
			"values (#{week_num}, #{user_id}, #{group_id}, #{text}, #{status}, #{reply_time})")
	public Boolean SubmitReport(com.wuan.weekly.entity.Report report);
	
	/**
	 * 获得周报列表
	 * @param user_id 	用户id
	 * @param startWeek 起始周数
	 * @param num		需要周报条数
	 * @return	周报列表
	 */
	@Select("select * " +
			"from report " +
			"where user_id = #{user_id} " + 
			"limit #{start}, #{num} ")
	public List<com.wuan.weekly.entity.Report> GetReportList(@Param("user_id") int user_id, 
			@Param("start") int startWeek, @Param("num") int num);
		  
	/**
	 * 根据周数获得周报
	 * 
	 * @param userId
	 *            用户ID
	 * @param week
	 *            周数
	 * @return 周报
	 */
	@Select("select * " + 
			"from report " + 
			"where user_id = #{user_id} and " + 
			"week_num = #{week} ")
	public Report GetReport(@Param("user_id") int userId, @Param("week") int week);
		
    /**
     * 得到用户一共提交过多少条周报
     * @param userId 用户id
     * return 周报数量
     */
    @Select("select count(*) "
    		+ "from report "
    		+ "where user_id = #{userId} ")
    public int getCountOfReport(@Param("userId") int userId);

    /**
     * 通过用户id和分组id查找用户
     * @param userId 用户id
     * @param groupId 分组id
     * @return 
     */
    @Select("select * " + 
    		"from user_group " + 
    		"where user_id = #{userId} " +
    		"and group_id = #{groupId} ")
    public User findUserByUserId(@Param("userId") int userId, @Param("groupId") int groupId);

    @Select("select distinct status " +
            "from report " +
            "where user_id = #{userId} " +
            "and week_num = #{weekNum} " +
            "and group_id = #{groupId}")
    public Integer findStatusByUserIdAndMaxWeekNumAndGroupId(@Param("userId") Integer userId, @Param("weekNum") Integer weekNum,@Param("groupId") Integer groupId);

    /**
     * 获得周报状态
     */
    @Select("select distinct status " +
            "from report " +
            "where user_id = #{userId} " +
            "and week_num = #{weekNum} ")
	public Integer findStatusByUserIdAndCurrentWeek(@Param("userId")int userId, @Param("weekNum") Integer weekNum);
}

