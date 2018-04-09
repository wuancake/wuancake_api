package com.wuan.weekly.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author Nobody
 * Date: 2018/3/24
 * Time: 9:34
 */
@Mapper
public interface AttendMapper {
    @Insert("INSERT INTO attend VALUES(#{user_id},#{group_id},#{status})")
    void forUserAndGroup(@Param("user_id") Integer user_id, @Param("group_id") Integer group_id, @Param("status") String status);

    @Update("update attend set group_id = #{group_id},status = #{status} where user_id = #{user_id}")
    void updateAttend( @Param("group_id") Integer group_id,@Param("status") String status,@Param("user_id") Integer user_id);
}
