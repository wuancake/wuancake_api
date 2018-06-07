package com.wuan.weekly.mapper;

import com.wuan.weekly.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.Date;

/**
 * @author Nobody
 * Date: 2018/3/23
 * Time: 22:14
 */
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE id = #{userId}")
    User findUserByUserId(@Param("userId") Integer userId);

    @Insert("INSERT INTO user " +
            "(user_name,email,wuan_name,password,QQ,auth,deleteFlg,create_time,modify_time) " +
            "VALUES (#{userName},#{email},#{wuanName},#{password},#{QQ},#{auth},#{deleteFlg},#{createTime},#{modifyTime})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = true, statementType = StatementType.STATEMENT, resultType = Integer.class)
    void saveUser(@Param("userName") String userName, @Param("email") String email, @Param("wuanName") String wuanName, @Param("password") String password, @Param("QQ") String QQ, @Param("auth") Integer auth, @Param("deleteFlg") Integer deleteFlg, @Param("createTime") Date createTime, @Param("modifyTime") Date modifyTime);

    @Select("SELECT * FROM user WHERE user_name = #{userName} OR email = #{email} OR QQ = #{qq} limit 1")
    User findUserByUsernameAndEmailAndQQ(@Param("userName") String userName, @Param("email") String email, @Param("qq") String qq);

    @Select("select * from user where email = #{email}")
    User findUserByEmail(@Param("email") String email);
}
