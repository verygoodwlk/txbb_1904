package com.qf.controller;

import com.qf.entity.Friends;
import com.qf.entity.ResultData;
import com.qf.service.IFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @version 1.0
 * @user ken
 * @date 2019/8/29 10:37
 */
@RestController
@RequestMapping("/friends")
public class FriendsController {

    @Autowired
    private IFriendsService friendsService;

    /**
     * 根据用户id查询所有的用户信息
     * @param uid
     * @return
     */
    @RequestMapping("/queryFriendsByUid")
    public ResultData<List<Friends>> queryFriendsByUid(Integer uid){
        List<Friends> friends = friendsService.queryFriendsByUid(uid);
        return ResultData.createSuccResultData(friends);
    }
}
