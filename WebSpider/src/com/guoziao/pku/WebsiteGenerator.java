package com.guoziao.pku;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

public class WebsiteGenerator {

	public List<String> generateAmazonDirectory(){
		List<String>  directoryList = new ArrayList<String>();
		for(int i = 2 ; i <= Constant.AMAZON_DIRECTORY_PAGES; i ++){
			String page = Constant.AMAZON_DIRECTORY_START_PAGE.replace("page=1", "page=" + i);
			System.out.println(page);
			directoryList.add(page);
		}
		return directoryList;
	}
	
	public List<String> getBookInfoUrl(){
		List<String> direUrl = generateAmazonDirectory();
		List<String> bookUrl = new ArrayList<String>();
		for( int i = 0 ; i < direUrl.size(); i ++){
			String url = direUrl.get(i);
			String webContent = new WebConnect().connectUrl(url);
			while(webContent == null){
				webContent =  new WebConnect().connectUrl(url);
			}
			List<String> pageUrl = getBooUrlFromContent(webContent);
			System.out.println("the page " + i + "info has url of " + pageUrl.size());
			bookUrl.addAll(pageUrl);
		}
		return bookUrl;
	}
	
	public List<String> getBooUrlFromContentFirstpage(String content){
		List<String> urlList = new ArrayList<String>();
		if(content == null || content.length() < 1){
			return null;
		}
		int startindex = startindex = content.indexOf("<div class=\"linePlaceholder\"></div><div class=\"image imageContainer\">", 1);
		while(startindex > 0){
			int urlfrom = content.indexOf("<a href=\"", startindex + 1);
			int urlto = content.indexOf("\"", urlfrom + 10);
			String url = content.substring(urlfrom + 9, urlto);
			urlList.add(url);
			startindex = content.indexOf("<div class=\"linePlaceholder\"></div><div class=\"image imageContainer\">", startindex + 1);
		}
		return urlList;
	}
	
	public List<String> getBooUrlFromContent(String content){
		List<String> urlList = new ArrayList<String>();
		if(content == null || content.length() < 1){
			return null;
		}
		int realContentIndex = content.indexOf("<div id=\"results-atf-next\" style=\"display: none;\">");
		if(realContentIndex > 0){										
			content = content.substring(1, realContentIndex);
		}
//		System.out.println(content);
		int startindex = content.indexOf("<div class=\"a-row a-spacing-small\"><a class=\"a-link-normal s-access-detail-page a-text-normal\" target=\"_blank\"", 1);
		while(startindex > 0){
			int urlfrom = content.indexOf("href=\"", startindex + 1);
			int urlto = content.indexOf("\"", urlfrom + 10);
			String url = content.substring(urlfrom + 6, urlto);
			startindex = content.indexOf("<div class=\"a-row a-spacing-small\"><a class=\"a-link-normal s-access-detail-page a-text-normal\" target=\"_blank\"", startindex + 1);
			if(startindex > 0){
				int salesto = content.indexOf("卖家", urlto);
				if(salesto > startindex || salesto < 0){
					url = url + "##0";
					urlList.add(url);
				}else{
					int salsefrom = content.indexOf("(", salesto - 10);
					String salesNum = content.substring(salsefrom + 1, salesto);
					url = url + "##" + salesNum;
					urlList.add(url);
				}
			}else{
				int salesto = content.indexOf("卖家", urlto);
				if(salesto < 0){
					url = url + "##0";
					urlList.add(url);
				}else{
					int salsefrom = content.indexOf("(", salesto - 20);
					if(salsefrom > salesto){
						url = url + "##0";
						urlList.add(url);
					}else{
						String salesNum = content.substring(salsefrom + 1, salesto);
						url = url + "##" + salesNum;
						urlList.add(url);
					}
				}
			}
		}
		return urlList;
	}
	
