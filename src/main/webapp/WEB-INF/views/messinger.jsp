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
  <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
  


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



<style>
.sidebar{
min-height: calc(100vh - 0px); !important;
}

.menu{
font-size : 30px;
}
* {
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
}
html,body {
    height: 100%;
    overflow: hidden;
}

.clearfix:after {
    display: block;
    content: "";
    clear: both;
}

.hidden {
    display: none;
}

.form-control {
    width: 100%;
    min-height: 38px;
    font-size: 15px;
    border: 1px solid #c8c8c8;
}

.form-group {
    margin-bottom: 15px;
}

input {
    padding-left: 10px;
    outline: none;
}

h1, h2, h3, h4, h5, h6 {
    margin-top: 20px;
    margin-bottom: 20px;
}

h1 {
    font-size: 1.7em;
}

a {
    color: #128ff2;
}

button {
    box-shadow: none;
    border: 1px solid transparent;
    font-size: 14px;
    outline: none;
    line-height: 100%;
    white-space: nowrap;
    vertical-align: middle;
    padding: 0.6rem 1rem;
    border-radius: 2px;
    transition: all 0.2s ease-in-out;
    cursor: pointer;
    min-height: 38px;
}

button.default {
    background-color: #e8e8e8;
    color: #333;
    box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.12);
}

button.primary {
    background-color: #128ff2;
    box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.12);
    color: #fff;
}

button.accent {
    background-color: #ff4743;
    box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.12);
    color: #fff;
}

#username-page {
    text-align: center;
}

.username-page-container {
    background: #fff;
    
    border-radius: 2px;
    width: 100%;
    max-width: 500px;
    display: inline-block;
    margin-top: 42px;
    vertical-align: middle;
    position: relative;
    padding: 35px 55px 35px;
    min-height: 250px;
    position: absolute;
    top: 50%;
    left: 0;
    right: 0;
    margin: 0 auto;
    margin-top: -160px;
}

.username-page-container .username-submit {
    margin-top: 10px;
}


#chat-page {
    position: relative;
    height: 100%;
}

.chat-container {
    max-width: 800px;
    margin-left: 20px;
    margin-right: 20px;
    background-color: #fff;
    box-shadow: 0 1px 11px rgba(0, 0, 0, 0.27);
    margin-top: 30px;
    height: 700px;
    width: 500px;
    max-height: 1000px;
    position: relative;
}

#chat-page ul {
    list-style-type: none;
    background-color: #FFF;
    margin: 0;
    overflow: auto;
    overflow-y: scroll;
    padding: 0 20px 0px 20px;
    height: calc(100% - 150px);
}

#chat-page #messageForm {
    padding: 20px;
}

#chat-page ul li {
    line-height: 1.5rem;
    padding: 10px 20px;
    margin: 0;
    border-bottom: 1px solid #f4f4f4;
}

#chat-page ul li p {
    margin: 0;
}

#chat-page .event-message {
    width: 100%;
    text-align: center;
    clear: both;
}

#chat-page .event-message p {
    color: #777;
    font-size: 14px;
    word-wrap: break-word;
}

#chat-page .chat-message {
    padding-left: 68px;
    position: relative;
}

#chat-page .chat-message i {
    position: absolute;
    width: 42px;
    height: 42px;
    overflow: hidden;
    left: 10px;
    display: inline-block;
    vertical-align: middle;
    font-size: 18px;
    line-height: 42px;
    color: #fff;
    text-align: center;
    border-radius: 50%;
    font-style: normal;
    text-transform: uppercase;
}

#chat-page .chat-message span {
    color: #333;
    font-weight: 600;
}

#chat-page .chat-message p {
    color: #43464b;
}

#messageForm .input-group input {
    float: left;
    width: calc(100% - 85px);
}

#messageForm .input-group button {
    float: left;
    width: 80px;
    height: 38px;
    margin-left: 5px;
}

.chat-header {
    text-align: center;
    padding: 15px;
    border-bottom: 1px solid #ececec;
}

.chat-header h2 {
    margin: 0;
    font-weight: 500;
}

.connecting {
    padding-top: 5px;
    text-align: center;
    color: #777;
    position: absolute;
    top: 65px;
    width: 100%;
}

#publicMessage{
	display : inline-box;
}



