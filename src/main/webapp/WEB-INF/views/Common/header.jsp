<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<script type="text/javascript" charset="utf-8">
  sessionStorage.setItem("contextpath", "${pageContext.request.contextPath}");

  var contextPath = sessionStorage.getItem("contextpath");
</script>

<%--<html>--%>
<%--  <head>--%>
    <!-- Bootstrap core CSS-->
    <link href="${pageContext.request.contextPath}/resources/bootstrap/vendor/bootstrap/css/bootstrap.min.css"
          rel="stylesheet">

    <!-- Custom fonts for this template-->
    <link href="${pageContext.request.contextPath}/resources/bootstrap/vendor/fontawesome-free/css/all.min.css"
          rel="stylesheet" type="text/css">

    <!-- Page level plugin CSS-->
    <link href="${pageContext.request.contextPath}/resources/bootstrap/vendor/datatables/dataTables.bootstrap4.css"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/sb-admin.css" rel="stylesheet">

<%--  </head>--%>
<%--  <body>--%>
    <!-- Bootstrap core JavaScript-->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/vendor/jquery/jquery.min.js"></script>
    <script
            src="${pageContext.request.contextPath}/resources/bootstrap/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script
            src="${pageContext.request.contextPath}/resources/bootstrap/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Page level plugin JavaScript-->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/vendor/chart.js/Chart.min.js"></script>
    <script
            src="${pageContext.request.contextPath}/resources/bootstrap/vendor/datatables/jquery.dataTables.js"></script>
    <script
            src="${pageContext.request.contextPath}/resources/bootstrap/vendor/datatables/dataTables.bootstrap4.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/sb-admin.min.js"></script>

    <!-- Demo scripts for this page-->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/demo/datatables-demo.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/demo/chart-area-demo.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>

<%--  </body>--%>
<%--</html>--%>
