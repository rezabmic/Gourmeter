(function() {
	//ui.bootstrap dependency contains angularJS components(e.g timePicker)
	var app = angular.module('app', ['ngRoute', 'app.addCateringFacilityView', 'app.mapView' , 'appControllers', 'ui.bootstrap']);

	app.config([ '$routeProvider', function($routeProvider) {
		$routeProvider.
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