app.service('UserSrv',['$http','URLS',function($http,URLS){

this.getProfile = function()
	{
		return $http.get(URLS.userProfileUrl)
		.then(function(res){
		    if(res.status == 204){
		        return null;
		    }
		     return res.data;
          },function(res){});
	  };
}]);