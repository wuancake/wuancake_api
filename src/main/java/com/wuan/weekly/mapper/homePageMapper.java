package com.wuan.weekly.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.wuan.weekly.entity.Leave;

@Mapper
public interface homePageMapper {

	/**
	 * 请假
	 */
    @Insert(
            "inset into report(week_num,user_id,group_id,status,text)"
                    + "values (leaveNum,userId,groupId,status,reason)")
    public void Main(Leave leave);

    @Select(
            "select * from report where user_id = #{userId} "
                    + "and status = #{status}")
    public Leave leave(
            @Param("userId") int user_id,
            @Param("status") int status);

    @Select("select status from report where user_id = #{userId} and week_num = #{thisWeek}")
    public Integer selectStatus(@Param("userId") int userId, @Param("thisWeek") int thisWeek);

//    @Insert("insert into report (week_num,user_id,group_id,status,text,reply_time) values (#{leave.weekNum},#{leave.userId},#{leave.groupId},#{leave.status},#{leave.reason},#{date})")
//    public void leaveWeekly(@Param("leave") Leave leave, @Param("date") Date date);

    //@Delete("delete from report where user_id = #{userId} and group_id = #{groupId} and week_num >= #{weekNum}")
    //public void cancelLeave(@Param("userId") int userId, @Param("groupId") int groupId, @Param("weekNum") int weekNum);

    /**
     * 删除周报
     */
    @Delete("delete from report where user_id = #{userId}  and week_num >= #{weekNum}")
    public boolean cancelLeave(@Param("userId") int userId, @Param("weekNum") int weekNum);

    /**
     * 插入请假周报
     */
	@Insert({ "<script>", "insert into report(week_num,user_id,group_id,status,text,reply_time) values ",
			"<foreach collection='leaves' item='item' index='index' separator=','>",
			"(#{item.weekNum}, #{item.userId}, #{item.groupId},#{item.status},#{item.reason},#{date})", "</foreach>",
			"</script>" })
	public boolean insertLeaves(@Param("leaves") List<Leave> leaves, @Param("date") Date date);
    
}
