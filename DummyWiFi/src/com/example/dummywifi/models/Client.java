package com.example.dummywifi.models;

import com.example.dummywifi.util.Connection;

public class Client {
	
	protected String userName, deviceId;
	private long id; // So the server can keep track of clients even if they haven't set a username or device id yet
	private boolean isAdmin, isModerator; // for if we want this functionality later
	
	protected Connection connection;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public Client(Connection connection) {
		this.connection = connection;
		this.isAdmin = this.isModerator = false;
	}
	
	public boolean compareTo(Client other) {
		return this.id == other.id;
	}

}