#publicMessageButton{
	display : inline-box;
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
            href="#taskMenu" class="nav-link" data-toggle="collapse"
            > <i
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
            href="#Community" class="nav-link" data-toggle="collapse"> <i
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
            class="nav-link" href="chatting"  style="background-color: #85ce36; color: #ffffff;"><i class="far fa-comments"></i><span style="font-size : 15px;">&nbsp;메신저</span>
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
               class="fas fa-user-circle fa-fw"></i> ${user.name }</a>
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

 <noscript>
      <h2>Sorry! Your browser doesn't support Javascript</h2>
    </noscript>

    <div id="username-page" style="background-color : #f0f3f6;">
   
	        	
        <div class="username-page-container" style="background-color : #f0f3f6;">
        
        
       <!-- List group -->
       
			
            
            <form id="usernameForm" name="usernameForm" style="background-color : #f0f3f6;">
	            
                <div class="form-group" style="background-color :#f0f3f6 ;">
                    <button type="submit" class="accent username-submit">${user.department}부서 채팅방 들어가기</button>
                </div>
            </form>
            
   
           
            
        </div>
    </div>

    <div id="chat-page" class="hidden">
        <div class="chat-container">
            <div class="chat-header">
                <h2>${user.department} 부서 채팅방</h2>
            </div>
            <div class="connecting">
                Connecting...
            </div>
            <ul id="messageArea">

            </ul>
             
        
         
            <form id="publicMessageForm" name="publicMessageForm" nameForm="publicMessageForm">
                <div id="publicMessageDiv" class="form-group" style="margin-top:40px;">
                
                    <div>
                    <button type="submit" class="primary" id="publicMessageButton" style="float:right;">전송</button>
                        <input type="text" id="publicMessage" placeholder="내용을 입력해주세요.." autocomplete="off" class="form-control" style="float:right; width:50%;"/>
                        
                    </div>
                
                    
                
                
                </div>
                
            </form>
            
            <!-- <form id="messageForm" name="messageForm" nameForm="messageForm">
                <div class="form-group">
                    <div class="input-group clearfix">
                        <input type="text" id="message" placeholder="Type a message..." autocomplete="off" class="form-control"/>
                        <button type="submit" class="primary">Send</button>
                 
                    </div>
                </div>
            </form>
            -->
            
          </div>
            
           
    </div>








            <!-- ---------------------------------------여기까지 복사해놓으면 본문-------------------------------- -->
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
      $(document).ready(function() {
			$("#Btn").click(function() {
				$("#myModal2").modal();
			});
		});
      
      
      
      
      ////////////////////////////////////////////////////////////
      
      'use strict';

var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
//var messageForm = document.querySelector('#messageForm');
var publicMessageForm = document.querySelector('#publicMessageForm');
//var messageInput = document.querySelector('#message');
var publicMessageInput = document.querySelector('#publicMessage');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');

var stompClient = null;
var username = null;
var receiver = null;
var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

function connect(event) {
	var idtest = '<c:out value="${user.name}"/>';
    username = idtest;
    receiver = "관리자";
	
	console.log(username);
	console.log(receiver);
    if(username) {
        usernamePage.classList.add('hidden');
        chatPage.classList.remove('hidden');


        	
         var socket = new SockJS("<c:url value="/ws"/>");
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    }
    event.preventDefault();
}


function onConnected() {
    // Subscribe to the Public Topic
    var department = '<c:out value="${user.department}"/>';
    stompClient.subscribe('/topic/public/'+department, onMessageReceived);
    //stompClient.subscribe('/topic/messages/'+username, onMessageReceived);
	    

    // Tell your username to the server
    stompClient.send("/app/chat.addUser",
        {},
        JSON.stringify({sender: username, type: 'JOIN'})
    )

    connectingElement.classList.add('hidden');
}


function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}


function sendMessage(event) {
    //var messageContent = messageInput.value.trim();
    var publicMessageContent = publicMessageInput.value.trim();
    console.log("publicMessageContent");
    console.log(publicMessageContent);
    
    //if(messageContent && stompClient) {//개인에게 보낼때
    //	console.log("message");
      //  var chatMessage = {
        //    sender: username,
          //  content: messageInput.value,
            //type: 'CHAT'
        //};

    
        //stompClient.send("/app/chat/" + receiver, {}, JSON.stringify(chatMessage));
        //messageInput.value = '';
    //}
    //else 
   	if(publicMessageContent && stompClient)
    {
    	var department = '<c:out value="${user.department}"/>';
    	console.log("public message"+publicMessageInput.value);
    	var chatMessage = {
                sender: username,
                content: publicMessageInput.value,
                type: 'CHAT'
            };

        stompClient.send("/app/chat.sendMessage/"+department, {}, JSON.stringify(chatMessage));
        publicMessageInput.value = '';
    }
    else
    {
    	console.log("send error");
    }
    event.preventDefault();
}


function onMessageReceived(payload) {
	console.log(payload);
    var message = JSON.parse(payload.body);
    var sendername = message.sender;
    var myname = '<c:out value="${user.name}"/>';



    if(sendername==myname)
    {
   		var messageElement = document.createElement('li');
   		messageElement.style.background='#feffe8';
   		messageElement.style.textAlign="right";
   		
   		
    }
    else
    {
    	var messageElement = document.createElement('li');
    }


    if(message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = message.sender +'님이 들어왔습니다.';
    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.sender + '님이 나갔습니다.';
    } else {
    	
        messageElement.classList.add('chat-message');



        	var avatarElement = document.createElement('i');
        	if(sendername==myname)
        	{
        		avatarElement.style.position="relative";
        	}
     
        	
        if(sendername==myname)
        {
	        var avatarText = document.createTextNode("나");
        }
        else
        {
        	var avatarText = document.createTextNode(message.sender[0]);
        	
        }
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.sender);



        messageElement.appendChild(avatarElement);
        

        if(sendername==myname)
        {
        	
        
        var usernameElement = document.createElement('span');
        
        messageElement.appendChild(usernameElement);
        
        }
        else
        {
            var usernameElement = document.createElement('span');
            var usernameText = document.createTextNode(message.sender);
            usernameElement.appendChild(usernameText);
            messageElement.appendChild(usernameElement);        	
        	
        }
    }

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);

    messageArea.scrollTop = messageArea.scrollHeight;
}


function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }

    var index = Math.abs(hash % colors.length);
    return colors[index];
}
//messageForm.addEventListener('submit', sendMessage, true)
usernameForm.addEventListener('submit', connect, true)
publicMessageForm.addEventListener('submit', sendMessage, true)

      
      
      
      </script>
</body>

</html>