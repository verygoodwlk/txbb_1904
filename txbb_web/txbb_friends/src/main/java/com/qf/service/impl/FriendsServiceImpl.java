package com.qf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qf.dao.FriendsMapper;
import com.qf.entity.Friends;
import com.qf.entity.User;
import com.qf.service.IFriendsService;
import com.qf.serviceapi.UserServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @version 1.0
 * @user ken
 * @date 2019/8/28 15:02
 */
@Service
public class FriendsServiceImpl implements IFriendsService {

    @Autowired
    private FriendsMapper friendsMapper;

    @Autowired
    private UserServiceApi userServiceApi;

    @Override
    @Transactional
    public int insertFriends(Integer uid, Integer fid) {

        if(!isFriends(uid, fid)){
            Friends friends1 = new Friends(uid, fid, new Date(), 0, "", null);
            friendsMapper.insert(friends1);
        }

        if(!isFriends(fid, uid)){
            Friends friends2 = new Friends(fid, uid, new Date(), 0, "", null);
            friendsMapper.insert(friends2);
        }
        return 1;
    }

    @Override
    public boolean isFriends(Integer uid, Integer fid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("uid", uid);
        queryWrapper.eq("fid", fid);
        return friendsMapper.selectCount(queryWrapper) > 0;
    }

    @Override
    public List<Friends> queryFriendsByUid(Integer uid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("uid", uid);
        List<Friends> list = friendsMapper.selectList(queryWrapper);

        for (Friends friend : list) {
            User friendUser = userServiceApi.queryUserByUid(friend.getFid());
            friendUser.setPassword(null);
            friend.setFriendUser(friendUser);
        }
        
        return list;
    }
}
