<%@ page contentType="text/html" language="java" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>이메일 찾기</title>
</head>
<body>
    <div>
        <form>
            <div></div>
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
            <input type="button" name="다음" value="다음">
        </form>
    </div>
</body>
</html>