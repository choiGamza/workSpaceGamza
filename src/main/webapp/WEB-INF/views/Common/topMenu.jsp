<%--
  Created by IntelliJ IDEA.
  User: chdlw
  Date: 2023-05-03
  Time: 오후 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
  <div class="navbar navbar-expand navbar-dark static-top" style="background-color: #d7dde4;">

    <!--   Navbar Search-->
    <form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
    </form>

    <!-- Navbar-->
    <ul class="navbar-nav ml-auto ml-md-0">
      <li class="nav-item dropdown no-arrow"><a
              class="nav-link dropdown-toggle" href="#" id="userDropdown"
              role="button" data-toggle="dropdown" aria-haspopup="true"
              aria-expanded="false" style="color: #4f5f6f;"><i
              class="fas fa-user-circle fa-fw"></i>
        ${user.name }
      </a>
        <div class="dropdown-menu dropdown-menu-right"
             aria-labelledby="userDropdown">
          <a class="dropdown-item" href="#" data-toggle = "modal" data-target="#changeModal" role="button" id="Btn">정보변경</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#" data-toggle="modal"
             data-target="#logoutModal">로그아웃</a>
        </div></li>
    </ul>
  </div>
</body>
</html>
