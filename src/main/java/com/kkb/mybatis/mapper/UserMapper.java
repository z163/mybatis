package com.kkb.mybatis.mapper;

import com.kkb.mybatis.po.User;

public interface UserMapper {
	public User findUserById(int id) throws Exception;
	public void insertUser(User user) throws Exception;
}