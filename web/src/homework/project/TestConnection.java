package homework.project;
import java.io.UnsupportedEncodingException;
import java.sql.*;

public class TestConnection {
	public static void main(String args[]) throws UnsupportedEncodingException
	{
		 String Name="编程之美de";              //����

		 String Author="John";            //����
		 String ISBN="12";                 //ISBN
		 String Price="32.15";             //�۸�
		 String FavRate="23.56";           //������
		 int Reviews=2222;              //��������
		//ʱ������ʱ��λString������String��date, timestamp֮����໥ת���ο�http://yunnick.iteye.com/blog/1074495
		 String Published="2011.12";         //����ʱ��
		 int ShopNum=111;              //�����
		 String Description="这本书，么么哒";       //����
		 String URL="http://open.163.com/ted/";  
		 String imageURL="";//����
		 String press="";
		
		MyConnection myconn=new MyConnection();
		Connection conn=myconn.getConn();
		if(myconn.myinsert(conn, "dangdang", Name, Author, ISBN, Price, FavRate, Reviews, Published, ShopNum, Description, URL, imageURL, press))
		{
			System.out.println("insert success"); 
		}
		else
		{
			System.out.println("insert failed"); 		
		}
		BookInfo book = new BookInfo();
		if(myconn.myselectByName(conn, book,"dangdang",  "编程   "))
		{	
			//System.out.println("setName=" + book.getName());
			System.out.println(book.getName() + ":" + book.getDescription());
		}
		else
		{
			System.out.println("select failed");
		}
	}
}
