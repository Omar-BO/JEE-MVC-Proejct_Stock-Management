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
          <li class="nav-item active">
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
  <div class="main-panel">
	<header>
		<nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
			<div>
				<a href="<%=request.getContextPath()%>/home" class="navbar-brand"><i class="material-icons">home</i></a>
			</div>
		<ul class="navbar-nav">
			<li><a href="<%=request.getContextPath()%>/provider" class="nav-link">Providers</a></li>
		</ul>
		<a href="<%=request.getContextPath()%>/" class="nav-link" style="position: absolute;right:5px;color:white"><i class="material-icons">logout</i> &nbsp; Log Out</a>
		</nav>
	</header>
	<br>
<div style="display: flex; justify-content: center;">
	<div class="container col-md-5" style="margin: 0px;">
		<div class="card">
			<div class="card-body" >
				<c:if test="${provider != null}">
					<form action="provider?page=update" method="post">
				</c:if>
				<c:if test="${provider == null}">
					<form action="provider?page=insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${provider != null}">
            				Edit Provider
            			</c:if>
						<c:if test="${provider == null}">
            				Add New Provider
            			</c:if>
					</h2>
				</caption>

				<c:if test="${provider != null}">
					<input type="hidden" name="id" value="<c:out value='${provider.getIdProvider()}' />" />
				</c:if>

				<fieldset class="form-group">
					<b><label>Name</label></b> <input type="text"
						value="<c:out value='${provider.getName()}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<b><label>Address</label></b> <input type="text"
						value="<c:out value='${provider.getAddress()}' />" class="form-control"
						name="address" required="required">
				</fieldset>

				<fieldset class="form-group">
					<b><label>Mobile</label></b> <input type="text"
						value="<c:out value='${provider.getMobile()}' />" class="form-control"
						name="mobile" required="required">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</div>
</div></div>
</body>
</html>
