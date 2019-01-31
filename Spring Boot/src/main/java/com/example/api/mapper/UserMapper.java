package com.example.api.mapper;

import com.example.api.model.domain.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * UserMapper
 *
 * @author Where
 * @date 2019-01-31
 */
@Component
@Mapper
public interface UserMapper {
    /**
     * 获取用户列表
     *
     * @return
     */
    List<UserDO> list();

    /**
     * 统计当前用户数量
     *
     * @return
     */
    @Select("SELECT COUNT(*) FROM USER")
    Integer count();
}
