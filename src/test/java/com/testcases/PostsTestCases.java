package com.testcases;

import org.testng.annotations.Test;

import com.businesslayer.PostsBusinessLogic;
import com.data.DataProviderClass;
import com.data.yaml.YamlTestDataForPostsAPI1;
import com.pojos.SinglePost;
import com.testcases.testsetup.TestBase;

import static org.testng.Assert.assertNull;

import org.apache.log4j.Logger;

/**
 * This class contains all the test cases related to API1 i.e. Posts API
 */
public class PostsTestCases extends TestBase{

	private static final Logger LOGGER = Logger.getLogger(PostsTestCases.class);
	
	@Test(dataProvider = "common_test_data_provider", dataProviderClass = DataProviderClass.class)
	public void testValidatePostNotExist(YamlTestDataForPostsAPI1 apitestdata) {
		
		int postId = apitestdata.getPostId();
		
		LOGGER.info("Retrieving data for postId - " + postId);		
		SinglePost singlePost = PostsBusinessLogic.getSinglePostDataFor(postId,builder);
		
		assertNull(singlePost, "Expected no post for PostId - " + postId);
		LOGGER.info("No Post Found for PostId - " +  postId);			
	}
}
