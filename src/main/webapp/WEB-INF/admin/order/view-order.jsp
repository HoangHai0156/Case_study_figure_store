<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Data Table | Velonic - Responsive Bootstrap 4 Admin Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content="Responsive bootstrap 4 admin template" name="description">
    <meta content="Coderthemes" name="author">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- App favicon -->
    <link rel="shortcut icon" href="/admin_frontend/assets\images\favicon.ico">

    <!-- third party css -->
    <link href="/admin_frontend/assets\libs\datatables\dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css">
    <link href="/admin_frontend/assets\libs\datatables\buttons.bootstrap4.min.css" rel="stylesheet" type="text/css">
    <link href="/admin_frontend/assets\libs\datatables\responsive.bootstrap4.min.css" rel="stylesheet" type="text/css">
    <link href="/admin_frontend/assets\libs\datatables\select.bootstrap4.min.css" rel="stylesheet" type="text/css">

    <!-- App css -->
    <link href="/admin_frontend/assets\css\bootstrap.min.css" rel="stylesheet" type="text/css"
          id="bootstrap-stylesheet">
    <link href="/admin_frontend/assets\css\icons.min.css" rel="stylesheet" type="text/css">
    <link href="/admin_frontend/assets\css\app.min.css" rel="stylesheet" type="text/css" id="app-stylesheet">
    <jsp:include page="/WEB-INF/homepage/layout/css_header.jsp"></jsp:include>

</head>
<body>

