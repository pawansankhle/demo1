app.
controller('authCtrl',['$scope','$rootScope','AuthService','$uibModal','$uibModalStack','TEMPLATES','AUTH_EVENTS',
            function ($scope, $rootScope,AuthService,$uibModal,$uibModalStack,TEMPLATES,AUTH_EVENTS) {
	
	$scope.errorDialog = false;
	$scope.isLoginLoading = false;
	$scope.isSignupLoading = false;
	$scope.credentials = {
			username: '',
			password: ''
	};
	
	$scope.login = function (credentials) {
		$scope.isLoginLoading = true;
	    AuthService.login(credentials).then(function (res) {
	    	$scope.isLoginLoading = false;
	        switch(res.status){
			case 204:
				$scope.message = Msgs.loginFailedMsg;
				$scope.errorDialog = true;
				$rootScope.$broadcast(AUTH_EVENTS.loginFailed);
				$scope.disable=!$scope.disable;
				$scope.reset();
				//$scope.close();
				break;
			case 200:
				$rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
				$rootScope.setCurrentUser(res.data);
				$scope.disable=!$scope.disable;
				$scope.reset();
				$scope.close();
				break;

			}
		});
	};
	$scope.logout = function () {
		AuthService.logout().then(function (res) {
			$rootScope.$broadcast(AUTH_EVENTS.logoutSuccess);
			$rootScope.setCurrentUser(null);
			$scope.reset();

		}, function () {
			$rootScope.$broadcast(AUTH_EVENTS.logoutFailed);
		});
	};
	
    $scope.reset = function(){
            $scope.credentials = {
			username: '',
			password: ''
	};
	
  };
  $scope.reset();
  
  $scope.authModal = function(){

		var modelInstance = $uibModal.open({
      	animation: true,
      	templateUrl: TEMPLATES.loginTplPath,
        controller: 'authCtrl',
        size: 'sm',
      			
    		});
            
		};
   
   $scope.close = function(){
   	$uibModalStack.dismissAll();
   	
   }

}]);


