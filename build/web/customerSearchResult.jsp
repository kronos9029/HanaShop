<%-- 
    Document   : customerSearchResult
    Created on : Jan 14, 2021, 6:46:36 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Page</title>
        <link rel="stylesheet" href="DECOR/navbar.css">
        <link rel="stylesheet" href="DECOR/guestPage.css">
        <link rel="stylesheet" href="DECOR/searchBar.css">
        <style>
            .header #logo{
                max-width: 10%;
                height: auto;
            }
            .styled-table{
                margin-top: 1%;
                margin-left: 20%;
            }

            h2{
                text-align:center;
                font-size:50px;
                margin-right:10%;
            }
            .paging{
                display: flex;
                padding-top: 4%;
                padding-left: 50%;
            }
            .update-table {
                justify-content: center;
                display: flex;
            }
        </style>
    </head>
    <body>
        <div class="header">
            <img id="logo" src="https://scontent.fhan4-1.fna.fbcdn.net/v/t1.0-9/69551763_103334007713298_7341215156115865600_n.jpg?_nc_cat=110&ccb=2&_nc_sid=09cbfe&_nc_ohc=ChqPyGJoJDcAX-P4OFk&_nc_ht=scontent.fhan4-1.fna&oh=6f92ce71ca84ec950e6d954cc1a0b9b0&oe=601D1E69">
            <ul id="navlist">
                <c:if test="${not empty sessionScope.role}">
                    <li> <h5>Welcome, ${sessionScope.welcomeName}</h5> </li>
                    </c:if>
                    <c:if test="${not empty sessionScope.role}">
                    <li><h6><a href="MainController?action=viewCart">Your Cart
                                <c:if test="${not empty sessionScope.shoppingCart.cart.values()}">
                                    (${sessionScope.shoppingCart.cart.values().size()})
                                </c:if>
                            </a></h6></li>
                        </c:if>
                <li id="active"><a href="loadFoodController" id="current">Foods</a></li>
                <li><a href="loadDrinkController">Drinks</a></li>
                    <c:if test="${not empty sessionScope.role}">
                    <li> <a href="logoutController">Logout</a> </li>
                    </c:if>
                    <c:if test="${empty sessionScope.role}">
                    <li><a href="login.jsp">Login</a></li>
                    </c:if>
                <li>
                    <div class="wrap">
                        <form action="MainController?index=1&action=Search" method="POST">
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

        <div class="bodyy">
            <div class="product">
                <c:forEach items="${requestScope.result}" var="productDTO">
                    <div>
                        <div class="aM">
                            <img src="${productDTO.picture}" style="width: 100px; height: 100px"/>
                            <h4>${productDTO.productName}</h4>                
                            <h5>${productDTO.price}</h5>
                            <form action="MainController?page=searching" method="POST">
                                <input type="hidden" name="txtProductID" value="${productDTO.productID}" />
                                <input type="hidden" name="checkSearch" value="yes" />
                                <input type="hidden" name="txtSearchValue" value="${txtSearchValue}"/>
                                <input type="hidden" name="searchCateID" value="${searchCateID}"/>
                                <input type="hidden" name="txtMinPrice" value="${txtMinPrice}">
                                <input type="hidden" name="txtMaxPrice" value="${txtMaxPrice}">
                                <input type="hidden" name="index" value="${requestScope.index}"/>
                                <input type="submit" name="action" value="addToCart"/>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div class="paging">
            <c:forEach begin="1" end="${end}" var="i">
                <form action="MainController?action=Search" method="POST">
                    <input type="hidden" name="index" value="${i}"/>
                    <input type="hidden" name="txtSearchValue" value="${txtSearchValue}"/>
                    <input type="hidden" name="searchCateID" value="${searchCateID}"/>
                    <input type="hidden" name="txtMinPrice" value="${txtMinPrice}">
                    <input type="hidden" name="txtMaxPrice" value="${txtMaxPrice}">
                    <input type="submit" name="${i}" value="${i}"/>
                </form>
            </c:forEach>
        </div>

    </body>

</html>
