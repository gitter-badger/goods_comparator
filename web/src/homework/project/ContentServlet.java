package homework.project;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ContentServlet
 */
@WebServlet("/ContentServlet")
public class ContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContentServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isbn = request.getParameter("isbn");
		SearchDaoImpl sd = new SearchDaoImpl();
		BookInfo bia;
		BookInfo bij;
		bia = sd.SearchByISBNinamazon(isbn);
		bij = sd.SearchByISBNinjingdong(isbn);
		request.setAttribute("aBook", bia);
		request.setAttribute("jBook", bij);
		request.setAttribute("aBookName", bia.getName());
		request.setAttribute("jBookImageURL", bij.getImageURL());
		request.setAttribute("aBookAuthor", bia.getAuthor());
		request.setAttribute("aBookPress", bia.getPress());
		request.setAttribute("aBookPublished", bia.getPublished());
		request.setAttribute("jBookPublished", bij.getPublished());
		request.setAttribute("aBookISBN", bia.getISBN());
		request.setAttribute("aBookURL", bia.getURL());
		request.setAttribute("jBookURL", bij.getURL());
		request.setAttribute("aBookPrice", bia.getPrice());
		request.setAttribute("jBookPrice", bij.getPrice());
		request.setAttribute("jBookFavRate", bij.getFavRate());
		request.setAttribute("aBookFavRate", bia.getFavRate());
		request.setAttribute("aBookReviews", bia.getReviews());
		request.setAttribute("jBookReviews", bij.getReviews());
		request.setAttribute("aBookShopNum", bia.getShopNum());
		request.setAttribute("jBookShopNum", bij.getShopNum());
		
		//评分
		int jn1 = (Float.parseFloat(bia.getPrice())<=Float.parseFloat(bij.getPrice()))?new Random().nextInt(89)%(89-87+1) + 87:new Random().nextInt(92)%(92-90+1) + 90;
		int an1 = (Float.parseFloat(bia.getPrice())<=Float.parseFloat(bij.getPrice()))?new Random().nextInt(92)%(92-90+1) + 90:new Random().nextInt(89)%(89-87+1) + 87;
		request.setAttribute("jn1", jn1);
		request.setAttribute("an1", an1);
		
		int jn2 = (Float.parseFloat(bia.getFavRate())>=Float.parseFloat(bij.getFavRate()))?new Random().nextInt(89)%(89-87+1) + 87:new Random().nextInt(92)%(92-90+1) + 90;
		int an2 = (Float.parseFloat(bia.getFavRate())>=Float.parseFloat(bij.getFavRate()))?new Random().nextInt(92)%(92-90+1) + 90:new Random().nextInt(89)%(89-87+1) + 87;
		request.setAttribute("jn2", jn2);
		request.setAttribute("an2", an2);
		
		int jn3 = (bia.getReviews()>=bij.getReviews())?new Random().nextInt(89)%(89-87+1) + 87:new Random().nextInt(92)%(92-90+1) + 90;
		int an3 = (bia.getReviews()>=bij.getReviews())?new Random().nextInt(92)%(92-90+1) + 90:new Random().nextInt(89)%(89-87+1) + 87;
		request.setAttribute("jn3", jn3);
		request.setAttribute("an3", an3);
		
		int jn4 = (bia.getShopNum()>=bij.getShopNum())?new Random().nextInt(89)%(89-87+1) + 87:new Random().nextInt(92)%(92-90+1) + 90;
		int an4 = (bia.getShopNum()>=bij.getShopNum())?new Random().nextInt(92)%(92-90+1) + 90:new Random().nextInt(89)%(89-87+1) + 87;
		request.setAttribute("jn4", jn4);
		request.setAttribute("an4", an4);
		
		
		request.getRequestDispatcher("contents.jsp").forward(request, response);
	}

}
