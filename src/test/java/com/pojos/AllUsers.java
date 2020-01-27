package com.pojos;

import java.util.List;

public class AllUsers {

	List<SingleUser> listOfUsers;

	public List<SingleUser> getListOfUsers() {
		return listOfUsers;
	}

	public void setListOfUsers(List<SingleUser> listOfUsers) {
		this.listOfUsers = listOfUsers;
	}

	@Override
	public String toString() {
		return "AllUsers [listOfUsers=" + listOfUsers + "]";
	}

}
