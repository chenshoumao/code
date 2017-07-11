/*
 * jHeartbeat 0.3.0
 * download:http://www.codefans.net
 * (C)Alex Richards - http://www.ajtrichards.co.uk/
 */
 
 $.jheartbeat = {

    options: {
		url: "heartbeat_default.asp",
		delay: 10000,
		div_id: "test_div"
    },
	
	beatfunction:  function(){
	   console.log("hello");
	},
	
	timeoutobj:  {
		id: -1
	},

    set: function(options, onbeatfunction) {
		if (this.timeoutobj.id > -1) {alert("12");
			clearTimeout(this.timeoutobj);
		}
        if (options) {
            $.extend(this.options, options);
        }
        if (onbeatfunction) {
            this.beatfunction = onbeatfunction;
        }

		// Add the HeartBeatDIV to the page
		$("body").append("<div id=\"" + this.options.div_id + "\" style=\"display: block;\"></div>");
		this.timeoutobj.id = setTimeout("$.jheartbeat.beat();", this.options.delay);
		 
    },

    beat: function() {
    	console.log(this.options.url);
    	var arr = (this.options.url).split(",");
    	 
    	$.each(arr,function(idx){
    		 console.log(arr[idx] +"," + idx);//idx为数组下标
    		 $.ajax({
 				url: arr[idx],
 				dataType: "html",
 				type: "GET",
 				error: function(e)   { 
 					console.log(arr[idx] + ",,,Error Requesting Data");
 					//$('#'+ $.jheartbeat.options.div_id).append("Error Requesting Data"); 
 				},
 				success: function(data){ 
 					console.log(arr[idx] + ",,," + data);
 					//$('#'+ $.jheartbeat.options.div_id).html(data); 
 				}
 			 });
    	});
    	alert("end");
		/*$.ajax({
				url: this.options.url,
				dataType: "html",
				type: "GET",
				error: function(e)   { 
					$('#'+ $.jheartbeat.options.div_id).append("Error Requesting Data"); 
				},
				success: function(data){ 
					$('#'+ $.jheartbeat.options.div_id).html(data); 
				}
			   });*/
	//	this.timeoutobj.id = setTimeout("$.jheartbeat.beat();", this.options.delay);
      //  this.beatfunction();
    }
};