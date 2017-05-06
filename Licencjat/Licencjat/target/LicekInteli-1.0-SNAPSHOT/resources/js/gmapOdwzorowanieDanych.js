/* global google, PF */

var GmapOdwzorowanie = null;
var JsonDaneDoOdwzorowania = null;

var directionsService = null;

$(document).ready(function(){

    directionsService = new google.maps.DirectionsService();

    GmapOdwzorowanie = PF('mapOdwzorowanie').getMap();
    
    var styleGMO = [{ featureType: 'water', stylers: [{color: '#036fab'}]}];
    GmapOdwzorowanie.setOptions({styles: styleGMO});
    
    JsonDaneDoOdwzorowania = JSON.parse($("#OdwzorowanieForm\\:daneDoOdwzorowania").val());      
        
    if(JsonDaneDoOdwzorowania.Markery.length !== 0){
        //tu dodam markery do mapy
        dodajMarkery(JsonDaneDoOdwzorowania.Markery);
    }
    if(JsonDaneDoOdwzorowania.MarkeryDlaTras.length !== 0){
        //tu dodam markery do mapy
        dodajMarkeryDlaTras(JsonDaneDoOdwzorowania.MarkeryDlaTras);
    }
    if(JsonDaneDoOdwzorowania.sciezkiManualne.length !== 0){
        //tu dodam sciezki manualne do mapy
        dodajTrasyManualne(JsonDaneDoOdwzorowania.sciezkiManualne);
    }
    if(JsonDaneDoOdwzorowania.sciezkiAutomatyczne.length !== 0){
        //tu dodam sciezki automatyczne do mapy
        dodajTrasyAutomatyczne(JsonDaneDoOdwzorowania.sciezkiAutomatyczne);
    }
    if(JsonDaneDoOdwzorowania.zaznaczoneObszary.length !== 0){
        //tu dodam zaznaczone obszary
        dodajObszary(JsonDaneDoOdwzorowania.zaznaczoneObszary);
    }
});

function dodajMarkery(markery){
    
    var marker;
    var Infowindow = new google.maps.InfoWindow();
    
    for(var i in markery){
        
        marker = new google.maps.Marker({
                position: new google.maps.LatLng(markery[i].lat, markery[i].lng),
                icon: wyborIconyMarkera(markery[i].typ),
                customInfo:{
                        typ:markery[i].typ,
                        opis:markery[i].opis,
                        adres:markery[i].adres,
                        dystans:markery[i].dystans
                },
                map: GmapOdwzorowanie
        });
        
        google.maps.event.addListener(marker, 'mouseover', function() {
                
            var content = '<span style="color: black; font-weight:700;" >Typ punktu: </span>'+
                          '<span style="color: green; font-weight:600; " >'+this.customInfo.typ+'</span><br/>'+
                          '<span style="color: black; font-weight:700;" >Adres: </span>'+
                          '<span style="color: blue; font-weight:600; " >'+this.customInfo.adres+'</span><br/>'+
                          '<span style="color: black; font-weight:700;" >Opis: </span>'+
                          '<span style="color: black; " >'+this.customInfo.opis+'</span><br/>'+         
                          '<span style="color: black; font-weight:700;" >Dystans: </span>'+
                          '<span style="color: black; " >'+this.customInfo.dystans+' km</span><br/>';
           
            Infowindow.setContent(content);
            Infowindow.open(GmapOdwzorowanie, this);  
        });
                
        google.maps.event.addListener(marker, 'mouseout', function() {
            Infowindow.close();
        });
       
        //centruje mape na punkcie startowym (o ile taki jest w trasie)
        if(markery[i].typ === "START"){ 
            GmapOdwzorowanie.setCenter(new google.maps.LatLng(markery[i].lat, markery[i].lng));
        }
    }  
}

function dodajMarkeryDlaTras(markery){
   
    var marker;
    var Infowindow = new google.maps.InfoWindow();
    
   
    for(var i in markery){
        
        marker = new google.maps.Marker({
                position: new google.maps.LatLng(markery[i].lat, markery[i].lng),
                icon: wyborIconyMarkera("ControlPoint"),
                customInfo:{
                        dystans:markery[i].dystans
                },
                map: GmapOdwzorowanie
        });
    
        google.maps.event.addListener(marker, 'mouseover', function() {
                
            var content = '<span style="color: red; font-weight:700;" >Dystans: </span>'+
                          '<span style="color: black; font-weight:700;" >'+this.customInfo.dystans+' km</span>';
                  
            Infowindow.setContent(content);
            Infowindow.open(GmapOdwzorowanie, this);  
        });
                
        google.maps.event.addListener(marker, 'mouseout', function() {
            Infowindow.close();
        });
        
    }//for
}//function

