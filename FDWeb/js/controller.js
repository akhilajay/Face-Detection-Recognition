 var i = 0

 function initFDWebAPP() {
     $("#langlabel").hide();
     $("#langselc").hide();
     $("#service").hide();

     _checkFace();
 }

 function _checkFace() {
     var url = "http://localhost:2000/identify";
     $.ajax({
             url: url,
             beforeSend: function(xhrObj) {},
             type: "GET",
             dataType: 'json',
             contentType: "application/json",
             processData: false
         })
         .done(function(response) {
             console.log(" Sucessfull");
             console.log(response);
             console.log(response.name);

             if (response.name != null && response.name != 'Anonymous') {
                 jQuery("label[for='welcomeTxt']").html("Welcome " + response.name);
                 $("#langlabel").show();
                 $("#langselc").show();
                 $("#service").show();
             } else if (response.name == 'Anonymous') {
                 $("#langlabel").show();
                 $("#langselc").show();
                 $("#service").show();
                 jQuery("label[for='welcomeTxt']").html("Welcome Customer");
             } else {
                 $("#langlabel").hide();
                 $("#langselc").hide();
                 $("#service").hide();
             }


         })
         .fail(function(error) {
             console.log("failed ");
             console.log(" error from server :: " + error);
             $("#langlabel").hide();
             $("#langselc").hide();
             $("#service").hide();
             jQuery("label[for='welcomeTxt']").html("");
         });

     console.log(i++);
 }

 function test() {
     console.log("------ ");
     console.log(i++);
 }