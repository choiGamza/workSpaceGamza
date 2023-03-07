<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!-- EUC-KR로 페이지 인코딩이랑 charset을 했을때 한글깨짐현상 일어남 -->




<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
   content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">


<title>업무관리 프로그램</title>





<!-- Bootstrap core CSS-->
<link href="resources/bootstrap/vendor/bootstrap/css/bootstrap.min.css"
   rel="stylesheet">

<!-- Custom fonts for this template-->
<link href="resources/bootstrap/vendor/fontawesome-free/css/all.min.css"
   rel="stylesheet" type="text/css">

<!-- Page level plugin CSS-->
<link
   href="resources/bootstrap/vendor/datatables/dataTables.bootstrap4.css"
   rel="stylesheet">

<!-- Custom styles for this template-->
<link href="resources/bootstrap/css/sb-admin.css" rel="stylesheet">

<!-- Modal  -->
<link
   href="${pageContext.request.contextPath}/resources/loginBootstrap/vendor/bootstrap/css/bootstrap.css">

<style>
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

.sidebar {
   min-height: calc(100vh - 0px);
   !
   important;
}

.menu {
   font-size: 30px;
}
</style>

</head>

<body id="page-top">


   <div id="wrapper">

      <!-- 사이드메뉴 -->
      <ul class="sidebar navbar-nav"
         style="background-color: #3a4651; padding-bottom: 0px; font-size: 13px;!important ">
         <!--------------- 제목-------------------- -->

         <li class="nav-item"><a class="nav-link"
            href="adminMain?id=${user.id }"> <span
               style="color: white; font-size: 17px; font-family: 'Open Sans', sans-serif;">&nbsp;<i
                  class="fab fa-audible" style="color: #85ce36; font-size: 21px;"></i>&nbsp;The
                  JoenAcademy
            </span>
         </a></li>


         <!-- -------------콜랩스 관리자메뉴 ---------- -->





         <c:if test="${user.id=='admin'}">

            <li class="nav-item"
               onmouseover="this.style.backgroundColor='#333e48'"
               onmouseout="this.style.backgroundColor='#3a4651'"><a
               href="#collapse1" class="nav-link" data-toggle="collapse"> <i
                  class="fas fa-fw fa-cog"></i> <span style="font-size: 15px;">관리자메뉴</span>
                  <i class="fas fa-angle-down"></i>
            </a></li>


            <div class="collapse" id="collapse1">
               <ul class="nav navbar-nav">
                  <li class="nav-item"
                     onmouseover="this.style.backgroundColor='#333e48'"
                     onmouseout="this.style.backgroundColor='#3a4651'"><a
                     href="#adminOrgz" class="nav-link" data-toggle="collapse"
                     style="padding-left: 20px;"> <i class="far fa-fw fa-building"></i>
                        <span style="font-size: 15px;">조직관리(관리자)</span> <i
                        class="fas fa-angle-down"></i>
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
                        <i class="fas fa-fw fa-address-book"></i> <span
                        style="font-size: 15px;">휴가관리(관리자)</span>
                  </a></li>
               </ul>
            </div>
         </c:if>

         <!-- ------------------------------------------- -->



         <li class="nav-item"
            onmouseover="this.style.backgroundColor='#333e48'"
            onmouseout="this.style.backgroundColor='#3a4651'"><a
            class="nav-link" href="userOrganization"> <i
               class="far fa-fw fa-building"></i> <span style="font-size: 15px;">회사조직도(사용자)</span>
         </a></li>




         <!-- ------------------------------------------------ -->

         <li class="nav-item"
            onmouseover="this.style.backgroundColor='#333e48'"
            onmouseout="this.style.backgroundColor='#3a4651'"><a
            href="#taskMenu" class="nav-link" data-toggle="collapse"
            style="background-color: #85ce36; color: #ffffff;"> <i
               class="far fa-fw fa-calendar-alt"></i> <span
               style="font-size: 15px;">업무관리(사용자)</span> <i
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
                  class="nav-link" href="userTask?id=${user.id }"
                  style="padding-left: 45px;"> 자신의 업무일지 </a></li>
            </ul>
         </div>

         <!-- ---------------------------------------------- -->

         <li class="nav-item"
            onmouseover="this.style.backgroundColor='#333e48'"
            onmouseout="this.style.backgroundColor='#3a4651'"><a
            href="#holidayMenu" class="nav-link" data-toggle="collapse"> <i
               class="fas fa-fw fa-address-book"></i> <span
               style="font-size: 15px;">휴가관리(사용자)</span> <i
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
            href="#Community" class="nav-link" data-toggle="collapse"> <i
               class="fas fa-fw fa-atom"></i> <span style="font-size: 15px;">커뮤니티</span>
               <i class="fas fa-angle-down"></i>
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







      <div id="content-wrapper"
         style="background-color: #f0f3f6; padding-top: 0px; padding-left: 0px; padding-right: 0px;">

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
                     class="fas fa-user-circle fa-fw"></i> ${user.name } </a>
                  <div class="dropdown-menu dropdown-menu-right"
                     aria-labelledby="userDropdown">
                     <a class="dropdown-item" href="#" data-toggle="modal"
                        data-target="#changeModal" role="button" id="Btn">정보변경</a>
                     <div class="dropdown-divider"></div>
                     <a class="dropdown-item" href="#" data-toggle="modal"
                        data-target="#logoutModal">로그아웃</a>
                  </div></li>
            </ul>

         </div>
         <div class="container-fluid"
            style="padding-left: 25px; padding-right: 25px; padding-top: 25px; padding-bottom: 25px;">


            <!-- -------------------------본문시작(복사해놓는 부분)-----------------------------  -->
            <h3>
               <c:if test="${projectimportant==0 }">
               <a href="projectImportant?important=1">
                  <i class="far fa-star" style="color:black;"></i>
               </a>
               </c:if>
               <c:if test="${projectimportant==1 }">
               <a href="projectImportant?important=0">
                  <i class="fas fa-star" style="color:red;"></i>
               </a> 
               </c:if>
               ${projectname}
            </h3>
            
            <br>
            <c:if test="${taskList!=null }">
               
                  <c:forEach items="${taskList}" var="tasklist" varStatus="vs">
                     <div class="col-xl-3 col-sm-3 mb-3" style="display:inline-block; width:50%">
                        <div class="card text-white o-hidden h-100"
                           style="background-color: #3a4651">
                           <div class="card-body"
                              style="padding-left: 0px; padding-right: 0px; text-align: center;">

                              <div class="row">
                                 <div class="col-8"
                                    style="color: white; text-align: center; margin-bottom: 0px; display: inline;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${tasklist.task }
                                 </div>
                                 <div class="col">
                                    <a href="#" class="badge badge-primary" data-toggle="modal"
                                       data-target="#testModal${vs.index}" role="button"
                                       id="taskdetail">업무추가</a>
                                        <a href="taskDelete?task=${tasklist.task}"
                                       class="badge badge-danger">리스트삭제</a>
                                 </div>
                              </div>
                           </div>
                           <c:forEach items="${taskdetailList}" var="taskdetaillist">
                              <c:if test="${taskdetaillist.task==tasklist.task }">

                                 <div class="card-footer text-white clearfix small z-1"
                                    style="background-color: #d7dde4">
                                    <div class="row">
                                       <c:if test="${taskdetaillist.progress=='process' }">
                                          <div class="col-xl-10 col-sm-7 col-md-4" style="color: black;">${taskdetaillist.taskdetail}</div>

                                          <div style="text-align: right;">
                                             <a
                                                href="taskdetailModify?task=${tasklist.task}&taskdetail=${taskdetaillist.taskdetail}&choice=1">&nbsp;<i
                                                class="fas fa-check" style="color: blue;"></i></a> <a
                                                href="taskdetailModify?task=${tasklist.task}&taskdetail=${taskdetaillist.taskdetail}&choice=4">&nbsp;<i
                                                class="fas fa-trash-alt" style="color: black;"></i></a>
                                          </div>
                                       </c:if>
                                       <c:if test="${taskdetaillist.progress=='complete' }">
                                          <div class="col-10" style="color: red;">${taskdetaillist.taskdetail}(업무완료)</div>

                                          <div style="text-align: right;">
                                             <a
                                                href="taskdetailModify?task=${tasklist.task}&taskdetail=${taskdetaillist.taskdetail}&choice=2">&nbsp;<i
                                                class="fas fa-check" style="color: red;"></i></a> <a
                                                href="taskdetailModify?task=${tasklist.task}&taskdetail=${taskdetaillist.taskdetail}&choice=3">&nbsp;<i
                                                class="fas fa-trash-alt" style="color: black;"></i></a>
                                          </div>
                                       </c:if>
                                    </div>
                                    <div class="w-100"></div>
                                    <!--  ${taskdetaillist.taskdetail}<i
                  class="fas fa-fw fa-cog" style="text-align : right"></i></div>-->

                                 </div>
                     </c:if>
                           </c:forEach>

                        </div>
                     </div>
                     <!-- ----------------------------------업무안에 detail업무 추가하는 모달창  ---------------------->

                     <div class="modal fade" id="testModal${vs.index}" role="dialog">
                        <div class="modal-dialog">
                           <div class="modal-content">
                              <div class="modal-header">
                                 <h3 class="modal-title">새 업무</h3>
                              </div>
                              <!-- ----------------------------2018/11/13------------------------------- -->
                              <!-- ---------------이부분 부터는 controller에 DB에 넣는 mapping 설정--------------- -->

                              <form action="newtaskDetail" class="form-inline" method="post">
                                 <div class="modal-body">
                                    새 업무 : <input type="text" placeholder="새 업무는 무엇인가요?"
                                       name="taskdetail" class="form-control"
                                       style="width: 380px;"> <input type="hidden"
                                       name="task" value="${tasklist.task}"> <br> <br>
                                 </div>
                                 <div class="modal-footer" style="width: 600px;">
                                    <input type="submit" class="btn btn-primary" value="등록">
                                    <button type="button" class="btn" data-dismiss="modal">닫기</button>
                                 </div>
                              </form>

                           </div>
                        </div>
                     </div>
                  </c:forEach>
               
            </c:if>

            <!-------------------------------- 플러스버튼 ---------------------------------->

            <div class="col-xl-3 col-sm-6 mb-3">
               <div class="card text-muted o-hidden h-100"
                  style="background-color: #f0f3f6; border: 0px;">
                  <div class="card-body"
                     style="padding-left: 0px; padding-right: 0px; text-align: center;">
                     <div class="mr-5">
                        <a href="newTeam" data-toggle="modal" data-target="#testModal"
                           role="button" id="myBtn"><i class="fas fa-plus-circle"
                           style="font-size: 70px;"></i></a>
                     </div>
                  </div>
               </div>
            </div>

            <!------------------------------------------------------------------- -->
         
         
         
         </div>
         <br><br>
         <form action = "projectFileupload" method = "post" style = "float : right;" enctype="multipart/form-data">
                  <div class="input-group">
                    <div class="custom-file">
                      <input type="file" name = "file2">      
                    </div>
                    <div class="input-group-prepend">
                      <button type = "submit" class="btn btn-primary">Upload</button>
                      <input type = "hidden" name = "number" value = "${number }">
                    </div>
                  </div>
         </form>
         <table class = "table table-bordered">
            <tr>
               <td style = "text-align : center;"> 파일목록 </td>
            </tr>
            <!-- forEach 리스트  -->
            <c:forEach items = "${taskfileList }" var = "taskfileList">
            <tr>
               <td> 
               <a href = "fileDownload?name=${taskfileList.projectname }">${taskfileList.projectname}</a> 
               <a href = "fileDelete?name=${taskfileList.projectname }&number=${number}" style = "float:right;"><i class="fas fa-trash-alt" style="color: black; padding-right : 20px;"></i></a>   
               </td>
            </tr>
            </c:forEach>
            <!-- --------------- -->
         </table>
         
         <div class="modal fade" id="myModal" role="dialog">
            <div class="modal-dialog">
               <div class="modal-content">
                  <div class="modal-header">
                     <h3 class="modal-title">새 리스트</h3>
                  </div>
                  <!-- ----------------------------2018/11/13------------------------------- -->
                  <!-- ---------------이부분 부터는 controller에 DB에 넣는 mapping 설정--------------- -->

                  <form action="newTask" class="form-inline" method="post">
                     <div class="modal-body">
                        새 리스트 : <input type="text" placeholder="새 리스트는 무엇인가요?" name="task"
                           class="form-control" style="width: 380px;"> <br> <br>
                     </div>
                     <div class="modal-footer" style="width: 600px;">
                        <input type="submit" class="btn btn-primary" value="등록">
                        <button type="button" class="btn" data-dismiss="modal">닫기</button>
                     </div>
                  </form>

               </div>
            </div>
         </div>




         <!-- ---------------------------------------여기까지 복사해놓으면 본문-------------------------------- -->
      </div>
      <!-- /.content-wrapper -->
      <div class="modal fade" id="changeModal" role="dialog">
         <div class="modal-dialog">
            <div class="modal-content">
               <div class="modal-header">
                  <h3 class="modal-title">정보 변경</h3>
               </div>
               <form action="changeInfo" class="form-inline" method="post"
                        enctype="multipart/form-data">
                        <div class="modal-body">
                           <c:if test="${user.profileimg=='false' }">
           프로필을 등록해주세요
           <img class="rounded-circle img-fluid d-block mx-auto"
                                 src="${pageContext.request.contextPath}/resources/profileimg/human.png"
                                 alt="">
                              <input type="file" name="file">
                           </c:if>

                           <c:if test="${user.profileimg == 'true' }">
                              <img class="rounded-circle img-fluid d-block mx-auto"
                                 src="resources/profileimg/${user.id }.jpg"
                                 style="width: 150px; height: 150px;">
                              <input type="file" name="file">
                           </c:if>

                           <br>
                           <br> 고유 ID ${user.id }<input type="hidden"
                              value="${user.id }" name="id"> <br> <br> PW
                           변경 <input type="password" name="pw" placeholder="비밀번호 변경"
                              class="form-control"><br>
                           <br> 이름 ${user.name }
                           <div style="float: right;">성별 ${user.sex }</div>
                           <br>
                           <br> 부서 ${user.department }<br>
                           <br> 직급 ${user.position }<br>
                           <br> 주민번호 ${user.registernum } <br>
                           <br> 폰번호 <input type="text" class="form-control"
                              name="phonenum" value="${user.phonenum }"
                              style="width: 380px;"> <br>
                           <br> 주소 <input type="text" name="address"
                              class="form-control" value="${user.address }"
                              style="width: 380px;"><br>
                           <br> email <input type="email" name="email"
                              class="form-control" value="${user.email }"
                              style="width: 380px;">


                  </div>
                  <div class="modal-footer" style="width: 600px;">
                     <input type="submit" class="btn btn-primary" value="변경">
                     <button type="button" class="btn" data-dismiss="modal">닫기</button>
                  </div>
               </form>
            </div>
         </div>
      </div>
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

   <!-- Bootstrap core JavaScript-->
   <script src="resources/bootstrap/vendor/jquery/jquery.min.js"></script>
   <script
      src="resources/bootstrap/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

   <!-- Core plugin JavaScript-->
   <script
      src="resources/bootstrap/vendor/jquery-easing/jquery.easing.min.js"></script>

   <!-- Page level plugin JavaScript-->
   <script src="resources/bootstrap/vendor/chart.js/Chart.min.js"></script>
   <script
      src="resources/bootstrap/vendor/datatables/jquery.dataTables.js"></script>
   <script
      src="resources/bootstrap/vendor/datatables/dataTables.bootstrap4.js"></script>

   <!-- Custom scripts for all pages-->
   <script src="resources/bootstrap/js/sb-admin.min.js"></script>

   <!-- Demo scripts for this page-->
   <script src="resources/bootstrap/js/demo/datatables-demo.js"></script>
   <script src="resources/bootstrap/js/demo/chart-area-demo.js"></script>




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