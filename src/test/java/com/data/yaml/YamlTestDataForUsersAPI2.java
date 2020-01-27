package com.data.yaml;

public class YamlTestDataForUsersAPI2 extends TestData{

	int userId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "YamlTestDataForUsersAPI2 [userId=" + userId + "]";
	}
}
