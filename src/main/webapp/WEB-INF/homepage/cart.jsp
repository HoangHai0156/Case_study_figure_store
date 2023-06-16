<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js" lang="zxx">
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Coron-cart</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Favicon -->
    <link rel="shortcut icon" type="image/x-icon" href="assets\img\favicon.png">

    <!-- all css here -->
    <jsp:include page="/WEB-INF/homepage/layout/css_header.jsp"></jsp:include>
</head>
<body>
<!-- Add your site or application content here -->

<!--pos page start-->
<div class="pos_page">
    <div class="container">
        <!--pos page inner-->
        <div class="pos_page_inner">
            <!--header area -->
            <jsp:include page="/WEB-INF/homepage/layout/head.jsp"></jsp:include>
            <!--header end -->
            <!--breadcrumbs area start-->
            <div class="breadcrumbs_area">
                <div class="row">
                    <div class="col-12">
                        <div class="breadcrumb_content">
                            <ul>
                                <li><a href="index.html">home</a></li>
                                <li><i class="fa fa-angle-right"></i></li>
                                <li>Shopping Cart</li>
                            </ul>

                        </div>
                    </div>
                </div>
            </div>
            <!--breadcrumbs area end-->



            <!--shopping cart area start -->
            <div class="shopping_cart_area">
                <form action="#" name="cart">
                    <div class="row">
                        <div class="col-12">
                            <div class="table_desc">
                                <div class="cart_page table-responsive">
                                    <table>
                                        <thead>
                                        <tr>
                                            <th class="product_remove">Delete</th>
                                            <th class="product_thumb">Image</th>
                                            <th class="product_name">Name</th>
                                            <th class="product-price">Price</th>
                                            <th class="product_quantity">Quantity</th>
                                            <th class="product_total">Total</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${sessionScope.order.getOrderItems()}" var="oT">

                                            <c:forEach items="${requestScope.products}" var="p">
                                                <c:if test="${p.getId() == oT.getIdProduct()}">
                                                    <tr>
                                                        <td class="product_remove"><a href="#"><i class="fa fa-trash-o"></i></a></td>
                                                        <td class="product_thumb"><a href="#"><img src="${p.getImgLink()}" alt=""></a></td>
                                                        <td class="product_name"><a href="#">${p.getName()}</a></td>
                                                        <td class="product-price">${p.getPrice()}</td>
                                                        <td class="product_quantity" ><input  min="0" max="100" value="${oT.getQuantity()}" type="number" name="quantity" ></td>
                                                        <td class="product_total">${p.getPrice() * oT.getQuantity()}.00</td>


                                                    </tr>
                                                </c:if>
                                            </c:forEach>
                                        </c:forEach>
                                        <%--
                                               <tr>
                                                   <td class="product_remove"><a href="#"><i class="fa fa-trash-o"></i></a></td>
                                                   <td class="product_thumb"><a href="#"><img src="assets\img\cart\cart18.jpg" alt=""></a></td>
                                                   <td class="product_name"><a href="#">Handbags justo</a></td>
                                                   <td class="product-price">£90.00</td>
                                                   <td class="product_quantity"><input min="0" max="100" value="1" type="number"></td>
                                                   <td class="product_total">£180.00</td>


                                               </tr>
                                               <tr>
                                                   <td class="product_remove"><a href="#"><i class="fa fa-trash-o"></i></a></td>
                                                   <td class="product_thumb"><a href="#"><img src="assets\img\cart\cart19.jpg" alt=""></a></td>
                                                   <td class="product_name"><a href="#">Handbag elit</a></td>
                                                   <td class="product-price">£80.00</td>
                                                   <td class="product_quantity"><input min="0" max="100" value="1" type="number"></td>
                                                   <td class="product_total">£160.00</td>


                                               </tr>
                                             --%>

                                        </tbody>
                                    </table>
                                </div>
                                <div class="cart_submit">
                                    <button type="button">Update cart</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--coupon code area start-->
                    <div class="coupon_area">
                        <div class="row">
                            <div class="col-lg-6 col-md-6">
                                <div class="coupon_code">
                                    <h3>Coupon</h3>
                                    <div class="coupon_inner">
                                        <p>Enter your coupon code if you have one.</p>
                                        <input placeholder="Coupon code" type="text">
                                        <button type="submit">Apply coupon</button>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6">
                                <div class="coupon_code">
                                    <h3>Cart Totals</h3>
                                    <div class="coupon_inner">
                                        <div class="cart_subtotal">
                                            <p>Subtotal</p>
                                            <p class="cart_amount">${order.getSubTotal()}</p>
                                        </div>
                                        <div class="cart_subtotal ">
                                            <p>Discount</p>
                                            <p class="cart_amount"><span>Discount</span> ${order.getDiscount()}%</p>
                                        </div>
                                        <div class="cart_subtotal ">
                                            <p>Shipping</p>
                                            <p class="cart_amount"><span>Ship:</span> 10</p>
                                        </div>
                                        <a href="#">Calculate shipping</a>

                                        <div class="cart_subtotal">
                                            <p>Total</p>
                                            <p class="cart_amount">${order.getSubTotal() - order.getDiscount() * order.getSubTotal() + 10}</p>
                                        </div>
                                        <div class="checkout_btn">
                                            <a href="#">Proceed to Checkout</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--coupon code area end-->
                </form>
            </div>
            <!--shopping cart area end -->

        </div>
        <!--pos page inner end-->
    </div>
</div>
<!--pos page end-->

<!--footer area start-->
<jsp:include page="/WEB-INF/homepage/layout/foot.jsp"></jsp:include>
<!--footer area end-->




<!-- all js here -->
<jsp:include page="/WEB-INF/homepage/layout/js_footer.jsp"></jsp:include>
<script>
    function handleQuantityChange(idProduct, element){
        let newQuantity = element.value;
        console.log(newQuantity);
        let url = "/cart?action=change-quantity&id=" + idProduct;
        window.location.assign(url);
    }
</script>
</body>
</html>
