package homework.project;

import java.util.ArrayList;

public class BookInfo {
	
	private int Id;
	private String Name;              //����
	private String Author;            //����
	private String ISBN;                 //ISBN
	private String Price;             //price FavRate改成String类型
	private String FavRate;           //������
	private int Reviews;              //��������
	//ʱ������ʱ��λString������String��date, timestamp֮����໥ת���ο�http://yunnick.iteye.com/blog/1074495
	private String Published;         //����ʱ��
	private int ShopNum;              //�����
	private String Description;       //����
	private String URL; 
	private String ImageURL;
	private String Press;
	private ArrayList<BookInfo> bookinfoList;
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
	public String getFavRate() {
		return FavRate;
	}
	public void setFavRate(String favRate) {
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
		this.Name = name;
		
	}
	public String getAuthor() {
		return Author;
	}
	public void setAuthor(String author) {
		Author = author;
	}
	public String getPrice() {
		return Price;
	}
	public void setPrice(String price) {
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
	public String getImageURL() {
		return ImageURL;
	}
	public void setImageURL(String ImageURL) {
		this.ImageURL = ImageURL;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getPress() {
		return Press;
	}
	public void setPress(String Press) {
		this.Press = Press;
	}
	public ArrayList<BookInfo> getBookInfoList() {
		return bookinfoList;
	}
	public void setBookinfoList(ArrayList<BookInfo> bookinfoList) {
		this.bookinfoList = bookinfoList;
	}	
	public BookInfo(String name,String author,String iSBN, String price, String favRate, int reviews,
			String published, int shopNum,String description,String url, String imageURL, String press) {
		super();
		this.Name=name;
		this.Author=author;
		this.ISBN = iSBN;
		this.Price = price;
		FavRate = favRate;
		Reviews = reviews;
		this.Published = published;
		ShopNum = shopNum;
		this.Description=description;
		this.URL=url;
		this.ImageURL=imageURL;
		this.Press=press;
		this.bookinfoList=new ArrayList <BookInfo>();
		
	}
	public BookInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
