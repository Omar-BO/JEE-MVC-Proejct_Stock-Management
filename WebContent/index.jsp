<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.InventoryManagement.Beans.CurrentUser" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>Login</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="INC/font/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
	<link rel="icon" type="image/png" href="INC/images/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="INC/font/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="INC/css/util.css">
	<link rel="stylesheet" type="text/css" href="INC/css/main.css">
<!--===============================================================================================-->
</head>
<body>
	
	<div class="limiter">
		<div class="container-login100" style="background-image: url('INC/images/bg-02.jpg');">
			<div class="wrap-login100 p-t-30 p-b-50">
				<span class="login100-form-title p-b-41">
					Account Login
				</span>
				<form class="login100-form validate-form p-b-33 p-t-5" action="<%=request.getContextPath()%>/login" method="post">

					<div class="wrap-input100 validate-input" data-validate = "Enter username">
						<input class="input100" type="text" name="username" placeholder="User name">
						<span class="focus-input100" data-placeholder="&#xe82a;"></span>
					</div>

					<div class="wrap-input100 validate-input" data-validate="Enter password">
						<input class="input100" type="password" name="password" placeholder="Password">
						<span class="focus-input100" data-placeholder="&#xe80f;"></span>
					</div>

					<div class="container-login100-form-btn m-t-32">
						<button class="login100-form-btn">
							Login
						</button>
					</div>
					<c:if test="${ test== 1 }">
					<div class="alert" >
						<span >
							<b>Wrong Username/Password ! Please Try Again.</b>
						</span>
					</div>
					</c:if>

				</form>
			</div>
		</div>
	</div>
	

	<div id="dropDownSelect1"></div>
	
<!--===============================================================================================-->
	<script src="INC/js/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="INC/js/main.js"></script>
<!--===============================================================================================-->


</body>
</html>