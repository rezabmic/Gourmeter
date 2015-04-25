'use strict';

(function() { 

var mapViewModule =  angular.module('app.mapView', ['ngRoute', 'ngResource', 'uiGmapgoogle-maps','app']);

//routes configuration
mapViewModule.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/map', {
	  	title: 'Mapa',
		templateUrl : 'mapView/map.html',
		reloadOnSearch: false
	});
}]);

//RESTfull client for markers
mapViewModule.factory('Markers',  ['$resource',  function($resource){
	return $resource('service/cateringFacility/near', {}, {
		get: {method:'POST', isArray:true}
	});
}]);

//RESTfull client for markers
mapViewModule.factory('MarkersByCategory',  ['$resource',  function($resource){
	return $resource('service/cateringFacility/byCategory/:id/near', {id: '@id'}, {
		get: {method:'POST', isArray:true}
	});
}]);

mapViewModule.factory('MarkersByCategories',  ['$resource',  function($resource){
	return $resource('service/cateringFacility/byCategories/near', {}, {
		get: {method:'POST', isArray:true}
	});
}]);

//RESTfull client for tags
mapViewModule.factory('TagsByCategories',  ['$resource',  function($resource){
	return $resource('service/tags/byCategories',{},{
		get: {method:'POST', isArray:true}
	});
}]);

//RESTfull client for categories
mapViewModule.factory('Categories',  ['$resource',  function($resource){
	return $resource('service//category/all');
}]);

mapViewModule.factory('CateringFacility',  ['$resource',  function($resource){
	return $resource('service/cateringFacility/:id',{id: '@id'});
}]);

mapViewModule.factory('Recommendation',  ['$resource',  function($resource){
	return $resource('service/recommendation');
}]);


var MAP_ZOOM = 13;
var map;
var checkedCategories;
var checkedTags;
//Controllers
mapViewModule.controller("MapCtrl", function($scope, $location, $filter, Markers, MarkersByCategories, CateringFacility) {
	var search = $location.search();
	checkedCategories = search.categories == undefined ? [] : search.categories.split(",").map( Number );
	checkedTags = search.tags == undefined ? [] : search.tags.split(",").map( Number );

	$scope.checkedCategories = checkedCategories;
	$scope.checkedTags = checkedTags;
	
	var selected = {
		options : {visible : false},	 
	}; //selected marker
	
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
    $scope.mapOptions = {}; 
    
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
		$scope.mapOptions = {
  	    		center: { latitude: latitude, longitude: longitude}, 
				zoom: MAP_ZOOM,
				
				events : {
					bounds_changed: _.debounce($scope.changeMarkers,333, false)
				},
				markers: [],
				filteredMarkers: []
  	    };
	};
	
	$scope.changeMarkers = function(map){
		if(map == undefined || map == null){
			return ;
		}
		
		$scope.map = map;
		 
	   	 var bounds = map.getBounds();
		 var ne = bounds.getNorthEast();
		 var sw = bounds.getSouthWest(); 
		 var coordinates = {
				  latitudeTop: ne.k,
				  longitudeTop: ne.D,
				  latitudeBottom: sw.k,
				  longitudeBottom: sw.D
		 };
		 
		 if(checkedCategories.length == 0){
			 Markers.get(coordinates, function(markers,responseHeaders){
					//success callback
					$scope.mapOptions.markers = markers;
					
					setOnClickHandler(markers);
			});
		 } else{
//			 $scope.mapOptions.markers = $filter('filterByCategories')($scope.mapOptions.markers, checkedCategories);
//			 if(checkedTags.length != 0){
//					$scope.mapOptions.filteredMarkers = $filter('filterByTags')($scope.mapOptions.markers, checkedTags);
//			 }
//			 setOnClickHandler(markers);
			 
			 $scope.mapOptions.markers = MarkersByCategories.get({},{categories: checkedCategories, mapPos: coordinates}, function(markers,responseHeaders){
					//success callback
					$scope.mapOptions.markers = markers;
					
					if(checkedTags.length != 0){
						$scope.mapOptions.filteredMarkers = $filter('filterByTags')($scope.mapOptions.markers, checkedTags);
					}
					
					setOnClickHandler(markers);
			});
		 }
	}
	
	function setOnClickHandler(markers){
		_.each(markers, function (marker) {
  		  	//move infowindow below the marker
			marker.options = {visible : false, pixelOffset: new google.maps.Size(0, -25, 'px', 'px')};
			marker.closeClick = function () {
				$scope.selected.options.visible = false;
				return $scope.$apply();	
			};
			marker.onClicked = markerClicked.bind(marker);
		});
	}
	
	function markerClicked() {
		var marker = this;
		
		var cateringFacility = CateringFacility.get({},marker, function(){
			marker.title =  cateringFacility.title;
			marker.category = cateringFacility.category;
			marker.tags =  cateringFacility.tags;
			marker.openingHours =  cateringFacility.openingHours;
			marker.options.visible = true;
			$scope.selected = marker;
		});
	}

});

