package com.qf.service;

public interface IFriendsService {

    int insertFriends(Integer uid, Integer fid);

    boolean isFriends(Integer uid, Integer fid);
}
