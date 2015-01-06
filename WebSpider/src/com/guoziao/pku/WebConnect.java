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
			// �򿪺�URL֮�������
			URLConnection connection = realUrl.openConnection();
			// ����ͨ�õ���������
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("Content-Type","text/plain; charset=utf-8");
			//����user-agent UA����һ�������ַ���ͷ��ʹ�÷������ܹ�ʶ��ͻ���ʹ�õĲ���ϵͳ�����������Ϣ�����������˷��ִ������ʵ�ʱ����ֹ���ʣ���ʱ�����ͨ������UA����ƭ������
			connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11");
			
			connection.setConnectTimeout(50000);
			connection.setReadTimeout(50000);
			// ����ʵ�ʵ�����
			connection.connect();
			// ���� BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "gb2312"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("����GET��������쳣��" + e);
			try {
				System.out.println("we start to sleep");
				Thread.sleep(30000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				System.out.println("�쳣��" + e);
			}
			return null;
		}
		// ʹ��finally�����ر�������
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
			// �򿪺�URL֮�������
			URLConnection connection = realUrl.openConnection();
			// ����ͨ�õ���������
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("Content-Type","text/plain; charset=utf-8");
			//����user-agent UA����һ�������ַ���ͷ��ʹ�÷������ܹ�ʶ��ͻ���ʹ�õĲ���ϵͳ�����������Ϣ�����������˷��ִ������ʵ�ʱ����ֹ���ʣ���ʱ�����ͨ������UA����ƭ������
			connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11");
			
			connection.setConnectTimeout(50000);
			connection.setReadTimeout(50000);
			// ����ʵ�ʵ�����
			connection.connect();
			// ���� BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("����GET��������쳣��" + e);
			try {
				System.out.println("we start to sleep");
				Thread.sleep(30000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				System.out.println("�쳣��" + e);
			}
			return null;
		}
		// ʹ��finally�����ر�������
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
