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
    @Insert("INSERT INTO attend VALUES(#{userId},#{groupId},#{status})")
    void forUserAndGroup(@Param("userId") Integer userId, @Param("groupId") Integer groupId, @Param("status") Integer status);

    @Update("update attend set group_id = #{groupId},status = #{status} where user_id = #{userId}")
    void updateAttend(@Param("groupId") Integer groupId, @Param("status") Integer status, @Param("userId") Integer userId);
}
