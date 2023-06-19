<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 15/6/2023
  Time: 9:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product Management</title>
<%--    <jsp:include page="/layout/head_css.jsp"></jsp:include>--%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

</head>
<body>
<div class="container">
    <h1 class="text-center">Product Management</h1>
    <div class="d-flex justify-content-between align-self-center">
        <div>
            <a href="/product-manager?action=create" class="btn btn-success m-3"><i class="fa-solid fa-plus"></i> Create Product</a>
        </div>
        <div class="m-3" style="height: 30px">
            <form action="/product-manager?action=search" method="get">
                <div class="container-search mt-1 d-flex">
                    <input type="text" name="kw" value="${requestScope.pageable.getKw()}">
                    <button type="submit"><i class="fas fa-search"></i></button>
                </div>
            </form>
        </div>
    </div>

    <div class="table-responsive">
        <table  class="table table-striped table-hover ">
            <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Scale</th>
                <th>Category</th>
                <th>Studio</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items='${requestScope.products}' var="p">
                <tr>
                    <td>${p.getId()}</td>
                    <td>${p.getName()}</td>
                    <td>${p.getPrice()}</td>
                    <td>${p.getLeftQuantity()}</td>
                    <td>${p.geteScale().getScale()}</td>
                    <td>
                        <c:forEach var="c" items="${categoryMap.keySet()}">
                            <c:if test="${p.getIdCategory() == c}">
                                ${categoryMap.get(c).getName()}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>${p.geteStudio().getName()}</td>
                    <td>
                        <div class="d-flex justify-content-around">
                            <a href="/product?action=view&id=${p.getId()}" class="btn btn-primary btn-sm"><i
                                    class="bi bi-eye-fill"></i></a>
                            <a href="/product-manager?action=edit&id=${p.getId()}" class="btn btn-primary btn-sm"><i
                                    class="fa-solid fa-pen"></i></a>
                            <a type="button" onclick="handleDeleteProduct(${p.getId()}, '${p.getName()}')" class="btn btn-danger btn-sm"><i
                                    class="fa-solid fa-trash-can"></i></a>
                        </div>
                    </td>
            </c:forEach>
            </tbody>
        </table>
        <div class="clearfix d-flex justify-content-center">
            <div>
                <ul class="pagination">
                    <c:if test="${pageable.getPage() > 1}">
                        <li class="page-item">
                                <%-- let url = '/customers?sortfield=' + sortfield + '&order=' + newOrder + '&keywork=' + keywork + '&customertype' + customertype;--%>

                            <a class="page-link" href="/product-manager?kw=${pageable.getKw()}&category=${pageable.getIdCategory()}&page=${pageable.getPage() + 1}">Previous</a>
                        </li>
                    </c:if>
                    <c:forEach begin="1" end="${pageable.getTotalPage()}" var="page">
                        <c:choose>
                            <c:when test="${page == pageable.getPage()}">
                                <li class="page-item active"><a class="page-link" href="#">${page}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a class="page-link" href="/product-manager?kw=${pageable.getKw()}&category=${pageable.getIdCategory()}&page=${page}">${page}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${pageable.getPage() < pageable.getTotalPage()}">
                        <li class="page-item">
                            <a class="page-link" href="/product-manager?kw=${pageable.getKw()}&category=${pageable.getIdCategory()}&page=${pageable.getPage() + 1}">Next</a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </div>
    </div>
</div>

<form method="post" id="frmHiden" action="/product-manager?action=delete">
    <input type="hidden" id="txtIdDelete" name="idDelete"  />
</form>
<%--<jsp:include page="/layout/footer_js.jsp"></jsp:include>--%>
<script>
    function handleDeleteProduct(id, name) {
        document.getElementById("txtIdDelete").value = id;
        // let cf = confirm("Bạn chắc chắn muốn xoá " + name);
        // if(cf){
        //     document.getElementById("frmHiden").submit();
        // }

        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-success',
                cancelButton: 'btn btn-danger'
            },
            buttonsStyling: false
        })

        swalWithBootstrapButtons.fire({
            title: 'Delete product "' + name + '" ?',
            text: "Are you sure?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes, delete it!',
            cancelButtonText: 'No, cancel!',
            reverseButtons: true
        }).then((result) => {
            if (result.isConfirmed) {
                swalWithBootstrapButtons.fire(
                    'Deleted!',
                )
                setTimeout(function() {
                    document.getElementById("frmHiden").submit();
                }, 1000);
            } else if (
                /* Read more about handling dismissals below */
                result.dismiss === Swal.DismissReason.cancel
            ) {
                swalWithBootstrapButtons.fire(
                    'Cancelled',
                )
            }
        })
    }
</script>
</body>
</html>
</html>
