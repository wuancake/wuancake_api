package com.wuan.weekly.controller;

import com.wuan.weekly.entity.EffectiveEntity;
import com.wuan.weekly.entity.User;
import com.wuan.weekly.mapper.WaGroupMapper;
import com.wuan.weekly.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ericheel
 * 安卓所需的 保证数据时效性的接口
 * 2018-10-6 15:51:23
 */
@RestController
public class EffectiveController {

    @Autowired
    private IUserService userService;

    @Autowired
    private WaGroupMapper waGroupMapper;

    /**
     * 获取时效性数据
     *
     * @return
     */
    @RequestMapping("/getBlablablaByUserId")
    public @ResponseBody
    EffectiveEntity getBlablablaByUserId(@RequestBody User auser) {
        Integer userId = auser.getId();
        try {
            User user = userService.findUserByUserId(userId);

            Integer userGroupId = userService.findUserGroupByUserId(userId);
            String groupName = waGroupMapper.getGroupNameByGroupId(userGroupId);

            return new EffectiveEntity(user, groupName, "200", "嘤"+userId+"~~~");
        } catch (Exception e) {
            return new EffectiveEntity(null, null, "500", "错误!!请联系管理员");
        }

    }


}
