<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js" lang="zxx">
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Figure-shop</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <jsp:include page="/WEB-INF/homepage/layout/css_header.jsp"/>
    <style>
        .product_thumb{
            height: 340px !important;
        }
    </style>
</head>
<body>
<!-- Add your site or application content here -->

<!--pos page start-->
<div class="pos_page">
    <div class="container">
        <!--pos page inner-->
        <div class="pos_page_inner">
            <!--header area -->
            <jsp:include page="/WEB-INF/homepage/layout/head.jsp"/>
            <!--header end -->
            <!--breadcrumbs area start-->
            <div class="breadcrumbs_area">
                <div class="row">
                    <div class="col-12">
                        <div class="breadcrumb_content">
                            <ul>
                                <li><a href="index.html">home</a></li>
                                <li><i class="fa fa-angle-right"></i></li>
                                <li>shop</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <!--breadcrumbs area end-->

            <!--pos home section-->
            <div class=" pos_home_section">
                <div class="row pos_home">
                    <div class="col-lg-3 col-md-12">
                        <!--layere categorie"-->
                        <div class="d-flex align-items-start flex-column">
                            <div class="sidebar_widget shop_c">
                                <h4>Categories</h4>
                                <form action="#">
                                    <select name="category" id="cate">
                                        <option value="-1">All</option>
                                        <c:forEach var="c" items="${categoryMap.keySet()}">
                                            <option
                                                    <c:if test="${c == requestScope.pageable.getIdCategory()}">selected</c:if>
                                                    value="${c}">${categoryMap.get(c).getName()}</option>
                                        </c:forEach>
                                    </select>
                                </form>
                            </div>
                            <div class="sidebar_widget shop_c">
                                <h4>Scale</h4>
                                <form action="#">
                                    <select name="scale" id="scale">
                                        <option value="all">All</option>
                                        <c:forEach var="sc" items="${scales}">
                                            <option
                                                    <c:if test="${sc.equals(requestScope.pageable.getScale())}">selected</c:if>
                                                    value="${sc.getScale()}">${sc.getScale()}</option>
                                        </c:forEach>
                                    </select>
                                </form>
                            </div>
                        </div>
                        <!--color area end-->

                        <!--price slider start-->
                        <div class="sidebar_widget price">
                            <h2>Price</h2>
                            <div class="ca_search_filters">

                                <input type="text" name="text" id="amount">
                                <div id="slider-range"></div>
                            </div>
                        </div>
                        <!--price slider end-->

                        <!--wishlist block start-->
                        <!--wishlist block end-->

                        <!--popular tags area-->
                        <!--popular tags end-->

                        <!--newsletter block start-->
                        <!--newsletter block end-->

                        <!--special product start-->
                        <div class="sidebar_widget special">
                            <div class="block_title">
                                <h3 style="z-index: 1">Special Products</h3>
                            </div>
                            <c:forEach var="p" items="${specials}">
                                <div class="special_product_inner mb-20 row">
                                    <div class="special_p_thumb col-5">
                                        <a href="/product?action=view&id=${p.getId()}"><img src="${p.getImgLink()}" alt=""></a>
                                    </div>
                                    <div class="small_p_desc col-6">
                                        <div class="product_ratting">
                                            <ul>
                                                <li><a href="#"><i class="fa fa-star"></i></a></li>
                                                <li><a href="#"><i class="fa fa-star"></i></a></li>
                                                <li><a href="#"><i class="fa fa-star"></i></a></li>
                                                <li><a href="#"><i class="fa fa-star"></i></a></li>
                                                <li><a href="#"><i class="fa fa-star"></i></a></li>
                                            </ul>
                                        </div>
                                        <h3><a href="/product?action=view&id=${p.getId()}">${p.getName()}</a></h3>
                                        <div class="special_product_proce">
                                            <span class="old_price">$${p.getPrice() + 20}</span>
                                            <span class="new_price">$${p.getPrice()}</span>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <!--special product end-->


                    </div>
                    <div class="col-lg-9 col-md-12">
                        <!--banner slider start-->
                        <!--banner slider start-->

                        <!--shop toolbar start-->
                        <div class="shop_toolbar mb-35">

                            <div class="list_button">
                                <ul class="nav" role="tablist">
                                    <li>
                                        <a class="active" data-toggle="tab" href="#large" role="tab" aria-controls="large" aria-selected="true"><i class="fa fa-th-large"></i></a>
                                    </li>
                                    <li>
                                        <a data-toggle="tab" href="#list" role="tab" aria-controls="list" aria-selected="false"><i class="fa fa-th-list"></i></a>
                                    </li>
                                </ul>
                            </div>
                            <div class="page_amount">
                                <p>Showing 1 - 10 of ${products.size()} results</p>
                            </div>

                            <div class="select_option">
                                <form action="#">
                                    <label>Sort By</label>
                                    <select name="orderby" id="short">
                                        <option selected="" value="1">Position</option>
                                        <option value="1">Price: Lowest</option>
                                        <option value="1">Price: Highest</option>
                                        <option value="1">Product Name:Z</option>
                                        <option value="1">Sort by price:low</option>
                                        <option value="1">Product Name: Z</option>
                                        <option value="1">In stock</option>
                                        <option value="1">Product Name: A</option>
                                        <option value="1">In stock</option>
                                    </select>
                                </form>
                            </div>
                        </div>
                        <!--shop toolbar end-->

                        <!--shop tab product-->
                        <div class="shop_tab_product">
                            <div class="tab-content" id="myTabContent">
                                <div class="tab-pane fade show active" id="large" role="tabpanel">
                                    <div class="row">
                                        <c:forEach var="p" items="${products}">
                                            <div class="col-lg-4 col-md-6">
                                                <div class="single_product">
                                                    <div class="product_thumb">
                                                        <a href="/product?action=view&id=${p.getId()}"><img src="${p.getImgLink()}" alt=""></a>
