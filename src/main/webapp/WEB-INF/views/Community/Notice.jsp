<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/Common/header.jsp"%>

<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
   content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">


<title>업무관리 프로그램</title>

<!-- Modal  -->
<link href="${pageContext.request.contextPath}/resources/loginBootstrap/vendor/bootstrap/css/bootstrap.css">
<style>
.sidebar{
min-height: calc(100vh - 0px); !important;
}

.menu{
font-size : 30px;
}
	.modal {
        text-align: left;
        vertical-align: middle;
        
}
@media screen and (min-width: 768px) { 
        .modal:before {
                display: inline-block !important;
                vertical-align: middle;
                content: " ";
                height: 10%;
        }
}

caption {
    padding-top: 0.75rem;
    padding-bottom: 0.75rem;
    color: #6c757d;
    text-align: center !important;
    caption-side: top !important;
    font-weight : bolder !important;
    font-size : 40px !important;
}
</style>
</head>
<body id="page-top">

   <div id="wrapper">
       <jsp:include page="/WEB-INF/views/Common/leftMenu.jsp" flush="false" />

       <div id="content-wrapper" style="background-color: #f0f3f6; padding-top : 0px; padding-left : 0px; padding-right : 0px; ">
            <jsp:include page="/WEB-INF/views/Common/topMenu.jsp" flush="false" />

        <div class="container-fluid" style="padding-left : 25px; padding-right : 25px; padding-top : 25px; padding-bottom : 25px;">
        <!-- -------------------------본문시작(복사해놓는 부분)-----------------------------  -->
            <table class="table" style="text-align: center; border: 2px;">
                <caption>공지사항/게시판</caption>
                <thead>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>날짜</th>
                    <th>조회수</th>
                </tr>
                </thead>
                <tbody id = "noticeList"></tbody>
            </table>

            <button type="button" class="btn btn-info btn-sm" id="myBtn" style="float: right;">글작성</button>
								
            <c:forEach items = "${generalList }" var = "list" varStatus = "status1">
                <c:set var = "count2" value = "${status1.count }" />
            </c:forEach>

            <c:set var = "result" value = "${count2/10 }"/>

            <nav aria-label="Page navigation example">
              <ul class="pagination justify-content-center">
                <c:forEach var = "count" begin = "1" end = "${result+1 }">
                    <li class = "page-item">	<a class = "page-link" href = "noticeListView?pagenum=${count }">${count }</a></li>
                </c:forEach>
             </ul>
            </nav>
						
						
						
						<form action = "Search" method = "post">
							
						<center>
						<select name = "Search">
							<option value = "title"> 제목 </option>
							<option value = "name"> 작성자 </option>
						</select>
						
						<input type = "text" class = "search-query" name = "Sear" placeholder = "Q search"> 
						<input type = "hidden" name = "pagenum" value = "1">
						<input type = "submit" value = "검색">
						</center>
						</form>
					</div>

					<div class="modal fade" id="myModal" role="dialog">
						<div class="modal-dialog">
							<div class="modal-content">
							
								<div class="modal-header">
								<c:if test = "${user.id == 'admin' }">
									<h3 class="modal-title">공지사항작성</h3>
								</c:if>
								<c:if test = "${user.id != 'admin'}">
									<h3 class="modal-title">게시판작성</h3>
								</c:if>
								</div>
								<!-- ----------------------------2018/11/13------------------------------- -->
								<!-- ---------------이부분 부터는 controller에 DB에 넣는 mapping 설정--------------- -->

								<form action="noticeAdd" class="form-inline" method="post">
									<div class="modal-body">


										제목 <input type="text" placeholder="제목을 입력하세요.."
											name="title" class="form-control" style="width: 380px;">
										<input type = "hidden" name = "important" value = "true">
										<br> 내용
										<textarea class="form-control" cols="50" rows="13" name = "content"
											placeholder="내용을 입력하세요.."></textarea>
										<input type = "hidden" name = "name" value = "${user.id }">
									</div>
									<div class="modal-footer">
									  
										<input type="submit" class="btn btn-primary" value="등록">
										<button type="button" class="btn" data-dismiss="modal">닫기</button>
									</div>
								</form>

								<!-- --------------------------------------------------------------- -->

							</div>
						</div>




            <!-- ---------------------------------------여기까지 복사해놓으면 본문-------------------------------- -->
         </div>
         <div class="modal fade" id="changeModal" role="dialog" >
      <div class="modal-dialog" >
         <div class="modal-content">
            <div class="modal-header">
               <h3 class="modal-title">정보 변경</h3>
            </div>
         <form action = "changeInfo" class = "form-inline" method = "post" enctype="multipart/form-data">
            <div class="modal-body">
          
		<c:if test = "${user.profileimg=='false' }">
        	프로필을 등록해주세요
        	<img class="rounded-circle img-fluid d-block mx-auto" src="${pageContext.request.contextPath}/resources/profileimg/human.png"style = "width:150px; height : 150px;">
        	<input type="file" name="file">
        </c:if>
          
        <c:if test = "${user.profileimg == 'true' }">
        	<img class = "rounded-circle img-fluid d-block mx-auto" src = "resources/profileimg/${user.id }.jpg" style = "width:150px; height : 150px;">
        	<input type="file" name="file">
        </c:if>
 
         <br><br>
             
                    고유 ID ${user.id }<input type = "hidden" value = "${user.id }" name = "id">
                   <br>   <br>
   		PW 변경 <input type = "password" name = "pw" placeholder = "비밀번호 변경" class = "form-control"><br><br>
                   이름  ${user.name }
                <div style ="float:right;"> 
                 성별	${user.sex }
                    </div>
                    <br><br>
                    
                부서 	${user.department }<br><br>
                직급 	${user.position }<br><br>
               
                주민번호 ${user.registernum }
               <br><br>
	        폰번호 <input type = "text" class = "form-control" name = "phonenum" value = "${user.phonenum }" style = "width:380px;">
	        <br><br>
                주소 <input type = "text" name = "address" class = "form-control" value = "${user.address }" style = "width:380px;"><br><br>
               email <input type = "email" name = "email" class = "form-control" value = "${user.email }" style = "width:380px;">
    
            </div>
            <div class="modal-footer" style="width : 600px;">
               <input type = "submit" class = "btn btn-primary" value = "변경">
               <button type="button" class="btn" data-dismiss="modal">닫기</button>
            </div>
            </form>
         </div>
      </div>
   </div>
         <!-- /.content-wrapper -->

      </div>
      <!-- /#wrapper -->

      <!-- Sticky Footer -->
      <footer class="sticky-footer">
         <div class="container my-auto">
            <div class="copyright text-center my-auto">
               <span>Copyright © Your Website 2018</span>
            </div>
         </div>
      </footer>


      <!-- Scroll to Top Button-->
      <a class="scroll-to-top rounded" href="#page-top"> <i
         class="fas fa-angle-up"></i>
      </a>

      <!-- Logout Modal-->
      <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalLabel" aria-hidden="true">
         <div class="modal-dialog" role="document">
            <div class="modal-content">
               <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLabel">로그아웃 하시겠습니까?</h5>
                  <button class="close" type="button" data-dismiss="modal"
                     aria-label="Close">
                     <span aria-hidden="true">×</span>
                  </button>
               </div>
               <div class="modal-body">로그아웃 하시려면 'Logout'을 클릭하세요.</div>
               <div class="modal-footer">
                  <button class="btn btn-secondary" type="button"
                     data-dismiss="modal">Cancel</button>
                  <a class="btn btn-primary" href="logout">Logout</a>
               </div>
            </div>
         </div>
      </div>

      <script type="text/javascript" src = "${pageContext.request.contextPath}/resources/js/noticeController.js" ></script>
</body>

</html>