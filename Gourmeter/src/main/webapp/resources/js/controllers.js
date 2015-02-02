(function() {
	var appControllers = angular.module("appControllers", ['uiGmapgoogle-maps', 'appServices']);
	
	appControllers.controller("EditCateringFacilityController", ["$scope", '$routeParams', function($scope, $routeParams) {

		$scope.catFacilityId = $routeParams.catFacilityId;
		
		$scope.cateringFacility = {
		    	 id: 1,
			     title : 'Pražská pivnice',
			     categoryId: 1, 
			     tags : ['česká kuchyně', 'regionální kuchyně'],
			     url : 'www.prazska-pivnice.cz',
			     description : 'Skvělé pivo a tlačenka.',
			     latitude: 50.101500,
				 longitude: 14.390791,
			     menu: null,
			     openingHours: [],
		};
		
	}]);
	
	appControllers.controller("TesterController", ["$scope", "$http", function($scope, $http) {
		var t1 = new Date();
	    t1.setHours( 10 );
	    t1.setMinutes( 0 );
	    var t2 = new Date();
	    t2.setHours( 14 );
	    t2.setMinutes( 0 );
	    
		this.cateringFacilities = [
		     {
		    	 id: 1,
			     title : 'Pražská pivnice',
			     category: {id:1, name: 'Restaurace'}, 
			     tags : ['česká kuchyně', 'regionální kuchyně'],
			     url : 'www.prazska-pivnice.cz',
			     description : 'Skvělé pivo a tlačenka.',
			     latitude: 50.101500,
				 longitude: 14.390791,
			     menu: null,
			     openingHours: [],
		     },
		     {	
		    	 id: 2,
		    	 title : 'U studny',
		    	 category: {id:1, name: 'Restaurace'}, 
			     tags : ['česká kuchyně', 'regionální kuchyně'],
			     url : 'www.ustudny.cz',
			     description : 'Skvělé regionální pokrmy.',
			     latitude: 50.101500,
				 longitude: 14.390791,
			     menu: {
			          from : t1,
			          to : t2,
			          url : 'www.ustudny.cz/denni-menu.html'
			     },
			     openingHours: []
		     }];
	}]);
	
	
})();