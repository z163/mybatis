package com.kkb.mybatis.po;

//符合设计原则：对修改关闭、对扩展开放
public class OrdersExt extends Orders {
	private String username;// 用户名称
	private String address;// 用户地址
	// 关联用户信息
	private User user;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
