package com.betamall.test;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONTest {
	public static void main(String[] args) {
		
		JSONArray data = new JSONArray();
		JSONObject pageData = new JSONObject();
		JSONArray mgrsData = new JSONArray();
		
		pageData.put("pageNum", 1);
		pageData.put("startPageNum", 1);
		pageData.put("endPageNum", 5);
		
		for(int i=0; i<4; i++) {
			JSONObject mgrData = new JSONObject();
			mgrData.put("a", i);
			mgrData.put("b", i*10);
			mgrData.put("c", i*100);
//			System.out.println(mgrData);
			mgrsData.put(i, mgrData);
			System.out.println(mgrsData);
			System.out.println();
			System.out.println();
		}
		data.put(mgrsData);
	}
}
