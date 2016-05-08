app.service('CandidateSrv',['$http','URLS','$rootScope','Msgs',function($http,URLS,$rootScope,Msgs){
	
	   this.currentCandidate = {};
	   this.setCurrentCandiate = function(obj,index)
	   {
	   	   this.currentCandidate = obj;
	   	   this.curretnIndex = index;
	   }

	   this.getCurrentObjIndex = function()
	   {
	   		return this.curretnIndex;
	   }

	   this.getCurrentCandiate = function(obj)
	   {

	   	  return this.currentCandidate;
	   }
	   this.getService = function(url,msg)
	   {
		    return $http.get(url).success(function(res){
				 switch(res)
				 {
				case 200:
					return res.data;
		        case 204:
					return false;
					 
			  }
		    })
		};

		this.postService = function(url,data){
             return $http.post(url,data);
		}
		/** function to get candidate list****/
	  this.getCandidateList = function(filter,lower,upper)
		{
        var url = '';
        var candidates = [];
        var fiql = this.createCandidateFIQL(filter);
    	if(hasValue(fiql)){
        	url  = URLS.CANDIDATE_SEARCH_URL+fiql+"&orderBy=firstName&llimit="+lower+"&ulimit="+upper+"&orderType=asc&date=" + new Date();
    	}else{
        	url = URLS.CANDIDATE_SEARCH_URL+"?orderBy=firstName&ulimit=100&llimit=0&orderType=asc&date=" + new Date();
        }
         return this.getService(url);
          };
          
        
       this.candidateCreate = function(obj)
      	{
      		var url = URLS.CANDIDATE_CREATE_URL
      		return this.postService(url,obj).then(function(res){
      			   if(res.status == 200)
      			   {
      			   	   toastr.success(Msgs.candidateCreatedSuccessMsg,"Candidate");
      			   	   var data = res.data;
                       $rootScope.$emit('candidate:add',{data})
      			   }else if(res.status == 204)
      			   {
                        toastr.error(Msgs.Error204Msg,"Candidate");
      			   	   return null;
      			   }
      			},function(res){
                 	toastr.error(res.data,"Error");
                 }); 
      			   
      		 };

      	this.candidateUpdate  = function(obj)
      	{
      		return this.postService(URLS.CANDIDATE_UPDATE_URL,obj)
      		.then(function(res){
                 if(res.status == 200)
                 {   
                 	 toastr.success(Msgs.candidateUpdatedSuccessMsg,"Candidate");
                     return res.data;
                 }else 
                 if(res.status == 204){
                 	toastr.error(Msgs.Error204Msg,"Candidate");
                    return null;
                 }
             },function(res){
                 	toastr.error(res.data,"Error");
           });  
      	};

        this.createCandidateFIQL  = function(filter)
        {
          var fiql= "?_s=";
          if(hasValue(filter))
          {
            if(hasValue(filter.registerId))
            {
              fiql+= "(id=="+filter.registerId+");";
            }
            if(hasValue(filter.name)){
              fiql+= "(firstName=="+filter.name+");";
            }

            if(hasValue(fiql))
            {
              fiql = fiql.substring(0,fiql.length-1);
            }
            else
          {
            fiql = "";
          }
          console.dir(fiql);
          return  fiql;

          }
          

        };
}]);
