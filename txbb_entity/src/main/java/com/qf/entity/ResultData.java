package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 返回给前端的包装对象
 *
 * @version 1.0
 * @user ken
 * @date 2019/8/27 11:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultData<T> implements Serializable {

    private String code;//状态码
    private String msg;//错误信息
    private T data;//返回数据

    //创建一个成功的返回对象
    public static <T> ResultData createSuccResultData(T data){
        ResultData<T> resultData = new ResultData<>();
        resultData.setCode(ResultCode.SUCC_CODE);
        resultData.setMsg(null);
        resultData.setData(data);
        return resultData;
    }

    //创建一个失败的返回对象
    public static <T> ResultData createFailResultData(String code, String msg){
        ResultData<T> resultData = new ResultData<>();
        resultData.setCode(code);
        resultData.setMsg(msg);
        resultData.setData(null);
        return resultData;
    }
}
