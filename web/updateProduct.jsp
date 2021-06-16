<%-- 
    Document   : updateProduct
    Created on : Jan 13, 2021, 8:08:00 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Product</title>
        <link rel="stylesheet" href="DECOR/createFood.css">
    </head>
    <body>
        <div class="container">  
            <form id="contact" action="MainController" method="post">
                <h3>Update Foods/Drinks</h3>
                <fieldset>
                    <select name="txtCateID" id="category">
                        <c:forEach items="${listCate}" var="cateDTO">
                            <option name="txtCateID" value="${cateDTO.cateID}">${cateDTO.cateName}</option>
                        </c:forEach>
                    </select>
                </fieldset>
                <fieldset>
                    ID:<input placeholder="${requestScope.txtProductID}" type="text" name="dd" value="${requestScope.txtProductID}" tabindex="2" disabled>
                </fieldset>
                <fieldset>
                    Name:<input placeholder="${requestScope.txtProductName}" type="text" name="txtProductName" value="${requestScope.txtProductName}" tabindex="2" required>
                </fieldset>
                <fieldset>
                    Product Description:<input placeholder="${requestScope.txtDescription}" type="text" name="txtDescription" value="${requestScope.txtDescription}" tabindex="2" required>
                </fieldset>
                <fieldset>
                    Picture:<input placeholder="${requestScope.txtPicture}" type="text" name="txtProductPic" value="${requestScope.txtPicture}" tabindex="3" required>
                </fieldset>
                <fieldset>
                    Price:<input placeholder="${requestScope.txtPrice}" type="number" min="1" step="1" name="txtProductPrice" value="${requestScope.txtPrice}" tabindex="4" required>
                </fieldset>
                <fieldset>
                    Quantity:<input placeholder="${requestScope.txtQuantity}" type="number" min="1" step="1" name="txtQuantity" value="${requestScope.txtQuantity}" tabindex="5" required>
                </fieldset>
                <fieldset>
                    status: 
                    <select name="txtStatus" tabindex="5">
                        <option name="txtStatus" value="active">active</option>
                        <option name="txtStatus" value="inactive">inactive</option>
                    </select>
                </fieldset>
                <fieldset>
                    <input type="hidden" name="index" value="${requestScope.index}"/>
                    <input type="hidden" name="page" value="${requestScope.page}"/>
                    <input type="hidden" name="txtProductID" value="${requestScope.txtProductID}"/>
                    <input type="hidden" name="txtSearchValue" value="${requestScope.txtSearchValue}"/>
                    <input type="hidden" name="searchCateID" value="${requestScope.searchCateID}"/>
                    <input type="hidden" name="txtMinPrice" value="${requestScope.txtMinPrice}">
                    <input type="hidden" name="txtMaxPrice" value="${requestScope.txtMaxPrice}">
                    <button name="action" value="Update" type="submit" id="contact-submit">Submit</button>
                </fieldset>
                <p class="copyright"><a href="loadFoodController" title="Colorlib">Back</a></p>
            </form>
        </div>
    </body>
</html>
