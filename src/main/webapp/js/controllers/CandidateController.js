app.controller('candidateCtrl',['$scope','CandidateSrv','TEMPLATES','$uibModal','$uibModalStack','$rootScope','$state','STATS',
	function($scope,CandidateSrv,TEMPLATES,$uibModal,$uibModalStack,$rootScope,$state,STATS){
	
	
  $scope.filter = {
    registerId : '',
  };
	$scope.getListOfCandidates = function(filter,lower,upper)
	{
    $scope.candidateList = [];
		CandidateSrv.getCandidateList(filter,lower,upper).then(function(res){
				 $scope.candidateList = res.data
			   });
	}

	
	$rootScope.$on('candidate:add',function(evnt,res){
		$scope.candidateList.push(res.data);
	});

	$scope.addCandidate  = function()
	{
		var modelInstance = $uibModal.open({
      	animation: true,
      	templateUrl: TEMPLATES.createCandidateTpl,
        controller: 'createCandidateCtrl',
        size: 'lg'});
    }

	$scope.close = function()
	{
   	  $uibModalStack.dismissAll();
   	}

   	$scope.openCandiateDetail = function(obj,index)
   	{
         CandidateSrv.setCurrentCandiate(obj,index);
         var name = obj.name;
         $state.go(STATS.CANDIDATE_DETAIL,{name});
   	};

    $scope.search = function(){
         $scope.getListOfCandidates($scope.filter,0,100);
     }

    $scope.resetFilter = function()
    {
        $scope.filter = 
        {
             registerId : '',
        }
        $scope.getListOfCandidates(null,0,10);
    }
	
	$scope.getListOfCandidates(null,0,10);
}])

.controller('candidateDetailCtrl',['$scope','CandidateSrv',function($scope,CandidateSrv){

     $scope.currentCandidate = CandidateSrv.getCurrentCandiate();
      
     $scope.updateCandidate = function()
     {
        var updatedCandidate = $scope.currentCandidate;
     	CandidateSrv.candidateUpdate(updatedCandidate).then(
     		function(res){
               var index = CandidateSrv.getCurrentObjIndex();
               if(hasValue(index)){
               	   console.log(index)
               	   candidateList[index] = res.data;
               }

     		});
     };

      
}])
