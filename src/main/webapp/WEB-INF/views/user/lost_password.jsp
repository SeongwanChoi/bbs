<%@ page contentType="text/html" language="java" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/resource/stylesheets/common.css">
    <link rel="stylesheet" href="/resource/stylesheets/user/border.css">
    <script src="../resource/scripts/class.ajax.js"></script>
    <script src="../resource/scripts/user/passwordLost.js"></script>
    <title>비밀번호 찾기</title>
</head>
<body>
<%@ include file="../border.jsp"%>
    <div>
        <form id="passwordLost-Form">
            <div></div>
            <label>
                <span hidden>이메일</span>
                <input autofocus maxlength="50" name="email" type="email" placeholder="이메일" value="${registerVo.email}">
            </label>
            <label>
                <span hidden>이름</span>
                <input maxlength="10" name="nameFirst" type="text" placeholder="이름" value="${registerVo.nameFirst}">
            </label>
            <label>
                <span hidden>중간이름</span>
                <input maxlength="10" name="nameOptional" type="text" placeholder="중간이름"value="${registerVo.nameOptional}">
            </label>
            <label>
                <span hidden>이름(성)</span>
                <input maxlength="10" name="nameLast" type="text" placeholder="이름(성)" value="${registerVo.nameLast}">
            </label>
            <br>
            <label>
                <span hidden>연락처</span>
                <input maxlength="3" name="contactFirst" type="tel" placeholder="연락처" value="${registerVo.contactFirst}">
            </label>
            <label>
                <span hidden>연락처(중간)</span>
                <input maxlength="4" name="contactSecond" type="number" placeholder="연락처(중간)" value="${registerVo.contactSecond}">
            </label>
            <label>
                <span hidden>연락처(끝)</span>
                <input maxlength="4" name="contactThird" type="number" placeholder="연락처(끝)" value="${registerVo.contactThird}">
            </label>
            <input name="sendCodeButton" type="button" value="다음">
            <br>
            <label hidden rel="authCode">
                <span hidden >인증코드</span>
                <input maxlength="6" name="authCode" type="number" placeholder="인증코드">
            </label>
            <br>
            <input hidden name="code" type="button" value="코드 확인">
            <br>
            <label hidden rel="password">
                <span hidden>비밀번호</span>
                <input maxlength="128" name="password" type="password" placeholder="비밀번호" value="${registerVo.password}">
            </label>
            <br>
            <label hidden rel="passwordCheck">
                <span hidden>비밀번호 재입력</span>
                <input maxlength="128" name="passwordCheck" type="password" placeholder="비밀번호 재입력">
            </label>
            <input hidden name="key" type="hidden">
            <br>
            <input hidden name="submit" type="submit" value="비밀번호 변경">
        </form>
    </div>
</body>
</html>