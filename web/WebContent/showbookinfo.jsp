<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="homework.project.*, java.util.*"
   
%>
<%
    
    //List<BookInfo> bi=(List<BookInfo>)request.getAttribute("books");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询结果</title>

<link rel="stylesheet" href="css/my.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
</head>

<body > 
 <%HttpSession mySession = request.getSession(true);
	// BookInfo current = (BookInfo) mySession.getAttribute("dangdang");
	 ArrayList<BookInfo> all = (ArrayList)mySession.getAttribute("jingdong");
	 %>
<nav class="navbar navbar-inverse" role="navigation">
  　<div class="navbar-header col-md-offset-1">
  　    <a href="##" class="navbar-brand">书籍比价</a>
  　</div>
    <ul class="nav navbar-nav">
       <li class="active"><a href="index.html">首页</a></li>
       <form action="SearchServlet" method="post"  class="navbar-form navbar-left" rol="search">
   	    <div class="form-group">
   		   <input type="text" name="bookname" class="form-control" placeholder="请输入搜索的图书名" />
   	    </div>
        <button type="submit" name="search" class="btn btn-default">搜索</button>
     </form>
	</ul>
</nav>
<div id="main" align="center"  > 
 <span style="align:center"><center><h3>在京东、亚马逊上的图书结果</center>    </h3></span>
<table border="1" width="80%" align="center" >
    <thead>
        <tr >
        <td colspan="4" align="left">
       

        </td>
      </tr><tr bgcolor="#e0e0e0" >
        <th width="120px" align="center" height="100"><font size="5">书名</font></th>
        <th width="60px" align="center"><font size="5">ISBN</font></th>
        <th width="120px" align="center"><font size="5">描述</font></th>
        <th width="90px" align="center"><font size="5">了解详情</font></th>
      </tr></thead>
    <tbody>
   
    <%
			for (int i = 0; i < all.size(); i++) {
		%>
		
		<tr bgcolor="#e0e0e0" height="100"  onmouseover="cc=this.bgColor; this.bgColor='LemonChiffon'"   onmouseout ="this.bgColor=cc; " >
			<td><font size="4"><%=all.get(i).getName()%></font></td>
			<td><font size="4"><%=all.get(i).getISBN()%></font></td>
			<td><font size="4"><%=all.get(i).getDescription()%></font></td>
			<td><a href="http://localhost:8080/testg/ContentServlet?isbn=<%=all.get(i).getISBN()%>" ><font size="4">点击详情</font></a></td>
		</tr>
        </form>
		<%
			}
		%>
		
  </tbody>

 </table>

</div> 

<table border="0" width="80%" align="center"  height="400">
<tr >
<td>
<span style="align:center"><center><h3>在京东、亚马逊上一共搜到<%=all.size()%>本书</center>    </h3></span>
</td>
</tr>
<tr bgcolor="#e0e0e0">
</tr>
<td>
</td>

</table>
<footer >
<nav class="navbar navbar-inverse" role="navigation">
<div class="navbar-header col-md-offset-1">
  　    <a href="##" class="navbar-brand">java课程作业</a>
</div>
<div class="navbar-right">
  　    <a href="##" class="navbar-brand">2014年12月</a>
</div>
</nav>
</footer>
</body> 
</html>