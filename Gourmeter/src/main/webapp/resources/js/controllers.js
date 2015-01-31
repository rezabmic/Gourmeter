(function() {
	var appControllers = angular.module("appControllers", ['uiGmapgoogle-maps', 'appServices']);
	
	appControllers.controller('MenuController', ['$scope', '$filter', function($scope, $filter){
		this.menuToString = function(menu){
			if(menu === null){
				return "Restaurace nemá denní menu";
			} else{
				
				return "Od " + $filter('date')(menu.from,"HH:mm") + " Do " +  $filter('date')(menu.to,"HH:mm"); 
			}
		}	
	}]);
	
	appControllers.controller("EditCateringFacilityController", ["$scope", "$http", '$routeParams', function($scope, $http, $routeParams) {

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
	
	appControllers.controller("MapController", ["$scope", "$http", function($scope, $http) {
		$scope.selected = {
			options : {visible : false},	 
		};
		
		var t1 = new Date();
	    t1.setHours( 10 );
	    t1.setMinutes( 0 );
	    var t2 = new Date();
	    t2.setHours( 14 );
	    t2.setMinutes( 0 );
	    
	    // Try HTML5 geolocation
	    if(navigator.geolocation) {
	  	    navigator.geolocation.getCurrentPosition(function(position) {

		  	    $scope.map.center.latitude = position.coords.latitude;
		  	    $scope.map.center.longitude = position.coords.longitude;
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
	    
		$scope.map = { 
				center: { latitude: 50.101500, longitude: 14.390791}, 
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
		]};
		
		_.each($scope.map.markers, function (marker) {
			marker.options = {visible : false, pixelOffset: new google.maps.Size(0, -25, 'px', 'px')};
			marker.closeClick = function () {
				$scope.selected.options.visible = false;
				//marker.options.visible = false;
				return $scope.$apply();	
			};
			marker.onClicked = function () {
				$scope.selected = marker;
				$scope.selected.options.visible = true;
				return $scope.$apply();
			};
		});
	}]);
	
	appControllers.controller("CategoriesController", [ 'Categories', function(Categories) {
		this.categories = Categories();
	}]);
	
	appControllers.controller("MainController", ["$scope", "$http", 'Categories', function($scope, $http, Categories) {
		$scope.menu = false;
		
		var menuFrom = new Date();
	    menuFrom.setHours( 10 );
	    menuFrom.setMinutes( 0 );
	    
	    var menuTo = new Date();
	    menuTo.setHours( 14 );
	    menuTo.setMinutes( 0 );
		
		$scope.cateringFacility = {
			title : '',
			categoryId : null,
			tags : [],
			url : '',
			description : '',
			address : {
				city: 'Praha',
				street: 'Zikova',
				cp : 702
			},
			menu : {
				from: menuFrom,
				to : menuTo,
				url : '',
			},
			latitude: null,
			longitude: null,
		};
		
		this.submit = function() {
			var cf = $scope.cateringFacility;
			var geocoder = new google.maps.Geocoder();
			//address format House Number, Street Direction, Street Name, Street Suffix, City, State, Zip, Country
			var address = cf.address.cp + ", " + cf.address.street + ", " + cf.address.city;
			geocoder.geocode( {'address': address}, function(results, status) {
			    if (status == google.maps.GeocoderStatus.OK) {
			    	cf.latitude = results[0].geometry.location.k;
			    	cf.longitude = results[0].geometry.location.D;
			    } else {
			      alert('Geocode was not successful for the following reason: ' + status);
			    }
			});

		}
	}]);
	
	appControllers.controller("TagController", ["$scope", 'Tags', 'MenuTag', function($scope, Tags, MenuTag) {
		this.tags = Tags($scope.cateringFacility.categoryId);
		
		this.isMenuSelected = function(){
			var selected = $scope.cateringFacility.tags;
			for(var i = 0; i<selected.length; i++){
				if(selected[i] == MenuTag()){
					return true;
				}
			}
			return false;
		};

		this.toggleSelection = function toggleSelection(tag) {
		    var idx = $scope.cateringFacility.tags.indexOf(tag);
		
		    // is currently selected
		    if (idx > -1) {
		      $scope.cateringFacility.tags.splice(idx, 1);
		    }
		
		    // is newly selected
		    else {
		      $scope.cateringFacility.tags.push(tag);
		    }
		};
	}]);
	
	appControllers.controller("OpeningHoursController", ["$scope", 'Days', function($scope, Days) {
		this.days = Days();
		
		var t1 = new Date();
	    t1.setHours( 8 );
	    t1.setMinutes( 0 );
	    
	    var t2 = new Date();
	    t2.setHours( 12 );
	    t2.setMinutes( 0 );
	    
	    var t3 = new Date();
	    t3.setHours( 13 );
	    t3.setMinutes( 0 );
	    
	    var t4 = new Date();
	    t4.setHours( 16 );
	    t4.setMinutes( 0 );
	    
	    var id = 0;
	    
	    this.isValid = function(openingHour){
	    	var days =  openingHour.days;
	    	var counter = 0; 
	    	for(var i = 0; i < days.length; i++){
	    		if(!days[i].selected){
	    			counter++;
	    		}
	    	}
	    	return counter != 7;
	    };
	    
	    $scope.cateringFacility.openingHours = [{
			id : id,
			time1 : t1,
			time2 : t2,
			time3 : t3,
			time4 : t4,
			pause : false,
			days : [{id: 1, selected: false},
			        {id: 2, selected: false},
					{id: 3, selected: false},
					{id: 4, selected: false},
					{id: 5, selected: false},
					{id: 6, selected: false},
					{id: 7, selected: false}]
		}];
		
		this.changed = function(openingHour, idx){
			var newTime;
			if(idx === 1){
				//change time2
				newTime = new Date();
				newTime.setHours(openingHour.time1.getHours()+1);
				newTime.setMinutes(0);
				openingHour.time2 = newTime;
				this.time2Updated(openingHour, idx);
				
			} else if(idx === 2){
				this.time2Updated(openingHour, idx);
			}
		};
		
		this.time2Updated = function(openingHour, idx){
			if(openingHour.time1.getHours() > openingHour.time2.getHours()){
				//change time1
				newTime = new Date();
				newTime.setHours(openingHour.time2.getHours()-1);
				newTime.setMinutes(0);
				openingHour.time1 = newTime;
			}
				
			//change time3 and time4
			newTime = new Date();
			newTime.setHours(openingHour.time2.getHours()+1);
			newTime.setMinutes(0);
			openingHour.time3 = newTime;
			openingHour.time4 = new Date();
			openingHour.time4.setHours(openingHour.time2.getHours()+2);
			openingHour.time4.setMinutes(0);
		};
		
		this.getFirstDay = function(openingHour){
			return this.days[openingHour.days[0]-1].name;
		};
		
		this.getLastDay = function(openingHour){
			var lastDay =  openingHour.days[1];
			var index = openingHour.days[0];
			
			while(lastDay != openingHour.days.length && lastDay === ++index){
				lastDay = openingHour.days[index];
			}
			
			return this.days[lastDay-1].name;
		};
		
		this.getText = function(openingHour){
			return this.getFirstDay(openingHour) + "-" + this.getLastDay(openingHour) + ": " + 
				openingHour.time1.getHours() + ":" + openingHour.time1.getMinutes() + "-" + 
				openingHour.time2.getHours() + ":" + openingHour.time2.getMinutes();
		};
		
		this.toggleSelection = function(day, openingHourID) {
			day.selected = !day.selected;
			
			var nextOpeningHour = $scope.cateringFacility.openingHours[openingHourID+1];
			if(openingHourID+1 < $scope.cateringFacility.openingHours.length){
				
			    // is currently selected
				if(day.selected) {
					if(nextOpeningHour.days.length === 1){
						$scope.cateringFacility.openingHours.splice(openingHourID+1, 1);
					} else{
						this.removeDay(nextOpeningHour.days, day);
					}
			    }
				
			    // is newly selected
			    else {
			    	nextOpeningHour.days.push(day);
			    	nextOpeningHour.days.sort(function(a,b){
			    		return a.id - b.id;
			    	});
			    }
			} 
		};
		
		this.removeDay = function(days, day){
			for(var i = 0; i< days.length; i++){
				if(days[i].id === day.id){
					var removed = days.splice(i,1);
					return;
				}
			}
		}
		
		this.addOpeningHours = function(){
			//select days
			var days = [];
			var lastOpeningHour = $scope.cateringFacility.openingHours[$scope.cateringFacility.openingHours.length-1];
			for(var j = 0; j< lastOpeningHour.days.length; j++){
				if(!lastOpeningHour.days[j].selected){
					var d = lastOpeningHour.days[j];
					days.push({
						id: d.id,
						selected: false});
					} 
			}
			
			var openingHour = {
				id : ++id,	
				time1 : t1,
				time2 : t2,
				time3 : t3,
				time4 : t4,
				pause : false,
				days : days
			};
			$scope.cateringFacility.openingHours.push(openingHour);
		};
		
		this.isEnabledButton = function(){
			var lastOpeningHour = $scope.cateringFacility.openingHours[$scope.cateringFacility.openingHours.length-1];
			for(var j = 0; j< lastOpeningHour.days.length; j++){
				if(!lastOpeningHour.days[j].selected){
					return true;
				}
			}
			return false;
		};
		
	}]);

})();