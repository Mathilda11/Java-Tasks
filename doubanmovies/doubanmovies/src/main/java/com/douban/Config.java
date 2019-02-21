package com.douban;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 配置类
 * @author 54060
 *
 */
public class Config {
	static{			
		try {
			Properties p=new Properties();
			InputStream input= Config.class.getResourceAsStream("/db.properties");
			p.load(input);
			host=p.getProperty("host");
			port=Integer.parseInt(p.getProperty("port"));
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}//加载		
	}	
	private static String host;//主机地址
	private static int port;//端口
	
	public static String getHost() {
		return host;
	}
	public static int getPort() {
		return port;
	}	
}
