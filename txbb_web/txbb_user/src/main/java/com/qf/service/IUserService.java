package com.qf.service;

import com.qf.entity.User;

public interface IUserService {

    int registerUser(User user);

    User login(User user);

    int updateImg(String img, String imgCrm, Integer uid);

    User queryByUserName(String username);

    User queryByUid(Integer uid);
}
