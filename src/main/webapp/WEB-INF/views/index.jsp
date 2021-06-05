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
</head>
<body class="main">
    <div>
        <div>
            <c:choose>
                <c:when test="${vo == LoginResult.SUCCESS}">
                    <a href="https://icons8.com/icon/84020/user">User icon by Icons8</a>
                </c:when>
                <c:when test="${vo != LoginResult.SUCCESS}">
                    <span>접속할려면 로그인하세요</span>
                    <a href="/user/login">로그인</a>
                    <div>
                        <a>아이디</a>
                        <a>비밀번호 찾기</a>
                        <a class="register" href="/user/register">회원가입</a>
                    </div>
                </c:when>
            </c:choose>
        </div>
    </div>
</body>
</html>
