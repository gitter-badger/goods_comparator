package com.guoziao.pku;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

public class WebsiteJDGenerator {
	
	public List<String> generateJDDirectory(){
		List<String>  directoryList = new ArrayList<String>();
		for(int i = 1 ; i <= Constant.JD_DIRECTORY_PAGES; i ++){
			String page = Constant.JD_DIRECTORY_START_PAGE.replace("p=1", "p=" + i);
			System.out.println(page);
			directoryList.add(page);
		}
		return directoryList;
	}
	
	public void writeBookUrl() throws IOException{
		List<String> bookUrlList = generateJDDirectory();
		for(int i = 0; i < bookUrlList.size(); i ++){
			List<String> urlList = getBookUrl(bookUrlList.get(i));
			System.out.println("we get hte list of " + i + "which the size is " + urlList.size());
			writeJDUrl(urlList);
		}
	}
	
	public void writeJDUrl(List<String> bookUrl) throws IOException{
		String filePath = "C:\\Users\\ziao\\Desktop\\javabigpro\\jd\\bookurl.txt";
		File file = new File(filePath);
		if(!file.exists()){
			file.createNewFile();
		}
		BufferedWriter writeer = new BufferedWriter(new FileWriter(file, true));
		for(int i = 0; i < bookUrl.size(); i ++){
			writeer.write(bookUrl.get(i));
			writeer.newLine();
		}
		writeer.close();
	}
	
	public List<String> getBookUrl(String url){
		List<String> urllist = new ArrayList<String>();
		String webContent = new WebConnect().connectUrl(url);
		while(webContent == null){
			webContent =  new WebConnect().connectUrl(url);
		}
		int start = webContent.indexOf("<a target=\"_blank\" onclick=");
		while(start > 0){
			start = webContent.indexOf("href=", start);
			int end = webContent.indexOf("title", start);
			String sigleurl = webContent.substring(start + 5, end);
			int commentsStart = webContent.indexOf("已有", end);
			int commentsEnd = webContent.indexOf("评价", commentsStart);
			if(commentsStart < 0 || commentsEnd < commentsStart){
				start = webContent.indexOf("<a target=\"_blank\" onclick=",start + 1);
				continue;
			}

			int comments = Integer.parseInt(webContent.substring(commentsStart + 2, commentsEnd));
			if(comments > 0){
				urllist.add(sigleurl);
			}
			start = webContent.indexOf("<a target=\"_blank\" onclick=", start + 1);
		}
		return urllist;
	}
	
