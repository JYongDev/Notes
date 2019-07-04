package com.example.demo.dao;

import com.example.demo.bean.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {

    List<Role> findRoleById(int id);

    List<Role> selectAll();

    List<Role> findRoleByUserId(int id);
}
