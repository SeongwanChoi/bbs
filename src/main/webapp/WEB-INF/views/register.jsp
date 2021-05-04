<%@ page contentType="text/html;charset=UTF-8" language="java"  trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>회원가입</title>
</head>
<body>
<form method="post">
    <label>
        <span hidden>이메일</span>
        <input autofocus maxlength="50" name="email" type="email" property="이메일">
    </label>
    <label>
        <span hidden>비밀번호</span>
        <input maxlength="128" name="password" type="password" property="비밀번호">
    </label>
    <label>
        <span hidden>비밀번호 재입력</span>
        <input maxlength="128" name="passwordCheck" type="password" property="비밀번호 재입력">
    </label>
    <label>
        <span hidden>이름</span>
        <input maxlength="10" name="name" type="text" property="이름">
    </label>
    <label>
        <span hidden>중간이름</span>
        <input maxlength="10" name="middleName" type="text" property="중간이름">
    </label>
    <label>
        <span hidden>이름(성)</span>
        <input maxlength="10" name="nameLast" type="text" property="비밀번호">
    </label>
    <label>
        <span hidden>연락처</span>
        <input maxlength="3" name="contactFirst" type="number" property="비밀번호">
    </label>
    <label>
        <span hidden>연락처(중간)</span>
        <input maxlength="4" name="contactSecond" type="number" property="연락처(중간)">
    </label>
    <label>
        <span hidden>연락처(끝)</span>
        <input maxlength="4" name="contactThird" type="number" property="연락처(끝)">
    </label>
    <label>
        <span hidden>우편번호</span>
        <input maxlength="5" name="addressPost" type="password" property="우편번호">
    </label>
    <label>
        <span hidden>주소</span>
        <input maxlength="100" name="addressPrimary" type="password" property="주소">
    </label>
    <label>
        <span hidden>상세 주소</span>
        <input maxlength="100" name="addressSecondary" type="password" property="상세 주소">
    </label>
</form>
<input type="submit" value="회원가입">
</body>
</html>

