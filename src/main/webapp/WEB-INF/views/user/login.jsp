<%@ page contentType="text/html" language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>로그인</title>
</head>
<body>
<form method="post">
    <label>
        <span hidden>이메일</span>
        <input autofocus maxlength="10" name="email" type="email" property="이메일">
    </label>
    <label>
        <span hidden>비밀번호</span>
        <input maxlength="128" name="password" type="password" property="비밀번호">
    </label>
</form>
<input type="submit" value="로그인">
</body>
</html>
