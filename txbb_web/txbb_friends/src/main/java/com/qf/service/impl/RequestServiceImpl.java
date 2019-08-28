package com.qf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qf.dao.RequestMapper;
import com.qf.entity.FriendsRequest;
import com.qf.entity.User;
import com.qf.service.IFriendsService;
import com.qf.service.IRequestService;
import com.qf.serviceapi.UserServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @version 1.0
 * @user ken
 * @date 2019/8/28 11:45
 */
@Service
public class RequestServiceImpl implements IRequestService {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private UserServiceApi userServiceApi;

    @Autowired
    private IFriendsService friendsService;

    /**
     * 添加好友申请
     * @param friendsRequest
     * @return
     */
    @Override
    public int addRequest(FriendsRequest friendsRequest) {

        //查询是否已经添加过申请 - 0 - 1
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("fromid", friendsRequest.getFromid());
        queryWrapper.eq("toid", friendsRequest.getToid());
        FriendsRequest request = requestMapper.selectOne(queryWrapper);

        if(request == null || request.getStatus() == 2){
            //添加申请
            return requestMapper.insert(friendsRequest);
        } else if(request.getStatus() == 0){
            //已经发送过申请
            return 2;
        } else if(request.getStatus() == 1){
            //已经成功为好友
            return 3;
        }
        return 0;
    }

    /**
     * 根据uid查询所有接收到的好友申请
     * @param uid
     * @return
     */
    @Override
    public List<FriendsRequest> queryRequestByUid(Integer uid) {

        //查询当前的所有好友申请
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("toid", uid);
        queryWrapper.orderByDesc("create_time");
        List<FriendsRequest> friendsRequestsList = requestMapper.selectList(queryWrapper);

        for (FriendsRequest friendsRequest : friendsRequestsList) {
            //查询申请发送者的信息
            User fromUser = userServiceApi.queryUserByUid(friendsRequest.getFromid());
            friendsRequest.setFromUser(fromUser);
        }

        return friendsRequestsList;
    }

    /**
     * 处理好友请求
     * @param rid
     * @param status
     * @return
     */
    @Override
    @Transactional
    public int updateRequestStatus(Integer rid, Integer status) {
        FriendsRequest friendsRequest = requestMapper.selectById(rid);
        friendsRequest.setStatus(status);

        if(status == 1){
            //添加好友关系
            friendsService.insertFriends(friendsRequest.getFromid(), friendsRequest.getToid());
        }

        return requestMapper.updateById(friendsRequest);
    }
}
