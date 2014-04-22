function url(controller, action, parameterString){
    var urlString = '/kumulus/' + controller + '/' + action + '/' + parameterString;
    return(urlString);
}

function request(url, data, successHandler) {
    $.ajax({
        url: url,
        type: 'POST',
        data: JSON.stringify(data),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        async: false,
        success: function(response) {
            successHandler(response);
        }
    });
}