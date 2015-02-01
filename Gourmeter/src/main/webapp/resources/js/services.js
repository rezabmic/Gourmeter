(function() {
var appServices = angular.module("appServices",['ngResource']);

appServices.factory('Categories', ['$resource',  function($resource){
      return $resource('service/category/all', {}, {
                 query: {method:'GET', isArray:true}
      });
}]);


appServices.factory('Tags',  ['$resource',  function($resource){
	return $resource('service/tags/byCategory/:categoryId', {}, {
        query: {method:'GET', params: {categoryId: '@categoryId'}, isArray:true}
	});
}]);

appServices.value('MenuTag', function(){
	return 'menu';
});

appServices.value('Days', function(){
	return ['Pondělí', 'Úterý', 'Středa', 'Čtvrtek',  'Pátek', 'Sobota', 'Neděle'];
});

appServices.factory('CateringFacility',  ['$resource',  function($resource){
	return $resource('service/cateringFacility/:cfId', {cfId:'@id'}, {
        save: {method:'POST', params: {userId: 1}}
	});
}]);

})();
