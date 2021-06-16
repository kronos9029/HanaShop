<%-- 
    Document   : history
    Created on : Jan 16, 2021, 7:59:08 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Page</title>
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
			.search-box{
				text-align: center;
			}
			.history{
				text-align: center;
			}
			.back-button{
				text-align: center;
			}
		</style>
    </head>
    <body>
		<div class="header">
			<img id="logo" src="https://scontent.fhan4-1.fna.fbcdn.net/v/t1.0-9/69551763_103334007713298_7341215156115865600_n.jpg?_nc_cat=110&ccb=2&_nc_sid=09cbfe&_nc_ohc=ChqPyGJoJDcAX-P4OFk&_nc_ht=scontent.fhan4-1.fna&oh=6f92ce71ca84ec950e6d954cc1a0b9b0&oe=601D1E69">
			<ul id="navlist">
				<li> <h5>Welcome, ${sessionScope.welcomeName}</h5> </li>
				<li id="active"><a href="MainController?action=" id="current">Foods</a></li>
				<li><a href="MainController?action=drink">Drinks</a></li>
				<c:if test="${not empty sessionScope.role}">
					<li><a href="MainController?action=history">History</a></li>
				</c:if>
				<li><a href="MainController?action=logout">Logout</a></li>
			</ul>
		</div>


        <h1 class="history">History</h1>
		<div class="search-box">
			<form action="MainController" method="POST">
				<c:if test="${sessionScope.role eq 'admin'}">
					Username: <input type="text" name="txtSearchingUser" value="" style="width: 100px"/><br/>
				</c:if>
				Product Name: <input type="text" name="txtProName" value="" style="width: 100px"/><br/>
				Month: <select name="boxMonth" size="1">
					<c:forEach begin="1" end="12" var="boxMonth">
						<option value="${boxMonth}">${boxMonth}</option>
					</c:forEach>
				</select><br/>
				<input id="button" type="submit" value="historySearch" name="action"/><br/>
			</form>
		</div>

		<c:forEach items="${requestScope.history}" var="dto">
			<table class="styled-table">
				<thead>
					<tr>
						<th>${dto.orderID}</th>
						<th>Product Name</th>
						<th>Image</th>
						<th>Quantity</th>
						<th>Price</th>
						<th>Date Of Create</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${dto.listProduct}" var="his" varStatus="counter">
						<tr>
						<tr>
							<td>${counter.count}</td>
                            <td>${his.productName}</td>
                            <td><img src="${his.picture}" style="width: 100px ; height: 100px" ></td>
                            <td>${his.quantity}</td>
                            <td>${his.price}</td>
                            <td>${his.createDate}</td>
                        </tr>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:forEach>
		<form action="MainController" method="POST">
			<button type="submit" class="back-button" value="" name="action">Back To Homepage</button>
		</form>
    </body>
</html>
