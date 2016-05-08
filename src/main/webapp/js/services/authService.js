app.factory('AuthService',['$http','SessionSrv','Msgs', function ($http, SessionSrv,Msgs) {
  var authService = {};
 
  authService.login = function (credentials) {
	  var url = 'rest/Users/login';
	  return $http
      .post(url , credentials)
      .then(function (res) {
		    switch(res.status){
				case 200:
			      SessionSrv.saveUser(res.data);
			      toastr.success(Msgs.loginSuccessMsg, "Login");
			      return res;
			      break;
			   case 204:
			      return res;
			      break;
			   }
			    
		});
  };
  
  authService.logout = function () {
	    return $http
	      .get('rest/Users/logout')
	      .then(function (res) {
			  switch(res.status){
				  case 200:
				      SessionSrv.clearUser();
				      toastr.success(Msgs.logoutSuccessMsg,"Logout")
				      return res;
				      break
				  case 204:
					  toastr.error(Msgs.logoutErrorMsg,"Logout")
				      return res;
				      break;
			         }
			     });
	  };
 
  authService.isAuthenticated = function () {
	  return !!SessionSrv.user;
  };
 
  authService.isAuthorized = function (authorizedRoles) {
  	  if (!angular.isArray(authorizedRoles)) {
           authorizedRoles = [authorizedRoles];
      }
      if(hasValue(SessionSrv.user)){
      return (authService.isAuthenticated() &&
      authorizedRoles.indexOf(SessionSrv.user.roles[0].rolename) !== -1);
      }else
          return false;
  };
 
  return authService;
  
}]);
