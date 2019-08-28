package com.qf.service;

import com.qf.entity.FriendsRequest;

import java.util.List;

public interface IRequestService {

    int addRequest(FriendsRequest friendsRequest);

    List<FriendsRequest> queryRequestByUid(Integer uid);

    int updateRequestStatus(Integer rid, Integer status);
}
