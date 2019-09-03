package com.qf.controller;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.qf.entity.ResultCode;
import com.qf.entity.ResultData;
import com.qf.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

/**
 * @version 1.0
 * @user ken
 * @date 2019/8/27 15:41
 */
@RestController
@RequestMapping("/res")
public class ResController {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Autowired
    private IUserService userService;

    /**
     * 上传图片
     * @return
     */
    @RequestMapping("/uploadImg")
    public ResultData uploadImg(MultipartFile file, Integer userid){


        Map<String, String> map = new HashMap<>();

        //上传到FastDFS上
        try {
            StorePath result = fastFileStorageClient.uploadImageAndCrtThumbImage(
                    file.getInputStream(),
                    file.getSize(),
                    "png",
                    null);

            //大图的url
            String fullPath = result.getFullPath();
            //小图的url
            String crmPath = fullPath.replace(".png", "_80x80.png");

            map.put("img", fullPath);
            map.put("imgCrm", crmPath);

            //将图片的url更新到数据库中
            userService.updateImg(fullPath, crmPath, userid);

        } catch (IOException e) {
            e.printStackTrace();
        }


        return ResultData.createSuccResultData(map);
    }

    /**
     * 上传音频文件
     * @return
     */
    @RequestMapping("/uploadFile")
    public ResultData uploadFile(MultipartFile file){
        //上传到FastDFS上
        try {
            StorePath result = fastFileStorageClient.uploadFile(
                    file.getInputStream(),
                    file.getSize(),
                    "amr",
                    null
            );

            String filePath = result.getFullPath();
            return ResultData.createSuccResultData(filePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultData.createFailResultData(ResultCode.SERVER_ERROR, "上传失败！");
    }
}
