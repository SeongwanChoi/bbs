<%@ page import="dev.bbs.study.csw.enums.LoginResult" %>
<%@ page contentType="text/html" language="java" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>메인페이지</title>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/resource/stylesheets/common.css">
    <link rel="stylesheet" href="/resource/stylesheets/index.css">
    <link rel="stylesheet" href="/resource/stylesheets//user/border.css">
</head>

<body class="main">
<%@ include file="border.jsp"%>
    <div>
            <c:choose>
                <c:when test="${user != null}">
                    <div>
                        <img src="https://img.icons8.com/material-sharp/24/000000/user.png" alt=""/>
                        <a class="logout" href="/user/logout">로그아웃</a>
                    </div>
                </c:when>
                <c:when test="${user == null}">
                    <div>
                        <span>접속할려면 로그인하세요</span>
                        <a class="loginButton" href="/user/login">로그인</a>
                        <div>
                            <a>아이디</a>
                            <a>비밀번호 찾기</a>
                            <a class="register" href="/user/register">회원가입</a>
                        </div>
                    </div>
                </c:when>
            </c:choose>
    </div>
</body>
</html>
