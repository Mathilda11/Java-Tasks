package com.douban;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.bson.Document;
import org.junit.Test;

import com.mongodb.client.FindIterable;
import com.mongodb.util.JSON;

public class UtilsTest {

	@Test
	public void testlist(){
		Map<String, Object> map =new HashMap();
		map.put("score", "9.5");	
		FindIterable<Document> list = Utils.list("doubanmovies", map);
		for( Document doc: list){//遍历集合中的文档输出数据
			System.out.println("ranking:"+ doc.getString("ranking") );
			System.out.println("score:"+ doc.getString("score") );//默认为浮点型
			System.out.println("movie_name:"+ doc.getString("movie_name") );
			System.out.println("score_num:"+ doc.getString("score_num") );
			System.out.println("--------------------------");
		}
		String json = JSON.serialize(list);
		System.out.println(json);

	}

	@Test
	public void testlistPage(){
		Map<String, Object> map = new HashMap();
		map.put("score", "9.0");
		Map<String, Object> m = Utils.listPage("doubanmovies", map, 2, 10);
		String json = JSON.serialize(m);
		System.out.println(json);
	}
}
