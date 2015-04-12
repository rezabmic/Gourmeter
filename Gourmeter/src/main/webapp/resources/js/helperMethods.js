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