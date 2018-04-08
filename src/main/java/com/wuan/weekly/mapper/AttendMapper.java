package com.wuan.weekly.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Nobody
 * Date: 2018/3/24
 * Time: 9:34
 */
@Mapper
public interface AttendMapper {
    @Insert("INSERT INTO attend VALUES(#{user_id},#{group_id},#{status})")
    void forUserAndGroup(@Param("user_id") Integer user_id, @Param("group_id") Integer goup_id, @Param("status") String status);
}
