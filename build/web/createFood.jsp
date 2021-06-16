<%-- 
    Document   : createFood
    Created on : Jan 8, 2021, 9:13:20 AM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Food</title>
		<link rel="stylesheet" href="DECOR/createFood.css">
		<script type="text/javascript">
				function checkDuplicate(){
					var id = document.getElementById("id");
					var valid = true;
					<c:forEach items="${requestScope.listID}" var="id">
						if(id.value === "${id.productID}"){
							alert("Duplicated ID!!");
							valid = false;
						}
					</c:forEach>
					return valid;
				};
		</script>
		
		
	</head>
    <body>
		
        <div class="container">  
			<form id="contact" action="MainController" method="post" onsubmit="return checkDuplicate()">
				<h3>Create New Foods/Drinks</h3>
				<fieldset>
					<select name="txtCateID" id="category">
						<c:forEach items="${requestScope.listCate}" var="cateDTO">
							<option name="txtCateID" value="${cateDTO.cateID}">${cateDTO.cateName}</option>
						</c:forEach>
					</select>
				</fieldset>
				<fieldset>
					Product ID:<input id="id" placeholder="Food/Drink ID" type="text" name="txtProductID" value="" tabindex="2" required>
				</fieldset>
				<fieldset>
					Product Name:<input placeholder="Food/Drink Name" type="text" name="txtProductName" value="" tabindex="2" minlength="1" maxlength="50" required>
				</fieldset>
				<fieldset>
					Product Description:<input placeholder="Food/Drink Description" type="text" name="txtDescription" value="" tabindex="2" required>
				</fieldset>
				<fieldset>
					Picture:<input placeholder="Food/Drink picture" type="text" name="txtProductPic" value="" tabindex="3" required>
				</fieldset>
				<fieldset>
					Price:<input placeholder="Price" type="number" min="1" step="1" name="txtProductPrice" value="" tabindex="4" required>
				</fieldset>
				<fieldset>
					Quantity:<input placeholder="Quantity" type="number" min="1" step="1" name="txtQuantity" value="" tabindex="5" required>
				</fieldset>
				<fieldset>
					<button name="action" value="Create" type="submit" id="contact-submit">Submit</button>
				</fieldset>
				<p class="copyright"><a href="MainController?page=food">Back To Main Page</a></p>
			</form>
		</div>
    </body>
</html>
