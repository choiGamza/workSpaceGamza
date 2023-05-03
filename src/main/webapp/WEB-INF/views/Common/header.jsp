<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<script type="text/javascript" charset="utf-8">
  sessionStorage.setItem("contextpath", "${pageContext.request.contextPath}");

  var contextPath = sessionStorage.getItem("contextpath");
</script>

<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

        <jsp:include page="/WEB-INF/views/Common/css.jsp" flush="false" />
        <jsp:include page="/WEB-INF/views/Common/js.jsp" flush="false" />
<%--        <jsp:include page="/WEB-INF/views/Common/leftMenu.jsp" flush="false" />--%>
    </head>
</html>
