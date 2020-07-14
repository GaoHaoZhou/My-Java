package com.glp.mapper;

import com.glp.model.Users;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    Users selectByPrimaryKey(Integer id);

    List<Users> selectAll();

    int updateByPrimaryKey(Users record);
}