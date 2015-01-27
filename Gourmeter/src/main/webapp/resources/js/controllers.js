(function() {
	var appControllers = angular.module("appControllers", ['uiGmapgoogle-maps']);

	appControllers.controller('WindowController', function ($scope) {
		$scope.selected = {};
		$scope.showPlaceDetails = function(param) {
			$scope.selected = param;
		}
	})
	
	appControllers.controller("MapController", ["$scope", "$http", function($scope, $http) {
		$scope.selected = {
				 options : {visible : false},
				 
			 };
		
		$scope.map = { 
				center: { latitude: 50.101500, longitude: 14.390791}, 
				zoom: 15,
				markers: [
				 {
					 id: 1,
					 latitude: 50.101500,
					 longitude: 14.390791,
					 cateringFacility: {
						 name: 'U studny',
						 type: 'Restaurace',
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
					}
				 },
				 {
					 id: 2,
					 latitude: 50.102246,
					 longitude: 14.392576,
					 cateringFacility: {
						 name: 'Pražská pivnice',
						 type: 'Hospoda',
						 tags: [{
							name: 'česká kuchyně',
							recommended : 10,
							reviewed: 15
						 },
						 {
							name: 'regionální kuchyně',
							recommended : 8,
							reviewed: 10
						 }]
					 }
				 }]};
		
		_.each($scope.map.markers, function (marker) {
			marker.options = {visible : false, pixelOffset: new google.maps.Size(-1, -25, 'px', 'px')};
			marker.closeClick = function () {
				$scope.selected.options.visible = false;
				//marker.options.visible = false;
				return $scope.$apply();	
			};
			marker.onClicked = function () {
				var v = $scope.selected;
				var m = marker;
				$scope.selected = marker;
				$scope.selected.options.visible = true;
				return $scope.$apply();
			};
			});
	}]);
	
	appControllers.controller("MainController", ["$scope", "$http", function($scope, $http) {
		this.categories = ['hlavní jídlo', 'rychlé občerstvení', 'potraviny'];
		
		//ajax request
		//$http.get('url').success(function(data) {
		//	this.categories = data;
		//});
		
		
		var menuFrom = new Date();
	    menuFrom.setHours( 10 );
	    menuFrom.setMinutes( 0 );
	    
	    var menuTo = new Date();
	    menuTo.setHours( 14 );
	    menuTo.setMinutes( 0 );
		
		$scope.cateringFacility = {
			title : '',
			tags : [],
			url : '',
			description : '',
			address : {
				city: '',
				street: '',
				cp : ''
			},
			menuFrom : menuFrom,
			menuTo : menuTo
		};
		
		this.submit = function() {
			this.cateringFacility = {};
		}
	}]);
	
	appControllers.controller("TagController", ["$scope", function($scope) {
		this.tags = ['menu','česká kuchyně','mezinárodní kuchyně', 'regionální kuchyně', 'grilované pokrmy', 'vegetariánské pokrmy'];
		
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
	
	appControllers.controller("OpeningHoursController", function() {
		this.days = ['Pondělí', 'Úterý', 'Středa', 'Čtvrtek',  'Pátek', 'Sobota', 'Neděle'];
		
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
	    
		this.openingHours = [{
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
			
			var nextOpeningHour = this.openingHours[openingHourID+1];
			if(openingHourID+1 < this.openingHours.length){
				
			    // is currently selected
				if(day.selected) {
					if(nextOpeningHour.days.length === 1){
						this.openingHours.splice(openingHourID+1, 1);
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
			var lastOpeningHour = this.openingHours[this.openingHours.length-1];
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
			this.openingHours.push(openingHour);
		};
		
		this.isEnabledButton = function(){
			var lastOpeningHour = this.openingHours[this.openingHours.length-1];
			for(var j = 0; j< lastOpeningHour.days.length; j++){
				if(!lastOpeningHour.days[j].selected){
					return true;
				}
			}
			return false;
		};
		
	});

})();