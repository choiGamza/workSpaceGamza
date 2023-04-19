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
</style>

</head>

<body id="page-top">

 
   <div id="wrapper">

      <!-- 사이드메뉴 -->
      <ul class="sidebar navbar-nav"
         style="background-color: #3a4651; padding-bottom : 0px; font-size : 13px;!important ">
         <!--------------- 제목-------------------- -->
         
         <li class="nav-item">
         <a class="nav-link" href="adminMain?id=${user.id }"> 
         	<span style="color : white; font-size : 17px; font-family: 'Open Sans', sans-serif;">&nbsp;<i class="fab fa-audible" style="color : #85ce36; font-size :21px;"></i>&nbsp;The JoenAcademy</span>
         </a>
         </li>


         <!-- -------------콜랩스 관리자메뉴 ---------- -->
         
         
         
         
         
         <c:if test="${user.id=='admin'}">

            <li class="nav-item"
               onmouseover="this.style.backgroundColor='#333e48'"
               onmouseout="this.style.backgroundColor='#3a4651'"><a
               href="#collapse1" class="nav-link" data-toggle="collapse"> <i
                  class="fas fa-fw fa-cog"></i> <span style="font-size : 15px;">관리자메뉴</span> <i
                  class="fas fa-angle-down"></i>
            </a></li>


            <div class="collapse" id="collapse1">
               <ul class="nav navbar-nav">
                  <li class="nav-item"
                     onmouseover="this.style.backgroundColor='#333e48'"
                     onmouseout="this.style.backgroundColor='#3a4651'"><a
                     href="#adminOrgz" class="nav-link" data-toggle="collapse"
                     style="padding-left: 20px;"> <i class="far fa-fw fa-building"></i>
                        <span style="font-size : 15px;">조직관리(관리자)</span> <i class="fas fa-angle-down"></i>
                  </a></li>
                  <div class="collapse" id="adminOrgz">
                     <ul class="nav navbar-nav" aria-expanded="true">
                     <li class="nav-item"
                           onmouseover="this.style.backgroundColor='#333e48'"
                           onmouseout="this.style.backgroundColor='#3a4651'"><a
                           class="nav-link" href="adminOrganization"
                           style="padding-left: 45px;"> 부서관리 </a></li>
                        <li class="nav-item"
                           onmouseover="this.style.backgroundColor='#333e48'"
                           onmouseout="this.style.backgroundColor='#3a4651'"><a
                           class="nav-link" href="adminRegister"
                           style="padding-left: 45px;"> 회원등록/삭제 </a></li>
                     </ul>
                  </div>
                  <li class="nav-item"
                     onmouseover="this.style.backgroundColor='#333e48'"
                     onmouseout="this.style.backgroundColor='#3a4651'"><a
                     class="nav-link" href="adminHoliday" style="padding-left: 20px;">
                        <i class="fas fa-fw fa-address-book"></i> <span style="font-size : 15px;">휴가관리(관리자)</span>
                  </a></li>
               </ul>
            </div>
         </c:if>

         <!-- ------------------------------------------- -->

         <li class="nav-item"
            onmouseover="this.style.backgroundColor='#333e48'"
            onmouseout="this.style.backgroundColor='#3a4651'"><a
            class="nav-link" href="userOrganization"> <i
               class="far fa-fw fa-building"></i> <span style="font-size : 15px;">회사조직도(사용자)</span>
         </a></li>




         <!-- ------------------------------------------------ -->

         <li class="nav-item"
            onmouseover="this.style.backgroundColor='#333e48'"
            onmouseout="this.style.backgroundColor='#3a4651'"><a
            href="#taskMenu" class="nav-link" data-toggle="collapse"> <i
               class="far fa-fw fa-calendar-alt"></i> <span style="font-size : 15px;">업무관리(사용자)</span> <i
               class="fas fa-angle-down"></i>
         </a></li>


         <div class="collapse" id="taskMenu">
            <ul class="nav navbar-nav" aria-expanded="true">
               <li class="nav-item"
                  onmouseover="this.style.backgroundColor='#333e48'"
                  onmouseout="this.style.backgroundColor='#3a4651'"><a
                  class="nav-link" href="userTeamSchedule"
                  style="padding-left: 45px;"> 팀 일정 </a></li>
               <li class="nav-item"
                  onmouseover="this.style.backgroundColor='#333e48'"
                  onmouseout="this.style.backgroundColor='#3a4651'"><a
                  class="nav-link" href="userTask?id=${user.id }" style="padding-left: 45px;">
                     자신의 업무일지 </a></li>
            </ul>
         </div>

         <!-- ---------------------------------------------- -->

         <li class="nav-item"
            onmouseover="this.style.backgroundColor='#333e48'"
            onmouseout="this.style.backgroundColor='#3a4651'"><a
            href="#holidayMenu" class="nav-link" data-toggle="collapse"> <i
               class="fas fa-fw fa-address-book"></i> <span style="font-size : 15px;">휴가관리(사용자)</span> <i
               class="fas fa-angle-down"></i>
         </a></li>


         <div class="collapse" id="holidayMenu">
            <ul class="nav navbar-nav" aria-expanded="true">
               <li class="nav-item"
                  onmouseover="this.style.backgroundColor='#333e48'"
                  onmouseout="this.style.backgroundColor='#3a4651'"><a
                  class="nav-link" href="userHolidayApply"
                  style="padding-left: 45px;">휴가신청</a></li>

               <li class="nav-item"
                  onmouseover="this.style.backgroundColor='#333e48'"
                  onmouseout="this.style.backgroundColor='#3a4651'"><a
                  class="nav-link" href="userHolidayCheck"
                  style="padding-left: 45px;">휴가조회</a></li>
            </ul>
         </div>
         <!-- ---------------------------------------------- -->

         <li class="nav-item"
            onmouseover="this.style.backgroundColor='#333e48'"
            onmouseout="this.style.backgroundColor='#3a4651'"><a
            href="#Community" class="nav-link" data-toggle="collapse"
             style="background-color: #85ce36; color: #ffffff;"> <i
               class="fas fa-fw fa-atom"></i> <span style="font-size : 15px;">커뮤니티</span> <i
               class="fas fa-angle-down"></i>
         </a></li>


         <div class="collapse" id="Community">
            <ul class="nav navbar-nav" aria-expanded="true">
               <li class="nav-item"
                  onmouseover="this.style.backgroundColor='#333e48'"
                  onmouseout="this.style.backgroundColor='#3a4651'"><a
                  class="nav-link" href="Notice" style="padding-left: 45px;">공지사항/게시판</a>
               </li>
            </ul>
         </div>
         
         <li class="nav-item"
            onmouseover="this.style.backgroundColor='#333e48'"
            onmouseout="this.style.backgroundColor='#3a4651'"><a
            class="nav-link" href="chatting"> <i class="far fa-comments"></i><span style="font-size : 15px;">&nbsp;메신저</span>
         </a></li>
         
         <!-- ---------------------------------------------- -->

      </ul>



