package com.qf.controller;

import com.qf.entity.FriendsRequest;
import com.qf.entity.ResultCode;
import com.qf.entity.ResultData;
import com.qf.service.IRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @version 1.0
 * @user ken
 * @date 2019/8/28 11:35
 */
@RestController
@RequestMapping("/request")
public class RequestController {

    @Autowired
    private IRequestService requestService;

    /**
     * 添加好友的申请
     * @return
     */
    @RequestMapping("/addFriendRequest")
    public ResultData addFriendRequest(FriendsRequest friendsRequest){

        Integer result = requestService.addRequest(friendsRequest);

        if(result == 1){
            //成功
            return ResultData.createSuccResultData(true);
        } else if(result == 2){
            //发送过申请
            return ResultData.createFailResultData(ResultCode.HAVE_SEND, "已经发送过申请！");
        } else if(result == 3){
            //已经是好友
            return ResultData.createFailResultData(ResultCode.HAVE_FRIEND, "已经是好友关系！");
        }

        return ResultData.createFailResultData(ResultCode.SERVER_ERROR, "服务器异常，请扫后再试！");
    }

    /**
     * 查询所有请求
     * @return
     */
    @RequestMapping("/queryRequest")
    public ResultData<List<FriendsRequest>> queryRequest(Integer uid){
        List<FriendsRequest> friendsRequests = requestService.queryRequestByUid(uid);
        return ResultData.createSuccResultData(friendsRequests);
    }

    /**
     * 处理好友申请
     * @return
     */
    @RequestMapping("/requestHandler")
    public ResultData requestHandler(Integer rid, Integer status){
        requestService.updateRequestStatus(rid, status);
        return ResultData.createSuccResultData(true);
    }
}
