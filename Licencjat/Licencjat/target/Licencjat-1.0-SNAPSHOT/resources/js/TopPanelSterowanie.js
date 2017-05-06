$(document).ready(function(){
               
    InitPanelLinks();
});
        
           
function InitPanelLinks(){ //zapalanie przyciskow z opcjami mapy
    
    var aktywnaStrona = $("#navigationForm\\:aktywnaStrona").val(); 
    
    $("#navigationForm\\:navigationLink"+aktywnaStrona).css({"background": "url('./resources/images/blackBg/black40.png')"});  //domyślnie zaswiecony button 1
 
    for(var i=0; i<=7 ; i++){  //dla kazdego buttona ustawiam eventy
                    
        $("#navigationForm\\:navigationLink"+i).mouseover(function(){   
            
            if(parseInt($(this).attr("id").charAt(29)) !== parseInt($("#navigationForm\\:aktywnaStrona").val())){
                $(this).css({"background": "url('./resources/images/blackBg/black20.png')"});
            }
        });

        $("#navigationForm\\:navigationLink"+i).mouseout(function(){                                     
            if(parseInt($(this).attr("id").charAt(29)) !== parseInt($("#navigationForm\\:aktywnaStrona").val())){
                $(this).css({"background": "none"});
            }
        });
        
    }   
}          

function startLogin(){
                
    $("#LoginForm\\:loginResultInfo").html("");

    if($("#LoginForm\\:login").val() !== "" && $("#LoginForm\\:password").val() !== "" ){
                    
        $("#LoginForm\\:loadingLogin").show(); 
    }     
}
           
           
function failLogin(){
       
    $("#LoginForm\\:loadingLogin").hide(); 
}
