package homework.project;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet(name = "SearchServle", urlPatterns = { "/SearchServle" })
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String bookname=request.getParameter("bookname");
		//System.out.println("bookname=" + bookname);
		MyConnection myconn=new MyConnection();
		Connection conn=myconn.getConn();		
		BookInfo bookinfo_jingdong = new BookInfo();
		try{
		
		if(myconn.myselectByName(conn, bookinfo_jingdong, "jingdong",  bookname))
		{	
			ArrayList<BookInfo> bookinfoList_jingdong= bookinfo_jingdong.getBookInfoList();
			HttpSession session_jingdong = request.getSession();
			session_jingdong.setAttribute("jingdong", bookinfoList_jingdong);
		//	System.out.println("正常," + bookinfo_jingdong.getName() + ":" + bookinfo_jingdong.getDescription());
			System.out.println("bookinfoList.size() = " + bookinfoList_jingdong.size());
			for (int i = 0; i < bookinfoList_jingdong.size(); i++)
			{
				System.out.println(bookinfoList_jingdong.get(i).getName()+":" + bookinfoList_jingdong.get(i).getDescription());
			}
		}
		else
		{
			System.out.println("select failed");
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		BookInfo bookinfo_amazon = new BookInfo();
		try{
		if(myconn.myselectByName(conn, bookinfo_amazon, "amazon",  bookname))
		{	
			ArrayList<BookInfo> bookinfoList_amazon= bookinfo_amazon.getBookInfoList();
			HttpSession session_dangdang = request.getSession();
			session_dangdang.setAttribute("amazon", bookinfoList_amazon);
		//	System.out.println("正常," + bookinfo_dangdang.getName() + ":" + bookinfo_dangdang.getDescription());
			System.out.println("bookinfoList_amazon.size() = " + bookinfoList_amazon.size());
			for (int i = 0; i < bookinfoList_amazon.size(); i++)
			{
				System.out.println(bookinfoList_amazon.get(i).getName()+":" + bookinfoList_amazon.get(i).getDescription());			
			}
		}
		else
		{
			System.out.println("select failed");
		}
		request.getRequestDispatcher("/showbookinfo.jsp").forward(request, response);
		conn.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
