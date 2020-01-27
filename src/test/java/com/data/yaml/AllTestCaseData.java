package com.data.yaml;

import java.util.List;
import java.util.Map;

/**
 * This class will load the yaml file data
 * 
 * Key : it will be the name of test case
 * Value: value are all list of data given for test case 
 * 
 */
public class AllTestCaseData {
	
	private Map<String, List<TestData>> allTestCaseDataMap;

	public Map<String, List<TestData>> getAllTestCaseDataMap() {
		return allTestCaseDataMap;
	}

	public void setAllTestCaseDataMap(Map<String, List<TestData>> allTestCaseDataMap) {
		this.allTestCaseDataMap = allTestCaseDataMap;
	}
}
