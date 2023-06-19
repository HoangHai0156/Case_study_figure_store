<%--
  Editd by IntelliJ IDEA.
  User: Admin
  Date: 15/6/2023
  Time: 9:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

</head>
<body>
<div class="container justify-center" style="align-items: center;">
    <div>
        <a href="product-manager" class="btn btn-primary">Back</a>
    </div>
    <form method="post">
        <fieldset>
            <legend class="text-center"><h4>Product Information</h4></legend>
            <c:if test="${requestScope.errors != null}">
                <div class="alert alert-danger">
                    <ul>
                        <c:forEach items="${requestScope.errors}" var="error">
                            <li>${error}</li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>

            <c:if test="${requestScope.message != null}">
                <div class="alert alert-success text-center">
                    <span class="message">${requestScope.message}</span>
                </div>
            </c:if>

            <table class="table table-striped table-hover">
                <tr>
                    <td>Name: </td>
                    <td><input type="text" name="name" id="name" class="form-control" value="${requestScope.product.getName()}"></td>
                </tr>
                <tr>
                    <td>Price </td>
                    <td><input type="text" name="price" id="price" class="form-control" value="${requestScope.product.getPrice()}"></td>
                </tr>
                <tr>
                    <td>Quantity: </td>
                    <td><input type="text" name="quantity" id="quantity" class="form-control" value="${requestScope.product.getLeftQuantity()}"></td>
                </tr>
                <tr>
                    <td>Scale: </td>
                    <td>
                        <select name="scale" class="form-control">
                            <c:forEach var="sc" items="${scales}">
                                <option
                                        <c:if test="${sc.getScale().equals(requestScope.product.geteScale().getScale())}">selected</c:if>
                                        value="${sc.getScale()}">${sc.getScale()}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Category </td>
                    <td>
                        <select name="category" class="form-control">
                            <c:forEach var="c" items="${categoryMap.keySet()}">
                                <option
                                        <c:if test="${c == requestScope.product.getIdCategory()}">selected</c:if>
                                        value="${c}"}>${categoryMap.get(c).getName()}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Description: </td>
                    <td><input type="text" name="description" id="description" class="form-control" value="${requestScope.product.getDescription()}"></td>
                </tr>
                <tr>
                    <td>Img Link: </td>
                    <td><input type="text" name="imgLink" id="imgLink" class="form-control" value="${requestScope.product.getImgLink()}"></td>
                </tr>
                <tr>
                    <td>Studio: </td>
                    <td>
                        <select name="studio" class="form-control">
                            <c:forEach var="st" items="${studios}">
                                <option
                                        <c:if test="${st.getName().equals(requestScope.product.geteStudio().getName())}">selected</c:if>
                                        value="${st.getName()}">${st.getName()}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Edit Product" class="btn btn-primary"></td>
                </tr>
            </table>
        </fieldset>
    </form>
</div>
</body>
</html>