function dodajTrasyManualne(sciezki){
    
    for(var i in sciezki){
      
        var decodedPath = google.maps.geometry.encoding.decodePath(sciezki[i].path);
 
        var sciezka = new google.maps.Polyline({
                // geodesic: true,
                path: decodedPath,
                strokeColor: '#3399FF',
                strokeOpacity: 0.7,
                strokeWeight: 4,
                icons: [{icon: {                 //przerywana  linia
                            path: 'M 0,-1 0,1',
                            strokeOpacity: 0.2,
                            strokeColor: 'black',
                            scale: 4
                        }, 
                        offset: '0', 
                        repeat: '20px'
                    }],  
                map: GmapOdwzorowanie         
        }); 
    }
}


function dodajTrasyAutomatyczne(sciezki){

    for(var i in sciezki){
        
        var decodedPath = google.maps.geometry.encoding.decodePath(sciezki[i].path);
        var b, a = decodedPath.shift();
        
        while(decodedPath.length){
           b = decodedPath.shift();
           TrasaAutoAB(a, b); 
           a = b;
        }   
    }
}

function TrasaAutoAB(startPoint, endPoint){  //metoda rysuja trase miedzy 2 punktami
        
    var directionsDisplay = new google.maps.DirectionsRenderer({ 
        map: GmapOdwzorowanie,
        polylineOptions:{               //styl sciezki
            strokeColor: '#3399FF',
            strokeOpacity: 0.7,
            strokeWeight: 5,
            icons: [{ 
                icon: {          //przerywana  linia
                    path: 'M 0,-1 0,1',    //SVG  sclable vector graphic
                    strokeOpacity: 0.1,
                    strokeColor: 'black',
                    scale: 4
                },
                offset: '0', 
                repeat: '20px' 
            }]
        },       
        draggable: false,
        suppressMarkers: true      //jesli true to nie pokazuja sie markery A i B(czyli tak jak chce)
       });

        var request = {
            origin: startPoint,
            destination: endPoint,
            unitSystem: google.maps.UnitSystem.METRIC,
            travelMode: google.maps.TravelMode.DRIVING
        };

        directionsService.route(request, function (response, status) {
        
            if (status === google.maps.DirectionsStatus.OK) {
              
                directionsDisplay.setDirections(response);
            } 
            else { //jesli sie nie udało wyznaczyc trasy to rysuje prosta
                
                var sciezkaTmp = new google.maps.Polyline({//jesli nie moge wyznaczyc drogi to rysuje polyline :)
                    path: [startPoint, endPoint],
                    map: GmapOdwzorowanie,
                    strokeColor: '#3399FF',
                    strokeOpacity: 0.7,
                    strokeWeight: 5,
                    icons: [{ 
                            icon: {          //przerywana  linia
                                path: 'M 0,-1 0,1',
                                strokeOpacity: 0.1,
                                strokeColor: 'black',
                                scale: 4
                            },
                            offset: '0', 
                            repeat: '20px' 
                    }]
                }); 
            }
        });             
}

function dodajObszary(obszary){
    
    for(var i in obszary){
        
        switch (obszary[i].typ) {
            case "OKRAG":
               
                rysujOkrag(obszary[i].lat, obszary[i].lng, obszary[i].size);
                break;
            case "WIELOKAT":
          
                rysujWielokat(obszary[i].path);
                break;
            case "PROSTOKAT":
            
                rysujProstokat(obszary[i].bounds);
                break;
        }//switch
    }//for
}

