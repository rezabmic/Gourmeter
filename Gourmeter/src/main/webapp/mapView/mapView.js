'use strict';

(function() { 

var mapViewModule =  angular.module('app.mapView', ['ngRoute', 'ngResource', 'uiGmapgoogle-maps','app']);

//routes configuration
mapViewModule.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/map', {
		templateUrl : 'mapView/map.html'
	});
}]);

//RESTfull client for markers
mapViewModule.factory('Markers',  ['$resource',  function($resource){
	/*return $resource('service/tags/byCategory/:categoryId', {}, {
        query: {method:'GET', params: {categoryId: '@categoryId'}, isArray:true}
	});*/
}]);

//Controllers
mapViewModule.controller("MapCtrl", function($scope) {
	var selected = {
		options : {visible : false},	 
	};
	
	$scope.selected = selected;
	
	var t1 = new Date();
    t1.setHours( 10 );
    t1.setMinutes( 0 );
    var t2 = new Date();
    t2.setHours( 14 );
    t2.setMinutes( 0 );
    
    /*
     * Having an empty object results in no rendering, once the data(currentPosition) arrives 
     * from the server then the object is populated with the data and the view 
     * automatically re-renders itself showing the new data.
    */
    $scope.map = {}; 
    
    // Try HTML5 geolocation
    if(navigator.geolocation) {
  	    navigator.geolocation.getCurrentPosition(function(position) {
	  	    
  	    	createMap(position.coords.latitude, position.coords.longitude);
  	    	$scope.$apply();
  	    }, function() {
  	    	handleNoGeolocation(true);
  	    });
  	} else {
  	    // Browser doesn't support Geolocation
  	    handleNoGeolocation(false);
  	}

  	function handleNoGeolocation(errorFlag) { //TODO
  	  if (errorFlag) {
  	    var content = 'Error: The Geolocation service failed.';
  	  } else {
  	    var content = 'Error: Your browser doesn\'t support geolocation.';
  	  }
  	}
	
	function createMap(latitude,longitude){
		$scope.map = {
  	    		center: { latitude: latitude, longitude: longitude}, 
				zoom: 15,
				events : {
				     bounds_changed: function(map){
				    	 var bounds = map.getBounds();
				    	 var ne = bounds.getNorthEast();
				    	 var sw = bounds.getSouthWest(); 
				     } 
				},
				markers: [
				{
					id: 1,
					latitude: 50.101500,
					longitude: 14.390791,
					title: 'U studny',
					description : 'Skvělé pivo a tlačenka.',
					category: {id:1, name: 'Restaurace'}, 
					url : 'www.ustudny.cz',
					tags: [{
						name: 'česká kuchyně',
						recommended : 10,
						reviewed: 15
					},
					{
						name: 'regionální kuchyně',
						recommended : 8,
						reviewed: 10
					}],
					menu: {
						from : t1,
					    to : t2,
					    url : 'www.ustudny.cz/denni-menu.html'
					},
					openingHours: []
				},
				{
					id: 2,
					latitude: 50.102246,
					longitude: 14.392576,
					title: 'Pražská pivnice',
					description : 'Skvělé regionální pokrmy.',
					category: {id:1, name: 'Restaurace'}, 
					url : 'www.prazska-pivnice.cz',
					tags: [{
						name: 'česká kuchyně',
						recommended : 10,
						reviewed: 15
					},
					{
						name: 'regionální kuchyně',
						recommended : 8,
						reviewed: 10
					}],
					menu: null,
					 openingHours: []
				}
				]	
  	    };
  	    
  	  _.each($scope.map.markers, function (marker) {
  		  	//move infowindow below the marker
			marker.options = {visible : false, pixelOffset: new google.maps.Size(0, -25, 'px', 'px')};
			marker.closeClick = function () {
				selected.options.visible = false;
				return $scope.$apply();	
			};
			marker.onClicked = function () {
				selected = marker;
				selected.options.visible = true;
				$scope.selected = selected;
			};
		});
	};
	
});

mapViewModule.controller('MenuController', function($scope, $filter){
	this.menuToString = function(menu){
		if(menu === null){
			return "Restaurace nemá denní menu";
		} else{
			
			return "Od " + $filter('date')(menu.from,"HH:mm") + " Do " +  $filter('date')(menu.to,"HH:mm"); 
		}
	}	
});

})();