<!-- Begin page -->
<div id="wrapper">


    <!-- Topbar Start -->
    <div class="navbar-custom">
        <ul class="list-unstyled topnav-menu float-right mb-0">

            <li class="dropdown d-none d-lg-block">
                <a class="nav-link dropdown-toggle mr-0 waves-effect" data-toggle="dropdown" href="#" role="button"
                   aria-haspopup="false" aria-expanded="false">
                    <img src="/admin_frontend/assets\images\flags\us.jpg" alt="user-image" class="mr-2" height="12">
                    <span
                            class="align-middle">English <i class="mdi mdi-chevron-down"></i> </span>
                </a>
                <div class="dropdown-menu dropdown-menu-right">
                    <!-- item-->
                    <a href="javascript:void(0);" class="dropdown-item notify-item">
                        <img src="/admin_frontend/assets\images\flags\germany.jpg" alt="user-image" class="mr-2"
                             height="12"> <span
                            class="align-middle">German</span>
                    </a>

                    <!-- item-->
                    <a href="javascript:void(0);" class="dropdown-item notify-item">
                        <img src="/admin_frontend/assets\images\flags\italy.jpg" alt="user-image" class="mr-2"
                             height="12"> <span
                            class="align-middle">Italian</span>
                    </a>

                    <!-- item-->
                    <a href="javascript:void(0);" class="dropdown-item notify-item">
                        <img src="/admin_frontend/assets\images\flags\spain.jpg" alt="user-image" class="mr-2"
                             height="12"> <span
                            class="align-middle">Spanish</span>
                    </a>

                    <!-- item-->
                    <a href="javascript:void(0);" class="dropdown-item notify-item">
                        <img src="/admin_frontend/assets\images\flags\russia.jpg" alt="user-image" class="mr-2"
                             height="12"> <span
                            class="align-middle">Russian</span>
                    </a>
                </div>
            </li>

            <li class="dropdown notification-list">
                <a class="nav-link dropdown-toggle  waves-effect" data-toggle="dropdown" href="#" role="button"
                   aria-haspopup="false" aria-expanded="false">
                    <i class="mdi mdi-email-outline noti-icon"></i>
                    <span class="badge badge-purple rounded-circle noti-icon-badge">3</span>
                </a>
                <div class="dropdown-menu dropdown-menu-right dropdown-lg" style="">

                    <!-- item-->
                    <div class="dropdown-item noti-title">
                        <h5 class="font-16 m-0">
                                    <span class="float-right">
                                        <a href="" class="text-dark">
                                            <small>Clear All</small>
                                        </a>
                                    </span>Chat
                        </h5>
                    </div>

                    <div class="slimScrollDiv"
                         style="position: relative; overflow: hidden; width: auto; height: 314.8px;">
                        <div class="slimscroll noti-scroll" style="overflow: hidden; width: auto; height: 314.8px;">

                            <div class="inbox-widget">
                                <a href="#">
                                    <div class="inbox-item">
                                        <div class="inbox-item-img"><img
                                                src="/admin_frontend/assets\images\users\avatar-1.jpg"
                                                class="rounded-circle" alt=""></div>
                                        <p class="inbox-item-author">Cristina Pride</p>
                                        <p class="inbox-item-text text-truncate">Hi, How are you? What about our next
                                            meeting</p>
                                    </div>
                                </a>
                                <a href="#">
                                    <div class="inbox-item">
                                        <div class="inbox-item-img"><img
                                                src="/admin_frontend/assets\images\users\avatar-2.jpg"
                                                class="rounded-circle" alt=""></div>
                                        <p class="inbox-item-author">Sam Garret</p>
                                        <p class="inbox-item-text text-truncate">Yeah everything is fine</p>
                                    </div>
                                </a>
                                <a href="#">
                                    <div class="inbox-item">
                                        <div class="inbox-item-img"><img
                                                src="/admin_frontend/assets\images\users\avatar-3.jpg"
                                                class="rounded-circle" alt=""></div>
                                        <p class="inbox-item-author">Karen Robinson</p>
                                        <p class="inbox-item-text text-truncate">Wow that's great</p>
                                    </div>
                                </a>
                                <a href="#">
                                    <div class="inbox-item">
                                        <div class="inbox-item-img"><img
                                                src="/admin_frontend/assets\images\users\avatar-4.jpg"
                                                class="rounded-circle" alt=""></div>
                                        <p class="inbox-item-author">Sherry Marshall</p>
                                        <p class="inbox-item-text text-truncate">Hi, How are you? What about our next
                                            meeting</p>
                                    </div>
                                </a>
                                <a href="#">
                                    <div class="inbox-item">
                                        <div class="inbox-item-img"><img
                                                src="/admin_frontend/assets\images\users\avatar-5.jpg"
                                                class="rounded-circle" alt=""></div>
                                        <p class="inbox-item-author">Shawn Millard</p>
                                        <p class="inbox-item-text text-truncate">Yeah everything is fine</p>

                                    </div>
                                </a>
                            </div> <!-- end inbox-widget -->

                        </div>
                        <div class="slimScrollBar"
                             style="background: rgb(158, 165, 171); width: 5px; position: absolute; top: 0px; opacity: 0.4; display: block; border-radius: 7px; z-index: 99; right: 1px;"></div>
                        <div class="slimScrollRail"
                             style="width: 5px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(51, 51, 51); opacity: 0.2; z-index: 90; right: 1px;"></div>
                    </div>
                    <!-- All-->
                    <a href="javascript:void(0);" class="dropdown-item text-center text-primary notify-item notify-all">
                        View all
                        <i class="fi-arrow-right"></i>
                    </a>

                </div>
            </li>

            <li class="dropdown notification-list">
                <a class="nav-link dropdown-toggle  waves-effect" data-toggle="dropdown" href="#" role="button"
                   aria-haspopup="false" aria-expanded="false">
                    <i class="mdi mdi-bell-outline noti-icon"></i>
                    <span class="badge badge-pink rounded-circle noti-icon-badge">4</span>
                </a>
                <div class="dropdown-menu dropdown-menu-right dropdown-lg">

                    <!-- item-->
                    <div class="dropdown-item noti-title">
                        <h5 class="font-16 m-0">
                                    <span class="float-right">
                                        <a href="" class="text-dark">
                                            <small>Clear All</small>
                                        </a>
                                    </span>Notification
                        </h5>
                    </div>

                    <div class="slimScrollDiv"
                         style="position: relative; overflow: hidden; width: auto; height: 314.8px;">
                        <div class="slimscroll noti-scroll" style="overflow: hidden; width: auto; height: 314.8px;">

                            <!-- item-->
                            <a href="javascript:void(0);" class="dropdown-item notify-item">
                                <div class="notify-icon">
                                    <i class="mdi mdi-comment-account-outline text-info"></i>
                                </div>
                                <p class="notify-details">Caleb Flakelar commented on Admin
                                    <small class="noti-time">1 min ago</small>
                                </p>
                            </a>

                            <!-- item-->
                            <a href="javascript:void(0);" class="dropdown-item notify-item">
                                <div class="notify-icon text-success">
                                    <i class="mdi mdi-account-plus text-primary"></i>
                                </div>
                                <p class="notify-details">New user registered.
                                    <small class="noti-time">5 hours ago</small>
                                </p>
                            </a>

                            <!-- item-->
                            <a href="javascript:void(0);" class="dropdown-item notify-item">
                                <div class="notify-icon text-danger">
                                    <i class="mdi mdi-heart text-danger"></i>
                                </div>
                                <p class="notify-details">Carlos Crouch liked
                                    <small class="noti-time">3 days ago</small>
                                </p>
                            </a>

                            <!-- item-->
                            <a href="javascript:void(0);" class="dropdown-item notify-item">
                                <div class="notify-icon text-warning">
                                    <i class="mdi mdi-comment-account-outline text-primary"></i>
                                </div>
                                <p class="notify-details">Caleb Flakelar commented on Admi
                                    <small class="noti-time">4 days ago</small>
                                </p>
                            </a>

                            <!-- item-->
                            <a href="javascript:void(0);" class="dropdown-item notify-item">
                                <div class="notify-icon text-purple">
                                    <i class="mdi mdi-account-plus text-danger"></i>
                                </div>
                                <p class="notify-details">New user registered.
                                    <small class="noti-time">7 days ago</small>
                                </p>
                            </a>

                            <!-- item-->
                            <a href="javascript:void(0);" class="dropdown-item notify-item">
                                <div class="notify-icon text-danger">
                                    <i class="mdi mdi-heart text-danger"></i>
                                </div>
                                <p class="notify-details">Carlos Crouch liked <b>Admin</b>.
                                    <small class="noti-time">Carlos Crouch liked</small>
                                </p>
                            </a>
                        </div>
                        <div class="slimScrollBar"
                             style="background: rgb(158, 165, 171); width: 5px; position: absolute; top: 0px; opacity: 0.4; display: block; border-radius: 7px; z-index: 99; right: 1px;"></div>
                        <div class="slimScrollRail"
                             style="width: 5px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(51, 51, 51); opacity: 0.2; z-index: 90; right: 1px;"></div>
                    </div>

                    <!-- All-->
                    <a href="javascript:void(0);" class="dropdown-item text-center notify-item notify-all">
                        See all notifications
                    </a>

                </div>
            </li>

            <li class="dropdown notification-list">
                <a class="nav-link dropdown-toggle nav-user mr-0 waves-effect" data-toggle="dropdown" href="#"
                   role="button" aria-haspopup="false" aria-expanded="false">
                    <img src="/admin_frontend/assets\images\users\avatar-1.jpg" alt="user-image" class="rounded-circle">
                    <span class="pro-user-name ml-1">
                                Thompson   <i class="mdi mdi-chevron-down"></i>
                            </span>
                </a>
                <div class="dropdown-menu dropdown-menu-right profile-dropdown ">
                    <!-- item-->
                    <div class="dropdown-header noti-title">
                        <h6 class="text-overflow m-0">Welcome !</h6>
                    </div>

                    <!-- item-->
                    <a href="javascript:void(0);" class="dropdown-item notify-item">
                        <i class="mdi mdi-account-outline"></i>
                        <span>Profile</span>
                    </a>

                    <!-- item-->
                    <a href="javascript:void(0);" class="dropdown-item notify-item">
                        <i class="mdi mdi-settings-outline"></i>
                        <span>Settings</span>
                    </a>

                    <!-- item-->
                    <a href="javascript:void(0);" class="dropdown-item notify-item">
                        <i class="mdi mdi-lock-outline"></i>
                        <span>Lock Screen</span>
                    </a>

                    <div class="dropdown-divider"></div>

                    <!-- item-->
                    <a href="javascript:void(0);" class="dropdown-item notify-item">
                        <i class="mdi mdi-logout-variant"></i>
                        <span>Logout</span>
                    </a>

                </div>
            </li>

            <li class="dropdown notification-list">
                <a href="javascript:void(0);" class="nav-link right-bar-toggle waves-effect">
                    <i class="mdi mdi-settings-outline noti-icon"></i>
                </a>
            </li>


        </ul>

        <!-- LOGO -->
        <div class="logo-box">
            <a href="index.html" class="logo text-center logo-dark">
                        <span class="logo-lg">
                            <img src="/admin_frontend/assets\images\logo-dark.png" alt="" height="18">
                            <!-- <span class="logo-lg-text-dark">Velonic</span> -->
                        </span>
                <span class="logo-sm">
                            <!-- <span class="logo-lg-text-dark">V</span> -->
                            <img src="/admin_frontend/assets\images\logo-sm.png" alt="" height="22">
                        </span>
            </a>

            <a href="index.html" class="logo text-center logo-light">
                        <span class="logo-lg">
                            <img src="/admin_frontend/assets\images\logo-light.png" alt="" height="18">
                            <!-- <span class="logo-lg-text-dark">Velonic</span> -->
                        </span>
                <span class="logo-sm">
                            <!-- <span class="logo-lg-text-dark">V</span> -->
                            <img src="/admin_frontend/assets\images\logo-sm.png" alt="" height="22">
                        </span>
            </a>
        </div>

        <!-- LOGO -->


        <ul class="list-unstyled topnav-menu topnav-menu-left m-0">
            <li>
                <button class="button-menu-mobile waves-effect">
                    <i class="mdi mdi-menu"></i>
                </button>
            </li>

            <li class="d-none d-lg-block">
                <form class="app-search">
                    <div class="app-search-box">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="Search...">
                            <div class="input-group-append">
                                <button class="btn" type="submit">
                                    <i class="fas fa-search"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </form>
            </li>
        </ul>
    </div>
    <!-- end Topbar --> <!-- ========== Left Sidebar Start ========== -->
    <div class="left-side-menu">

        <div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 258px;">
            <div class="slimscroll-menu" style="overflow: hidden; width: auto; height: 258px;">

                <!--- Sidemenu -->
                <div id="sidebar-menu" class="mm-active">
                    <ul class="metismenu mm-show" id="side-menu">

                        <li class="menu-title">Navigation</li>

                        <li>
                            <a href="javascript: void(0);" class="waves-effect">
                                <i class="ion-md-speedometer"></i>
                                <span>  Dashboard  </span>
                                <span class="badge badge-info badge-pill float-right"> 3 </span>
                            </a>
                            <ul class="nav-second-level mm-collapse" aria-expanded="false">
                                <li><a href="index.html">Dashboard 1</a></li>
                                <li><a href="dashboard-2.html">Dashboard 2</a></li>
                                <li><a href="dashboard-3.html">Dashboard 3</a></li>
                            </ul>
                        </li>

                        <li>
                            <a href="javascript: void(0);" class="waves-effect">
                                <i class="ion-md-basket"></i>
                                <span> UI Elements </span>
                                <span class="menu-arrow"></span>
                            </a>
                            <ul class="nav-second-level mm-collapse" aria-expanded="false">

                                <li><a href="ui-typography.html">Typography</a></li>
                                <li><a href="ui-buttons.html">Buttons</a></li>
                                <li><a href="ui-fontawesome.html">Font Awesome Icons</a></li>
                                <li><a href="ui-materialdesign.html">Material Design Icons</a></li>
                                <li><a href="ui-ionicons.html">Ion Icons</a></li>
                                <li><a href="ui-cards.html">Cards</a></li>
                                <li><a href="ui-tabs-accordions.html">Tabs &amp; Accordions</a></li>
                                <li><a href="ui-modals.html">Modals</a></li>
                                <li><a href="ui-bootstrap-ui.html">BS Elements</a></li>
                                <li><a href="ui-progressbars.html">Progress Bars</a></li>
                                <li><a href="ui-notification.html">Notification</a></li>
                                <li><a href="ui-sweet-alert.html">Sweet-Alert</a></li>
                            </ul>
                        </li>

                        <li>
                            <a href="javascript: void(0);" class="waves-effect">
                                <i class="ion-ios-apps"></i>
                                <span> Components </span>
                                <span class="menu-arrow"></span>
                            </a>
                            <ul class="nav-second-level mm-collapse" aria-expanded="false">
                                <li><a href="components-grid.html">Grid</a></li>
                                <li><a href="components-portlets.html">Portlets</a></li>
                                <li><a href="components-widgets.html">Widgets</a></li>
                                <li><a href="components-nestable-list.html">Nesteble</a></li>
                                <li><a href="components-calendar.html">Calendar</a></li>
                                <li><a href="components-range-sliders.html">Range Slider</a></li>
                            </ul>
                        </li>

                        <li>
                            <a href="javascript: void(0);" class="waves-effect">
                                <i class="ion-md-speedometer"></i>
                                <span>  Forms  </span>
                                <span class="badge badge-danger badge-pill float-right"> 8 </span>
                            </a>
                            <ul class="nav-second-level mm-collapse" aria-expanded="false">
                                <li><a href="forms-elements.html">General Elements</a></li>
                                <li><a href="forms-validation.html">Form Validation</a></li>
                                <li><a href="forms-advanced.html">Advanced Form</a></li>
                                <li><a href="forms-wizard.html">Form Wizard</a></li>
                                <li><a href="form-quilljs.html">Quilljs Editor</a></li>
                                <li><a href="forms-uploads.html">Multiple File Upload</a></li>
                                <li><a href="forms-image-crop.html">Image Crop</a></li>
                                <li><a href="forms-xeditable.html">X-Editable</a></li>
                            </ul>
                        </li>

                        <li class="mm-active">
                            <a href="javascript: void(0);" class="waves-effect active">
                                <i class="ion-ios-list"></i>
                                <span> Tables </span>
                                <span class="menu-arrow"></span>
                            </a>
                            <ul class="nav-second-level mm-collapse mm-show" aria-expanded="false">
                                <li><a href="tables-basic.html">Basic Tables</a></li>
                                <li class="mm-active"><a href="tables-datatable.html" class="active">Data Table</a></li>
                                <li><a href="tables-editable.html">Editable Table</a></li>
                                <li><a href="tables-responsive.html">Responsive Table</a></li>
                            </ul>
                        </li>

                        <li>
                            <a href="javascript: void(0);" class="waves-effect">
                                <i class="ion-md-pie"></i>
                                <span> Charts </span>
                                <span class="menu-arrow"></span>
                            </a>
                            <ul class="nav-second-level mm-collapse" aria-expanded="false">
                                <li><a href="charts-morris.html">Morris Chart</a></li>
                                <li><a href="charts-chartjs.html">Chartjs</a></li>
                                <li><a href="charts-flot.html">Flot Chart</a></li>
                                <li><a href="charts-rickshaw.html">Rickshaw Chart</a></li>
                                <li><a href="charts-peity.html">Peity Chart</a></li>
                                <li><a href="charts-c3.html">C3 Chart</a></li>
                                <li><a href="charts-other.html">Other Chart</a></li>
                            </ul>
                        </li>

                        <li>
                            <a href="javascript: void(0);" class="waves-effect">
                                <i class="ion-md-mail"></i>
                                <span> Mail </span>
                                <span class="badge badge-warning badge-pill float-right">12</span>
                            </a>
                            <ul class="nav-second-level mm-collapse" aria-expanded="false">
                                <li><a href="email-inbox.html">Inbox</a></li>
                                <li><a href="email-compose.html">Compose Mail</a></li>
                                <li><a href="email-read.html">View Mail</a></li>
                                <li><a href="email-templates.html">Email Templates</a></li>
                            </ul>
                        </li>

                        <li>
                            <a href="javascript: void(0);" class="waves-effect">
                                <i class="ion-md-map"></i>
                                <span> Maps </span>
                                <span class="menu-arrow"></span>
                            </a>
                            <ul class="nav-second-level mm-collapse" aria-expanded="false">
                                <li><a href="maps-gmap.html"> Google Map</a></li>
                                <li><a href="maps-vector.html"> Vector Map</a></li>
                            </ul>
                        </li>

                        <li>
                            <a href="javascript: void(0);" class="waves-effect">
                                <i class="ion ion-ios-hourglass"></i>
                                <span> Layouts </span>
                                <span class="badge badge-danger badge-pill float-right">New</span>
                            </a>
                            <ul class="nav-second-level mm-collapse" aria-expanded="false">
                                <li><a href="layouts-horizontal.html">Horizontal</a></li>
                                <li><a href="layouts-light-sidebar.html">Light Sidebar</a></li>
                                <li><a href="layouts-small-sidebar.html">Small Sidebar</a></li>
                                <li><a href="layouts-sidebar-collapsed.html">Sidebar Collapsed</a></li>
                                <li><a href="layouts-unsticky.html">Unsticky Layout</a></li>
                                <li><a href="layouts-boxed.html">Boxed Layout</a></li>
                            </ul>
                        </li>

                        <li>
                            <a href="javascript: void(0);" class="waves-effect">
                                <i class="ion-md-copy"></i>
                                <span> Pages </span>
                                <span class="menu-arrow"></span>
                            </a>
                            <ul class="nav-second-level mm-collapse" aria-expanded="false">
                                <li><a href="pages-profile.html">Profile</a></li>
                                <li><a href="pages-timeline.html">Timeline</a></li>
                                <li><a href="pages-invoice.html">Invoice</a></li>
                                <li><a href="pages-contact.html">Contact-list</a></li>
                                <li><a href="pages-login.html">Login</a></li>
                                <li><a href="pages-register.html">Register</a></li>
                                <li><a href="pages-recoverpw.html">Recover Password</a></li>
                                <li><a href="pages-lock-screen.html">Lock Screen</a></li>
                                <li><a href="pages-blank.html">Blank Page</a></li>
                                <li><a href="pages-404.html">404 Error</a></li>
                                <li><a href="pages-404_alt.html">404 alt</a></li>
                                <li><a href="pages-500.html">500 Error</a></li>
                            </ul>
                        </li>

                        <li>
                            <a href="javascript: void(0);" class="waves-effect">
                                <i class="mdi mdi-share-variant"></i>
                                <span> Multi Level </span>
                                <span class="menu-arrow"></span>
                            </a>
                            <ul class="nav-second-level nav mm-collapse" aria-expanded="false">
                                <li>
                                    <a href="javascript: void(0);">Level 1.1</a>
                                </li>
                                <li>
                                    <a href="javascript: void(0);" aria-expanded="false">Level 1.2
                                        <span class="menu-arrow"></span>
                                    </a>
                                    <ul class="nav-third-level nav mm-collapse" aria-expanded="false">
                                        <li>
                                            <a href="javascript: void(0);">Level 2.1</a>
                                        </li>
                                        <li>
                                            <a href="javascript: void(0);">Level 2.2</a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                    </ul>

                </div>
                <!-- End Sidebar -->

                <div class="clearfix"></div>

            </div>
            <div class="slimScrollBar"
                 style="background: rgb(158, 165, 171); width: 5px; position: absolute; top: -382.025px; opacity: 0.4; display: none; border-radius: 7px; z-index: 99; right: 1px; height: 88.0476px;"></div>
            <div class="slimScrollRail"
                 style="width: 5px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(51, 51, 51); opacity: 0.2; z-index: 90; right: 1px;"></div>
        </div>
        <!-- Sidebar -left -->

    </div>
    <!-- Left Sidebar End -->

    <!-- ============================================================== -->
    <!-- Start Page Content here -->
    <!-- ============================================================== -->

    <div class="content-page">
        <div class="content">

            <!-- Start Content-->
            <div class="container-fluid">

                <!-- start page title -->

                <!-- end page title -->


                <!-- end row -->

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
                                    <c:forEach items="${requestScope.order.getOrderItems()}" var="oT">
                                        <c:forEach items="${requestScope.products}" var="p">
                                            <c:if test="${p.getId() == oT.getIdProduct()}">

                                                <tr>
                                                    <form action="/order?action=view" method="post">
                                                        <input name="idOrder" value="${requestScope.order.getId()}" hidden="hidden">
                                                        <input name="idProduct" value="${oT.getIdProduct()}" hidden="hidden">
                                                        <td class="product_remove"><button class="btn btn-danger rounded-lg fa fa-trash-o" type="submit"></button></td>
                                                        <td class="product_thumb"><a href="#"><img src="${p.getImgLink()}" alt=""></a></td>
                                                        <td class="product_name"><a href="#">${p.getName()}</a></td>
                                                        <td class="product-price">${p.getPrice()}</td>
                                                        <td class="product_quantity" ><input onchange="handleQuantityChange(${p.getId()},this.value)" min="0" max="10" value="${oT.getQuantity()}" type="number" name="quantity" ></td>
                                                        <td class="product_total">${p.getPrice() * oT.getQuantity()}</td>
                                                    </form>
                                                </tr>

                                            </c:if>
                                        </c:forEach>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- end row -->


                <!-- end row-->


                <!-- end row-->

            </div>
            <!-- end container-fluid -->

        </div>
        <!-- end content -->


        <!-- Footer Start -->
        <footer class="footer">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        2015 - 2020 © Velonic theme by <a href="">Coderthemes</a>
                    </div>
                </div>
            </div>
        </footer>
        <!-- end Footer -->

    </div>

    <!-- ============================================================== -->
    <!-- End Page content -->
    <!-- ============================================================== -->

