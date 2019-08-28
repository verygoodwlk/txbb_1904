package com.qf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.qf.dao.UserMapper;
import com.qf.entity.User;
import com.qf.service.IUserService;
import com.qf.util.PinyinUtils;
import com.qf.util.QRCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @version 1.0
 * @user ken
 * @date 2019/8/27 10:48
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    //用户注册 -1表示用户名存在
    @Override
    public int registerUser(User user) {

        //判断用户名是否存在
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", user.getUsername());
        Integer count = userMapper.selectCount(queryWrapper);

        if(count > 0){
            //已经存在
            return -1;
        }

        //TODO 生成昵称的拼音
        user.setPinyin(PinyinUtils.string2Pinyin(user.getNickname()));
        //TODO 生成用户的二维码名片 二维码是什么？
        File file = null;
        try {
            file = File.createTempFile("qrcode_" + user.getUsername(), "png");
            //生成二维码，放入临时文件
            QRCodeUtils.createQRCode(file, "txbb:" + user.getUsername());
            //将临时文件上传到FastDFS
            StorePath path = fastFileStorageClient.uploadImageAndCrtThumbImage(new FileInputStream(file), file.length(), "png", null);
            String fullPath = path.getFullPath();

            //放入user
            user.setQrcode(fullPath);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(file != null){
                file.delete();
            }
        }

        //保存到数据库
        return userMapper.insert(user);
    }

    /**
     * 登录的业务
     * @param user
     * @return
     */
    @Override
    public User login(User user) {

        //通过用户名查询用户信息
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", user.getUsername());
        User userInfo = userMapper.selectOne(queryWrapper);

        //验证密码是否正确
        if(userInfo != null && user.getPassword().equals(userInfo.getPassword())){
            //登录成功
            return userInfo;
        }
        return null;
    }

    /**
     * 更新用户头像
     * @param img
     * @param imgCrm
     * @param uid
     * @return
     */
    @Override
    public int updateImg(String img, String imgCrm, Integer uid) {

        User user = userMapper.selectById(uid);
        user.setHeader(img);
        user.setHeaderCrm(imgCrm);

        return userMapper.updateById(user);
    }

    @Override
    public User queryByUserName(String username) {
        //判断用户名是否存在
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }

    @Override
    public User queryByUid(Integer uid) {
        User user = userMapper.selectById(uid);
        return user;
    }
}
