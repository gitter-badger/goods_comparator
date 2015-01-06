package com.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * 数据库操作工具类，不允许实例化
 * @author shi
 *
 */
public class DBUtils {
	public final static String URL;
	public final static String USERNAME;
	public final static String PASSWORD;
	public final static String DRIVER;
	private static ResourceBundle rb = ResourceBundle.getBundle("com.util.db.db-config");
	/*
	 * 私有构造方法
	 */
	private DBUtils(){}
	//静态代码块，加载时候只会执行一次
	static{
		URL = rb.getString("jdbc.url");
		USERNAME = rb.getString("jdbc.username");
		PASSWORD = rb.getString("jdbc.password");
		DRIVER = rb.getString("jdbc.driver");
		
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 一个获得数据库连接的方法
	 * @return
	 */
	public static Connection getConnection(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("获取连接失败");
		}
		return conn;
	}
	
	/**
	 * 一个关闭连接的方法
	 * @param rs
	 * @param stat
	 * @param conn
	 */
	public static void close(ResultSet rs,Statement stat,Connection conn){
		try {
			if(rs!=null) rs.close();
			if(stat!=null) stat.close();
			if(conn!=null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
