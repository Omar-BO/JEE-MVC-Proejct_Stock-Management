<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.InventoryManagement.Beans.CurrentUser" %>

<html>
<head>
<title>Stock Management Application</title>
	<!--     Fonts and icons     -->
	<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
	<!-- CSS Files -->
	<link href="INC/css/material-dashboard.css" rel="stylesheet" />
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nunito:wght@900&display=swap" rel="stylesheet">
	<script src="INC/js/vue.js"></script>
</head>
<body>
<div class="wrapper ">
    <div class="sidebar" data-color="purple" data-background-color="white" data-image="../assets/img/sidebar-1.jpg">
      <div class="logo"><a href="<%=request.getContextPath()%>/home" class="simple-text logo-normal">
          Stock management &nbsp; <i class="material-icons">inventory_2</i>
        </a></div>
      <div class="sidebar-wrapper">
        <ul class="nav">
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/category">
              <i class="material-icons">category</i>
              <p>Category</p>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/provider">
              <i class="material-icons">local_shipping</i>
              <p>Provider</p>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/product">
              <i class="material-icons">shopping_cart</i>
              <p>Product</p>
            </a>
          </li>
          <c:if test="${CurrentUser.role==0}">
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/user">
              <i class="material-icons">manage_accounts</i>
              <p>User</p>
            </a>
          </li>
          </c:if>
        </ul>
        <div class="logo"></div>
        <div  style = "position:relative; down:80px;">
        	<div style="display:flex;justify-content:center;font-size:15;font-family:Nunito">Made with ❤️ in Tunisia</div>   
        	<div style="display:flex;justify-content:center;font-size:15;font-family:Nunito">OMAR BEN OMRANE</div>
        	<div style="display:flex;justify-content:center;">
        	<a rel="noreferrer" href="https://www.linkedin.com/in/omar-ben-omrane" target="_blank" style="background-color:rgb(243, 243, 241);border-radius:50px;text-align:center;box-shadow: 2px 2px 3px #999">
        		<img src="https://cliply.co/wp-content/uploads/2021/02/372102050_LINKEDIN_ICON_TRANSPARENT_400.gif" height="50px" alt="LinkedIn"/>
       		</a> &nbsp;&nbsp;&nbsp;&nbsp;
       		<a rel="noreferrer" href="https://github.com/Omar-BO" target="_blank" >
        		<img src="https://raw.githubusercontent.com/kvssankar/kvssankar/main/octo.gif" height="50px" alt="LinkedIn"/>
       		</a>
       		&nbsp;
       		<a rel="noreferrer" href="https://github.com/Omar-BO/JEE-MVC-Proejct_Stock-Management" target="_blank" >
        		<img src="https://i.pinimg.com/originals/f7/90/15/f79015eb40067aed28ebf6f38a04a1d7.gif" height="50px" alt="LinkedIn"/>
       		</a>
       		
        	</div>
        </div>
      </div>
    </div>
  <div class="main-panel" style="text-align: center;">
	<header>
		<nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
			<div>
				<a href="<%=request.getContextPath()%>/home" class="navbar-brand"><i class="material-icons">home</i></a>
			</div>
		<ul class="navbar-nav">
			<li><a href="<%=request.getContextPath()%>/home" class="nav-link">Home</a></li>
		</ul>
		<a href="<%=request.getContextPath()%>/" class="nav-link" style="position: absolute;right:5px;color:white"><i class="material-icons">logout</i> &nbsp; Log Out</a>
		</nav>
	</header>
	<br>
	<h1 style="color: white; margin: 4% 0% 0% 52%; position: absolute; z-index: 1; text-shadow: 0 0 20px #0aafe6, 0 0 20px rgba(10, 175, 230, 0); ">WELCOME BACK <br><c:out value="${CurrentUser.username}" /> </h1>
	<div id="clock">
	    <p class="date">{{ date }}</p>
	    <p class="time">{{ time }}</p>
	</div>
	<div class="content" style="margin-top:0px; display: flex; justify-content: center;">
		<div class="welcome-nav">
	  </div>
	</div>
  </div>
</div>
<script>
var clock = new Vue({
    el: '#clock',
    data: {
        time: '',
        date: ''
    }
});

var week = ['SUN', 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT'];
var timerID = setInterval(updateTime, 1000);
updateTime();
function updateTime() {
    var cd = new Date();
    clock.time = zeroPadding(cd.getHours(), 2) + ':' + zeroPadding(cd.getMinutes(), 2) + ':' + zeroPadding(cd.getSeconds(), 2);
    clock.date = zeroPadding(cd.getFullYear(), 4) + '-' + zeroPadding(cd.getMonth()+1, 2) + '-' + zeroPadding(cd.getDate(), 2) + ' ' + week[cd.getDay()];
};

function zeroPadding(num, digit) {
    var zero = '';
    for(var i = 0; i < digit; i++) {
        zero += '0';
    }
    return (zero + num).slice(-digit);
}
</script>
</body>
</html>