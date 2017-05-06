//obiekt mapy
var GmapPrzypnijSIe = null;     
var Infowindow = null;
var marker = null;
var usrId;
var usrLog;
var wspolrzedne;

$(document).ready(function(){

    GmapPrzypnijSIe = PF('mapPrzypnijSie').getMap();
    Infowindow = new google.maps.InfoWindow({maxWidth: 300});
    usrId = $("#userIdPrzypnijSie").val();
    usrLog = $("#userLoginPrzypnijSie").val();
    
   
   GmapPrzypnijSIe.setOptions({styles: getMapStyle()});
   
});
 


function GmapClickPrzypnijSie(event) {

    createMarker(event);
    wspolrzedne = {lat: event.latLng.lat(), lng: event.latLng.lng()};
    $("#przypnijSieDialogOpis").val("");
    PF('przypnijSieDialog').show(); 
}//function
 
function createMarker(event){
           
    marker = new google.maps.Marker({
            position: new google.maps.LatLng(event.latLng.lat(), event.latLng.lng()),
            animation: google.maps.Animation.DROP,
            map: GmapPrzypnijSIe,
            icon: {  //wybieram ikonke markera
                    url: './resources/images/markers/32.png', 
                    size: new google.maps.Size(32, 32),
                    anchor: new google.maps.Point(16, 32)
            },
            customInfo:{
                userId: null,
                userLogin: null,
                opis: null   
            }
    }); 
}

 
function przypnijSieAcceptButton(){
    
 
    $("#przypnijSieLat").val(wspolrzedne.lat);
    $("#przypnijSieLng").val(wspolrzedne.lng);

    marker.customInfo.opis = $("#przypnijSieDialogOpis").val();
 
    marker.customInfo.userId = usrId;
    marker.customInfo.userLogin = usrLog;
                
    google.maps.event.addListener(marker, 'mouseover', function() {
        
        var content = '<span style="color: red; font-weight:700;" >User: </span>'+
                      '<span style="color: red; font-weight:700;" >'+this.customInfo.userLogin +'</span><br/>'+
                      '<span style="color: black; font-weight:700;" >Opis: </span>'+
                      '<span style="color: black; font-weight:700;" >'+this.customInfo.opis +'</span>';
                                 
        Infowindow.setContent(content);
        Infowindow.open(GmapPrzypnijSIe, this);  
    });
                
    google.maps.event.addListener(marker, 'mouseout', function() {
            
        Infowindow.close();
    });
                  
    PF('przypnijSieDialog').hide();
    wspolrzedne = null;
}

function przypnijSieCancelButton(){  
                
    $("#przypnijSieDialogOpis").val("");         //czyszcze pole formualrza
    marker.setMap(null);                  //nulluje marker
    PF('przypnijSieDialog').hide();    //zamukam okno
    wspolrzedne = null;
}

function gekodowaniePrzypnijSIe(){
   
    var adr = $("#PrzypnijSieForm\\:wyszukajAdresIdPrzynijSie").val();
    
    var Geocoder2 =  new google.maps.Geocoder();
                             
    Geocoder2.geocode( { 'address': adr}, function(results, status) {
        if (status == google.maps.GeocoderStatus.OK){
             
            var Marker = new google.maps.Marker({
                    position: results[0].geometry.location,
                    map: GmapPrzypnijSIe
            });
            
                           
            google.maps.event.addListener(Marker, 'mouseover', function() {
                
                 this.setMap(null); 
            });
            
            GmapPrzypnijSIe.setCenter(results[0].geometry.location); //centruje mape
             
        } 
        else {
            if(status === "OVER_QUERY_LIMIT"){  //OVER_QUERY_LIMIT czyli Googlowski limit na zapytania do bazy
                window.alert('LIMIT USLUGI GEOKODINGU' + status);
            }
            else{
                window.alert('WYSTAPIŁ BŁAD: ' + status);
            }
        }
    });              
}


function getMapStyle(){
    
    
    var mapStyle =[
      
      {  
        featureType: 'water',
        stylers: [{color: '#036fab'}]
      }
    ];
       
       
    /*var mapStyle = [
    {
        "featureType": "all",
        "elementType": "labels.text.fill",
        "stylers": [
            {"saturation": 36},
            {"color": "#000000"},
            {"lightness": 50}
        ]
    },
    {
        "featureType": "all",
        "elementType": "labels.text.stroke",
        "stylers": [
            {"visibility": "on"},
            {"color": "#000000"},
            {"lightness": 16}
        ]
    },
    {
        "featureType": "all",
        "elementType": "labels.icon",
        "stylers": [
            {"visibility": "simplified"}
         
        ]
    },
    {
        "featureType": "administrative",
        "elementType": "geometry.fill",
        "stylers": [
            {"color": "#000000"},
            {"lightness": 20}
        ]
    },
    {
        "featureType": "administrative",
        "elementType": "geometry.stroke",
        "stylers": [
            {"color": "#000000"},
            {"lightness": 17},
            {"weight": 1.2}
        ]
    },
    {
        "featureType": "landscape",
        "elementType": "geometry",
        "stylers": [
            {"color": "#000000"},
            {"lightness": 20}
        ]
    },
    {
        "featureType": "poi",
        "elementType": "geometry",
        "stylers": [
            {"color": "#000000"},
            {"lightness": 21}
        ]
    },
    {
        "featureType": "road.highway",
        "elementType": "geometry.fill",
        "stylers": [
            {"color": "#000000"},
            {"lightness": 17}
        ]
    },
    {
        "featureType": "road.highway",
        "elementType": "geometry.stroke",
        "stylers": [
            {"color": "#000000"},
            {"lightness": 29},
            {"weight": 0.2}
        ]
    },
    {
        "featureType": "road.arterial",
        "elementType": "geometry",
        "stylers": [
            {"color": "#000000"},
            {"lightness": 18}
        ]
    },
    {
        "featureType": "road.local",
        "elementType": "geometry",
        "stylers": [
            {"color": "#000000"},
            {"lightness": 16}
        ]
    },
    {
        "featureType": "transit",
        "elementType": "geometry",
        "stylers": [
            {"color": "#000000"},
            {"lightness": 19}
        ]
    },
    {
        "featureType": "water",
        "elementType": "geometry",
        "stylers": [
            {"color": "#000000"},
            {"lightness": 17}
        ]
    }
   ];
   */
   return mapStyle;
    
}