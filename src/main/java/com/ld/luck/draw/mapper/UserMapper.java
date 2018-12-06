package com.ld.luck.draw.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ld.luck.draw.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {

    int updateForNo();

    int updateForYes(@Param("name") String name);

    int deleteAll();
}
