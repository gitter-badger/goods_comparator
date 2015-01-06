<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书籍页面-${requestScope.aBookName }</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="mycss.css">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/Chart.js"></script>
</head>
<body>
<nav class="navbar navbar-inverse" role="navigation">
  　<div class="navbar-header col-md-offset-1">
  　    <a href="##" class="navbar-brand">书籍比价</a>
  　</div>
    <ul class="nav navbar-nav">
       <li class="active"><a href="index.html">首页</a></li>
       <form action="SearchServlet" method="post"  class="navbar-form navbar-left" role="search">
   	    <div class="form-group">
   		   <input type="text" name="bookname" class="form-control" placeholder="请输入搜索的图书名" />
   	    </div>
        <button type="submit" name="search" class="btn btn-default">搜索</button>
     </form>
	</ul>
</nav>
<div class="container">
	<div class="row">
		<div class="col-md-3 col-md-offset-1">
			<a href="${requestScope.aBookURL }" target="_blank" class="thumbnail ">
				<img src="${requestScope.jBookImageURL }" style="height: 300px; width: 100%; display: block;" alt="点击查看详细">
			</a>
		</div>
		<div class="col-md-6"><h3>${requestScope.aBookName }</h3>
		<blockquote>
		<ul class="list-unstyled">
        <li>作 者	${requestScope.aBookAuthor }</li>
        <li>出版社	${requestScope.aBookPress }</li>
		<li>出版时间	${requestScope.aBookPublished }</li>
		<li>ＩＳＢＮ	${requestScope.aBookISBN }</li>
		<li>.</li>
		<li>.</li>
        </ul>
        </blockquote>
		<div class="bshare-custom icon-medium"><div class="bsPromo bsPromo2"></div>分享到<a title="分享到微信" class="bshare-weixin" href="javascript:void(0);"></a><a title="分享到新浪微博" class="bshare-sinaminiblog"></a><a title="分享到人人网" class="bshare-renren" href="javascript:void(0);"></a><a title="分享到QQ好友" class="bshare-qqim" href="javascript:void(0);"></a><a title="分享到Twitter" class="bshare-twitter" href="javascript:void(0);"></a></div><script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/buttonLite.js#style=-1&amp;uuid=&amp;pophcol=2&amp;lang=zh"></script><script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/bshareC0.js"></script>
	</div>
	</div>
	<div class="row">
		<div class="col-md-5 col-md-offset-1">
		<p></p>
	<table class="table table-striped  table-hover">
   <thead>
     <tr>
       <th><span class="btn  btn-sm btn-default">比较维度</span></th>
       <th><a href="${requestScope.jBookURL }" target="_blank"><span class="btn  btn-sm btn-primary">京东</span></a></th>
       <th><a href="${requestScope.aBookURL }" target="_blank"><span class="btn  btn-sm btn-success">亚马逊</span></a></th>
     </tr>
   </thead>
   <tbody>
     <tr>
       <td>价格</td>
       <td>${requestScope.jBookPrice }</td>
       <td>${requestScope.aBookPrice }</td>
     </tr>
     <tr>
       <td>评分</td>
       <td>${requestScope.jBookFavRate }</td>
       <td>${requestScope.aBookFavRate }</td>
     </tr>
     <tr>
       <td>收藏次数</td>
       <td>${requestScope.jBookReviews }</td>
       <td>${requestScope.aBookReviews }</td>
     </tr>
     <tr>
       <td>卖家数量</td>
       <td>${requestScope.jBookShopNum }</td>
       <td>${requestScope.aBookShopNum }</td>
     </tr>
	 <tr>
       <td>出版时间</td>
       <td>${requestScope.jBookPublished }</td>
       <td>${requestScope.aBookPublished }</td>
     </tr>
   </tbody>
 </table></div>
		<div class="col-md-3"><canvas id="myChart" height="340" width="340"></canvas> </div>
	</div>
</div>
<footer>
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

<script>  

	var jn1 = ${requestScope.jn1 };
	var an1 = ${requestScope.an1 };
	var jn2 = ${requestScope.jn2 };
	var an2 = ${requestScope.an2 };
	var jn3 = ${requestScope.jn3 };
	var an3 = ${requestScope.an3 };
	var jn4 = ${requestScope.jn4 };
	var an4 = ${requestScope.an4 };
	
	
    var data = {  
            labels : ["价格","评分","收藏次数","卖家数量","出版时间"],  
            datasets : [  
                {  
                    fillColor : "rgba(66,139,202,0.5)",  
                    strokeColor : "rgba(66,139,202,1)",  
                    pointColor : "rgba(66,139,202,1)",  
                    pointStrokeColor : "#fff",  
                    data : [jn1,jn2,jn3,jn4,90]  
                },  
                {  
                    fillColor : "rgba(92,184,92,0.5)",  
                    strokeColor : "rgba(92,184,92,1)",  
                    pointColor : "rgba(92,184,92,1)",  
                    pointStrokeColor : "#fff",  
                    data : [an1,an2,an3,an4,90]  
                }  
            ]  
        };
    
  	//var ctx = document.getElementById("myChart").getContext("2d");
    var ctx = $("#myChart").get(0).getContext("2d");
  	var myNewChart = new Chart(ctx).Radar(data);  
          
    </script>  

</html>