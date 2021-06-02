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
			<li><a href="<%=request.getContextPath()%>/home" class="nav-link">Home</a></li>
		</ul>
		<a href="<%=request.getContextPath()%>/" class="nav-link" style="position: absolute;right:5px;color:white"><i class="material-icons">logout</i> &nbsp; Log Out</a>
		</nav>
	</header>
	<br>
	<div class="content">
		<div class="container-fluid">
        	<div class="row">
            	<div class="col-md-12">
                	<div class="card">
			        	<div class="card-header card-header-primary">
			            	<h4 class="card-title ">List of Providers</h4>
			            	<div class="container text-left" >
			            		<a href="<%=request.getContextPath()%>/provider?page=new" class="btn btn-success">Add New Provider</a>
							</div>
			            </div>
						<div class="card-body">
			            	<div class="table-responsive">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th><b>ID</b></th>
											<th><b>Name</b></th>
											<th><b>Address</b></th>
											<th><b>Mobile</b></th>
											<th><b>Actions</b></th>
										</tr>
									</thead>
								<tbody>
								<!--   for (Todo todo: todos) {  -->
								<c:forEach var="provider" items="${listProvider}">
								<tr>
									<td><c:out value="${provider.getIdProvider()}" /></td>
									<td><c:out value="${provider.getName()}" /></td>
									<td><c:out value="${provider.getAddress()}" /></td>
									<td><c:out value="${provider.getMobile()}" /></td>
									
									<td>
									<a class="pen-icon" href="provider?page=edit&id=<c:out value='${provider.getIdProvider()}' />">
									<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
  									<path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
  									<path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
									</svg>
									</a>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<a class="trash-icon" href="provider?page=delete&id=<c:out value='${provider.getIdProvider()}' />">
									<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
 									<path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
  									<path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
									</svg>	
									</a></td>
								</tr>
								</c:forEach>
								<!-- } -->
								</tbody>
							</table>   
						</div>
					</div>
				</div>
				</div>
			</div>
		</div>
	</div>
	</div></div>
</body>
</html>
