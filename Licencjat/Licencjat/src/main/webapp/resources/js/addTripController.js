$(document).ready(function(){
    
    /*************************Obsluga własnego wizarda****************/
    $("#wizard1").css("background", "url('./resources/images/blackBg/black40.png')");
    $("#content1").show();
    // $("#bottomNavigate\\:prev").hide(); 
    // $("#bottomNavigate\\:next").show();
    $("#newTripForm\\:prev").disableSelection(); //  blokuje zaznaczanie tekstu bo sie zaly czas zaznacza przy klikaniu
    $("#newTripForm\\:next").disableSelection(); 
    
    $("#content2").hide();
    $("#content3").hide();
    var activeContent = 1;
    
    $("#newTripForm\\:prev").mouseenter(function(){      //efekt przycisku //zmiana koloru po najechaniu 
        $(this).css("color", "#ff9900");
        $(this).css("text-decoration", "none");
    });
    $("#newTripForm\\:prev").mouseleave(function(){
        $(this).css("color", "#cccccc");
        $(this).css("text-decoration", "none");
    });    
    
    $("#newTripForm\\:next").mouseenter(function(){
        $(this).css("color", "#ff9900");
        $(this).css("text-decoration", "none");
    });
    $("#newTripForm\\:next").mouseleave(function(){
        $(this).css("color", "#cccccc");
        $(this).css("text-decoration", "none");
    }); 
  
    
    
        
    $("#newTripForm\\:prev").click(function(){
       
        if(activeContent===2){
            
            $("#wizard1").css("background", "url('./resources/images/blackBg/black40.png')");
            $("#wizard2").css("background", "none");
            $("#wizard3").css("background", "none");
            
            $("#content2").hide();
            $("#content1").show();
       //     $(this).hide(); 
            activeContent--;
        }
        else if(activeContent===3){
            $("#wizard2").css("background", "url('./resources/images/blackBg/black40.png')");
            $("#wizard1").css("background", "none");
            $("#wizard3").css("background", "none");            
            
            $("#content3").hide();
            $("#content2").show();
          //  $("#bottomNavigate\\:next").show();
            activeContent--;
        }
          
    });
    
    $("#newTripForm\\:next").click(function(){
       
        if(activeContent===1){
            
            $("#wizard2").css("background", "url('./resources/images/blackBg/black40.png')");
            $("#wizard1").css("background", "none");
            $("#wizard3").css("background", "none");   
            
          //  $("#bottomNavigate\\:prev").show();
            $("#content2").show();
            $("#content1").hide();
            activeContent++;
            
        }
        else if(activeContent===2){
            
            $("#wizard2").css("background", "none");
            $("#wizard1").css("background", "none");
            $("#wizard3").css("background", "url('./resources/images/blackBg/black40.png')");                 
            
            $("#content3").show();
            $("#content2").hide();
          //  $(this).hide(); 
            activeContent++;
        }
        
        
    });
    
    
    /*************************Metoda blokujaca zaznaczanie tekstu****************/
    jQuery.fn.extend({                           //dodawanie wlasnych metod do o     
        
        disableSelection : function() {          //blokowanie zaznaczania tekstu
                return this.each(function() { 
                        this.onselectstart = function() { return false; }; 
                        this.unselectable = "on"; 
                        jQuery(this).css('user-select', 'none');    //dla kazdej przegladarki
                        jQuery(this).css('-o-user-select', 'none'); 
                        jQuery(this).css('-moz-user-select', 'none'); 
                        jQuery(this).css('-khtml-user-select', 'none'); 
                        jQuery(this).css('-webkit-user-select', 'none'); 
                }); 
        } 
    }); 
    
});

function showLoader(){
    $("#loadingZapisWycieczki").show();
    
}