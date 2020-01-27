package com.pojos;

import java.util.List;

public class AllPosts {

	List<SinglePost> listOfPosts;

	public List<SinglePost> getListOfPosts() {
		return listOfPosts;
	}

	public void setListOfPosts(List<SinglePost> listOfPosts) {
		this.listOfPosts = listOfPosts;
	}

	@Override
	public String toString() {
		return "AllPosts [listOfPosts=" + listOfPosts + "]";
	}

}
