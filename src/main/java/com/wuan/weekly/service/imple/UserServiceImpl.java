package com.wuan.weekly.service.imple;

import com.wuan.weekly.entity.User;
import com.wuan.weekly.entity.UserGroup;
import com.wuan.weekly.entity.WaGroup;
import com.wuan.weekly.mapper.AttendMapper;
import com.wuan.weekly.mapper.UserGroupMapper;
import com.wuan.weekly.mapper.UserMapper;
import com.wuan.weekly.mapper.WaGroupMapper;
import com.wuan.weekly.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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

    @Autowired
    private WaGroupMapper waGroupMapper;

    @Override
    public List<WaGroup> findAllGroupInfo() {
        return waGroupMapper.findAllGroupInfo();
    }

    @Override
    public void saveUser(User user) throws Exception {
        User userByUsernameAndEmail = userMapper.findUserByUsernameAndEmail(user.getUserName(), user.getEmail());
        if (userByUsernameAndEmail == null) {
            userMapper.saveUser(user.getUserName(), user.getEmail(), user.getWuanName(), user.getPassword(), user.getQQ(), user.getAuth(), user.getDeleteFlg(), user.getCreateTime(), user.getModifyTime());
        } else {
            throw new Exception();
        }
        //用户注册成功,增加对应的user_group表
        UserGroup userGroup = new UserGroup();
        userGroup.setUserId(userMapper.findUserByEmailAndPassword(user.getEmail(), user.getPassword()).getId());
        userGroup.setGroupId(0);
        userGroup.setCreateTime(user.getCreateTime());
        userGroup.setModifyTime(user.getModifyTime());
        userGroup.setDeleteFlg(0);
        userGroupMapper.selectGroup(userGroup.getUserId(), userGroup.getGroupId(), userGroup.getDeleteFlg(), userGroup.getCreateTime(), userGroup.getModifyTime());
        //对应的attend表
        attendMapper.forUserAndGroup(userGroup.getUserId(), userGroup.getGroupId(), 1);
    }


    @Override
    public User findUserByUserId(Integer userId) {
        return userMapper.findUserByUserId(userId);
    }

    @Override
    public void selectGroup(User user, Integer groupId) {
        userGroupMapper.updateGroup(user.getId(), groupId);

        attendMapper.updateAttend(groupId, 1, user.getId());
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
