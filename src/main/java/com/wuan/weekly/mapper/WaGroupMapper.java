package com.wuan.weekly.mapper;

import com.wuan.weekly.entity.WaGroup;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface WaGroupMapper {

    @Select("SELECT * FROM wa_group")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "groupName", column = "group_name"),
            @Result(property = "deleteFlg", column = "deleteFlg"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "modifyTime", column = "modify_time")
    })
    List<WaGroup> findAllGroupInfo();

    @Select("SELECT MAX(id) " +
            "from wa_group")
    Integer getMaxGroupId();

    @Select("select group_name " +
            "from wa_group " +
            "where id = #{groupId}")
    String getGroupNameByGroupId(@Param("groupId") Integer groupId);
}
