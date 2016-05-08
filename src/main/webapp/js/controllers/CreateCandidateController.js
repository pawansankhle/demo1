app.controller('createCandidateCtrl',['$scope','CandidateSrv','$uibModalStack','$rootScope',
	function($scope,CandidateSrv,$uibModalStack,$rootScope){
	
	//$scope.candidate.companyDetails = [];
	$scope.isLoginLoading = false;
	/*** function to create candidate**/
	$scope.createCandidate = function(form)
	{
		$scope.isLoginLoading = true;
		form.companyDetails=[];
		form.companyDetails.push($scope.company);
		CandidateSrv.candidateCreate(form).then(function(res){
			      $scope.isLoginLoading = false;
                   $scope.close();

		});
	};

	$scope.close = function()
	{
   	  $uibModalStack.dismissAll();
   	};
}])
	
