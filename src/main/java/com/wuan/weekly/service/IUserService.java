package com.wuan.weekly.service;

import com.wuan.weekly.entity.User;
import com.wuan.weekly.entity.WaGroup;

import java.util.List;

/**
 * @author Nobody
 * Date: 2018/3/23
 * Time: 22:46
 */
public interface IUserService {
    void saveUser(User user) throws Exception;

    User findUserByUserId(Integer userId);

    void selectGroup(User user, Integer groupId) throws Exception;

    Integer findUserGroupByUserId(Integer id);

    List<WaGroup> findAllGroupInfo();

    User findUserByEmail(String email);

    int updatePasswordById(Integer userId, String password);

    String findUserNameByUserName(String userName);

    void updateUserNameById(String userName, Integer userId);

    void quitgroupById(Integer userId);


}
