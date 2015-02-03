(function() {
	//ui.bootstrap dependency contains angularJS components(e.g timePicker)
	var app = angular.module('app', ['ngRoute', 'app.signInView', 'app.addCateringFacilityView', 'app.mapView', 'app.registrationView', 'appControllers', 'ui.bootstrap']);

	// enumerate routes that don't need authentication
	var routesThatDontRequireAuth = ['/login', '/registration'];

	 // check if current location matches route  
	 var routeClean = function (route) {
		 var b = false;
		 _.each(routesThatDontRequireAuth, function (noAuthRoute) {
			 if(route.startsWith(noAuthRoute)){
				 b = true;
			 }
		 });
		return b; 
	 };
	  
	app.factory("AuthenticationSvc", function($window, $resource, $location) {
		  var userInfo = null;
		 
		  function login(userName, password) {
		    var loginEnc = btoa(password); 
		    var passwordEnc = btoa(password);	
		    
		    var signIn = $resource('service/user/auth', {}, {});
		    
		    var user = signIn.save({
		    	loginEnc: loginEnc,
		    	passwordEnc: passwordEnc
			    }, function(value, responseHeaders){
				//success callback
				userInfo = value;
				$window.sessionStorage["userInfo"] = JSON.stringify(userInfo);
				$location.path("/index");
			}, function(httpResponse){
				//error callback
				$promise.reject(error);
			});
		    
		    return user.$promise;
		  }
		  
		  function logout() {
			  var a = 1;
			  $window.sessionStorage["userInfo"] = null;
			  userInfo = null;
		  }
		 
		  function getUserInfo() {
			  return userInfo;
		  }
		  
		  function isLoggedIn() {
			  return userInfo != null && userInfo != "null";
		  }
		  
		  function init() {
			  if ($window.sessionStorage["userInfo"]) {
			    userInfo = JSON.parse($window.sessionStorage["userInfo"]);
			  }
		  }
		  
		  
		  return {
		    login: login,
		    logout: logout,
		    getUserInfo: getUserInfo,
		    init: init,
		    isLoggedIn: isLoggedIn
		  };
	});
	
	app.config([ '$routeProvider', function($routeProvider) {
		$routeProvider.
			when('/tester', {
				templateUrl : 'tester.html'
			}).
			when('/cateringFacilities/:catFacilityId', {
		        templateUrl: 'testerReview.html'
		    }).
		    when('/signIn', {
		        templateUrl: 'signInView/signIn.html'
		    }).
			otherwise({
				redirectTo : '/index'
			});
	}]);
	
	app.run(["$rootScope", "$location", 'AuthenticationSvc', function($rootScope, $location, AuthenticationSvc) {
		$rootScope.$on('$routeChangeStart', function (event, next, current) {
			AuthenticationSvc.init();
		    // if route requires auth and user is not logged in
		    if (!routeClean($location.url()) && !AuthenticationSvc.isLoggedIn()) {
		      // redirect back to login
		      $location.path('/signIn');
		    }
		  });  
		}]);
	
})();