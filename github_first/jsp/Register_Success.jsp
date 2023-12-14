<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page import="beans.AccountBeans" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">]
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/style.css" />
<title>MyPage</title>
</head>
<body style="padding-top: 5rem">
	<div class="container-md">
            <!--画面幅固定-->
		<div class="row">
			<div class="container-md">
                <div class="row">
                  <header class="header">
                  
            <nav class="navbar navbar-expand-md navbar-light bg-light fixed-top navbar-style">
              <div class="container">
                <a class="navbar-brand logo" href="#">bootstrap</a>

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
                  <ul class="navbar-nav">
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
					 <form action="/java_shop/MyPageCheck" method="post">
					 <button class="nav-link"><img src="images/login_icon.png" width="70px" height="63px"
                        title="ログイン" /></button>
                     </form>
				  </li>
                  <li class="nav-item">
                <li class="nav-item">
                  <a href="order.jsp" class="nav-link cart"><img src="images/cart_icon.png" width="70px"
                      height="70px" title="カートへ" /></a>
                </li>
              </ul>
            </nav>
          </header>
<main>         
	<h1>ご登録完了</h1>
<% String msg= (String)session.getAttribute("msg"); %>
<p>ようこそ
<%= "" +msg %>
</p>
</main> 
</body>
			</div>
			</div>
		</div>
	</div>
	<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>