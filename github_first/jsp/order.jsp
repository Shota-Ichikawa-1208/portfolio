<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

  <%@ page import="beans.Order_ProductBeans" %>
    <%@ page import="java.util.ArrayList" %>
      <%@ page import="java.text.NumberFormat" %>
        <!DOCTYPE html>
        <html>

        <head>
          <meta charset="UTF-8">
          <title>確認画面</title>
          <link rel="stylesheet" href="css/bootstrap.min.css" />
          <link rel="stylesheet" href="css/style.css" />
          <link rel="stylesheet" href="css/order_jsp.css"/>
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
                  
<!--　メイン  -->
<main>
<h2>商品確認画面</h2>

                  <%
                  ArrayList<Order_ProductBeans> cart = (ArrayList<Order_ProductBeans>)session.getAttribute("cart_list");
                  %>
                      <%
                      NumberFormat comFormat=NumberFormat.getNumberInstance();
                      %>
                        <%
                        if(cart==null){
                        %>
                          <p>カート内は空です</p>
                          <%
                          }else{
                          %>
                          <div id="main_div">
                            <table class="choice_table">
                              <tr>
                                <th>商品名</th>
                                <th>サイズ</th>
                                <th>枚数</th>
                                <th>小計</th>
                                <th
                                  style="border-top: none; border-bottom:none; border-left: none; border-right: none;">
                                </th>
                              </tr>

                              <%
                              for(int i=0;i < cart.size();i++){ Order_ProductBeans rb=cart.get(i);
                              %>
                                <tr >
                                  <td class="choice_table_td">
                                    <%=rb.getProduct_color()%>
                                  </td>
                                  <td class="choice_table_td">
                                    <%=rb.getSize()%>
                                  </td>
                                  <td class="choice_table_td">
                                    <%="" + rb.getQuantity()%>
                                  </td>
                                  <td class="choice_table_td">
                                    <%="" + comFormat.format(rb.getProduct_price()) + "円"%>
                                  </td>
                                  <td class="choice_table_delet_btn">
                                    <form action="/java_shop/Product_Delete_Servlet" method="post">
                                      <input type="hidden" value="<%=i%>" name="cart_index">
                                        <input type="submit" value="カートから削除" id="delete_button">
                                    </form>
                                  </td>
                                </tr>
                                
                                <%
                                                                }
                                                                %>
								<tr>
								<td  class="last_choice_table_td" colspan="4" style="border-bottom:none">&nbsp</td>
								<td>&nbsp</td>
								</tr>
                            </table>

                            <div class="price_table_div">
                           
                            <table class="price_table">
                            <tr> 
                            	<th >注文内容</th>                            	
                                <td  ><strong><%=cart.size() + "件"%></strong></td>
                              
                                <%
                                                              int product_total_price=0;
                                                              %>
                                <%
                                for(Order_ProductBeans rb:cart){ product_total_price +=rb.getProduct_price(); }
                                %>
                            <tr>
                                <th >商品合計</th>
                                <td ><strong><%= comFormat.format( product_total_price) + "円" %></strong>
                            </tr>
                            <tr>
                            	<th class="tax">内消費税</th>
                            	<td class="tax"><%= comFormat.format( product_total_price * 0.1) + "円" %></td>
                            </tr>   
                               </table>
                              </div> 
                             </div>
                             <div class="order_from">
                             <form action="/java_shop/Order_Confurmation" method="post" >
                              <input type="submit" value="注文を確定する" id="order_button">
                            </form>
                            </div>
                            <%} %>
</main>

				</div>
			</div>
		</div>
	</div>
	<script src="js/bootstrap.bundle.min.js"></script>
</body>
                  