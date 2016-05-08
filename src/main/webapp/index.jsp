<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html lang="en">
	<head>
	<link rel="icon" type="image/png" sizes="16x16" href="images/fav/kp.png">
       <link  rel="stylesheet" href="css/bootstrap.min.css" />
       <link  rel="stylesheet" href="css/bootstrap-theme.min.css" />
       <link  rel="stylesheet" href="css/font-awesome.min.css" />
       <link  rel="stylesheet" href="css/ps-animate.css" />
       <link  rel="stylesheet" href="css/toastr.min.css" />
       <link  rel="stylesheet" href="css/bootstrap-material-design.min.css" />
       <link  rel="stylesheet" href="css/ripples.min.css" />
       <link  rel="stylesheet" href="css/app.css" />

       
   </head>
   <body ng-app="demoApp">
      <div ng-controller="ApplicationController">
          <div ng-include="'pages/navbar.tpl.html'"></div>
       </div>

       <div class="container-fluid">
	       <div class="col-sm-12">
	            <ui-view></ui-view>
	       </div>
       </div>
     
    </div>
     
        
       <script src="js/lib/jquery-2.2.3.min.js"></script>
       <script src="js/lib/angular/angular.min.js"></script>
       <script src="js/lib/angular/angular-animate.js"></script>
       <script src="js/lib/angular/angular-animate.js"></script>
       <script src="js/lib/angular/angular-messages.min.js"></script>
       <script src="js/lib/angular/angular-ui-router.js"></script>
       <script src="js/lib/angular/angular-resource.min.js"></script>
       <script src="js/lib/angular/underscore-min.js"></script>
       <script src="js/lib/ui-bootstrap-tpls-1.3.2.min.js"></script>
       <script src="js/lib/toastr.min.js"></script>
      
      

       <!-- constants -->
      <script src="js/app/common.js"></script>
       <script src="js/application.js"></script>
       <script src="js/constants/userRoles.js"></script>
       <script src="js/constants/urls.js"></script>
       <script src="js/constants/templates.js"></script>
       <script src="js/constants/messages.js"></script>
       <script src="js/constants/authEvents.js"></script>
       <script src="js/constants/stats.js"></script>


        <!-- services -->
       <script src="js/services/userService.js"></script>
       <script src="js/services/authService.js"></script>
       <script src="js/services/sessionService.js"></script>
       <script src="js/services/candidateService.js"></script>

       
       <!-- controllers -->
       <script src="js/controllers/homeController.js"></script>
       <script src="js/controllers/homeCorouselController.js"></script>
       <script src="js/controllers/CandidateController.js"></script>
       <script src="js/controllers/userAuthController.js"></script>
         <script src="js/controllers/CreateCandidateController.js"></script>

      

   </body>
</html>


