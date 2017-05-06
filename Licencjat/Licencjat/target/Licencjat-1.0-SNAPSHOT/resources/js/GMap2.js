/*
window.setTimeout(function() {
    map.panTo(marker.getPosition());
  },3000);
            
    
 panControl:true,
zoomControl:true,
mapTypeControl:true,
scaleControl:true,
streetViewControl:true,
overviewMapControl:true,
rotateControl:true           
            
    
 google.maps.ZoomControlStyle.SMALL - displays a mini-zoom control (only + and - buttons)
google.maps.ZoomControlStyle.LARGE - displays the standard zoom slider control
google.maps.ZoomControlStyle.DEFAULT - picks the best zoom control based on device and map size   
    
    
    zoomControl:true,
zoomControlOptions: {
    style:google.maps.ZoomControlStyle.SMALL
}        
            
            
            
google.maps.MapTypeControlStyle.HORIZONTAL_BAR - display one button for each map type
google.maps.MapTypeControlStyle.DROPDOWN_MENU - select map type via a dropdown menu
google.maps.MapTypeControlStyle.DEFAULT - displays the "default" behavior (depends on screen size)
Example
mapTypeControl:true,
mapTypeControlOptions: {
    style:google.maps.MapTypeControlStyle.DROPDOWN_MENU
}         
        
        
controlDiv.style.padding = '5px';
var controlUI = document.createElement('div');
controlUI.style.backgroundColor = 'yellow';
controlUI.style.border='1px solid';
controlUI.style.cursor = 'pointer';
controlUI.style.textAlign = 'center';
controlUI.title = 'Set map to London';
controlDiv.appendChild(controlUI);
var controlText = document.createElement('div');
controlText.style.fontFamily='Arial,sans-serif';
controlText.style.fontSize='12px';
controlText.style.paddingLeft = '4px';
controlText.style.paddingRight = '4px';
controlText.innerHTML = '<b>Home<b>'
controlUI.appendChild(controlText);       
        
    


ROADMAP (normal, default 2D map)
SATELLITE (photographic map)
HYBRID (photographic map + roads and city names)
TERRAIN (map with mountains, rivers, etc.)
            

     
     Google Maps - Disable 45° Perspective View - setTilt(0)
You can disable 45° perspective view by calling setTilt(0) on the Map object:

Example
map.setTilt(0);
*/

            //Kontenery
            var MarkerList = [];        //kontener na Markery
            var PolylineList = [];      //kontener na trasy
            var PolylineListAuto = [];      //kontener na trasy automatyczne
            var PolylineMetry = [];
            var CircleList = [];        //kontener na okregi  
            var Rectangles = [];
            var Polygons = [];
            //kontenery do zapisu
            var MarkerListData = [];
            var SciezkiZakodowaneManualne = [];
            var SciezkiZakodowaneAutomatyczne = [];
            var zaznaczoneObszary = [];
            
            //
            //Obiekty robocze
            var encodePath;             //Zakodowana sciezka
            var Gmap = null;            //reprezentacja mapy Gmap
            var Marker = null;          //reprezentacja Markera
            var path = null;            //pusta lista obiektow w sciezce
            var sciezka = null;         //obiekt sciezki
            var okrag = null;           //obiekt Circle Overlay
            var image = null;           //na ikony markerow
            var markerClusterer = null;
            var Geocoder = null;
            var directionsService = null;
            var directionsDisplay = null;
            var Infowindow = null; 
            var bounds = null;
            
            //inne zmienne
            var pelny_adres = null;
            var opis = null;
            var title = null;
            var MarkerType = null;      //typ markera
            var point1 = null;  //punkt startowy i docelowy do sciekzki automatycznej
            var point2 = null;
            
            //zmienne prostokątu
            var Rectangle = null;
            var LeftTopBound = null;
            var RightBotBound = null;
            
            //zmienne wielokata
            var Polygon = null;
            var PolygonStart = null;
            var PolygonEnd = null;
            var polygonPath = [];
            
            
            
            var lineSymbol = {          //przerywana  linia
                    path: 'M 0,-1 0,1',
                    strokeOpacity: 0.2,
                    strokeColor: 'black',
                    scale: 4
            };   
            
            var CircleSymbol = {
                path: google.maps.SymbolPath.CIRCLE,
                scale: 8,
                strokeColor: '#393'
            };
            
            var symbolCircle = { 
                        path: google.maps.SymbolPath.CIRCLE,
                        scale: 40,
                        //anchor: (0, 0),    //(0, 0) jest domyślnie
                        strokeColor: 'white',
                        strokeWeight: 1,
                        strokeOpacity: 1,
                        fillColor: '#3399FF',
                        fillOpacity: 0.5                     
           };
            
            $(document).ready(function(){

                Gmap = PF('map').getMap();
                path = new google.maps.Polyline().getPath();
                Geocoder =  new google.maps.Geocoder();
                directionsService = new google.maps.DirectionsService();
                Infowindow = new google.maps.InfoWindow();



                WyswietlanyTryb(); //ustawiam informacje z aktywnym trybem
                InitContextMenu(); //inicjuje menu kontekstowe mapy
                InitMapOptionsButtons();

            });
           
            function InitMapOptionsButtons(){
                
                //zapalanie przyciskow z opcjami mapy
                $("#mapOption1").css("background", "#006633");  //domyślnie
                
                for(var i=0; i<=6 ; i++){  //dla kazdego komponentu ustawiam zdarzenie klikniecia
                    
                    $("#mapOption"+i).click(function(){                                     
                   
                        for(var j=0; j<=6; j++){  //czyszcze kolory przyciskow
                            $("#mapOption"+j).css({"background": "none", "color": "#cccccc"});
                        }
                      
                   
                        $(this).css({"background": "#006633", "color": "#FF3300"});
   
                    });
                }   
            }
           
            function InitContextMenu(){  //rejestruje context menu dla mapy

                google.maps.event.addListener(Gmap, 'rightclick', function(mouseEvent) {   
                   // document.getElementById('cmenu').show();  
                    
                    $("#wyznaczanieTrasyForm1\\:cmenu").show();
                });
            }
           
            function GmapClickEventHandler(event) {

                var overlayType = $("#overlayType").val();

                if(overlayType.substring(0, overlayType.indexOf("_"))  === "PATH"){  //jesli dodawanie trasy
                         
                    addMarker(event);   //dodaje marker
                        
                    image = {           //wybieram ikonke markera
                            url: './resources/images/markers/control_point32.png', 
                            size: new google.maps.Size(8, 8),
                            anchor: new google.maps.Point(4, 4)                
                    };
                        
                    Marker.setIcon(image);    //ustawiam ikone
                    MarkerList.push(Marker);  //dodaje marker do listy markerow
                    Marker = null;            //
                    dodajTrase(event.latLng);       //dodaje sciezke do tego punktu                        
                }
                else{ //jesli punkty i obszary
                   
                    switch ($("#overlayType").val()) {
                        case "PUNKT":
                            
                            geocodeLatLng(event.latLng);//sprawdzam jaki adres
                            addMarker(event);           //dodaje marker
                            SelectMarkerType();         //wybieram ikonke w zaleznsci od zaznaczoneo typu (mozna to zmnienic w trakcie dodawania markera)                
                            PF('DataDialog').show();    //otwieram okienko z opcjami puntku    
                            break;
                        case "OKRAG":
                                                
                            RysujOkrag(event);
                            break;
                        case "PROSTOKAT":

                            addMarker(event);
                            image = {           //wybieram ikonke markera
                                url: './resources/images/markers/control_point32.png', 
                                size: new google.maps.Size(8, 8),
                                anchor: new google.maps.Point(4, 4)               
                            };
                            Marker.setIcon(image);
                            

                            if(RightBotBound !== null){
                                LeftTopBound = null;
                                RightBotBound = null;
                            }

                            if(LeftTopBound === null){         //2 klikniecia w mape zczytuja krawedzie prostokata
                                LeftTopBound = event.latLng;
                            }
                            else{
                                RightBotBound = event.latLng;
                            }
                        
                            RysujProstokat(LeftTopBound, RightBotBound);
                            break;
                        case "WIELOKAT":
                            
                            addMarkerForPolygon(event);
                            
                            if(PolygonStart === null){
                                Marker.setTitle("START_PATH");
                                
                                image = {           //wybieram ikonke markera
                                    url: './resources/images/markers/control_point42.png', 
                                    size: new google.maps.Size(8, 8),
                                    anchor: new google.maps.Point(4, 4)               
                                };
                                Marker.setIcon(image);
                                
                                PolygonStart = event.latLng;
                                polygonPath.push(event.latLng);
                            }
                            else{
                                polygonPath.push(event.latLng);  
                            }

                            break;
                    }    
                }
            }   
             
            function addMarker(event){
                     
                Marker = new google.maps.Marker({
                    position: new google.maps.LatLng(event.latLng.lat(), event.latLng.lng()),
                    animation: google.maps.Animation.DROP,
                    map: Gmap
                });
                     
                /***********************EVENTY MARKERA************************/
                    
                google.maps.event.addListener(Marker, 'click', function() {
                       
                    var overlayType = $("#overlayType").val();

                    if(overlayType.substring(0, overlayType.indexOf("_"))  === "PATH"){
                       dodajTrase(this.getPosition());
                    }
                });
            } 
            
            function addMarkerForPolygon(event){
                     
                Marker = new google.maps.Marker({
                    position: new google.maps.LatLng(event.latLng.lat(), event.latLng.lng()),
                    animation: google.maps.Animation.DROP,
                    icon:{           
                            url: './resources/images/markers/control_point32.png', 
                            size: new google.maps.Size(8, 8),
                            anchor: new google.maps.Point(4, 4)               
                        },
                    map: Gmap
                });
                     
                /***********************EVENTY MARKERA************************/
                    
                google.maps.event.addListener(Marker, 'click', function() {
                       
                    var overlayType = $("#overlayType").val();

                    if(overlayType.substring(0, overlayType.indexOf("_"))  === "PATH"){
                       dodajTrase(this.getPosition());
                    }

                    if(this.getTitle() === "START_PATH"){
                        polygonPath.push(event.latLng);
                        RysujWielokat();
                    } 
                });
            } 
            
            
            
            function dodajTrase(wspolrzedne){
                
                if($("#overlayType").val()  === "PATH_AUTOMATIC"){ 
                    
                    addPolylineAutomatic(wspolrzedne);
                }
                else if($("#overlayType").val()  === "PATH_MANUAL"){
                      
                    addPolyline(wspolrzedne);  //dodaje sciezke do tego Markera manualnie
                }
            }      
            
            function addPolylineAutomatic(wspolrzedne){
               
                path.push(wspolrzedne); //dodaje wspolrzedna do sciezkie
                kodowanieSciezkiAuto(); //koduje sciezke
                
                
                if(point1 === null){        //jesli punkt startowy sciezki jest null to go ustawiam
                    point1 = wspolrzedne;
                }
                else{
                    point2 = wspolrzedne;
                    RysujTrase(point1, point2);
                        
                    point1 = point2;  //kolejna sciezka idzie od kolejnego punktu
                }
            }
             
            function addPolyline(wspolrzedne){
                
                if(sciezka===null){  //jesli sciezka pusta          
                    
                    sciezka = new google.maps.Polyline({
                       // geodesic: true,
                        strokeColor: '#3399FF',
                        strokeOpacity: 0.7,
                        strokeWeight: 4,
                        icons: [{icon: lineSymbol, offset: '0', repeat: '20px'}],
                        //icons: [{icon: CircleSymbol, offset: '100%'}],
                        map: Gmap         
                    });
                    
                    path = sciezka.getPath();  //wyciagam liste z kolejnymi punktmi Polyline
                    path.push(wspolrzedne);   //dodaje do sciezki wspołrzedne punktu w który kliknołem
                    PolylineList.push(sciezka);
                    kodowanieSciezkiManual();
                    sciezka.title = google.maps.geometry.spherical.computeLength(path);  //wyliczam dlugosc sciezki
                }
                else{ 
                    path.push(wspolrzedne);  //dodaje kolejny punkt sciezki
                    kodowanieSciezkiManual();
                    sciezka.title = google.maps.geometry.spherical.computeLength(path);     
                }
                
                //var lengthInMeters = google.maps.geometry.spherical.computeLength(path);
                
                google.maps.event.addListener(sciezka, 'click', function() {
                      //  var content = '<span style="color: black; font-weight:700;" >Długość trasy:</span>'+
                                  //   '<span style="color: green; font-weight:600; " >'+this.title+'</span><br/>';
                    
                      ///  Infowindow.setContent(content);
                       // Infowindow.setPosition();
                      //  Infowindow.open(Gmap); 
                     // alert(this.title);
                });      
            }
            
            function kodowanieSciezkiManual(){
             
                //aktualizuje zakodowana sciezke
                encodePath = google.maps.geometry.encoding.encodePath(path); //koduje aktualna scieke za kazdym razem jak sie doda punkt
                SciezkiZakodowaneManualne[SciezkiZakodowaneManualne.length-1].path = encodePath;  
            }
            function kodowanieSciezkiAuto(){
              
                encodePath = google.maps.geometry.encoding.encodePath(path); //koduje aktualna scieke za kazdym razem jak sie doda punkt
                SciezkiZakodowaneAutomatyczne[SciezkiZakodowaneAutomatyczne.length-1].path = encodePath; 
            }

            function SelectMarkerType(){
            
                var typ = $("#mType").val();  // lub "#dataDialogForm\\:mType"
                var imgUrl = "./resources/images/markers/";
   
                switch (typ) {
                    case "ControlPoint":
                        image = {           //wybieram ikonke markera
                            url: imgUrl+'control_point32.png', 
                            size: new google.maps.Size(8, 8),
                            anchor: new google.maps.Point(4, 4)               
                        };
                        break;
                    case "START":
                        image = {
                            url: imgUrl+'Mgreen3.png', 
                            size: new google.maps.Size(18, 18),
                            anchor: new google.maps.Point(9, 9),
                        };    
                        break;
                    case "KONIEC":
                        image = {
                            url: imgUrl+'Mred3.png', 
                            size: new google.maps.Size(18, 18),
                            anchor: new google.maps.Point(9, 9)                  
                        };                    
                        break;
                    case "PUNKT_SZCZEGOLNY":
                        image = {
                            url: imgUrl+'Myellow3.png', 
                            size: new google.maps.Size(16, 16),
                            anchor: new google.maps.Point(8, 8)                 
                        };        
                        break; 
                    case "INNY_PUNKT":
                        image = {
                            url: imgUrl+'Mblue3.png', 
                            size: new google.maps.Size(16, 16),
                            anchor: new google.maps.Point(8, 8)             
                        };
                        break;                                         
                    default:
                        image = {           //wybieram ikonke markera
                            url: imgUrl+'Mblue3.png', 
                            size: new google.maps.Size(16, 16),
                            anchor: new google.maps.Point(8, 8)                
                        };
                }
            
                MarkerType = typ;
                Marker.setIcon(image); 
            }
 
 
            function AcceptButton(){
                
                opis = $("#opis");//dataDialogForm\\:opis
                Marker.customInfo = MarkerType+":#"+opis.val()+"$@"+pelny_adres;
  
                google.maps.event.addListener(Marker, 'mouseover', function() {
                
                    var typParam = this.customInfo.substring(0, this.customInfo.indexOf(":"));   //wycinam ze custom info typ i opis markera
                    var opisParam = this.customInfo.substring(this.customInfo.indexOf("#")+1, this.customInfo.indexOf("$")) ;
                    var adresParam = this.customInfo.substring(this.customInfo.indexOf("@")+1, this.customInfo.length);

                    var content = '<span style="color: black; font-weight:700;" >Typ punktu: </span>'+
                                  '<span style="color: green; font-weight:600; " >'+typParam+'</span><br/>'+
                                  '<span style="color: black; font-weight:700;" >Adres: </span>'+
                                  '<span style="color: blue; font-weight:600; " >'+adresParam+'</span><br/>'+
                                  '<span style="color: black; font-weight:700;" >Opis: </span>'+
                                  '<span style="color: black; " >'+opisParam+'</span><br/>';
                                  
                                  
                     
                    Infowindow.setContent(content);
                    Infowindow.open(Gmap, this);  
                });
                
                google.maps.event.addListener(Marker, 'mouseout', function() {
                    Infowindow.close();
                });
       
                MarkerList.push(Marker); //zanim zNulluje marker dodam go do listy 

                MarkerListData.push({  
                            lat: Marker.getPosition().lat(), 
                            lng: Marker.getPosition().lng(), 
                            opis: opis.val(), 
                            adres: pelny_adres, 
                            typ: MarkerType
                });
                            
                //alert(JSON.stringify(MarkerListData));    
                
                opis.val(""); //czyszcze wartosci w polach
                Marker = null;
                PF('DataDialog').hide();
            }

            function CancelButton(){  
                
                $("#opis").val("");         //czyszcze pole formualrza
                Marker.setMap(null);        //usuwam marker z mapy 
                Marker = null;              //nulluje marker
                PF('DataDialog').hide();    //zamukam okno
            }

            function RysujOkrag(event){
                
                zaznaczoneObszary.push({  
                            lat: event.latLng.lat(),
                            lng: event.latLng.lng(), 
                            opis: null, 
                            typ: "OKRAG",
                            path: null,
                            size: null
                });
                
                PF('sizeDialogVar').show();  //otwieram otwieram okno z opcjami okregu
                
                okrag = new google.maps.Circle({
                    strokeColor: '#0099FF',
                    strokeOpacity: 0.8,
                    strokeWeight: 1.5,
                    fillColor: '#ffffff',
                    fillOpacity: 0.3,
                    map: Gmap,
                    center: event.latLng,
                    radius: 10          //wielkosc okregu
                    //editable: true     // edycja rozmiaru i pozycji okregu
                });
                CircleList.push(okrag);               
                
                google.maps.event.addListener(okrag, 'click', function() {    //dodawanie sciezki do okregu
                              
                    var overlayType = $("#overlayType").val();

                    if(overlayType.substring(0, overlayType.indexOf("_"))  === "PATH"){
                      
                       dodajTrase(this.getCenter());
                    }
                    else{  //jesli nie dodaje sciezki to pokazuje info window
                       
                        var content = '<span style="color: black; font-weight:700;" >Opis: </span>'+
                                      '<span style="color: green; font-weight:600; " >'+this.title+'</span><br/>';
                    
                        Infowindow.setContent(content);
                        Infowindow.setPosition(this.getCenter());
                        Infowindow.open(Gmap);  
                    }
                });
                
                google.maps.event.addListener(okrag, 'mouseover', function() {
                   
                    this.setOptions({
                        fillColor: '#ffffff',
                        fillOpacity: 0.5,
                        strokeColor: "#FF6633",
                        strokeOpacity: 0.8
                    }); 
                });
                
                google.maps.event.addListener(okrag, 'mouseout', function() {
                   
                    this.setOptions({
                        fillColor: '#ffffff',
                        fillOpacity: 0.3,
                        strokeColor: "#0099FF",
                        strokeOpacity: 0.8
                    }); 
                });  
            }
  
            function RysujProstokat(LeftTop, RightBot){
                
                zaznaczoneObszary.push({  
                            lat: null,
                            lng: null, 
                            opis: null, 
                            typ: "PROSTOKAT",
                            path: null,
                            size: null,
                            boundsLeftTop: LeftTop,
                            boundsRightBot: RightBot
                });
                
                Rectangle = new google.maps.Rectangle({
                    strokeColor: '#0099FF',
                    strokeOpacity: 0.8,
                    strokeWeight: 1.5,
                    fillColor: '#ffffff',
                    fillOpacity: 0.3,
                    map: Gmap,
                    bounds: new google.maps.LatLngBounds(LeftTop, RightBot)
                });
                
                google.maps.event.addListener(Rectangle, 'click', function() {    //dodawanie sciezki do okregu
                              
                    var overlayType = $("#overlayType").val();

                    if(overlayType.substring(0, overlayType.indexOf("_"))  === "PATH"){
                      
                       dodajTrase(this.getBounds().getSouthWest());
                    }
                    else{  //jesli nie dodaje sciezki to pokazuje info window
                       
                        var content = '<span style="color: black; font-weight:700;" >Opis: </span>'+
                                      '<span style="color: green; font-weight:600; " >'+this.title+'</span><br/>';
                    
                        Infowindow.setContent(content);
                        Infowindow.setPosition(this.getBounds().getSouthWest());
                        Infowindow.open(Gmap);  
                    }
                }); 
                
                google.maps.event.addListener(Rectangle, 'mouseover', function() {
                   
                    this.setOptions({
                        fillColor: '#ffffff',
                        fillOpacity: 0.5,  
                        strokeColor: "#FF6633",
                        strokeOpacity: 0.8
                    }); 
                });
                
                google.maps.event.addListener(Rectangle, 'mouseout', function() {
                   
                    this.setOptions({
                        fillColor: '#ffffff',
                        fillOpacity: 0.3,
                        strokeColor: "#0099FF",
                        strokeOpacity: 0.8
                    }); 
                });
       
            }
            
            function RysujWielokat(){

                zaznaczoneObszary.push({  
                            lat: null,
                            lng: null, 
                            opis: null, 
                            typ: "WIELOKAT",
                            path: polygonPath,
                            size: null,
                            boundsLeftTop: null,
                            boundsRightBot: null
                });
                
                Polygon = new google.maps.Polygon({
                    paths: polygonPath,
                    strokeColor: '#0099FF',
                    strokeOpacity: 0.8,
                    strokeWeight: 1.5,
                    fillColor: '#ffffff',
                    fillOpacity: 0.3,
                    map: Gmap
                });
                
                google.maps.event.addListener(Polygon, 'mouseover', function() {
                   
                    this.setOptions({
                        fillColor: '#ffffff',
                        fillOpacity: 0.5,  
                        strokeColor: "#FF6633",
                        strokeOpacity: 0.8
                    }); 
                });
                
                google.maps.event.addListener(Polygon, 'mouseout', function() {
                   
                    this.setOptions({
                        fillColor: '#ffffff',
                        fillOpacity: 0.3,
                        strokeColor: "#0099FF",
                        strokeOpacity: 0.8
                    }); 
                });
                
                PolygonStart = null;
                polygonPath = [];
                               
            }
            /******************************FUNKCJE DO POLYLINE*********************/
            function ResetPolyline(){      //resetuje zmienne sciezki                                      
                
                    sciezka = null;
                   // path = null; 
                    path = [];
                    point1 = null;
                    point2 = null; 
                    
                    var OTyp = $("#overlayType").val();
             
                    if(OTyp === "PATH_MANUAL"){
                        SciezkiZakodowaneManualne.push({path:" "}); //nowa sciezka bedzie kodowana
                    }
                    else if(OTyp === "PATH_AUTOMATIC"){
                        SciezkiZakodowaneAutomatyczne.push({path:" "}); //nowa sciezka bedzie kodowana
                    }      
            }
         
            /******************************FUNKCJE DO CIRCLE***********************/
            function ChangeSize(){   
                
                var sizeO = $("#sizeHidden").val();
                okrag.setRadius(parseInt(sizeO)*10);      
                zaznaczoneObszary[zaznaczoneObszary.length-1].size = parseInt(sizeO)*10;
            }
            function AcceptChangeSize(){  
                
                var CircleOpis = $("#opisObszaru");
                okrag.title = CircleOpis.val();
                zaznaczoneObszary[zaznaczoneObszary.length-1].opis = okrag.title;
                okrag = null;
                CircleOpis.val("");
                PF('sizeDialogVar').hide(); 
            }
            function CancelChangeSize(){
                
                okrag.setMap(null);
                okrag = null;
                PF('sizeDialogVar').hide();
                zaznaczoneObszary[zaznaczoneObszary.length-1] = null;
            }
            /***************************FUNKCJE DO RECTANGLE************************/
            
            
            /**************************CZYSZCZENIE ZAWARTOSCI MAPY*****************/
            function ClearMarkers(){
                
                while(MarkerList.length){ 
                    MarkerList.pop().setMap(null); 
                }
                MarkerList.length = 0; 
                Marker = null;
            }
            function ClearCircles(){
                
                while(CircleList.length){ 
                    CircleList.pop().setMap(null); 
                }
                CircleList.length = 0; 
                okrag = null;
            }
            function ClearPolylines(){
                
                while(PolylineList.length){ 
                    PolylineList.pop().setMap(null); 
                }
                PolylineList.length = 0;
                sciezka = null;
            }
            /********************************GEOCODING******************************/
            
            function codeAddress() {
                
                var address = $("#searchPanel\\:wyszukajAdresId").val(); 
                
                Geocoder.geocode( { 'address': address}, function(results, status) {
                    if (status == google.maps.GeocoderStatus.OK){
                        
                        Gmap.setCenter(results[0].geometry.location); //centruje mape
                        
                        Marker = new google.maps.Marker({
                            position: new google.maps.LatLng(results[0].geometry.location),
                            map: Gmap
                        });
                        
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

            function geocodeLatLng(SzukanyAdreslatlng) {
                
                Geocoder.geocode({'location': SzukanyAdreslatlng}, function(results, status) {
                    if (status === google.maps.GeocoderStatus.OK) {
                        if (results[1]) {
                            
                            pelny_adres = results[0].formatted_address; // to jest pełny adresm, 1 to miasto i kraj
                        } 
                        else {
                            window.alert('NIE ZNALEZIONO ADRESU');
                        }
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
  
            /******************MARKER CLUSTER**********************/
            function LoadCluster() {
             
                markerClusterer = new MarkerClusterer(Gmap, MarkerList, {maxZoom: 20, gridSize: 40 });
            }

            function RysujTrase(startPoint, endPoint){  //metoda rysuja trase miedzy 2 punktami
               
                
                directionsDisplay = new google.maps.DirectionsRenderer({ 
                    map: Gmap,
                    draggable: false
                   // panel: document.getElementById("directions")  //panel <div> na informacje dojazdu
                });
                bounds = new google.maps.LatLngBounds();
                bounds.extend(startPoint);
                bounds.extend(endPoint);
                Gmap.fitBounds(bounds);
                
                var request = {
                    origin: startPoint,
                    destination: endPoint,
                    unitSystem: google.maps.UnitSystem.METRIC,
                    travelMode: google.maps.TravelMode.DRIVING
                };
                
                directionsService.route(request, function (response, status) {
        
                    if (status === google.maps.DirectionsStatus.OK) {
                        directionsDisplay.setDirections(response);
                    } else {
                        //alert("error");
                        sciezka = new google.maps.Polyline({//jesli nie moge wyznaczyc drogi to rysuje polyline :)
                                path: [startPoint, endPoint],
                                map: Gmap,
                                //geodesic: true,
                                strokeColor: '#3399FF',
                                strokeOpacity: 0.7,
                                strokeWeight: 4,
                                icons: [{ icon: lineSymbol, offset: '0', repeat: '20px' }]
                            });  
                    }
                });
                
            }

            function GPS(){
               
                if (navigator.geolocation) {
                    navigator.geolocation.getCurrentPosition(function(position) {
                        var pos = {
                            lat: position.coords.latitude,
                            lng: position.coords.longitude
                        };

                        Marker = new google.maps.Marker({
                            position: pos,
                            icon: symbolCircle,
                            map: Gmap      
                        });
            
            
                        Gmap.setCenter(pos);
                        infoWindow.setPosition(pos);
                        Infowindow.setContent("lokalizacja");
                    
                    }, function() {
                        alert("error");
                    });
                } else {
                    // Browser doesn't support Geolocation
                    alert("golocation not supported");
                }
            }


            function WyswietlanyTryb(){
                
                ResetPolyline(); //zakazdym raze mjak zmeinie tryb to sobie resetuje sciezke
                var info;
                var OTyp = $("#overlayType").val();
              
                switch (OTyp) {
                    case "PUNKT":
                       
                        info = "Dodawanie punktow";
                        break;
                    case "OKRAG":
                        
                        info = "Zaznaczanie obszaru: Okrag";
                        break;
                    case "PROSTOKAT":
                       
                        info = "Zaznaczanie obszaru: Prostokat";
                        break;
                    case "WIELOKAT":
                        
                        info = "Zaznaczanie obszaru: Wielokat";
                        break;
                    case "PATH_MANUAL":
                       
                        info = "Dodawanie sciezki - manualnie";
                        break;
                    case "PATH_AUTOMATIC":
                        
                        info = "Dodawanie sciezki - autmatyczne";
                        break;
                }
                
                $("#searchPanel\\:trybData").html(info);   
            }

            function ZapiszDaneDoBazy(){

                var JSONBuilder = {
                                   "Markery": MarkerListData,
                                   "sciezkiManualne": SciezkiZakodowaneManualne,
                                   "sciezkiAutomatyczne": SciezkiZakodowaneAutomatyczne,
                                   "zaznaczoneObszary": zaznaczoneObszary

                };
                JSONBuilder = JSON.stringify(JSONBuilder);
                
                var dane = $("#FinalTripData");
                dane.val(JSONBuilder);
                
               
                
                //doPOST(JSONBuilder);
            }
           
            function doPOST(data){
                    
                //var JSONdata = JSON.stringify(wspolrzedne);                 
                $.ajax({
                    url: 'GmapWyznaczanieTrasyController',
                    type: 'POST',
                    dataType: 'json',
                    data: data,
                    contentType: 'application/json',
                    mimeType: 'application/json',
                                
                    success: function(response) {
                                    
                                alert('SUCCESS '+response.valueOf());
                    },
                                
                    error : function(response) {
                                 alert('ERROR'+JSON.stringify(response));    
                    },

                    headers : {
                                
                        Accept: "application/json; charset=utf-8",
                        "Content-Type": "application/json; charset=utf-8"
                    }
                });
            }
   
    function doGet(){
       
       
       /*
$.getJSON('json.php', function(data) {
    //var items = [];
    $.each(data, function(key, val) {
            path.push(new google.maps.LatLng(val.lat, val.longi));
    });

    // now update your polyline to use this path
    poly.setPath(path);
});
        */
       
   }