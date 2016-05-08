<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html lang="en">
	<head>
       <link  rel="stylesheet" href="css/bootstrap.min.css" />
       <link  rel="stylesheet" href="css/bootstrap-theme.min.css" />
       
 <style>
    

body {
	background: #eee !important;	
}

.wrapper {	
	margin-top: 80px;
  margin-bottom: 80px;
}

.form-signin {
  max-width: 380px;
  padding: 15px 35px 45px;
  margin: 0 auto;
  background-color: #fff;
  border: 1px solid rgba(0,0,0,0.1);  

  .form-signin-heading,
	.checkbox {
	  margin-bottom: 30px;
	}

	.checkbox {
	  font-weight: normal;
	}

	.form-control {
	  position: relative;
	  font-size: 16px;
	  height: auto;
	  padding: 10px;
		@include box-sizing(border-box);

		&:focus {
		  z-index: 2;
		}
	}

	input[type="text"] {
	  margin-bottom: -1px;
	  border-bottom-left-radius: 0;
	  border-bottom-right-radius: 0;
	}

	input[type="password"] {
	  margin-bottom: 20px;
	  border-top-left-radius: 0;
	  border-top-right-radius: 0;
	}
}

 </style>
	</head>
	 <body>
      <div class="container">
           <div class="wrapper">
		    <form class="form-signin" method="post" action="<c:url value='j_spring_security_check' />">       
		      <h2 class="form-signin-heading">Please login</h2>
		      <input style="margin: 10px 0px 10px 0px;" type="text" class="form-control" name="j_username" placeholder="Email Address" required="" autofocus="" />
		      <input style="margin: 10px 0px 10px 0px;" type="password" class="form-control" name="j_password" placeholder="Password" required=""/>      
		      <!-- <label class="checkbox">
		        <input type="checkbox" value="remember-me" id="rememberMe" name="rememberMe"> Remember me
		      </label> -->
		      <button style="margin: 10px 0px 10px 0px;" class="btn btn-lg btn-primary btn-block" type="submit">Login</button>   
		    </form>
		  </div>
	  </div>
	</body>
	<!-- Latest compiled and minified JavaScript -->
	   <script src="js/lib/jquery-2.2.3.min.js"></script>
       <script src="js/lib/ui-bootstrap-tpls-1.3.2.min.js"></script>
</html>