<!--  --------------------------------------------------------------------------------------------------------------------------- -->
         
         





      <div id="content-wrapper" style="background-color: #f0f3f6; padding-top : 0px; padding-left : 0px; padding-right : 0px; ">
      
      <div class="navbar navbar-expand navbar-dark static-top"
      style="background-color: #d7dde4;">
       

      
      

      <!--   Navbar Search--> 
      <form
         class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
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
         <div class="container-fluid" style="padding-left : 25px; padding-right : 25px; padding-top : 25px; padding-bottom : 25px;">
         
         
         
         
            <!-- -------------------------본문시작(복사해놓는 부분)-----------------------------  -->

<style>                           
      caption {                     
           padding-top: 0.75rem;
           padding-bottom: 0.75rem;
           color: #6c757d;
           text-align: center !important;
           caption-side: top !important;
           font-weight : bolder !important;
           font-size : 40px !important;
      }
      .impor {
	float: right;
}
	.pageing{
		float : center !important;
	}
      </style>

      <table class="table" style="text-align: center; border: 2px;">
							<caption>공지사항/게시판</caption>
							<tr>
								<td>번호</td>
								<td>제목</td>
								<td>작성자</td>
								<td>날짜</td>
								<td>조회수</td>
							</tr>
							<c:forEach items = "${noticeList }" var = "noticeList">
								<tr>
									<td><span class="badge badge-pill badge-primary">공지</span></td>
									<td><a href = "noticeView?bid=${noticeList.bid }">${noticeList.title }</a></td>
									<td>${noticeList.name }</td>
									<td>${noticeList.rdate }</td>
									<td>${noticeList.hit }</td>
								</tr>
							</c:forEach>
							<c:forEach items = "${listCount }" var = "listCount">
								<tr>
							
									<td>${listCount.bid }</td>
									<td><a href = "noticeView?bid=${listCount.bid }">${listCount.title }</a></td>
									<td>${listCount.name }</td>
									<td>${listCount.rdate }</td>
									<td>${listCount.hit }</td>
							
								</tr>
								
							</c:forEach>
			</table>
						<button type="button" class="btn btn-info btn-sm" id="myBtn"
								style="float: right;">글작성</button>
								
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

	  <script>
			$(document).ready(function(){
			    $("#myBtn").click(function(){
			        $("#myModal").modal();
			    });
			});
			$(document).ready(function() {
				$("#Btn").click(function() {
					$("#myModal2").modal();
				});
			});
		</script>
</body>

</html>