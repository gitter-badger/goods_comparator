package homework.project;
import java.sql.*;
import java.util.ArrayList;

public class MyConnection {
	public Connection getConn() 
	{
		Connection conn=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(
			          "jdbc:mysql://localhost:3306/javaproject?useUnicode=true&characterEncoding=utf-8","root","123456");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return conn;
	}
	public boolean myinsert(Connection conn,String website, String name,String author,String iSBN, String price, String favRate, int reviews,
			String published, int shopNum,String description,String url, String imageURL, String press)
			{	
		String checksql="select * from "+ website + " where isbn="+iSBN;
		try{
			Statement checkstat = conn.prepareStatement(checksql);
			ResultSet checkrs = checkstat.executeQuery(checksql);
			
			if(checkrs.next())
			{
				return false;
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		String sql="insert into " + website +" (name, author, isbn, price, favRate, reviews, published, shopNum, description, url, imageURL, press) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		try
		{
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setString(1, name);
			pstat.setString(2, author);
			pstat.setString(3, iSBN);
			pstat.setString(4, price);
			pstat.setString(5, favRate);
			pstat.setInt(6, reviews);
			pstat.setString(7, published);
			pstat.setInt(8, shopNum);
			pstat.setString(9, description);
			pstat.setString(10, url);
			pstat.setString(11, imageURL);
			pstat.setString(12, press);
			pstat.executeUpdate();
			return true;			
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}

	//��ѯ���µ�version
	public boolean myselectByName(Connection conn, BookInfo book, String website,  String name)
	{   
	    String sp[] = name.split(" ");
	    int i;
	    for(i=1; i<sp.length; i++)
	    {
	    	sp[0].concat(sp[i]);
	    }
	    String change="";
	    if(website.equals("jingdong"))change="amazon";
	    if(website.equals("amazon"))change="jingdong";
	    
		String sql="select * from "+ website + " where name like '%" +sp[0] + "%' AND isbn IN (select isbn from " +change+")" ;
		try
		{
			ArrayList<BookInfo> list = new ArrayList<BookInfo>();
			Statement stat = conn.prepareStatement(sql);
			ResultSet rs = stat.executeQuery(sql);
			int row=0;
			while(rs.next())
			{	
				row++;
				book.setId(rs.getInt("id"));
				book.setAuthor(rs.getString("author"));
				book.setDescription(rs.getString("description"));
				book.setFavRate(rs.getString("favRate"));
				book.setISBN(rs.getString("isbn"));
				book.setName(rs.getString("name"));
				//System.out.println("setName=" + book.getName());
				book.setPrice(rs.getString("price"));
				book.setPublished(rs.getString("published"));
				book.setReviews(rs.getInt("reviews"));
				book.setShopNum(rs.getInt("shopNum"));
				book.setURL(rs.getString("url"));
				//public BookInfo(String name,String author,String iSBN, double price, double favRate, int reviews,
						//String published, int shopNum,String description,String url)
			    list.add(new BookInfo(rs.getString("name"), rs.getString("author"),rs.getString("isbn"),
			    		rs.getString("price"), rs.getString("favRate"),  rs.getInt("reviews"), rs.getString("published"),
			    		rs.getInt("shopNum"), rs.getString("description"), rs.getString("url"), rs.getString("imageURL"), 
			    		rs.getString("press")));
			   	
			}	
			book.setBookinfoList(list);
			System.out.println("row=" + row);
			return true;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
//		finally
//		{
//			try
//			{
//				conn.close();
//			}catch(Exception e)
//			{
//				e.printStackTrace();
//			}
//		}
		return false;
	}
	
}
