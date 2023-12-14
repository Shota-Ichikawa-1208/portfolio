<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/login_register_jsp.css" />
<title>ログイン画面</title>
</head>
<body>
<div class="shop_icon">
<a href="index.html"><img src="images/icon/shop_icon.png" ></a>
</div>
<main>
<div class="main_div">

<h1>ログイン</h1>
<p>ユーザーIDとパスワードを入力してください。</p>
	<form action="/java_shop/AccountSerch" method="post">
		<label><p>ユーザーID</p><input type="text" name="loginId" required minlength = 5 maxlength = 32></label><br>
		<label><p>パスワード</p><input type="password" name="password" required  required minlength = 5 maxlength = 32></label><br>
		<input type="submit" value="ログイン" class="login_btn">
	</form>
	
	<div class="account_div">
	<p>↓新規アカウント登録はこちらから↓<br>
	<a href="register.jsp"><button class="newAccount_btn">新規アカウント登録</button></a>
	</p>
	</div>
</div>
</main>	
</body>
</html>

