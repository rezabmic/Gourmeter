(function() {
var appServices = angular.module("appServices",[])

appServices.value('Categories', function(){
	return [{id:1, name:'hlavní jídlo'}, {id:2, name: 'rychlé občerstvení'}, {id:3, name:'potraviny'}];
});

appServices.value('Tags', function(category){
	return ['menu','česká kuchyně','mezinárodní kuchyně', 'regionální kuchyně', 'grilované pokrmy', 'vegetariánské pokrmy'];
});

appServices.value('MenuTag', function(){
	return 'menu';
});

appServices.value('Days', function(){
	return ['Pondělí', 'Úterý', 'Středa', 'Čtvrtek',  'Pátek', 'Sobota', 'Neděle'];
});



})();
