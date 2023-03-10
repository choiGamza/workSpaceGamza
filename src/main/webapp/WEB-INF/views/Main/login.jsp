
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Welcome to TheJoen</title>

<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/resources/loginBootstrap/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom fonts for this template -->
<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:200,200i,300,300i,400,400i,600,600i,700,700i,900,900i" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Merriweather:300,300i,400,400i,700,700i,900,900i" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/loginBootstrap/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">


<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/resources/loginBootstrap/css/coming-soon.min.css" rel="stylesheet">

<!-- Modal  -->
<link href="${pageContext.request.contextPath}/resources/loginBootstrap/vendor/bootstrap/css/bootstrap.css">

<style>
	.modal {
        text-align: center;
        vertical-align: middle;
}
@media screen and (min-width: 768px) { 
        .modal:before {
                display: inline-block;
                vertical-align: middle;
                content: " ";
                height: 20%;
        }
}
</style>
</head>

<body>

	<div class="overlay"></div>
	<video playsinline="playsinline" autoplay="autoplay" muted="muted"
		loop="loop">
		<source src="${pageContext.request.contextPath}/resources/loginBootstrap/mp4/bg.mp4"
			type="video/mp4">
	</video>

	<div class="masthead">
		<div class="masthead-bg"></div>
		<div class="container h-100">
			<div class="row h-100">
				<div class="col-12 my-auto">
					<div class="masthead-content text-white py-5 py-md-0">
						<h1 class="mb-3">TheJoen Academy</h1>
							<p class="mb-5">????????? ???????????? : qweqwe<br>
										????????? ?????????, ???????????? : admin,qweasd
						<form action="Login" method="post">
							<div class="input-group input-group-newsletter">
								<input type="text" name="ID" class="form-control"
									placeholder="login..." aria-label="login..."
									aria-describedby="basic-addon">
							</div>
							<div class="input-group input-group-newsletter">
								<input type="password" name="PW" class="form-control"
									placeholder="password..." aria-label="password..."
									aria-describedby="basic-addon">
							</div>
							<div class="input-group-append">
								<button class="btn btn-secondary" type="submit" value="Login">Login</button>
							</div>
						</form>
						<div class="input-group-append">
							<button class="btn btn-secondary" type="submit" value="pwFind"
								id="pwBtn">???????????? ?????????</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div class="social-icons">
		<ul class="list-unstyled text-center mb-0">
			<li class="list-unstyled-item"><a href="https://www.google.com/">
					<i class="fab fa-google"></i>
			</a></li>
			<li class="list-unstyled-item">
				<a href="#" data-toggle="modal" data-target="#testModal" role="button" id="myBtn">
					 <i class="fab fa-whatsapp">
						
					 </i>
				</a>
			</li>
		</ul>
	</div>

	<div class="modal fade" id="testModal" tabindex="-1" role="dialog">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">
					???????????????
					<i class = "fas fa-phone"></i>
					</h5>
				</div>
				<div class="modal-body">
		
					????????? : 02-255-1515 <br>
					????????? : 02-255-1516 <br>
					????????? : 02-255-1517 <br>
					????????? : 02-255-1518 <br>
					
				</div>

				<div class="modal-footer">
					<button type="button" class="btn" data-dismiss="modal">??????</button>
				</div>

			</div>
		</div>
	</div>
 
 
 	
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" >
		<div class="modal-dialog modal-lg" >
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title">??????????????????</h3>
				</div>
				<form action = "Find" method = "post">
				<div class="modal-body">
					
        				????????? <input type="text" class="form-control" placeholder="?????????" name = "id">
    					<br>   
					    ?????? <input type="text" class="form-control" placeholder="??????" name = "name">
					     <br>
					  	
						e-mail <input type="email" class = "form-control" placeholder="email" name = "email"><br>
						
					
				</div>
				<div class="modal-footer">
					<input type = "submit" class = "btn btn-primary" value = "??????">
					
					<button type="button" class="btn" data-dismiss="modal">??????</button>
				</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Bootstrap core JavaScript -->
	<script src="${pageContext.request.contextPath}/resources/loginBootstrap/vendor/jquery/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/loginBootstrap/vendor/bootstrap/js/bootstrap.min.js"></script>

	<!-- Custom scripts for this template -->
	<script src="${pageContext.request.contextPath}/resources/loginBootstrap/js/coming-soon.min.js"></script>

	<script>
		$(document).ready(function(){
		    $("#pwBtn").click(function(){
		        $("#myModal").modal();
		    });
		});
		$(document).ready(function(){
		    $("#myBtn").click(function(){
		        $("#testModal").modal();
		    });
		});
		
	</script>
</body>

</html>
