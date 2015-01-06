package homework.project;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.shi.base.ResultSetHandler;
import com.util.db.JdbcTemplete;

public class SearchDaoImpl {
	private JdbcTemplete jt;
	public SearchDaoImpl() {
		jt = new JdbcTemplete();
	}
	
	public BookInfo SearchByISBNinamazon(final String isbn){
		String sql = "SELECT id,name,author,price,favRate,reviews,published,shopNum,description,url,imageURL,press FROM amazon WHERE isbn=? ";
		return (BookInfo)jt.query(sql, new ResultSetHandler() {
			
			@Override
			public Object doHandler(ResultSet rs) throws SQLException {
				BookInfo bi = null;
				if(rs.next()){
					bi = new BookInfo();
					bi.setId(rs.getInt(1));
					bi.setName(rs.getString(2));
					bi.setAuthor(rs.getString(3));
					bi.setPrice(rs.getString(4));
					bi.setFavRate(rs.getString(5));
					bi.setReviews(rs.getInt(6));
					bi.setPublished(rs.getString(7));
					bi.setShopNum(rs.getInt(8));
					bi.setDescription(rs.getString(9));
					bi.setURL(rs.getString(10));
					bi.setImageURL(rs.getString(11));
					bi.setISBN(isbn);
					bi.setPress(rs.getString(12));
					
					
				}
				return bi;
			}
		},isbn );
		
	}
	
	public BookInfo SearchByISBNinjingdong(final String isbn){
		String sql = "SELECT id,name,author,price,favRate,reviews,published,shopNum,description,url,imageURL,press FROM jingdong WHERE isbn=? ";
		return (BookInfo)jt.query(sql, new ResultSetHandler() {
			
			@Override
			public Object doHandler(ResultSet rs) throws SQLException {
				BookInfo bi = null;
				if(rs.next()){
					bi = new BookInfo();
					bi.setId(rs.getInt(1));
					bi.setName(rs.getString(2));
					bi.setAuthor(rs.getString(3));
					bi.setPrice(rs.getString(4));
					bi.setFavRate(rs.getString(5));
					bi.setReviews(rs.getInt(6));
					bi.setPublished(rs.getString(7));
					bi.setShopNum(rs.getInt(8));
					bi.setDescription(rs.getString(9));
					bi.setURL(rs.getString(10));
					bi.setImageURL(rs.getString(11));
					bi.setPress(rs.getString(12));
					bi.setISBN(isbn);
					
					
				}
				return bi;
			}
		},isbn );
		
	}
}

