package com.guoziao.pku;

public class BookInfo {
	
	private int Id;
	private String Name;              //书名
	private String Author;            //作者
	private String ISBN;                 //ISBN
	private double Price;             //价格
	private double FavRate;           //好评率
	private int Reviews;              //评论人数
	//时间我暂时定位String，关于String，date, timestamp之间的相互转化参考http://yunnick.iteye.com/blog/1074495
	private String Published;         //出版时间
	private int ShopNum;              //店家数
	private String Description;       //描述
	private String URL;               //链接
	private String press;			  //出版社
	private String picUrl;			  //图片链接地址
	
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}

	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public double getFavRate() {
		return FavRate;
	}
	public void setFavRate(double favRate) {
		FavRate = favRate;
	}
	public int getReviews() {
		return Reviews;
	}
	public void setReviews(int reviews) {
		Reviews = reviews;
	}
	public int getShopNum() {
		return ShopNum;
	}
	public void setShopNum(int shopNum) {
		ShopNum = shopNum;
	}
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAuthor() {
		return Author;
	}
	public void setAuthor(String author) {
		Author = author;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public String getPublished() {
		return Published;
	}
	public void setPublished(String published) {
		Published = published;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}

	public BookInfo(int id, String name, String author, String iSBN, double price,
			double favRate, int reviews, String published, int shopNum,
			String description, String uRL, String press, String picUrl) {
		super();
		Id = id;
		Name = name;
		Author = author;
		ISBN = iSBN;
		Price = price;
		FavRate = favRate;
		Reviews = reviews;
		Published = published;
		ShopNum = shopNum;
		Description = description;
		URL = uRL;
		this.press = press;
		this.picUrl = picUrl;
	}
	
	public BookInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
