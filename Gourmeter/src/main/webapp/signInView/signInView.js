'use strict';

(function() { 

var signInViewModule =  angular.module('app.signInView', ['ngRoute', 'ngResource', 'app']);

//routes configuration
signInViewModule.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/login', {
		templateUrl : 'loginView/login.html'
	});
}]);

//Model
var user = {
	login: '',
	password: '',
};

//Controllers
signInViewModule.controller("SignInCtrl", function($scope, $location, AuthenticationSvc) {
	$scope.user = user;
	
	this.submit = function(){
		var a = AuthenticationSvc.login(user.login,user.password);
		user = {
				login: '',
				password: '',
		};
		
	};
	
	this.logout = function(){
		AuthenticationSvc.logout();
		$location.path("/signIn");
	};
	
});

})();