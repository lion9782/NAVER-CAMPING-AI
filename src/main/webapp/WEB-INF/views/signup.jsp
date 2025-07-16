
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>캠핑GPT - 회원가입</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="/resources/css/style.css" />
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <!-- 회원가입 화면 -->
    <div id="authScreen" class="auth-screen">
        <div class="auth-container">
            <div class="auth-header">
                <i class="fas fa-campground"></i>
                <h1>캠핑GPT</h1>
                <p>AI 캠핑 전문가와 함께하는 완벽한 캠핑 여행</p>
            </div>
            <!-- 회원가입 폼 -->
            <div id="signupForm" class="auth-form active">
                <h2>회원가입</h2>
                <form onsubmit="handleSignup(event)">
                    <div class="form-group">
                        <label for="signupName">이름</label>
                        <input type="text" id="signupName" required>
                    </div>
                    <div class="form-group">
                        <label for="signupEmail">이메일</label>
                        <input type="email" id="signupEmail" required>
                    </div>
                    <div class="form-group">
                        <label for="signupPassword">비밀번호</label>
                        <input type="password" id="signupPassword" required>
                    </div>
                    <div class="form-group">
                        <label for="confirmPassword">비밀번호 확인</label>
                        <input type="password" id="confirmPassword" required>
                    </div>
                    <button type="submit" class="btn-primary">회원가입</button>
                </form>
                <div class="auth-switch">
                    <p>이미 계정이 있으신가요? <a href="login.html">로그인</a></p>
                </div>
            </div>
        </div>
    </div>
    <script src="/resources/script/auth.js"></script>
</body>
</html>
