package com.example.demo.dao;

import com.example.demo.bean.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface PermissionMapper {

    List<Permission> selectByRoleId(int id);

}
