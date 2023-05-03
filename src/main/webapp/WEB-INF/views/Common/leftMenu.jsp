<%--
  Created by IntelliJ IDEA.
  User: chdlw
  Date: 2023-05-03
  Time: 오후 9:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

</head>
<body>

    <!-- 사이드메뉴 -->
    <ul class="sidebar navbar-nav" style="background-color: #3a4651; padding-bottom : 0px; font-size : 13px;!important ">
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

    </ul>
</body>
</html>