function rysujOkrag(paramLat, paramLng, paramSize){
       
    var circleTmp =  new google.maps.Circle({
        strokeColor: '#0099FF',
        strokeOpacity: 0.8,
        strokeWeight: 1.5,
        fillColor: '#00CC33',
        fillOpacity: 0.5,
        map: GmapOdwzorowanie,
        center: new google.maps.LatLng(paramLat, paramLng),
        radius: paramSize     
    });
    
    google.maps.event.addListener(circleTmp, 'mouseover', function() {
                   
        this.setOptions({
            strokeWeight: 2.5,
            strokeColor: "#FF6633",
            strokeOpacity: 0.8
        }); 
    });
                
    google.maps.event.addListener(circleTmp, 'mouseout', function() {
                   
        this.setOptions({
            strokeWeight: 2.5,
            strokeColor: "#0099FF",
            strokeOpacity: 0.8
        }); 
    });
    
}
function rysujProstokat(paramBounds){
    
    var prostTmp = new google.maps.Rectangle({
        bounds: paramBounds,
        strokeColor: '#0099FF',
        strokeOpacity: 0.8,
        strokeWeight: 1.5,
        fillColor: '#00CC33',
        fillOpacity: 0.5,
        map: GmapOdwzorowanie 
    });
        
    google.maps.event.addListener(prostTmp, 'mouseover', function() {
                   
        this.setOptions({
            strokeWeight: 2.5,
            strokeColor: "#FF6633",
            strokeOpacity: 0.8
        }); 
    });
                
    google.maps.event.addListener(prostTmp, 'mouseout', function() {
                   
        this.setOptions({
            strokeWeight: 2.5,
            strokeColor: "#0099FF",
            strokeOpacity: 0.8
        }); 
    });
}

function rysujWielokat(paramPath){
    
    var wieloTmp = new google.maps.Polygon({
        paths: google.maps.geometry.encoding.decodePath(paramPath),
        strokeColor: '#0099FF',
        strokeOpacity: 0.8,
        strokeWeight: 1.5,
        fillColor: '#00CC33',
        fillOpacity: 0.5,
        map: GmapOdwzorowanie
    });
    
    google.maps.event.addListener(wieloTmp, 'mouseover', function() {
                   
        this.setOptions({
            strokeWeight: 2.5,
            strokeColor: "#FF6633",
            strokeOpacity: 0.8
        }); 
    });
                
    google.maps.event.addListener(wieloTmp, 'mouseout', function() {
                   
        this.setOptions({
            strokeWeight: 2.5,
            strokeColor: "#0099FF",
            strokeOpacity: 0.8
        }); 
    });    
}

function wyborIconyMarkera(typ){
            
    var image = null;

    switch (typ) {
        case "ControlPoint":
            
            image = {      
                path: google.maps.SymbolPath.CIRCLE,
                scale: 3,
                fillColor: '#FFFFFF',
                fillOpacity: 0.9,
                strokeColor: '#9600E6',
                strokeOpacity: 0.9
            };
  
            break;
        case "START":
            
           /* image = {      
                path: google.maps.SymbolPath.CIRCLE,
                scale: 7,
                strokeColor: '#000000',
                strokeOpacity: 0.9,
                strokeWeight: 1,
                fillColor: '#11A800',
                fillOpacity:1
            };*/

            image = {  //wybieram ikonke markera
                url: './resources/images/markers/greenMarker.png', 
                size: new google.maps.Size(30, 44),
                anchor: new google.maps.Point(14, 44)
            };

            break;
        case "KONIEC":
            
            /*image = {      
                path: google.maps.SymbolPath.CIRCLE,
                scale: 7,
                strokeColor: '#000000',
                strokeOpacity: 0.9,
                strokeWeight: 1,
                fillColor: '#FC0000',
                fillOpacity: 1
            };  */
                    
            image = {  //wybieram ikonke markera
                url: './resources/images/markers/redMarker.png', 
                size: new google.maps.Size(30, 44),
                anchor: new google.maps.Point(14, 44)
            };
        
            break;
        case "PUNKT_SZCZEGOLNY":
            
            image = {      
                path: google.maps.SymbolPath.CIRCLE,
                scale: 6,
                strokeColor: '#FA6A03',
                strokeOpacity: 0.9,
                strokeWeight: 3,
                fillColor: '#FFFFFF',
                fillOpacity: 0.5
            };   
            
            break; 
        case "INNY_PUNKT":
            
            image = {      
                path: google.maps.SymbolPath.CIRCLE,
                scale: 6,
                strokeColor: '#0073FF',
                strokeOpacity: 0.9,
                strokeWeight: 3,
                fillColor: '#FFFFFF',
                fillOpacity: 0.5
            };  
            
            break;                                         
        default:
            image = {      
                path: google.maps.SymbolPath.CIRCLE,
                scale: 6,
                strokeColor: '#0073FF',
                strokeOpacity: 0.9,
                strokeWeight: 3,
                fillColor: '#FFFFFF',
                fillOpacity: 0.5
            };  
    }
            
    return image; 
}
