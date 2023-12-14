<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/login_register_jsp.css" />
<title>新規登録画面</title>
<script>
        function validateInput(input) {
            // 半角英数字以外の文字が含まれている場合エラーメッセージを表示
            if (!/^[a-zA-Z0-9]+$/.test(input.value)) {
                alert("半角英数字のみを入力してください。");
                // エラーがある場合、入力値をクリア
                input.value = "";
                
            }
        }
    </script>
</head>
<body>
<div class="shop_icon">
<a href="index.html"><img src="images/icon/shop_icon.png" ></a>
</div>
<main>
<div class="main_div">
<h1>アカウントを作成</h1>
<p>すべての項目を入力してください</p>
<form action="/java_shop/AccountRegister" method="Post">
<label><p>お名前</p><input type="text" name="name" required></label><br>
<label><p>ユーザーID</p><input type="text" name="loginId" required  required minlength = 5 maxlength = 32 onblur="validateInput(this)"></label><br>
<label><p>パスワード</p><input type="password" name="password" required  required minlength = 5 maxlength = 32 onblur="validateInput(this)"></label><br>
<input class ="account_register_btn" type="submit" value="登録">
</form>
</div>
</main>
</body>
</html>