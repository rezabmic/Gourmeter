(function() {
var appServices = angular.module("appServices",[])

appServices.value('Categories', function(){
	return ['hlavní jídlo', 'rychlé občerstvení', 'potraviny'];
});

appServices.value('Tags', function(category){
	return ['menu','česká kuchyně','mezinárodní kuchyně', 'regionální kuchyně', 'grilované pokrmy', 'vegetariánské pokrmy'];
});

appServices.value('Days', function(){
	return ['Pondělí', 'Úterý', 'Středa', 'Čtvrtek',  'Pátek', 'Sobota', 'Neděle'];
});

})();