mapViewModule.controller('MenuController', function($scope, $filter){
	this.menuToString = function(menu){
		if(menu == null){
			return "Restaurace nemá denní menu";
		} else{
			
			return "Od " + $filter('date')(menu.from,"HH:mm") + " Do " +  $filter('date')(menu.to,"HH:mm"); 
		}
	}	
});

mapViewModule.controller('NavigationCtrl', function($scope, $location, Categories){
	var categories = {array : Categories.query()};
	
	$scope.categories = categories;
	
	$scope.selectCategory = function(categoryId){
		toggleSelection(categoryId, checkedCategories);
		
		if(checkedCategories.length != 0){
			$location.search('categories', $buildString(checkedCategories));
		} else{
			$location.search('categories', undefined);
			$location.search('tags', undefined);
			//checkedTags = [];  nefunguje - zacne blbnout pridavani tagu
			checkedTags.length = 0;
		}
		
		$scope.changeMarkers($scope.map);
	}
	
});

mapViewModule.controller('TagCtrl', function($scope, $filter, $location, TagsByCategories){
	var tags = {array : checkedCategories.length == 0?  []: TagsByCategories.get(checkedCategories)};
	
	$scope.$watchCollection('checkedCategories', function(newValue, oldValue) {
		if(newValue != undefined && newValue.length > 0){
			tags.array = TagsByCategories.get(newValue);
		}
	});
	
	$scope.tags = tags;
	
	$scope.selectTag = function(tagId){
		toggleSelection(tagId, checkedTags);
		
		if(checkedTags.length != 0){
			$location.search('tags', $buildString(checkedTags));
		} else{
			$location.search('tags', undefined);
		}
		
		$scope.mapOptions.filteredMarkers = $filter('filterByTags')($scope.mapOptions.markers, checkedTags);		
	}
	
});

mapViewModule.filter('filterByTags', function () {
    return function (markers, tags) {
    	console.log("filterTags");
    	console.log(markers);
        var filtered = []; 
        (markers || []).forEach(
        	function (marker) { 
	            var matches = marker.tags.some(function (tag) {         
	                return (tags.indexOf(tag.id) > -1);   
	            });                                               
	            if (matches) {           
	                filtered.push(marker); 
	            }
        	}
        );
        console.log(filtered);
        return filtered;
    };
});

mapViewModule.filter('filterByCategories', function () {
    return function (markers, categories) {
    	console.log("filterCategories");
    	console.log(markers);
        var filtered = []; 
        (markers || []).forEach(
        	function (marker) { 
	            var matches = marker.categories.some(function (category) {         
	                return (categories.indexOf(category.id) > -1);   
	            });                                               
	            if (matches) {           
	                filtered.push(marker); 
	            }
        	}
        );
        console.log(filtered);
        return filtered;
    };
});

mapViewModule.controller('RecommendationCtrl', function($scope, Recommendation, AuthenticationSvc){
	$scope.recommend = function(tag, facilityId, recommended){
		tag.recommended = tag.recommended +1;
		tag.reviewed = tag.reviewed +1;
		Recommendation.save({recommended: recommended, facilityId: facilityId, tagId: tag.id, userId: AuthenticationSvc.getUserInfo().userId});
	}
	
});


})();