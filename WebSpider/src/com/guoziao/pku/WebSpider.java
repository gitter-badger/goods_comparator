package com.guoziao.pku;

import java.io.IOException;

public class WebSpider {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			new WebsiteJDGenerator().getBookInfo();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
