package com.qf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @version 1.0
 * @user ken
 * @date 2019/8/28 11:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendsRequest implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer fromid;
    private Integer toid;
    private Date createTime = new Date();
    private String content;
    private Integer status = 0;//0 - 待处理 1 - 已通过 2 - 已忽略

    //谁发送的添加申请
    @TableField(exist = false)
    private User fromUser;

}
