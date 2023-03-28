package com.demo.userLogin.dao;

import com.demo.userLogin.dataobject.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface UserDAO {



    int add(UserDO userDO);

    int update(UserDO userDO);

    int delete(@Param("id") long id);

    UserDO findByUserName(@Param("userName") String name);

}
