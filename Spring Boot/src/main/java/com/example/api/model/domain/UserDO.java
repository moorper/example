package com.example.api.model.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * UserDO
 * 
 * @author Where
 * @date 2019-01-31
 */
@Data
public class UserDO {
    private Integer id;
    private Integer username;
    private Integer password;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss", serialize = false)
    private Date createdAt;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss", serialize = false)
    private Date updatedAt;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss", serialize = false)
    private Date deletedAt;
}
