package com.wuan.weekly.controller;

import com.wuan.weekly.entity.ShiXiaoXingEntity;
import com.wuan.weekly.entity.User;
import com.wuan.weekly.mapper.WaGroupMapper;
import com.wuan.weekly.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ericheel
 * 安卓特供(不懂别问,我也不懂
 * 2018-10-6 15:51:23
 */
@RestController
public class ShiXiaoXingController {

    @Autowired
    private IUserService userService;

    @Autowired
    private WaGroupMapper waGroupMapper;

    /**
     * 获取一点东西
     *
     * @return
     */
    @RequestMapping("/getBlablablaByUserId")
    public @ResponseBody
    ShiXiaoXingEntity getBlablablaByUserId(@RequestBody User auser) {
        Integer userId = auser.getId();
        try {
            User user = userService.findUserByUserId(userId);

            Integer userGroupId = userService.findUserGroupByUserId(userId);
            String groupName = waGroupMapper.getGroupNameByGroupId(userGroupId);

            return new ShiXiaoXingEntity(user, groupName, "200", "嘤"+userId+"~~~");
        } catch (Exception e) {
            return new ShiXiaoXingEntity(null, null, "500", "诗道:[情不知所起,一往情深,再而衰,三而竭,所以还是没有找到前端想要的信息.略略略" + userId + "~~~]" + "又有诗云:[" + e.getCause() + "---" + e.getMessage() + "]");
        }

    }


}
