
function hasValue(val)
{
  return (val != null && val != undefined && val != NaN && val != "NaN" && val != "null" && val != "undefined" && (val != "" || String(val) == "0") && val != "-Please select-" && val != "--" && val != "?_s=");
}
var app = angular.module('demoApp',['ngResource', 'ui.router','ngMessages','ui.bootstrap'])

.run(['$rootScope','AUTH_EVENTS','STATS','AuthService','$state',
function ($rootScope, AUTH_EVENTS, STATS, AuthService,$state) 
    {
      
      $rootScope.$on('$stateChangeStart', function (event, next) {
      if(next.name == STATS.CANDIDATE ){
        var authorizedRoles = next.data.authorizedRoles;
        if (!AuthService.isAuthorized(authorizedRoles)) {
               event.preventDefault();
               $state.go(STATS.HOME);
             if (AuthService.isAuthenticated()) {
              // user is not allowed
              $rootScope.$broadcast(AUTH_EVENTS.notAuthorized);
            } else {
              // user is not logged in
              $rootScope.$broadcast(AUTH_EVENTS.notAuthenticated);
            }
          }
         }
      });
      
}])
.config(['$stateProvider','$urlRouterProvider','$httpProvider','TEMPLATES','STATS','USER_ROLES',
  function($stateProvider,$urlRouterProvider,$httpProvider,TEMPLATES,STATS,USER_ROLES) {
      $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
      $urlRouterProvider.otherwise("/home");

     $stateProvider
      .state(STATS.HOME, {
        url: "/home",
        templateUrl: TEMPLATES.homeTpl,
        controller : 'homeCtrl'
        })
      .state(STATS.CANDIDATE, {
        url: "/candidates",
        templateUrl: TEMPLATES.candidateTpl,
        controller : 'candidateCtrl',
        data:{
              authorizedRoles: [USER_ROLES.admin]
             }
        }
      )
      .state(STATS.CANDIDATE_DETAIL, {
        url: "/candidate/:name",
        templateUrl: TEMPLATES.candidateDetailTpl,
        controller : 'candidateDetailCtrl',
        data:{
              authorizedRoles: [USER_ROLES.admin]
             }
        }
      )
      
  }])
.controller('ApplicationController',['$scope','$rootScope','USER_ROLES','AuthService','SessionSrv','AUTH_EVENTS','UserSrv','$state'
,'STATS',function($scope,$rootScope,USER_ROLES,AuthService,SessionSrv,AUTH_EVENTS,UserSrv,$state,STATS)
    { 
     $rootScope.$watch('$rootScope.currentUser',function(){return $rootScope.currentUser},true);
      UserSrv.getProfile().then(function(user){SessionSrv.saveUser(user);$rootScope.setCurrentUser(user)});
      
      $rootScope.$on('toggleLoading',function(){
                  if(hasValue($scope.showLoading)){
                      $scope.showLoading = ''
                  }else{
                    $scope.showLoading = 'loading';
                  }
           });
          $scope.userRoles = USER_ROLES;
           $rootScope.setCurrentUser = function (user) 
        {  
          
          if(hasValue(user) && hasValue(user.roles))
        {
             if(user.roles[0].roleName == $scope.userRoles.admin)
              {
                $state.go(STATS.CANDIDATE)
              }
            $rootScope.currentUser = user;
        }else{
          $rootScope.currentUser = null;
          $state.go(STATS.HOME);
          
        }
            };
      
      $scope.isAuthorized = AuthService.isAuthorized;    
     

    }]);



