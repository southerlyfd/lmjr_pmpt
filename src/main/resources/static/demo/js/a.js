$(document).ready(function(){
	// alert("332");
	/*$.ajax({
        url: SettinClass.urlERPWeb + "/login.do",
        type: "get",
        dataType: SettinClass.cross_domain ? "jsonp" : "json",
        jsonp: SettinClass.cross_domain ? "callback" : "",
        success: function (json) {
        	alert("hh");
        	if(json != ""){
          	  if(json.status == '0'){
          		  window.location = "../module/summary/index.html";
          	  }
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            //alert(XMLHttpRequest.responseText);
        }
	 });*/
	
	  $("form").submit(function(e){
		  alert(SettinClass.urlERPWeb + "/login.do");
		  var name2 = $("#name").val();
			  $.ajax({
			        type: "POST",  
			              url: SettinClass.urlERPWeb + "/login.do",
			              // 下面"json"与此大小写相同
			              dataType: "json",
			              data:  {"name":name2},
			              success: function(json){
			            	  alert(json.hello + ":" + SettinClass.urlERPWeb + "/login.do");
			            	  },
			              error: function (XMLHttpRequest) {
			                  alert(XMLHttpRequest.status);
			                  
			              }
			             });
          
	    return false;
	  });
	  /* $("button").click(function(){
	    $("form").submit();
	  });  */ 
	});