</div>
<!-- END wrapper -->


<!-- Right Sidebar -->
<div class="right-bar">
    <div class="rightbar-title">
        <a href="javascript:void(0);" class="right-bar-toggle float-right">
            <i class="mdi mdi-close"></i>
        </a>
        <h4 class="font-17 m-0 text-white">Theme Customizer</h4>
    </div>
    <div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 356px;">
        <div class="slimscroll-menu" style="overflow: hidden; width: auto; height: 356px;">

            <div class="p-4">
                <div class="alert alert-warning" role="alert">
                    <strong>Customize </strong> the overall color scheme, layout, etc.
                </div>
                <div class="mb-2">
                    <img src="/admin_frontend/assets\images\layouts\light.png" class="img-fluid img-thumbnail" alt="">
                </div>
                <div class="custom-control custom-switch mb-3">
                    <input type="checkbox" class="custom-control-input theme-choice" id="light-mode-switch" checked="">
                    <label class="custom-control-label" for="light-mode-switch">Light Mode</label>
                </div>

                <div class="mb-2">
                    <img src="/admin_frontend/assets\images\layouts\dark.png" class="img-fluid img-thumbnail" alt="">
                </div>
                <div class="custom-control custom-switch mb-3">
                    <input type="checkbox" class="custom-control-input theme-choice" id="dark-mode-switch"
                           data-bsstyle="/admin_frontend/assets/css/bootstrap-dark.min.css"
                           data-appstyle="/admin_frontend/assets/css/app-dark.min.css">
                    <label class="custom-control-label" for="dark-mode-switch">Dark Mode</label>
                </div>

                <div class="mb-2">
                    <img src="/admin_frontend/assets\images\layouts\rtl.png" class="img-fluid img-thumbnail" alt="">
                </div>
                <div class="custom-control custom-switch mb-5">
                    <input type="checkbox" class="custom-control-input theme-choice" id="rtl-mode-switch"
                           data-appstyle="/admin_frontend/assets/css/app-rtl.min.css">
                    <label class="custom-control-label" for="rtl-mode-switch">RTL Mode</label>
                </div>

            </div>
        </div>
        <div class="slimScrollBar"
             style="background: rgb(158, 165, 171); width: 5px; position: absolute; top: -437.2px; opacity: 0.4; display: none; border-radius: 7px; z-index: 99; right: 1px; height: 163.53px;"></div>
        <div class="slimScrollRail"
             style="width: 5px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(51, 51, 51); opacity: 0.2; z-index: 90; right: 1px;"></div>
    </div> <!-- end slimscroll-menu-->
