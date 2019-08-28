package com.qf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @version 1.0
 * @user ken
 * @date 2019/8/28 11:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Friends implements Serializable {

    @TableId(type = IdType.INPUT)
    private Integer uid;
    @TableId(type = IdType.INPUT)
    private Integer fid;
    private Date createTime;
    private Integer status;
    private String beizhu;
}
