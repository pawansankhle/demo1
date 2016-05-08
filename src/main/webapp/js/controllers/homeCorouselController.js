app.controller('HomeCarouselCtrl',['$scope',function($scope){
  
  $scope.myInterval = 5000;
  $scope.noWrapSlides = false;
  $scope.active = 0;
  var slides = $scope.slides = [];
  var currIndex = 0;

 $scope.addSlide = function(index)
 { 
 	
 	slides.push({
    image: 'images/corousel/download'+index+'.jpg',
    text: 'search',
    id: currIndex++
   });
}
  
			for (var i = 1; i < 4; i++)
 			{
    			$scope.addSlide(i);
  			}
}]);