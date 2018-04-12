package com.wuan.weekly.service.imple;

import com.wuan.weekly.entity.User;
import com.wuan.weekly.entity.UserGroup;
import com.wuan.weekly.mapper.AttendMapper;
import com.wuan.weekly.mapper.UserGroupMapper;
import com.wuan.weekly.mapper.UserMapper;
import com.wuan.weekly.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author Nobody
 * Date: 2018/3/23
 * Time: 22:47
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserGroupMapper userGroupMapper;

    @Autowired
    private AttendMapper attendMapper;

    @Override
    public void saveUser(User user) throws Exception {
        User userByUsernameAndEmail = userMapper.findUserByUsernameAndEmail(user.getUser_name(), user.getEmail());
        if (userByUsernameAndEmail == null) {
            userMapper.saveUser(user.getUser_name(), user.getEmail(), user.getWuan_name(), user.getPassword(), user.getQQ(), user.getAuth(), user.getDeleteFlg(), user.getCreate_time(), user.getModify_time());
        } else {
            throw new Exception();
        }
        //用户注册成功,增加对应的user_group表
        UserGroup userGroup = new UserGroup();
        userGroup.setUser_id(userMapper.findUserByEmailAndPassword(user.getEmail(), user.getPassword()).getId());
        userGroup.setGroup_id(0);//默认.JavaBean中也默认为0
        userGroup.setCreate_time(user.getCreate_time());
        userGroup.setModify_time(user.getModify_time());
        userGroup.setDeleteFlg(0);//默认
        userGroupMapper.selectGroup(userGroup.getUser_id(), userGroup.getGroup_id(), userGroup.getDeleteFlg(), userGroup.getCreate_time(), userGroup.getModify_time());
        //对应的attend表
        attendMapper.forUserAndGroup(userGroup.getUser_id(), userGroup.getGroup_id(), "1");
    }


    @Override
    public User findUserByUserId(Integer user_id) {
        return userMapper.findUserByUserId(user_id);
    }

    @Override
    public void selectGroup(User user, Integer group_id) {
        userGroupMapper.updateGroup(user.getId(), group_id);

        attendMapper.updateAttend(group_id, "1", user.getId());
    }

    @Override
    public User findUserByEmailAndPassword(String email, String password) {
        return userMapper.findUserByEmailAndPassword(email, password);
    }

    @Override
    public Integer findUserGroupByUserId(Integer id) {
        return userGroupMapper.findUserGroupByUserId(id);
    }

}
