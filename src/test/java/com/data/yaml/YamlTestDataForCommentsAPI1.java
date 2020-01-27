package com.data.yaml;

public class YamlTestDataForCommentsAPI1 extends TestData{

	int commentId;

	public int getCommnetId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	@Override
	public String toString() {
		return "YamlTestDataForCommentsAPI1 [commentId=" + commentId + "]";
	}
}
