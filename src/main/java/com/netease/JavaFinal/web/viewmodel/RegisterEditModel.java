package com.netease.JavaFinal.web.viewmodel;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.netease.JavaFinal.utils.Compare;

@Compare(field = "password", verifyField = "confirmPassword", message = "两次输入密码不一致")
public class RegisterEditModel {

	@NotEmpty(message = "不能为空")
	@Size(min = 6, max = 15, message = "请输入长度在{min}到{max}之间的字符串")
	private String userName;
	
	@NotEmpty(message = "不能为空")
	@Length(min = 6, message = "不能少于{min}个字符")
	private String password;
	
	private String confirmPassword;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
