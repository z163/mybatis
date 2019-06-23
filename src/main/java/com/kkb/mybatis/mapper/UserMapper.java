package com.kkb.mybatis.mapper;

import java.util.List;

import com.kkb.mybatis.po.OrdersExt;
import com.kkb.mybatis.po.User;

public interface UserMapper {
	public User findUserById(int id) throws Exception;
	public List<User> findUserByUsername(String id) throws Exception;
	public void insertUser(User user) throws Exception;
	public void deleteUserById(int id) throws Exception;
	public void updateUser(User user) throws Exception;
	public List<OrdersExt> findOrdersList() throws Exception;
}