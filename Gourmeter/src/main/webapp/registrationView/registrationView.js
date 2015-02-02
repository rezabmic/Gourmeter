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

//Model
var user = {
	login: 'a',
	nickname: 'a',
	email: 'a@a',
	city: 'a',
	password: 'a',
	password2: 'a',
};

//Controllers
registrationViewModule.controller("RegistrationCtrl", function($scope, Registration) {
	$scope.user = user;
	
	this.submit = function(){
		delete user.password2;
		user.password = btoa(user.password);
		Registration.save(user, function(value, responseHeaders){
			//success callback
			var a = 1;
		}, function(httpResponse){
			//error callback
			var a = 1;
		});
	}
	
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