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
    @Select("SELECT * FROM user WHERE email = #{email} AND password = #{password}")
    User findUserByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    @Select("SELECT * FROM user WHERE id = #{user_id}")
    User findUserByUserId(@Param("user_id") Integer user_id);

    @Insert("INSERT INTO user(user_name,email,wuan_name,password,QQ,auth,deleteFlg,create_time,modify_time) VALUES(#{user_name},#{email},#{wuan_name},#{password},#{QQ},#{auth},#{deleteFlg},#{create_time},#{modify_time})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = true, statementType = StatementType.STATEMENT, resultType = Integer.class)
    void saveUser(@Param("user_name") String user_name, @Param("email") String email, @Param("wuan_name") String wuan_name, @Param("password") String password, @Param("QQ") Integer QQ, @Param("auth") Integer auth, @Param("deleteFlg") Integer deleteFlg, @Param("create_time") Date create_time, @Param("modify_time") Date modify_time);

    @Select("SELECT * FROM user WHERE user_name = #{user_name} OR email = #{email}")
    User findUserByUsernameAndEmail(@Param("user_name") String user_name, @Param("email") String email);

}
