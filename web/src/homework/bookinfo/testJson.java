package homework.bookinfo;

import homework.project.MyConnection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import net.sf.json.*;

import java.io.*;
import java.sql.Connection;

public class testJson {
	//private static final String FILE_PATH = "/homework/bookinfo/bookinfo.txt"; 
	
	public static long getTime(){  
        return System.currentTimeMillis();  
    }  

public static void main(String args[]) {  
    long time1 = getTime() ;  
    // readFileByLine(FILE_PATH);// 110, 94,  94,  110, 93  
    String FILE_PATH = "src/homework/bookinfo/bookinfo_amazon.txt"; 
    //FILE_PATH = "src/homework/bookinfo/test"; 
    readFileByBybeBuffer(FILE_PATH);// 125, 78,  62,  78, 62  
    long time2 = getTime() ;  
  //  System.out.println(time2-time1);  
}

private static void readFileByBybeBuffer(String filePath) {
	// TODO Auto-generated method stub	
	File file = new File(filePath); 
    // BufferedReader:从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。  
    BufferedReader buf = null;  
    try{    	
        // FileReader:用来读取字符文件的便捷类。  
       // buf = new BufferedReader(new FileReader(file));
    	BufferedReader buf2=new BufferedReader(new FileReader(file));
    	buf2=new BufferedReader(new InputStreamReader(new FileInputStream(file), ""));
     //   if(buf==null)  System.out.println("buf=null"); 
        buf = new BufferedReader(new InputStreamReader(new FileInputStream(file), "gb2312"));  
        String temp = null ;  
        int count=0;
        
         String website="amazon";
         String Name="";             
		 String Author="";            
		 String ISBN="";                 
		 String Price;            
		 String FavRate;           
		 int Reviews;              	
		 String Published="";        
		 int ShopNum;              
		 String Description="";     
		 String URL="";  
		 String ImageURL="";
		 String Press="";
		 MyConnection myconn=new MyConnection();
		 Connection conn=myconn.getConn();
		 
        while ((temp = buf.readLine())!= null){  
        	// System.out.println((++count) + ":" + temp);  
        	 JSONObject a = JSONObject.fromObject(temp);  
             Name = (String) a.get("name"); // d  
             Author=(String) a.get("author");
             ISBN=(String) a.get("ISBN");
             Price= a.get("price").toString();
             FavRate= a.get("favRate").toString();
             Reviews=(int) a.get("reviews");
             Published=(String) a.get("published");
             ShopNum=(int) a.get("shopNum");
             Description=(String) a.get("name");
             URL=(String) a.get("URL");
             ImageURL=(String) a.get("picUrl");
             Press=(String) a.get("press");
           //  System.out.println(Name +":"+ ISBN +"," +Author +"," +Price +"," + FavRate);
             if(myconn.myinsert(conn, website, Name, Author, ISBN, Price, FavRate, Reviews, Published, ShopNum, Description, URL, ImageURL, Press))
    		{
     			//System.out.println("insert success"); 
     		}
     		else
     		{
     			//System.out.println("insert failed"); 		
     		}
             System.out.println((++count) + ":" + temp);  
           //  if(count==30) break;
        }
        if(temp==null) System.out.println(count + ":" + "temp is null");
        System.out.println("count="+count);
    }catch(Exception e){  
        System.out.println(e.getStackTrace());  
    }finally{  
        if(buf != null){  
            try{  
                buf.close();  
            } catch (IOException e) {  
                System.out.println(e.getStackTrace());  
            }  
        }  
    }  
	
}  
}