</div>
<!-- /Right-bar -->

<!-- Right bar overlay-->
<div class="rightbar-overlay"></div>

<a href="javascript:void(0);" class="right-bar-toggle demos-show-btn">
    <i class="mdi mdi-settings-outline mdi-spin"></i> &nbsp;Choose Demos
</a>

<!-- Vendor js -->
<script src="/admin_frontend/assets\js\vendor.min.js"></script>

<!-- Required datatable js -->
<script src="/admin_frontend/assets\libs\datatables\jquery.dataTables.min.js"></script>
<script src="/admin_frontend/assets\libs\datatables\dataTables.bootstrap4.min.js"></script>
<!-- Buttons examples -->
<script src="/admin_frontend/assets\libs\datatables\dataTables.buttons.min.js"></script>
<script src="/admin_frontend/assets\libs\datatables\buttons.bootstrap4.min.js"></script>
<script src="/admin_frontend/assets\libs\jszip\jszip.min.js"></script>
<script src="/admin_frontend/assets\libs\pdfmake\pdfmake.min.js"></script>
<script src="/admin_frontend/assets\libs\pdfmake\vfs_fonts.js"></script>
<script src="/admin_frontend/assets\libs\datatables\buttons.html5.min.js"></script>
<script src="/admin_frontend/assets\libs\datatables\buttons.print.min.js"></script>

<!-- Responsive examples -->
<script src="/admin_frontend/assets\libs\datatables\dataTables.responsive.min.js"></script>
<script src="/admin_frontend/assets\libs\datatables\responsive.bootstrap4.min.js"></script>

<script src="/admin_frontend/assets\libs\datatables\dataTables.keyTable.min.js"></script>
<script src="/admin_frontend/assets\libs\datatables\dataTables.select.min.js"></script>

<!-- Datatables init -->
<script src="/admin_frontend/assets\js\pages\datatables.init.js"></script>

<!-- App js -->
<script src="/admin_frontend/assets\js\app.min.js"></script>
<jsp:include page="/WEB-INF/homepage/layout/js_footer.jsp"></jsp:include>


</body>
<grammarly-desktop-integration data-grammarly-shadow-root="true"></grammarly-desktop-integration>
</html>
