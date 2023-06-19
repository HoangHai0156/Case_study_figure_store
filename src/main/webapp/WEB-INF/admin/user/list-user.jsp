<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Editable Table | Velonic - Responsive Bootstrap 4 Admin Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content="Responsive bootstrap 4 admin template" name="description">
    <meta content="Coderthemes" name="author">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <jsp:include page="/WEB-INF/admin/layout/header_css.jsp"></jsp:include>

</head>

<body>

<!-- Begin page -->
<div id="wrapper">


    <!-- Topbar Start -->
    <jsp:include page="/WEB-INF/admin/layout/head.jsp"></jsp:include>
    <!-- end Topbar --> <!-- ========== Left Sidebar Start ========== -->
    <jsp:include page="/WEB-INF/admin/layout/left.jsp"></jsp:include>
    <!-- Left Sidebar End -->

    <!-- ============================================================== -->
    <!-- Start Page Content here -->
    <!-- ============================================================== -->

    <div class="content-page">
        <div class="content">

            <!-- Start Content-->
            <div class="container-fluid">

                <!-- start page title -->
                <div class="row">
                    <div class="col-12">
                        <div class="page-title-box">
                            <h4 class="page-title">Editable Table</h4>
                            <div class="page-title-right">
                                <ol class="breadcrumb p-0 m-0">
                                    <li class="breadcrumb-item"><a href="#">Velonic</a></li>
                                    <li class="breadcrumb-item"><a href="#">Tables</a></li>
                                    <li class="breadcrumb-item active">Editable Table</li>
                                </ol>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
                <!-- end page title -->


                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="header-title mb-4">Inline edit with Button</h4>
                                <div class="table-responsive">
                                    <table class="table table-centered mb-0 table-nowrap" id="btn-editable">
                                        <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Name</th>
                                            <th>Dob</th>
                                            <th>Address</th>
                                            <th>Phone</th>
                                            <th>Email</th>
                                            <th>Create At</th>
                                            <th>Role</th>
                                            <th>Delete At</th>
                                        </tr>
                                        </thead>

                                        <tbody>
                                            <c:forEach var="u" items="${users}">
                                                <tr>
                                                    <td>${u.id}</td>
                                                    <td>${u.name}</td>
                                                    <td>${u.dob}</td>
                                                    <td>${u.address}</td>
                                                    <td>${u.phone}</td>
                                                    <td>${u.email}</td>
                                                    <td>${u.createAt}</td>
                                                    <td>${u.eRole.name}</td>
                                                    <td>
                                                        <input style="width: 100px" type="text" name="deleteAt" value="${u.deleteAt}">
                                                        <button style="width: 60px; margin-left: 6px" class="btn btn-md btn-block btn-primary waves-effect waves-light" type="submit">Xóa</button>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- end .table-responsive-->
                            </div>
                            <!-- end card-body -->
                        </div>
                        <!-- end card -->
                    </div>
                    <!-- end col -->
                </div>
                <!-- end row -->

            </div>
            <!-- end container-fluid -->

        </div>
        <!-- end content -->



        <!-- Footer Start -->
        <jsp:include page="/WEB-INF/admin/layout/footer.jsp"></jsp:include>
        <!-- end Footer -->

    </div>

    <!-- ============================================================== -->
    <!-- End Page content -->
    <!-- ============================================================== -->

</div>
<!-- END wrapper -->


<!-- Right Sidebar -->
<jsp:include page="/WEB-INF/admin/layout/right.jsp"></jsp:include>

<jsp:include page="/WEB-INF/admin/layout/footer_js.jsp"></jsp:include>

</body>

</html>