<%--                                                        <div class="img_icone">--%>
<%--                                                            <img src="/homepage_frontend/assets\img\cart\span-new.png" alt="">--%>
<%--                                                        </div>--%>
                                                        <div class="product_action">
                                                            <a href="/cart?action=add&id=${p.getId()}"> <i class="fa fa-shopping-cart"></i> Add to cart</a>
                                                        </div>
                                                    </div>
                                                    <div class="product_content">
                                                        <span class="product_price">$${p.getPrice()}</span>
                                                        <h3 class="product_title"><a href="/product?action=view&id=${p.getId()}">${p.getName()}</a></h3>
                                                    </div>
                                                    <div class="product_info">
                                                        <ul>
<%--                                                            <li><a href="#" title=" Add to Wishlist ">Add to Wishlist</a></li>--%>
                                                            <li><a href="/product?action=view&id=${p.getId()}" data-toggle="modal" data-target="#modal_box" title="Quick view">View Detail</a></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="tab-pane fade" id="list" role="tabpanel">
                                    <div class="product_list_item mb-35">
                                        <c:forEach var="p" items="${products}">
                                            <div class="row align-items-center mb-2">
                                                <div class="col-lg-4 col-md-6 col-sm-6">
                                                    <div class="product_thumb">
                                                        <a href="/product?action=view&id=${p.getId()}"><img src="${p.getImgLink()}" alt=""></a>
<%--                                                        <div class="hot_img">--%>
<%--                                                            <img src="/homepage_frontend/assets\img\cart\span-hot.png" alt="">--%>
<%--                                                        </div>--%>
                                                    </div>
                                                </div>
                                                <div class="col-lg-8 col-md-6 col-sm-6">
                                                    <div class="list_product_content">
                                                        <div class="product_ratting">
                                                            <ul>
                                                                <li><a href="#"><i class="fa fa-star"></i></a></li>
                                                                <li><a href="#"><i class="fa fa-star"></i></a></li>
                                                                <li><a href="#"><i class="fa fa-star"></i></a></li>
                                                                <li><a href="#"><i class="fa fa-star"></i></a></li>
                                                                <li><a href="#"><i class="fa fa-star"></i></a></li>
                                                            </ul>
                                                        </div>
                                                        <div class="list_title">
                                                            <h3><a href="/product?action=view&id=${p.getId()}">${p.getName()}</a></h3>
                                                        </div>
                                                        <p class="design">${p.getDescription()}</p>

                                                        <div class="content_price">
                                                            <span>$${p.getPrice()}</span>
                                                            <span class="old-price">$${p.getPrice() + 20}</span>
                                                        </div>
                                                        <div class="add_links">
                                                            <ul>
                                                                <li><a href="/cart?action=add&id=${p.getId()}" title="add to cart"><i class="fa fa-shopping-cart" aria-hidden="true"></i></a></li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <!--shop tab product end-->

                        <!--pagination style start-->
                        <div class="pagination_style">
                            <div class="item_page">
                                <form action="#">
                                    <label for="page_select">show</label>
                                    <select id="page_select">
                                        <c:forEach var="limit" begin="1" end="10">
                                            <option value="${limit}">${limit}</option>
                                        </c:forEach>
                                    </select>
                                    <span>Products by page</span>
                                </form>
                            </div>
                            <div class="page_number">
                                <span>Pages: </span>
                                <nav aria-label="Page navigation example ">
                                    <ul>
                                        <c:if test="${pageable.getPage() > 1}">
                                            <li class="page-item">
                                                <a class="page-link" href="/product?kw=${pageable.getKw()}&category=${pageable.getIdCategory()}&scale=${pageable.getScale()}&page=${pageable.getPage() - 1}">
                                                    <span aria-hidden="true">&laquo;</span>
                                                </a>
                                            </li>
                                        </c:if>
                                        <c:forEach begin="1" end="${pageable.getTotalPage()}" var="page">
                                            <c:choose>
                                                <c:when test="${page == pageable.getPage()}">
                                                    <li class="page-item active">
                                                        <a class="page-link" href="#">${page}</a>
                                                    </li>
                                                </c:when>
                                                <c:otherwise>
                                                    <li class="page-item">
                                                        <a class="page-link" href="product?kw=${pageable.getKw()}&category=${pageable.getIdCategory()}&page=${page}">${page}</a>
                                                    </li>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>

<%--                                        <li class="page-item"><a class="page-link" href="#">1</a></li>--%>
<%--                                        <li class="page-item"><a class="page-link" href="#">2</a></li>--%>
<%--                                        <li class="page-item"><a class="page-link" href="#">3</a></li>--%>

                                        <c:if test="${pageable.getPage() > pageable.getTotalPage()}">
                                            <li class="page-item">
                                                <a class="page-link" href="/product?kw=${pageable.getKw()}&category=${pageable.getIdCategory()}&page=${pageable.getPage() + 1}">
                                                    <span aria-hidden="true">&raquo;</span>
                                                </a>
                                            </li>
                                        </c:if>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                        <!--pagination style end-->
                    </div>
                </div>
            </div>
            <!--pos home section end-->
        </div>
        <!--pos page inner end-->
    </div>
</div>
<!--pos page end-->

<!--footer area start-->
<jsp:include page="/WEB-INF/homepage/layout/foot.jsp"/>
<!--footer area end-->


<!-- modal area start -->

<!-- modal area end -->



<!-- all js here -->
<jsp:include page="/WEB-INF/homepage/layout/js_footer.jsp"/>
</body>
</html>
