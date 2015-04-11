'use strict';

(function() { 

var addCFModule =  angular.module('app.addCateringFacilityView', ['ngRoute', 'ngResource', 'app']);

//routes configuration
addCFModule.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/index', {
	title: 'Přidat stravovací zařázení',
    templateUrl: 'addCateringFacilityView/addCateringFacility.html'
  });
}]);

//RESTfull client for categories
addCFModule.factory('Categories', ['$resource',  function($resource){
    return $resource('service/category/all', {}, {
               query: {method:'GET', isArray:true}
    });
}]);

//RESTfull client for tags
addCFModule.factory('Tags',  ['$resource',  function($resource){
	return $resource('service/tags/byCategory/:categoryId', {}, {
        query: {method:'GET', params: {categoryId: '@categoryId'}, isArray:true}
	});
}]);

//RESTfull client for tags
addCFModule.factory('TagsByCategories',  ['$resource',  function($resource){
	return $resource('service/tags/byCategories', {}, {
        query: {method:'POST', isArray:true}
	});
}]);

//RESTfull client for cateringFacility
addCFModule.factory('CateringFacility',  ['$resource',  function($resource) {
	return $resource('service/cateringFacility/:cfId:userId', {userId: "@userId", cfId:'@id'}, {
        save: {method:'POST', params: {userId: "@userId"}}
	});
}]);

//DEFAULT VALUES
//default values for menu 
var MENU_DEFAULT_VALUES = function(){
	var menuFrom = new Date();
  menuFrom.setHours( 10 );
  menuFrom.setMinutes( 0 );
  
  var menuTo = new Date();
  menuTo.setHours( 14 );
  menuTo.setMinutes( 0 );
  
	return {from : menuFrom, to: menuTo};
}();

//default menuTag name
var MENU_TAG = 'menu';

//default values for openingHours
var DEFAULT_OPENING_HOURS = function(){
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
	
	return [{from : t1, to: t2}, {from : t3, to: t4}];
}();

var DAYS = ['Pondělí', 'Úterý', 'Středa', 'Čtvrtek',  'Pátek', 'Sobota', 'Neděle'];

//MODEL
var cateringFacility = {
	title : '',
	categories : [],
	tags : [],
	url : null,
	description : '',
	address : {
		city: '',
		street: '',
		houseNumber : null
	},
	menu : {
		from: MENU_DEFAULT_VALUES.from,
		to : MENU_DEFAULT_VALUES.to,
		url : null,
	},
	latitude: null,
	longitude: null,
};	

function cleanModel(){
	cateringFacility = {
			title : '',
			categoryId : null,
			tags : [],
			url : null,
			description : '',
			address : {
				city: '',
				street: '',
				houseNumber : null
			},
			menu : {
				from: MENU_DEFAULT_VALUES.from,
				to : MENU_DEFAULT_VALUES.to,
				url : null,
			},
			latitude: null,
			longitude: null,
		};
}

var tags = {array : []};

//Controllers
addCFModule.controller('AddCateringFacilityCtrl',  function($scope, CateringFacility, AuthenticationSvc) {
	$scope.cateringFacility = cateringFacility;
	
	this.submit = function() {
		var geocoder = new google.maps.Geocoder();
		
		//address format House Number, Street Direction, Street Name, Street Suffix, City, State, Zip, Country
		var address = cateringFacility.address.houseNumber + ", " + cateringFacility.address.street + ", " + cateringFacility.address.city;
		
		geocoder.geocode( {'address': address}, function(results, status) {
		    if (status == google.maps.GeocoderStatus.OK) {
		    	cateringFacility.latitude = results[0].geometry.location.k;
		    	cateringFacility.longitude = results[0].geometry.location.D;
		    	
		    	CateringFacility.save({userId:AuthenticationSvc.getUserInfo().userId},cateringFacility, function(value, responseHeaders){
		    		//success callback
		    		cleanModel();
		    	});
		    } else {
		      alert('Geocode was not successful for the following reason: ' + status);
		    }
		});	
	};	
});

addCFModule.controller("CategoriesController",  function($scope, Categories, Tags, TagsByCategories) {
	this.categories = Categories.query();
	
	this.changeTags = function(){
		//get tags by categoryID
		//tags.array = Tags.query({categoryId: cateringFacility.categories[0]});
		tags.array = TagsByCategories.query(cateringFacility.categories);
	};
});

addCFModule.controller("TagController",  function($scope, MenuTag) {
	var cfTags = cateringFacility.tags;
	
	$scope.tags = tags;
	
	this.isMenuSelected = function(){
		var selected = cfTags;
		for(var i = 0; i < selected.length; i++){
			if(selected[i] == MenuTag()){
				return true;
			}
		}
		return false;
	};

	this.toggleSelection = function toggleSelection(tag) {
	    var idx = cfTags.indexOf(tag);
	
	    // is currently selected
	    if (idx > -1) {
	    	cfTags.splice(idx, 1);
	    }
	
	    // is newly selected
	    else {
	    	cfTags.push(tag);
	    }
	};
});

addCFModule.controller("OpeningHoursController", function($scope) {
	this.days = DAYS;
	var id = 0;
    
    var openingHours = [{
		id : id,
		time1 : DEFAULT_OPENING_HOURS[0].from,
		time2 : DEFAULT_OPENING_HOURS[0].to,
		time3 : DEFAULT_OPENING_HOURS[1].from,
		time4 : DEFAULT_OPENING_HOURS[1].to,
		pause : false,
		days : [{id: 1, selected: false},
		        {id: 2, selected: false},
				{id: 3, selected: false},
				{id: 4, selected: false},
				{id: 5, selected: false},
				{id: 6, selected: false},
				{id: 7, selected: false}]
	}];
    
    cateringFacility.openingHours = openingHours;
	
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
	
	this.toggleSelection = function(day, openingHourID) {
		day.selected = !day.selected;
		
		var nextOpeningHour = openingHours[openingHourID+1];
		if(openingHourID+1 < openingHours.length){
			
		    // is currently selected
			if(day.selected) {
				if(nextOpeningHour.days.length === 1){
					openingHours.splice(openingHourID+1, 1);
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
		var lastOpeningHour = openingHours[openingHours.length-1];
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
			time1 : DEFAULT_OPENING_HOURS[0].from,
			time2 : DEFAULT_OPENING_HOURS[0].to,
			time3 : DEFAULT_OPENING_HOURS[1].from,
			time4 : DEFAULT_OPENING_HOURS[1].to,
			pause : false,
			days : days
		};
		
		openingHours.push(openingHour);
	};
	
	this.isEnabledButton = function(){
		var lastOpeningHour = openingHours[openingHours.length-1];
		for(var j = 0; j< lastOpeningHour.days.length; j++){
			if(!lastOpeningHour.days[j].selected){
				return true;
			}
		}
		return false;
	};
	
});
})();