	public void writeAmazonUrl(List<String> bookUrl) throws IOException{
		String filePath = "C:\\Users\\ziao\\Desktop\\javabigpro\\amazon\\bookurl.txt";
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
	
	public List<String> readAmazonUrl(){
		String filePath = "C:\\Users\\ziao\\Desktop\\javabigpro\\amazon\\bookurl.txt";
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
	
	public List<BookInfo> getBookInfo() throws IOException{
		List<String> urlList = readAmazonUrl();
		for(int i = 0 ; i < urlList.size(); i ++){
			String str = urlList.get(i);
			String[] strs = str.split("##");
			if(strs.length == 2){
				String url = strs[0];
				int shopNum = Integer.parseInt(strs[1].trim());
				String webContent = new WebConnect().connectUrl(url);
				while(webContent == null){
					webContent =  new WebConnect().connectUrl(url);
				}
				BookInfo bookInfo = analyzeBookInfo(webContent.trim(), url, shopNum);
				if(bookInfo != null){
					JSONObject json = new JSONObject().fromObject(bookInfo);
					writeAmazonUrl(json.toString());
				}
			}
			System.out.println("we have get the page " + i);
		}
		return null;
	}
	
	public void writeAmazonUrl(String bookInfo) throws IOException{
		String filePath = "C:\\Users\\ziao\\Desktop\\javabigpro\\amazon\\bookinfo.txt";
		File file = new File(filePath);
		if(!file.exists()){
			file.createNewFile();
		}
		BufferedWriter writeer = new BufferedWriter(new FileWriter(file, true));
		writeer.write(bookInfo);
		writeer.newLine();
		writeer.close();
	}
	
	public BookInfo analyzeBookInfo(String content, String myurl , int myshopNum){
		if(myshopNum == 0){
			return null;
		}
		
		int id = 0;
		String name = getName(content);
		if(name == null){
			return null;
		}
		System.out.println("name " + name);
		
		String author = getAuthor(content);
		System.out.println("author" + author);
		
		int ISBNStart = content.indexOf("<li><b>条形码:</b>");
		String ISBN = "";
		if (ISBNStart > 0) {
			int ISBNend = content.indexOf("</li>", ISBNStart);
			System.out.println("ISBN" + content.substring(ISBNStart + 14, ISBNend));
			ISBN = content.substring(ISBNStart + 15, ISBNend).trim();
		}
		
		int priceStart = content.indexOf("<span class='a-color-price'>");
		double price = 0;
		if (priceStart > 0) {
			int priceend = content.indexOf("</span>", priceStart);
			System.out.println("price" + content.substring(priceStart + 29, priceend));
			price = Double.parseDouble(content.substring(priceStart + 29, priceend).trim());
		}
		
		int favRateStart = content.indexOf("<div id=\"avgRating\" class=\"a-row a-spacing-small a-color-secondary\">");
		double favRate = 0;
		if(favRateStart > 0){
			favRateStart = content.indexOf("平均", favRateStart);
			int favRateEnd = content.indexOf("星", favRateStart);
			String favRateStr = content.substring(favRateStart + 2, favRateEnd);
			System.out.println("favratestr "+favRateStr);
			favRate =Double.parseDouble(favRateStr.trim());
		}
		
		int reviewsStart = content.indexOf("<span id=\"acrCustomerReviewText\" class=\"a-size-base\">");
		int reviews = 0;
		if(reviewsStart > 0){
			int reviewsEnd = content.indexOf("条商品评论", reviewsStart);
			System.out.println("review "+content.substring(reviewsStart + 53, reviewsEnd).trim());
			reviews = Integer.parseInt(content.substring(reviewsStart + 53, reviewsEnd).trim().replace(",", "").trim());
		}
		
		int publishedStart = content.indexOf("<span class=\"a-size-medium a-color-secondary a-text-normal\">&ndash;");
		String published = "";
		if(publishedStart > 0){
			int publishedEnd = content.indexOf("</span>", publishedStart);
			System.out.println("publish "+content.substring(publishedStart + 68, publishedEnd).trim());
			published = content.substring(publishedStart + 68, publishedEnd).trim();
		}
		
		int shopNum = myshopNum;
		
		int descriptionStart = content.indexOf(" <noscript>");
		String description = ""; 
		if(descriptionStart > 0){
			descriptionStart = content.indexOf("<div>", descriptionStart);
			int descriptionEnd = content.indexOf("</noscript>", descriptionStart);
			System.out.println("descriptino "+content.substring(descriptionStart + 6, descriptionEnd).trim());
			description = content.substring(descriptionStart + 6, descriptionEnd).trim();
		}
		String url = myurl;
		
		//出版社
		int pressStart = content.indexOf("<li><b>出版社:</b>");
		String press = "";
		if(pressStart > 0){
			int pressEnd = content.indexOf("</li>", pressStart);
			System.out.println("press "+content.substring(pressStart + 15, pressEnd));
			press = content.substring(pressStart + 15, pressEnd);
		}
		
		int picUrlStart = content.indexOf("<img id=\"imgBlkFront\" src=\"");
		String picUrl = "";
		if(picUrlStart > 0){
			int picUrlEnd = content.indexOf("\" class=\"image-stretch", picUrlStart);
			System.out.println("picrul "+content.substring(picUrlStart + 27, picUrlEnd).trim());
			picUrl = content.substring(picUrlStart + 27, picUrlEnd).trim();
		}

		BookInfo bookInfo = new BookInfo(id, name, author, ISBN, price, favRate, reviews, published, shopNum, description, url, press, picUrl);
		return bookInfo;
	}
	
	public String getAuthor(String content){
		StringBuffer author = new StringBuffer();
		
		int startindex = content.indexOf("author notFaded");
		while(startindex >= 0){
			int start = content.indexOf("href", startindex);
			start = content.indexOf(">", start);
			int end = content.indexOf("</a>", startindex);
			author.append(content.substring(start + 1, end));
			author.append("(作者)  ");
			startindex = content.indexOf("author notFaded", startindex + 1);
		}
		
		int translateindex = content.indexOf("class=\"author\"");
		while(translateindex >= 0){
			int start = content.indexOf("href", translateindex);
			start = content.indexOf(">", start);
			int end = content.indexOf("</a>", translateindex);
			author.append(content.substring(start + 1, end));
			author.append("(译者)  ");
			translateindex = content.indexOf("class=\"author\"", translateindex + 1);
		}
		return author.toString();
	}
	
	public String getName(String content){
		int start = content.indexOf("<span id=\"productTitle\" class=\"a-size-large\">");
		if(start < 0){
			return null;
		}
		int end = content.indexOf("</span>", start);
		String toTransName = content.substring(start + 45, end);
		int transStart = toTransName.indexOf("&#");
		if(transStart > end){
			return null;
		}
		while(transStart >= 0){
			String str = toTransName.substring(transStart, transStart + 8);
			toTransName = toTransName.replace(str, (char)(Integer.parseInt(str.substring(2, 7))) + "");
			transStart = toTransName.indexOf("&#");
		}
		return toTransName;
	}
}
