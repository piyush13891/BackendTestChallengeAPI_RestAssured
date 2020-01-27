package com.pojos;

import java.util.List;

public class AllComments {

	List<SingleComment> listOfComments;

	public List<SingleComment> getListOfcomments() {
		return listOfComments;
	}

	public void setListOfcomments(List<SingleComment> listOfComments) {
		this.listOfComments = listOfComments;
	}

	@Override
	public String toString() {
		return "AllComments [listOfComments=" + listOfComments + "]";
	}

}
