package com.guoziao.pku;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class WebConnect {

	public String connectUrl(String url){
		String result = "";
		BufferedReader in = null;
		if(url == null){
			return null;
		}
		if(url.isEmpty()){
			return null;
		}
		try {
			System.out.println("we start to connect");
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("Content-Type","text/plain; charset=utf-8");
			//设置user-agent UA，是一个特殊字符串头，使得服务器能够识别客户端使用的操作系统和浏览器等信息。当服务器端发现大量访问的时候会禁止访问，这时候可以通过设置UA来欺骗服务器
			connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11");
			
			connection.setConnectTimeout(50000);
			connection.setReadTimeout(50000);
			// 建立实际的连接
			connection.connect();
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "gb2312"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			try {
				System.out.println("we start to sleep");
				Thread.sleep(30000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				System.out.println("异常！" + e);
			}
			return null;
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				return null;
//				e2.printStackTrace();
			}
		}
		return result;
	}
	
	public String connectJDComUrl(String url){
		String result = "";
		BufferedReader in = null;
		if(url == null){
			return null;
		}
		if(url.isEmpty()){
			return null;
		}
		try {
			System.out.println("we start to connect");
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("Content-Type","text/plain; charset=utf-8");
			//设置user-agent UA，是一个特殊字符串头，使得服务器能够识别客户端使用的操作系统和浏览器等信息。当服务器端发现大量访问的时候会禁止访问，这时候可以通过设置UA来欺骗服务器
			connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11");
			
			connection.setConnectTimeout(50000);
			connection.setReadTimeout(50000);
			// 建立实际的连接
			connection.connect();
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			try {
				System.out.println("we start to sleep");
				Thread.sleep(30000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				System.out.println("异常！" + e);
			}
			return null;
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				return null;
//				e2.printStackTrace();
			}
		}
		return result;
	}
}
