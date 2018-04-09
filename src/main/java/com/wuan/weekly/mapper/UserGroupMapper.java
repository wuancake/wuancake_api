package com.wuan.weekly.mapper;

import com.wuan.weekly.entity.UserGroup;
import org.apache.ibatis.annotations.*;

import java.util.Date;

/**
 * @author Nobody
 * Date: 2018/3/24
 * Time: 0:20
 */
@Mapper
public interface UserGroupMapper {
    @Insert("INSERT INTO user_group(user_id,group_id,deleteFlg,create_time,modify_time) values(#{user_id},#{group_id},#{deleteFlg},#{create_time},#{modify_time})")
    void selectGroup(@Param("user_id") Integer user_id, @Param("group_id") Integer group_id, @Param("deleteFlg") Integer deleteFlg, @Param("create_time") Date create_time, @Param("modify_time") Date modify_time);

    @Select("SELECT group_id from user_group WHERE user_id = #{user_id}")
    Integer findUserGroupByUserId(@Param("user_id") Integer user_id);

    @Update("update user_group set group_id = #{group_id} where user_id = #{user_id}")
    void updateGroup(@Param("user_id") Integer user_id,@Param("group_id") Integer group_id);
}
