
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>캠핑GPT - 로그인</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="/resources/css/style.css" />
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <!-- 로그인 화면 -->
    <div id="authScreen" class="auth-screen">
        <div class="auth-container">
            <div class="auth-header">
                <i class="fas fa-campground"></i>
                <h1>캠핑GPT</h1>
                <p>AI 캠핑 전문가와 함께하는 완벽한 캠핑 여행</p>
            </div>
            <!-- 로그인 폼 -->
            <div id="loginForm" class="auth-form active">
                <h2>로그인</h2>
                <form onsubmit="handleLogin(event)">
                    <div class="form-group">
                        <label for="loginEmail">이메일</label>
                        <input type="email" id="loginEmail" required>
                    </div>
                    <div class="form-group">
                        <label for="loginPassword">비밀번호</label>
                        <input type="password" id="loginPassword" required>
                    </div>
                    <button type="submit" class="btn-primary">로그인</button>
                </form>
                <div class="auth-switch">
                    <p>계정이 없으신가요? <a href="signup.html">회원가입</a></p>
                </div>
            </div>
        </div>
    </div>
    <script src="/resources/script/auth.js"></script>
</body>
</html>
