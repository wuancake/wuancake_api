package com.wuan.weekly.mapper;

import org.apache.ibatis.annotations.*;

import java.util.Date;

/**
 * @author Nobody
 * Date: 2018/3/24
 * Time: 0:20
 */
@Mapper
public interface UserGroupMapper {
    @Insert("INSERT INTO user_group(user_id,group_id,deleteFlg,create_time,modify_time) values(#{userId},#{groupId},#{deleteFlg},#{createTime},#{modifyTime})")
    void selectGroup(@Param("userId") Integer userId, @Param("groupId") Integer groupId, @Param("deleteFlg") Integer deleteFlg, @Param("createTime") Date createTime, @Param("modifyTime") Date modifyTime);

    @Select("SELECT group_id from user_group WHERE user_id = #{userId} limit 1")
    Integer findUserGroupByUserId(@Param("userId") Integer userId);

    @Update("update user_group set group_id = #{groupId} where user_id = #{userId}")
    void updateGroup(@Param("userId") Integer userId, @Param("groupId") Integer groupId);

}
