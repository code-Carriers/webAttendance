package com.dahua;

import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.JSONObject;
import com.dahua.client.SdkClient;

public class DahuaImplement {

	int userId=1;
	String userName="system";
	String ip="192.168.78.119";
	String password="admin123";
	int port=80;
		
	public String login() throws Exception {
		
		String login=SdkClient.login(ip, port, userName, password);	
		JSONObject Object=JSON.parseObject(login);
		String token =Object.getString("token");
		System.out.println(token);
		return token;
	}
	public int getUserId() {
		return userId;
	}
	public String getUserName() {
		return userName;
	}
	public String getIp() {
		return ip;
	}
	public String getPassword() {
		return password;
	}
	public int getPort() {
		return port;
	}
	
}
