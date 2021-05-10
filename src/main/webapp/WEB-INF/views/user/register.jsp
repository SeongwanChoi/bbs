<%@ page import="dev.bbs.study.csw.enums.RegisterResult" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>회원가입</title>
    <link rel="stylesheet" href="/resource/stylesheets/user/register.css">
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script src="/resource/scripts/class.ajax.js"></script>
    <script src="/resource/scripts/user/register.js"></script>
</head>
<body class="register">
<form action="/user/register" id="register-form" method="post">
    <label>
        <span hidden>이메일</span>
        <input autofocus maxlength="50" name="email" type="email" placeholder="이메일" value="${registerVo.email}">
        <span class="warning" rel="email-warning" >해당 이메일은 이미 사용 중입니다.</span>
    </label>
    <br>
    <label>
        <span hidden>비밀번호</span>
        <input maxlength="128" name="password" type="password" placeholder="비밀번호">
    </label>
    <br>
    <label>
        <span hidden>비밀번호 재입력</span>
        <input maxlength="128" name="passwordCheck" type="password" placeholder="비밀번호 재입력">
    </label>
    <br>
    <label>
        <span hidden>닉네임</span>
        <input maxlength="10" name="nickname" type="text" placeholder="닉네임" value="${registerVo.nickname}">
        <span class="warning" rel="nickname-warning">해당 닉네임은 이미 사용 중입니다.</span>
    </label>
    <br>
    <label>
        <span hidden>이름</span>
        <input maxlength="10" name="nameFirst" type="text" placeholder="이름">
    </label>
    <label>
        <span hidden>중간이름</span>
        <input maxlength="10" name="nameOptional" type="text" placeholder="중간이름">
    </label>
    <label>
        <span hidden>이름(성)</span>
        <input maxlength="10" name="nameLast" type="text" placeholder="이름(성)">
    </label>
    <br>
    <label>
        <span hidden>연락처</span>
        <input maxlength="3" name="contactFirst" type="number" placeholder="연락처">
    </label>
    <label>
        <span hidden>연락처(중간)</span>
        <input maxlength="4" name="contactSecond" type="number" placeholder="연락처(중간)">
    </label>
    <label>
        <span hidden>연락처(끝)</span>
        <input maxlength="4" name="contactThird" type="number" placeholder="연락처(끝)">
    </label>
    <br>
    <label>
        <span hidden>우편번호</span>
        <input maxlength="5" name="addressPost" type="password" placeholder="우편번호">
    </label>
    <input name="addressPostButton" type="button" value="우편번호 찾기">
    <br>
    <label>
        <span hidden>주소</span>
        <input maxlength="100" name="addressPrimary" type="password" placeholder="주소">
    </label>
    <br>
    <label>
        <span hidden>상세 주소</span>
        <input maxlength="100" name="addressSecondary" type="password" placeholder="상세 주소">
    </label>
    <br>
    <input type="submit" value="회원가입">
</form>
</body>
</html>