	public List<String> readJDUrl(){
		String filePath = "C:\\Users\\ziao\\Desktop\\javabigpro\\jd\\bookurl.txt";
		File file = new File(filePath);
		List<String> fileUrl = new ArrayList<String>();
		if(file.exists()){
			BufferedReader br = null;
			String str = "";
			try {
				br = new BufferedReader(new FileReader(file));
				str = br.readLine();
				while (str != null) {
					str = br.readLine();
					fileUrl.add(str);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e);
			} finally{
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		System.out.println(fileUrl.size());
		return fileUrl;
	}
	
	public void writeJDBookInfo(String bookInfo) throws IOException{
		String filePath = "C:\\Users\\ziao\\Desktop\\javabigpro\\jd\\bookinfo.txt";
		File file = new File(filePath);
		if(!file.exists()){
			file.createNewFile();
		}
		BufferedWriter writeer = new BufferedWriter(new FileWriter(file, true));
		writeer.write(bookInfo);
		writeer.newLine();
		writeer.close();
	}
	
	public void getBookInfo() throws IOException{
		List<String> urllist = readJDUrl();
		for(int i = 0 ; i < urllist.size(); i ++){
			String url = urllist.get(i);
			String webContent = new WebConnect().connectUrl(url);
			while(webContent == null){
				webContent =  new WebConnect().connectUrl(url);
			}
			String commentsUrl = "http://wap.jd.com/comments/" + url.substring(19);
			String commentsContent = new WebConnect().connectJDComUrl(commentsUrl);
			while(commentsContent == null){
				commentsContent = new WebConnect().connectJDComUrl(commentsUrl);
			}
			String shopUrl = "http://spu.jd.com/" + url.substring(19);
			String shopContent = new WebConnect().connectUrl(shopUrl);
			while(shopContent == null){
				shopContent =  new WebConnect().connectUrl(shopUrl);
			}
			
			BookInfo bookInfo = analyzeBookInfo(webContent, url, commentsContent, shopContent);
			if(bookInfo != null){
				JSONObject json = new JSONObject().fromObject(bookInfo);
				writeJDBookInfo(json.toString());
			}
			System.out.println("we have get the page " + i);
		}
		
	}

	public BookInfo analyzeBookInfo(String content, String  myurl, String commentsContent, String shopContent){
		int id = 0;
		
		int nameStart = content.indexOf("<div id=\"product-intro\">");
		String name = "";
		if(nameStart > 0){
			nameStart = content.indexOf("<h1>", nameStart);
			int nameEnd = content.indexOf("<", nameStart + 4);
			System.out.println("name " + content.substring(nameStart + 4, nameEnd));
			name = content.substring(nameStart + 4, nameEnd);
		}
		
		int authorstart = content.indexOf("product-authorinfo");
		String author = "";
		if(authorstart > 0){
			authorstart = content.indexOf("target", authorstart);
			authorstart = content.indexOf("target", authorstart);
			authorstart = content.indexOf(">", authorstart);
			int authorend = content.indexOf("<", authorstart);
			System.out.println("author " +  content.substring(authorstart + 1, authorend));
			author = content.substring(authorstart + 1, authorend);
		}

		int ISBNStart = content.indexOf("ISBN");
		String ISBN = "";
		if(ISBNStart > 0){
			int ISBNEnd = content.indexOf("</li>", ISBNStart);
			System.out.println("ISBN " + content.substring(ISBNStart + 6, ISBNEnd));
			ISBN =  content.substring(ISBNStart + 6, ISBNEnd);
		}
		
		double price = 0;
		int priceStart = content.indexOf("<strong id=\"J_price_trigger\">");
		if(priceStart > 0){
			int priceend = content.indexOf("</strong>", priceStart);
			String priceStr = content.substring(priceStart + 30, priceend);
			System.out.println("price " + priceStr);
			price = Double.parseDouble(priceStr);
		}
		
		double favRate = 0;
		
		int reviews = 0;
		
		int goodstart = commentsContent.indexOf("好评</font><font color=\"orange\">");
		if(goodstart > 0){
			int goodend = commentsContent.indexOf("</font>", goodstart + 30);
			int middlestart = commentsContent.indexOf("中评<font color=\"orange\">");
			int middleend = commentsContent.indexOf("</font>", middlestart + 23);
			int badstart = commentsContent.indexOf("差评<font color=\"orange\">");
			int badend = commentsContent.indexOf("</font>", badstart + 23);
			String goodstr = commentsContent.substring(goodstart + 30, goodend);
			String middlestr = commentsContent.substring(middlestart + 23, middleend);
			String badstr = commentsContent.substring(badstart + 23, badend);
			int good = Integer.parseInt(goodstr);
			int middle = Integer.parseInt(middlestr);
			int bad = Integer.parseInt(badstr);
			reviews = good + middle + bad;
			System.out.println("reviews " + reviews);
			favRate = (good + middle * 0.5) / (reviews) * 5;
			DecimalFormat df = new DecimalFormat("#.00");
			favRate = Double.parseDouble(df.format(favRate));
			System.out.println("favRate " + favRate);
		}
		
		
		String published = "";
		int pubStart = content.indexOf("出版时间：");
		if(pubStart > 0){
			int pubEnd = content.indexOf("</li>", pubStart);
			published = content.substring(pubStart + 5, pubEnd);
			System.out.println("published " + published);
		}
		
		int shopNum = 0;
		int shopNumStart = shopContent.indexOf("skuIds");
		if(shopNumStart > 0){
			int shopNumEnd = shopContent.indexOf("}", shopNumStart);
			String shops = shopContent.substring(shopNumStart + 8, shopNumEnd -1);
			shopNum = shops.split(",").length;
			System.out.println("shopNum  " + shopNum );
		}
		
		String desicription = "";
		int desStart = content.indexOf("<h3>内容简介</h3>");
		if(desStart > 0){
			desStart = content.indexOf("<p>", desStart);
			int desend = content.indexOf("</p>", desStart);
			desicription = content.substring(desStart + 3, desend);
			System.out.println("descipriton " + desicription);
		}
		
		
		String url = myurl;
		
		String press = "";
		int pressStart = content.indexOf(">出版社：");
		if(pressStart > 0){
			pressStart = content.indexOf("title=\"", pressStart);
			int pressEnd = content.indexOf("\" ", pressStart + 7);
			press = content.substring(pressStart + 7, pressEnd);
			System.out.println("press " + press);
		}
		
		String picurl = "";
		int picurlStart = content.indexOf("data-img=");
		if(picurlStart > 0){
			picurlStart = content.indexOf("src=\"", picurlStart);
			int picurlend = content.indexOf("\"", picurlStart + 6);
			picurl = content.substring(picurlStart + 5, picurlend);
			System.out.println("picurl" + picurl);
		}
		
		BookInfo bookInfo = new BookInfo(id, name, author, ISBN, price, favRate, reviews, published, shopNum, desicription, url, press, picurl);
		return bookInfo;
	}
}
