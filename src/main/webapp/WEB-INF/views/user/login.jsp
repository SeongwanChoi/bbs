<%@ page contentType="text/html" language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>로그인</title>
    <link rel="stylesheet" href="/resource/stylesheets/user/login.css">
    <script src="/resource/scripts/class.ajax.js"></script>
    <script src="/resource/scripts/user/login.js"></script>
</head>
<body class="login">
<form method="post" action="/user/login" id="login-form">
    <div>Login</div>
    <label>
        <span hidden>이메일</span>
        <input autofocus maxlength="50" name="email" type="email" placeholder="이메일" value="${vo.email}">
    </label>
    <br>
    <span class="warning" rel="email-warning" >이메일을 입력해주세요.</span>
    <label>
        <span hidden>비밀번호</span>
        <input maxlength="128" name="password" type="password" placeholder="비밀번호" value="${vo.password}">
    </label>
    <br>
    <span class="warning" rel="password-warning" >비밀번호를 입력해주세요.</span>
    <span class="warning" rel="error-warning" >가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.</span>
    <input type="submit" value="로그인">
    <br>
    <a>이메일 찾기</a>
    <a>비밀번호 찾기</a>
    <a href="register">회원가입</a>
</form>
</body>
</html>
