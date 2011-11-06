package com.example.struts2.core.action;

public class LoginAction {
	private String username;
	private String password;

	// 处理用户请求的execute方法
	public String execute() {
		if (getUsername().equals(getPassword())) {
			return "success";
		} else {
			return "error";
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
