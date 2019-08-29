package com.qf.service;

import com.qf.entity.Friends;

import java.util.List;

public interface IFriendsService {

    int insertFriends(Integer uid, Integer fid);

    boolean isFriends(Integer uid, Integer fid);

    List<Friends> queryFriendsByUid(Integer uid);
}
