'use strict';

(function() { 

var registrationViewModule =  angular.module('app.registrationView', ['ngRoute', 'ngResource']);

//routes configuration
registrationViewModule.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/registration', {
		templateUrl : 'registrationView/registration.html'
	});
}]);

//RESTfull client for user registration
registrationViewModule.factory('Registration',  ['$resource',  function($resource){
	return $resource('service/user/create', {}, {});
}]);

////RESTfull client for testUniqueLogin
registrationViewModule.factory('UniqueLogin',  ['$resource',  function($resource){
	return $resource('service/user/testUniqueLogin', {login: '@login'}, {});
}]);


//Model
var user = {
	login: '',
	nickname: '',
	email: '',
	city: '',
	password: '',
	password2: '',
};

//Controllers
registrationViewModule.controller("RegistrationCtrl", function($scope, $location, Registration, UniqueLogin) {
	$scope.user = user;
	
	var uniqueLogin = {unique: true};
	$scope.uniqueLogin = uniqueLogin;
	
	this.submit = function(){
		delete user.password2;
		user.password = btoa(user.password);
		Registration.save(user, function(value, responseHeaders){
			//success callback
			$location.path("/index");
		}, function(httpResponse){
			//error callback
		});
	}
	
	this.checkLogin = function(){
		uniqueLogin.unique = UniqueLogin.get({login: user.login}, function(value, responseHeaders){
			uniqueLogin.unique = value.$resolved;
			//$scope.$apply();
		});
	};
	
	this.checkPassword = function(passwd1, passwd2){
		var b = passwd1 === passwd2;
		if(b === false){
			user.password = '';
			user.password2 = '';
		}
		return b;
	}
});

})();