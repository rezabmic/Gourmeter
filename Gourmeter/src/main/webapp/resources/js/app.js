(function() {
	//ui.bootstrap dependency contains angularJS components(e.g timePicker)
	var app = angular.module('app', [ 'ui.bootstrap', 'ngRoute', 'appControllers']);

	app.config([ '$routeProvider', function($routeProvider) {
		$routeProvider.
			/*
			 * when('/index', { 
			 * 	  templateUrl: 'index.html', 
			 * 	  controller: 'MainContriller', 
			 * 	  controllerAs: 'main' }).
			 */
			when('/index', {
				templateUrl : 'addCateringFacility.html'
			}).
			when('/map', {
				templateUrl : 'map.html'
			}).
			when('/tester', {
				templateUrl : 'tester.html'
			}).
			when('/cateringFacilities/:catFacilityId', {
		        templateUrl: 'testerReview.html'
		    }).
			otherwise({
				redirectTo : '/index'
			});
	}]);

})();