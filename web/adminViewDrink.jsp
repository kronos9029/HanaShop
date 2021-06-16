<%-- 
    Document   : adminViewDrink
    Created on : Jan 8, 2021, 12:13:59 AM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
		<link rel="stylesheet" href="DECOR/navbar.css">
		<link rel="stylesheet" href="DECOR/table.css">
		<link rel="stylesheet" href="DECOR/searchBar.css">
		<style>
			.header #logo{
				max-width: 10%;
				height: auto;
			}
			.styled-table{
				float: left;
				width: auto;
				margin-left: 2%;
			}

			h2{
				text-align:center;
				font-size:50px;
				margin-right:10%;
			}
			.paging{
				text-align: center;
			}
			.update-table {
				margin-top: 70px;
			}
			.paging{
				clear: both;
				margin-top: 30px;
			}
			.update-table{
				margin-top: 64px;
			}
			.gap{
				height: 30px;
			}
		</style>
		<script>
			function checkRemove(){
				var check = confirm("Do You Want To Delete This Product?");
				if(check == true)
					return true;
				else
					return false
			}
		</script>
    </head>
    <body>
		<div class="header">
			<img id="logo" src="https://scontent.fhan4-1.fna.fbcdn.net/v/t1.0-9/69551763_103334007713298_7341215156115865600_n.jpg?_nc_cat=110&ccb=2&_nc_sid=09cbfe&_nc_ohc=ChqPyGJoJDcAX-P4OFk&_nc_ht=scontent.fhan4-1.fna&oh=6f92ce71ca84ec950e6d954cc1a0b9b0&oe=601D1E69">
			<ul id="navlist">
				<li> <h5>Welcome, ${sessionScope.welcomeName}</h5> </li>
				<li id="active"><a href="MainController?action=" id="current">Foods</a></li>
				<li><a href="MainController?action=drink">Drinks</a></li>
				<li><a href="MainController?action=createProduct">Create New Food</a></li>
				<c:if test="${not empty sessionScope.role}">
					<li><a href="MainController?action=history">History</a></li>
				</c:if>
				<li><a href="MainController?action=logout">Logout</a></li>
				<li>
					<div class="wrap">
						<form action="MainController?page=searching&index=1&action=Search" method="POST">
							<div class="search">
								<input type="text" value="" name="txtSearchValue" class="searchTerm" placeholder="What are you looking for?">
								<input type="number" value="" name="txtMinPrice" class="searchTerm" placeholder="Min Price"><br/>
								<input type="number" value="" name="txtMaxPrice" class="searchTerm" placeholder="Max Price">
								<select name="searchCateID" class="category">
									<option name="searchCateID" value="all">All</option>
									<c:forEach items="${listCate}" var="cateDTO">
										<option name="searchCateID" value="${cateDTO.cateID}">${cateDTO.cateName}</option>
									</c:forEach>
								</select>
								<button type="submit" class="searchButton">
									<i class="fa fa-search"></i>
								</button>
							</div>
						</form>
					</div>
				</li>
			</ul>
		</div>

		<h2>Drinks</h2>
		<form action="MainController?page=drink" method="POST">
			<table class="styled-table">
				<thead>
					<tr>
						<th>No.</th>
						<th>Name</th>
						<th>Picture</th>
						<th>Description</th>
						<th>Price</th>
						<th>Status</th>
						<th>Quantity</th>
						<th>Date Of Create</th>
						<th>Date Of Update</th>
						<th>Update User</th>
						<th><input type="submit" value="Delete" name="action"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${requestScope.result}" var="productDTO" varStatus="counter">
						<tr>
							<td>${counter.count}</td>
							<td>${productDTO.productName}</td>
							<td><img src="${productDTO.picture}" width="70" height="70"></td>
							<td>${productDTO.description}</td>
							<td>${productDTO.price}</td>
							<td>${productDTO.status}</td>
							<td>${productDTO.quantity}</td>
							<td>${productDTO.createDate}</td>
							<td>${productDTO.updateDate}</td>
							<td>${productDTO.updateUser}</td>
							<td>
								<input type="checkbox" name="chkRemove" value="${productDTO.productID}"/>									  
							</td>
							<td>
								<a href="MainController?page=drink&action=updateProduct&txtProductID=${productDTO.productID}&txtProductName=${productDTO.productName}&txtPicture=${productDTO.picture}&txtDescription=${productDTO.description}&txtPrice=${productDTO.price}&txtQuantity=${productDTO.quantity}&index=${requestScope.index}">Edit</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>

		
		<div class="paging">
			<c:forEach begin="1" end="${endPage}" var="i">
				<a href="MainController?action=drink&index=${i}">${i}</a>
			</c:forEach>
		</div>

    </body>

</html>
