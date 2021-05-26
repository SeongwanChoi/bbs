<%@ page import="dev.bbs.study.csw.enums.LoginResult" %>
<%@ page contentType="text/html" language="java" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>로그인</title>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/resource/stylesheets/common.css">
    <link rel="stylesheet" href="/resource/stylesheets/user/login.css">
</head>
<body class="login">
<%@ include file="/WEB-INF/views/header.jsp"%>
<form method="post" action="/user/login">
    <div>
        <div class="login-login">LOGIN</div>
        <div class="login-welcome">WELCOME BACK</div>
    </div>

    <label>
        <span hidden>이메일</span>
        <input autofocus maxlength="50" name="email" type="email" placeholder="이메일" value="${vo.email}">
    </label>
    <br>
    <c:choose>
        <c:when test="${vo.loginResult == LoginResult.EMAILBlank}">
            <span style="color: red; display: block;">이메일을 입력해주세요.</span>
        </c:when>
        <c:when test="${vo.loginResult != LoginResult.EMAILBlank}">
            <span style="display: none;">이메일을 입력해주세요.</span>
        </c:when>
    </c:choose>
    <label>
        <span hidden>비밀번호</span>
        <input maxlength="128" name="password" type="password" placeholder="비밀번호" value="${vo.password}">
    </label>
    <br>
    <c:choose>
        <c:when test="${vo.loginResult != LoginResult.PASSWORDBlank}">
            <span style="display: none;">비밀번호를 입력해주세요.</span>
        </c:when>
        <c:when test="${vo.loginResult == LoginResult.PASSWORDBlank}">
            <span style="color: red; display: block;">비밀번호를 입력해주세요.</span>
        </c:when>
    </c:choose>
    <c:choose>
        <c:when test="${vo.loginResult == LoginResult.NONE}">
            <span style="display: block; color: red;">이메일 혹은 비밀번호가 올바르지 않습니다.</span>
        </c:when>
        <c:when test="${vo.loginResult != LoginResult.NONE}">
            <span style="display: none;">이메일 혹은 비밀번호가 올바르지 않습니다.</span>
        </c:when>
    </c:choose>
    <input type="submit" value="로그인">
    <br>
    <a class="login-side">이메일 찾기</a>
    <a class="login-side">비밀번호 찾기</a>
    <a class="login-side" href="register">회원가입</a>
</form>
<%@ include file="/WEB-INF/views/footer.jsp"%>
</body>
</html>