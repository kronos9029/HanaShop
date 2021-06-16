<%-- 
    Document   : searchResult
    Created on : Jan 11, 2021, 9:24:23 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
        <link rel="stylesheet" href="DECOR/navbar.css">
        <link rel="stylesheet" href="DECOR/table.css">
        <link rel="stylesheet" href="DECOR/searchBar.css">
        <style>
            .header #logo{
                max-width: 10%;
                height: auto;
            }
            .styled-table{
                margin-top: 120px;
                float: left;
                width: auto;
            }

            h2{
                text-align:center;
                font-size:50px;
                margin-right:10%;
            }
            .update-table {
                margin-top: 70px;
            }
            .paging{
                clear: both;
            }
            .update-table{
                margin-top: 64px;
            }
            .gap{
                height: 150px;
            }
            .paging-btn{
                display: inline-flex;
            }
            .styled-table{
                margin-top: 1%;
                margin-left: 10%;
            }
        </style>
        <script>
            function checkRemove() {
                var check = confirm("Do You Want To Delete This Product?");
                if (check == true)
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
                <c:if test="${not empty sessionScope.role}">
                    <li> <h5>Welcome, ${sessionScope.welcomeName}</h5> </li>
                    </c:if>
                <li id="active"><a href="loadFoodController" id="current">Foods</a></li>
                <li><a href="loadDrinkController">Drinks</a></li>
                <li><a href="MainController?action=createProduct">Create New Food</a></li>
                <li><a href="logoutController">Logout</a></li>
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

        <form action="MainController?page=searching" method="POST" onsubmit="return checkRemove()">
            <table class="styled-table">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Picture</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Status</th>
                        <th>Category ID</th>
                        <th>Quantity</th>
                        <th>Date Of Create</th>
                        <th>Date Of Update</th>
                        <th>Update User</th>
                        <th><input type="submit" value="Delete" name="action"/></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${result}" var="productDTO" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${productDTO.productName}</td>
                            <td><img src="${productDTO.picture}" width="70" height="70"></td>
                            <td>${productDTO.description}</td>
                            <td>${productDTO.price}</td>
                            <td>${productDTO.status}</td>
                            <td>${productDTO.cateID}</td>
                            <td>${productDTO.quantity}</td>
                            <td>${productDTO.createDate}</td>
                            <td>${productDTO.updateDate}</td>
                            <td>${productDTO.updateUser}</td>
                            <td>
                                <input type="checkbox" name="chkRemove" value="${productDTO.productID}"/>									  
                            </td>
                            <td>
                                <a href="MainController?action=updateProduct&page=${requestScope.page}&txtSearchValue=${requestScope.txtSearchValue}&searchCateID=${requestScope.searchCateID}&txtMinPrice=${requestScope.txtMinPrice}&txtMaxPrice=${requestScope.txtMaxPrice}&txtProductID=${productDTO.productID}&txtDescription=${productDTO.description}&txtProductName=${productDTO.productName}&txtPicture=${productDTO.picture}&txtPrice=${productDTO.price}&txtQuantity=${productDTO.quantity}&index=${requestScope.index}">Edit</a>
                            </td>
                    <input type="hidden" name="page" value="${requestScope.page}"/>
                    <input type="hidden" name="txtProductID" value="${requestScope.txtProductID}"/>
                    <input type="hidden" name="txtSearchValue" value="${requestScope.txtSearchValue}"/>
                    <input type="hidden" name="searchCateID" value="${requestScope.searchCateID}"/>
                    <input type="hidden" name="txtMinPrice" value="${requestScope.txtMinPrice}">
                    <input type="hidden" name="txtMaxPrice" value="${requestScope.txtMaxPrice}">
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form>

        <div class="paging">
            <c:forEach begin="1" end="${end}" var="i">
                <form class="paging-btn" action="MainController?action=Search&page=searching" method="POST">
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
