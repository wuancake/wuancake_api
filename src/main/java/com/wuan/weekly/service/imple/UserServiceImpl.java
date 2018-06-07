package com.wuan.weekly.service.imple;

import com.wuan.weekly.entity.User;
import com.wuan.weekly.entity.UserGroup;
import com.wuan.weekly.entity.WaGroup;
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
    private static final String BLANK = " ";
    private static final String EMAIL_CHECK = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
    private static final String QQ_CHECK = "[1-9]([0-9]{5,11})";
    private static final String USERNAME_CHECK = "^[A-Za-z0-9\\u4e00-\\u9fa5]+$";

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserGroupMapper userGroupMapper;

    @Autowired
    private WaGroupMapper waGroupMapper;

    @Override
    public List<WaGroup> findAllGroupInfo() {
        return waGroupMapper.findAllGroupInfo();
    }

    @Override
    public User findUserByEmail(String email) {
        return userMapper.findUserByEmail(email);
    }

    @Override
    public void saveUser(User user) throws Exception {
        /*
        增强接口健壮性
         */
        String userName = user.getUserName();
        String email = user.getEmail();
        String password = user.getPassword();
        String qq = user.getQQ();
        //后端给予的值，暂不加判断
        Integer auth = user.getAuth();
        Integer deleteFlg = user.getDeleteFlg();
        Date createTime = user.getCreateTime();
        Date modifyTime = user.getModifyTime();

        if (userName == null || email == null || password == null || qq == null) {
            throw new Exception("所需的字段名不正确或不存在");
        }
        if (userName.contains(BLANK) || password.contains(BLANK) || email.contains(BLANK) || qq.contains(BLANK)) {
            throw new Exception("所需字段值不能包含空格");
        }
        if ("".equals(userName) || "".equals(email) || "".equals(password) || "".equals(qq)) {
            throw new Exception("所需值不能为空");
        }
        if (!qq.matches(QQ_CHECK)) {
            throw new Exception("QQ号格式错误");
        }
        if (!email.matches(EMAIL_CHECK)) {
            throw new Exception("邮箱格式错误");
        }
        if (!userName.matches(USERNAME_CHECK)) {
            throw new Exception("用户名格式错误");
        }
        /*
        -------------------------------------------------------------------------------------------------
         */
        User userByUsernameAndEmail = userMapper.findUserByUsernameAndEmailAndQQ(userName, email, qq);
        if (userByUsernameAndEmail == null) {
            userMapper.saveUser(userName, email, user.getWuanName(), password, qq, auth, deleteFlg, createTime, modifyTime);

        } else {
            throw new Exception("用户名或邮箱已存在");
        }
        //用户注册成功,增加对应的user_group表
        UserGroup userGroup = new UserGroup();
        userGroup.setUserId(userMapper.findUserByEmail(email).getId());
        //注册后就是没有选择分组的0
        userGroup.setGroupId(0);
        userGroup.setCreateTime(createTime);
        userGroup.setModifyTime(modifyTime);
        //没有被踢
        userGroup.setDeleteFlg(0);
        userGroupMapper.selectGroup(userGroup.getUserId(), userGroup.getGroupId(), userGroup.getDeleteFlg(), userGroup.getCreateTime(), userGroup.getModifyTime());
        //对应的attend表
        //attendMapper.forUserAndGroup(userGroup.getUserId(), userGroup.getGroupId(), 2);
    }


    @Override
    public User findUserByUserId(Integer userId) {
        return userMapper.findUserByUserId(userId);
    }

    @Override
    public void selectGroup(User user, Integer groupId) throws Exception {

        if (groupId == null) {
            throw new Exception("分组名不能为Null");
        }
        if (groupId <= 0 || groupId > waGroupMapper.getMaxGroupId()) {
            throw new Exception("分组ID超过限定值");
        }

        userGroupMapper.updateGroup(user.getId(), groupId);

        // attendMapper.updateAttend(groupId, 1, user.getId());
    }

    @Override
    public Integer findUserGroupByUserId(Integer id) {
        return userGroupMapper.findUserGroupByUserId(id);
    }

}
