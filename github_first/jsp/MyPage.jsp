<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page import="beans.AccountBeans" %>
<%@ page import="DB_Connection.My_OrderDAO" %>
<%@ page import="beans.My_OrderBeans" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">]
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/order_jsp.css"/>
<title>MyPage</title>
</head>
<body style="padding-top: 5rem">
	<div class="container-md">
            <!--画面幅固定-->
		<div class="row">
			<div class="container-md">
                <div class="row">
  <header>
            <nav class="navbar navbar-expand-md navbar-light bg-light fixed-top navbar-style">
              <div class="container">
                <a href="index.html"><img src="images/icon/shop_icon02.png"class="navbar-brand logo" ></a>

                <ul class="navbar-nav ms-auto cart_768_none">
                  <!--カートアイコン スマホ表記でハンバーガーの横に出す-->
                   <li class="nav-item">
					 <form action="/java_shop/MyPageCheck" method="post">
					 <button class="nav-link"><img src="images/login_icon.png" width="70px" height="63px"
                        title="ログイン" /></button>
                     </form>
				  </li>
                  <li class="nav-item">
                    <a href="order.jsp" class="nav-link"><img src="images/cart_icon.png" width="70px" height="70px"
                        title="カートへ" /></a>
                  </li>
                </ul>
                <button class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#main_nav">
                  <span class="navbar-toggler-icon"></span>
                </button>
                <!--メニューの画面中央表示*/-->
                <div class="collapse navbar-collapse nav-style" id="main_nav">
                  <ul class="navbar-nav ">
                    <li class="nav-item">
                      <a href="index.html" class="nav-link">
                        <p class="nav-text-style">
                          <strong class="menu-font-style">HOME</strong>
                        </p>
                      </a>
                    </li>
                    <li class="nav-item">
                      <a href="t-shirt.html" class="nav-link">
                        <p class="nav-text-style">
                          <strong class="menu-font-style">T-shirt</strong>
                        </p>
                      </a>
                    </li>
                    <li class="nav-item">
                      <a href="jeans.html" class="nav-link">
                        <p class="nav-text-style">
                          <strong class="menu-font-style">Jeans</strong>
                        </p>
                      </a>
                    </li>
                    <li class="nav-item ms-auto">
                        <p class="nav-text-style">
						<form action="/java_shop/MyPageCheck" method="post">
                          <strong><input class="nav-link menu-font-style menu_MyPage_btn" type="submit" value="MyPage"></strong>
                         </form>
                        </p>
                      
                    </li>
                  </ul>
                </div>
              </div>

              <ul class="navbar-nav ms-auto cart_767_none">
                <!--カートアイコン pc表示で画面右端表示-->
                 <li class="nav-item">
				 <li class="nav-item">
					 <form action="/java_shop/MyPageCheck" method="post">
					 <button class="nav-link"><img src="images/login_icon.png" width="70px" height="63px"
                        title="ログイン" /></button>
                     </form>
				  </li>
                
				  </li>
                  <li class="nav-item">
                <li class="nav-item">
                  <a href="order.jsp" class="nav-link cart"><img src="images/cart_icon.png" width="70px"
                      height="70px" title="カートへ" /></a>
                </li>
              </ul>
            </nav>
          </header>
<main class="MyPage_main">
     
<h1 >ユーザーページ</h1>
<% AccountBeans user = (AccountBeans)session.getAttribute("User"); %>
<div class="MyPage_main">  
<div class="logout_div">
<h2>ようこそ<%= user.getName() %>さん</h2>

<form action="/java_shop/LogOut" method="post">
<input type="submit" value="ログアウト">
</form>
</div>
<div class="order_table">
<caption>ご注文履歴</caption>
<table class="choice_table">
<tr>
<th>注文ID</th>
<th>商品名(サイズ)</th>
<th>数量</th>
<th>注文日</th>

<% My_OrderDAO moDAO = new My_OrderDAO(); %>
<% ArrayList MyOrder = new ArrayList<My_OrderBeans>();
   MyOrder = moDAO.getMyOrder(user.getLoginId());
   
   //イテレータを使いリストの要素を順番に取り出す
   Iterator<My_OrderBeans> iter = MyOrder.iterator();
   My_OrderBeans One_Order = new My_OrderBeans();
   while(iter.hasNext()){
	   One_Order = iter.next();
%>
<tr>
	<td class="choice_table_td"><%= One_Order.getOrderId() %></td>
	<td class="choice_table_td"><%= One_Order.getProductName() + "  " + "(" + One_Order.getSize() + ")"%></td>
	<td class="choice_table_td"><%= One_Order.getQuantity() %></td>
	<td class="choice_table_td"><%= One_Order.getOrderDate() %></td>
</tr>
<% 	   
   }
%>
</table>
</div>
</div> 
</main> 
</div>
</div>
</div>
</div>
	<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>