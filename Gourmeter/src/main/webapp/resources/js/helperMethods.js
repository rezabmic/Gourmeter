function toggleSelection(object, array) {
    var idx = array.indexOf(object);

    // is currently selected
    if (idx > -1) {
    	array.splice(idx, 1);
    }

    // is newly selected
    else {
    	array.push(object);
    }
};

//helper method used to transform array to string which could be set as url param
function $buildString(array){
	var string = '';
	array.forEach(function(item){
		string = string + item + ",";
	});
	return string.substring(0, string.length - 1);
}