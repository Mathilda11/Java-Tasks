package com.douban;

import java.util.HashMap;
import java.util.Map;
import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class Utils {
	
	/**
	 * 可带条件查询
	 * @param tableName
	 * @param map
	 * @return
	 */
	public static FindIterable<Document> list(String tableName,Map<String, Object>map){
		MongoDatabase database = MongoManager.getDatabase();
		MongoCollection<Document>collection = database.getCollection(tableName);
		BasicDBObject bson=new BasicDBObject(map);//构建查询条件			
		return collection.find(bson);
	}
	/**
	 * 分页查询
	 * @param tableName 表名称
	 * @param map 条件
	 * @param pageIndex 页码
	 * @param pageSize 页大小
	 * @return
	 */
	public static Map<String,Object> listPage(String tableName,Map<String, Object> map,int pageIndex,int pageSize){
		MongoDatabase database = MongoManager.getDatabase();
		MongoCollection<Document> collection = database.getCollection(tableName);		
		BasicDBObject bson = new BasicDBObject(map);//构建查询条件			
		FindIterable<Document> find = collection.find(bson);
		int skip= (pageIndex-1)*pageSize;
		find.skip(skip);//跳过记录数
		find.limit(pageSize);//每页查询记录数
		//{ total:x,rows:[]  }
		long count = collection.count(bson);
		Map<String,Object> m = new HashMap();
		m.put("total", count);
		m.put("rows", find);
		return m;
	}

}
