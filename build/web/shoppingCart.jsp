<%-- 
    Document   : shoppingCart
    Created on : Jan 15, 2021, 2:29:47 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Basket</title>
        <script src="cartJS.js"></script>
        <link rel="stylesheet" href="DECOR/shoppingCart.css">
        <script>
            function checkRemove() {
                var check = confirm("Do You Want To Remove This Product From Cart?");
                if (check === true)
                    return true;
                else
                    return false;
            }
        </script>
    </head>

    <body>
        <main>
            <div class="basket">
                <form action="MainController?" method="POST">
                    <div class="basket-module">
                        <button class="promo-code-cta" name="action" value="updateQuantity">Back</button>
                    </div>
                    <div class="basket-labels">
                        <ul>
                            <li class="item item-heading">Item</li>
                            <li class="price">Price</li>
                            <li class="quantity">Quantity</li>
                            <li class="sub-total">Subtotal</li>
                        </ul>
                    </div>
                    <c:forEach items="${sessionScope.shoppingCart.cart.values()}" var="dto" varStatus="counter">
                        <script>
                            function updatePrice${dto.productID}() {
                                var newQuantity = Number(document.getElementById("quantity${dto.productID}").value);
                                var price = Number(document.getElementById("price${dto.productID}").innerHTML);
                                var totalPrice = Number(document.getElementById("basket-total").innerHTML);

                                var oldQuantity = price /${dto.price};
                                var newPrice = price - (oldQuantity *${dto.price}) + newQuantity *${dto.price};
                                document.getElementById("price${dto.productID}").innerHTML = newPrice.toString();
                                totalPrice = totalPrice - price + newPrice;
                                document.getElementById("basket-total").innerHTML = totalPrice.toString();
                            }
                        </script>
                        <div class="basket-product">
                            <div class="item">
                                <div class="product-image">
                                    <img src="${dto.picture}" alt="Placholder Image 2" class="product-frame">
                                </div>
                                <div class="product-details">
                                    <h1><strong> ${dto.productName} </strong></h1>
                                    <p><strong>${dto.cateID}</strong></p>
                                </div>
                            </div>
                            <div class="price">${dto.price}</div>
                            <div class="quantity">
                                <input id="quantity${dto.productID}" type="number" value="${dto.quantity}" min="1" max="1000" name="txtCartQuan" onchange="updatePrice${dto.productID}()" class="quantity-field">
                            </div>
                            <div>
                                <c:if test="${not empty requestScope.OutOfStock and requestScope.StockProduct eq dto.productID}">
                                    <h4>${requestScope.OutOfStock}</h4>
                                </c:if>
                            </div>
                            <div id="price${dto.productID}" class="sub-total">${dto.cartPrice}</div>
                            <div class="remove">
                                <a href="MainController?action=Remove&txtProductID=${dto.productID}" onclick="return checkRemove()">Remove</a>
                            </div>
                        </div>
                    </c:forEach>
            </div>
            <aside>
                <div class="summary">
                    <div class="summary-total-items"><span class="total-items"></span> Items in your Bag</div>
                    <div class="summary-total">
                        <div class="total-title">Total</div>
                        <div class="total-value final-value" id="basket-total" name="totalPrice" value="${sessionScope.totalPrice}">${sessionScope.totalPrice}</div>
                    </div>
                    <c:if test="${not empty sessionScope.shoppingCart.cart.values()}">
                        <div class="summary-checkout">
                            <button class="checkout-cta" name="action" value="Checkout">Checkout</button>
                        </div>
                    </c:if>
                </div>
            </aside>
        </form>
    </main>
</body>

</html>
