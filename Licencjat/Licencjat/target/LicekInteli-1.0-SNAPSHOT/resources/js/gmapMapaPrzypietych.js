            
//obiekt mapy
var GmapMapaPrzypietych = null;     
var Infowindow = null;
var JSONData;
var loginToView=null;
var markery = [];
var markerCluster = null;

$(document).ready(function(){

    GmapMapaPrzypietych = PF('mapMapaPrzypietych').getMap();
    Infowindow = new google.maps.InfoWindow({maxWidth: 300});
    
    JSONData = $("#MapaPrzypietychForm\\:jsonDataMapaPrzypietych").val();
    JSONData = JSON.parse(JSONData);
    
    while(JSONData.length){
        var obj = JSONData.pop();
        markery.push(createMarker(obj.lat, obj.lng, obj.opis, obj.login, obj.userId));
    }

    MarkerKluster();

    var styleGMP = [{ featureType: 'water', stylers: [{color: '#036fab'}]}];
    GmapMapaPrzypietych.setOptions({styles: styleGMP});
});
 
function MarkerKluster() {

   markerCluster = new MarkerClusterer(GmapMapaPrzypietych, markery, {maxZoom: 20, gridSize: 40 });  
}
            
function createMarker(lat, lng, opis, login, userId){
           
    var marker = new google.maps.Marker({
            position: new google.maps.LatLng(lat, lng),
            animation: google.maps.Animation.DROP,
            map: GmapMapaPrzypietych,
            icon: {  //wybieram ikonke markera
                    url: './resources/images/markers/32.png', 
                    size: new google.maps.Size(32, 32),
                    anchor: new google.maps.Point(16, 32)
            },
            customInfo:{
                userId: userId,
                userLogin: login,
                opis: opis   
            }
    }); 
                                           //mouseover
    google.maps.event.addListener(marker, 'click', function() {
                
        loginToView = this.customInfo.userLogin;
                
        var content = '<span style="color: red; font-weight:700;font-size: 14px;" >Login: </span>'+
                      '<span style="color: red; ;font-size: 14px; font-weight:700" >'+this.customInfo.userLogin+'</span><br/>'+
                      '<span style="color: black; font-weight:700;font-size: 14px;" >Opis: </span>'+
                      '<span style="color: black; ;font-size: 14px;" >'+this.customInfo.opis+'</span><br/><br/>'+
                      '<a href="#" style="color: #017b02;font-size: 14px;text-decoration: none; "'+
                      'onclick="doPOSTviewProfile(); ">Zobacz profil</a>';
                                   
   
        Infowindow.setContent(content);
        Infowindow.open(GmapMapaPrzypietych, this);  
    });
                
    google.maps.event.addListener(marker, 'mouseout', function() {
      //Infowindow.close();
    });
      
    return marker;
}

    function doPOSTviewProfile(){
            
            $.ajax({
                url: 'ServletUstawiajacyProfil',
                type: 'POST',
                dataType: 'text',
                data: loginToView,
                contentType: 'text/plain',
                mimeType: 'text/plain',
                                
                success: function(response) {
                                    
                   // alert('SUCCESS '+response.valueOf());
                    window.location.href = "profilView.xhtml";
                },
                                
                error : function(response) {
                   // alert('ERROR'+JSON.stringify(response));    
                },

                headers : {
                                
                    Accept: "application/json; charset=utf-8",
                    "Content-Type": "application/json; charset=utf-8"
                }
            });
    }
         