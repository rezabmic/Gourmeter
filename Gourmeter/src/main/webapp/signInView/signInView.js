'use strict';

(function() { 

var signInViewModule =  angular.module('app.signInView', ['ngRoute', 'ngResource', 'app']);

//routes configuration
signInViewModule.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/signIn', {
	  title: 'Přihlásit se',
      templateUrl: 'signInView/signIn.html'
  });
}]);

//Model
var user = {
	login: '',
	password: '',
};

function clearModel(){
	user = {
			login: '',
			password: '',
	};
}

//Controllers
signInViewModule.controller("SignInCtrl", function($scope, $location, AuthenticationSvc) {
	$scope.user = user;
	
	this.submit = function(){
		var a = AuthenticationSvc.login(user.login,user.password);
		clearModel();
	};
	
	this.logout = function(){
		AuthenticationSvc.logout();
	};
	
});

})();