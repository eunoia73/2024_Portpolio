function movePage( url, params){
	if(console){
		console.log(url);
		console.log(params);
	}
	
	if(params == undefined) params = {};
		
	
	$.ajax({
		url : ctx + url,
		data : params,
		success : function(data, status, XMLHttpRequest){
			$(document).scrollTop(0)
			//console.log(data);
			$('section.features.section--padding > div.container').html(data);
			
		},
		error : function(XMLHttpRequest, status, errorThrows){
			console.log(XMLHttpRequest.responseText);
			$('section.features.section--padding > div.container').html(XMLHttpRequest.responseText);
		}
	});
}