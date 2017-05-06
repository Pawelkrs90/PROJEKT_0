/* global PF, google */

//obiekt mapy
var Gmap = null;
var Layer = null;

//obiekt info window
var Infowindow = null;

//kontenery głowne do zapisu
var MarkerListData = [];
var MarkerListData2 = [];
var SciezkiZakodowaneManualne = [];
var SciezkiZakodowaneAutomatyczne = [];
var zaznaczoneObszary = [];

//kontenery tymczasowe
var MarkerList = [];
var MarkeryDlaTras = [];  //markery dla polyline i directions
var PolygonList = [];
var PolylineList = [];
var PolylineAutoList = [];
var CircleList = [];
var RectangleList = [];

//scezka i path dla sciezki automatycznej
var currentMarker = null;  //marker dla polyline i polygon
var sciezka = null;
var path;

//krawedzie prostokata
var LeftBound = null;
var RightBound = null;

//punkt poczatkowy, koncowy i sciezka wielokata
var PolygonStart = null;
var PolygonEnd = null;
var polygonPath = [];

//obiekty servisu do wyznacznia sciezki oraz rysujaca sciezke i punkt startowy i docelowy do wyznaczania trasy automatycznej
var directionsService = null;
var directionsDisplay = null;
var point1 = null;
var point2 = null;
var dystansTrasy = 0; //robocza zmienna na odleglosc
var TravelMode = null;

$(document).ready(function(){

    Gmap = PF('map').getMap();
    Gmap.setOptions({styles: getMapStyle()});
    //Gmap.setMapTypeId(google.maps.MapTypeId.SATELLITE);

    google.maps.event.addListener(Gmap, 'zoom_changed', function() {
        
        if (Gmap.getZoom() < 4){
            
            Gmap.setZoom(4);
        }
    });

    path = new google.maps.Polyline().getPath();
    directionsService = new google.maps.DirectionsService();
    TravelMode = "DRIVING";
    Infowindow = new google.maps.InfoWindow({maxWidth: 240});

    $("#overlayType").val("PUNKT");
    setWyswietlanyTryb(); //ustawiam informacje z aktywnym trybem
    InitContextMenu(); //inicjuje menu kontekstowe mapy
    InitMapOptionsButtons();

});


function InitMapOptionsButtons(){ //zapalanie przyciskow z opcjami mapy

    $("#mapOption1").css({"color": "#009900"});  //domyślnie zaswiecony button 1
    $("#overlayType").val("PUNKT"); //przy okazji nadaje wartosc startowa (wrazie odswiezania strony)

    for(var i=0; i<=6 ; i++){  //dla kazdego buttona ustawiam zdarzenie klikniecia

        $("#mapOption"+i).click(function(){

            for(var j=0; j<=6; j++){  //czyszcze kolory przyciskow
                $("#mapOption"+j).css({"color": "#6699ff"});
            }

            $(this).css({"color": "#009900"});
        });

        $("#mapOption"+i).mouseover(function(){

            if($(this).css("color") === "rgb(102, 153, 255)"){
                $(this).css({"color": "#ff9900"});
            }

        });

        $("#mapOption"+i).mouseout(function(){

            if($(this).css("color") === "rgb(255, 153, 0)"){
                $(this).css({"color": "#6699ff"});
            }

        });
    }
}

function InitContextMenu(){  //rejestruje context menu dla mapy

    google.maps.event.addListener(Gmap, 'rightclick', function(mouseEvent) {
        document.getElementById('cmenu').show();
    });
}

function SelectMapLayer(param){

    switch (param) {
        case "NORMAL":

            if(Layer){  //jesli jakas warstwa jest juz ustawiona to ją usuwam
                Layer.setMap(null);
            }
            break;
        case "TRAFFIC":

            if(Layer){  //jesli jakas warstwa jest juz ustawiona to ją usuwam
                Layer.setMap(null);
            }
            Layer = new google.maps.TrafficLayer();

            Layer.setMap(Gmap);
            break;
        case "BICYCLING":

            if(Layer){  //jesli jakas warstwa jest juz ustawiona to ją usuwam
                Layer.setMap(null);
            }
            Layer = new google.maps.BicyclingLayer();
            Layer.setMap(Gmap);
            break;
        case "TRANSIT":

            if(Layer){  //jesli jakas warstwa jest juz ustawiona to ją usuwam
                Layer.setMap(null);
            }
            Layer = new google.maps.TransitLayer();
            Layer.setMap(Gmap);
            break;
    }
}

function setWyswietlanyTryb(){   //zarzadznie wyswietlanym trybem na pasku
    
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

            info = "Dodawanie sciezki - autmatyczne, mode: "+TravelMode;
            break;
    }

    $("#searchPanel\\:trybData").html(info);
}

function SelectTransportType(mode){

    for(var j=0; j<=6; j++){  //czyszcze kolory przyciskow
        $("#mapOption"+j).css({"color": "#6699ff"});
    }

    $("#mapOption6").css({"color": "#009900"});

    switch (mode) {

        case "DRIVING":
        case "WALKING":
        case "BICYCLING":
        case "TRANSIT":

            TravelMode = mode;
            break;
        case "PLANE":
            TravelMode = "PLANE";
            break;
        default:
            TravelMode = "DRIVING";
    }


    //Zaswiecam przycisk z path_automatic jesli wybiore rodzaj transportu
    $("#overlayType").val("PATH_AUTOMATIC");
    setWyswietlanyTryb();

}


function GmapClickEventHandler(event) {

    var overlayType = $("#overlayType").val();

    if(overlayType.search("PATH") >= 0){  //jesli dodawanie trasy jest wybrane

        currentMarker = createControlPoint(event);
        MarkeryDlaTras.push(currentMarker); // tworze punkt/marker i dodaje go do listy
        dodajTrase(event.latLng);    //dodaje sciezke do tego punktu
    }
    else{ //jesli punkty i obszary

        switch (overlayType) {
            case "PUNKT":

                MarkerList.push(createMarker(event));           //dodaje marker
                SelectMarkerType($("#mType").val());         //wybieram ikonke w zaleznsci od zaznaczoneo typu (mozna to zmnienic w trakcie dodawania markera)
                geocodeLatLng(event.latLng);//sprawdzam jaki adres

                PF('DataDialog').show();    //otwieram okienko z opcjami puntku
                break;
            case "OKRAG":

                RysujOkrag(event);
                break;
            case "PROSTOKAT":

                currentMarker = createMarker(event);
                currentMarker.setIcon({
                    path: google.maps.SymbolPath.CIRCLE,
                    scale: 5,
                    fillColor: '#9600E6',
                    fillOpacity: 0.9,
                    strokeColor: '#FFFFFF',
                    strokeOpacity: 0.9,
                    strokeWeight: 1.5
                });
                currentMarker = null;
                //no narysowania prostokata potrzebuje 2 punkty lewy i prawy
                if(LeftBound === null){       //1# klikniecie - lewy punkt (gora albo dol)
                    LeftBound = event.latLng;
                }
                else{                            //2# klikniecie
                    RightBound = event.latLng;

                    if(RightBound.lng() < LeftBound.lng()){  //to likwiduje problem klinania najpierw z lewej potem z prawej strony
                        RysujProstokat(RightBound, LeftBound);   //teraz to bez znaczenia bo jesli drugi klik jest z lewej to zaminiam miejscami
                    }
                    else{
                        RysujProstokat(LeftBound, RightBound);  //rysuje prostokat i czyszcze wartosci
                    }

                    LeftBound = null;
                    RightBound = null;
                }

                break;
            case "WIELOKAT":

                currentMarker = createMarker(event);
                currentMarker.setIcon({
                    path: google.maps.SymbolPath.CIRCLE,
                    scale: 5,
                    fillColor: '#9600E6',
                    fillOpacity: 0.9,
                    strokeColor: '#FFFFFF',
                    strokeOpacity: 0.9,
                    strokeWeight: 1.5
                });


                if(PolygonStart === null){

                    currentMarker.setTitle("START_PATH");
                    currentMarker.setIcon({
                        path: google.maps.SymbolPath.CIRCLE,
                        scale: 5,
                        fillColor: '#FC0000',
                        fillOpacity: 0.9,
                        strokeOpacity: 0.9,
                        strokeWeight: 1.5,
                        strokeColor: '#FFFFFF'

                    });

                    PolygonStart = event.latLng;
                    polygonPath.push(event.latLng);
                }
                else{
                    polygonPath.push(event.latLng);
                }

                currentMarker = null;
                break;
        }//switch
    }//else
}//function

/*##################### MARKERY - addMarker, Accept, Cancel , selectMarkerIcon ##########################*/
function createMarker(event){

    var marker = new google.maps.Marker({
        position: new google.maps.LatLng(event.latLng.lat(), event.latLng.lng()),
        animation: google.maps.Animation.DROP,
        map: Gmap,
        customInfo:{
            typ: null,
            adres: null,
            opis: null,
            dystans: 0
        }
    });

    //event doczepiajacy sciezke do markera
    google.maps.event.addListener(marker, 'click', function() {

        if($("#overlayType").val().search("PATH") >= 0){

            currentMarker = this;
            dodajTrase(this.getPosition());
        }

        if(this.getTitle() === "START_PATH"){
            polygonPath.push(event.latLng);
            RysujWielokat();
        }
    });

    return marker;
}

function ZmianaTypuMarkera(){    //funkcja wywolana przy zminie typu markera w formularzu

    SelectMarkerType($("#mType").val());
}

function SelectMarkerType(typ){

    var imgUrl = "./resources/images/";
    var image = null;

    switch (typ) {
        case "ControlPoint":

            image = {
                path: google.maps.SymbolPath.CIRCLE,
                scale: 5,
                fillColor: '#9600E6',
                fillOpacity: 0.9,
                strokeColor: '#FFFFFF',
                strokeOpacity: 0.9,
                strokeWeight: 1.5
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
             };*/

            image = {  //wybieram ikonke markera
                url: './resources/images/markers/redMarker.png',
                size: new google.maps.Size(30, 44),
                anchor: new google.maps.Point(14, 44)
            };

            /*   image = {  //wybieram ikonke markera
             url: './resources/images/markers/finish2.png',
             size: new google.maps.Size(56, 38),
             anchor: new google.maps.Point(28, 38)
             };*/

            break;
        case "PUNKT_SZCZEGOLNY":

            /*image = {
             path: google.maps.SymbolPath.CIRCLE,
             scale: 6,
             strokeColor: '#FA6A03',
             strokeOpacity: 0.9,
             strokeWeight: 3,
             fillColor: '#FFFFFF',
             fillOpacity: 0.5
             }; */

            image = {  //wybieram ikonke markera
                url: './resources/images/markers/star30.png',
                size: new google.maps.Size(30, 30),
                anchor: new google.maps.Point(15, 15)
            };

            break;
        case "INNY_PUNKT":

            image = {
                path: google.maps.SymbolPath.CIRCLE,
                scale: 8,
                strokeColor: '#000000',
                strokeOpacity: 0.9,
                strokeWeight: 1.5,
                fillColor: '#0099CC',
                fillOpacity: 1
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

    MarkerList[MarkerList.length-1].customInfo.typ = typ;
    MarkerList[MarkerList.length-1].setIcon(image);
}

function AcceptButton(){

    MarkerList[MarkerList.length-1].customInfo.opis = $("#opis").val();  //ustawiam markerowi opis w customInfo

    google.maps.event.addListener(MarkerList[MarkerList.length-1], 'mouseover', function() {

        var content = '<span style="color: black; font-weight:700;" >Typ punktu: </span>'+
            '<span style="color: green; font-weight:600; " >'+this.customInfo.typ+'</span><br/>'+
            '<span style="color: black; font-weight:700;" >Adres: </span>'+
            '<span style="color: blue; font-weight:600; " >'+this.customInfo.adres+'</span><br/>'+
            '<span style="color: black; font-weight:700;" >Opis: </span>'+
            '<span style="color: black; " >'+this.customInfo.opis+'</span><br/>'+
            '<span style="color: black; font-weight:700;" >Dystans: </span>'+
            '<span style="color: black; " >'+this.customInfo.dystans+' km</span><br/>';

        Infowindow.setContent(content);
        Infowindow.open(Gmap, this);
    });

    google.maps.event.addListener(MarkerList[MarkerList.length-1], 'mouseout', function() {

        Infowindow.close();
    });

    $("#opis").val(""); //czyszcze wartosci w polach
    PF('DataDialog').hide();
}

function CancelButton(){

    $("#opis").val("");         //czyszcze pole formualrza
    MarkerList.pop().setMap(null);          //nulluje marker
    PF('DataDialog').hide();    //zamukam okno
}


/*##################### SCIEZKI - TRASY / POLYLINE ##########################*/
function dodajTrase(wspolrzedne){

    if($("#overlayType").val()  === "PATH_AUTOMATIC"){

        if(TravelMode !== "PLANE"){
            addPolylineAutomatic(wspolrzedne);
        }
        else{
            addPolyline(wspolrzedne);  //dodaje sciezke do tego Markera manualnie
        }
    }
    else if($("#overlayType").val()  === "PATH_MANUAL"){

        addPolyline(wspolrzedne);  //dodaje sciezke do tego Markera manualnie
    }
}

function addPolylineAutomatic(wspolrzedne){

    path.push(wspolrzedne); //dodaje wspolrzedna do sciezkie

    if(point1 === null){        //jesli punkt startowy sciezki jest null to go ustawiam
        point1 = wspolrzedne;
    }
    else{
        point2 = wspolrzedne;
        RysujTrase(point1, point2, TravelMode);
        point1 = point2;  //kolejna sciezka idzie od kolejnego punktu
    }
}

function addPolyline(wspolrzedne){

    if(sciezka === null){  //jesli sciezka pusta
        sciezka = new google.maps.Polyline({
            strokeColor: '#3399FF',
            strokeOpacity: 0.7,
            strokeWeight: 5,
            map: Gmap,
            icons: [{
                icon: {          //przerywana  linia
                    path: 'M 0,-1 0,1',
                    strokeOpacity: 0.2,
                    strokeColor: 'black',
                    scale: 4
                },
                offset: '0',
                repeat: '20px'
            }]
        });

        path = sciezka.getPath();  //wyciagam liste z kolejnymi punktmi Polyline
        path.push(wspolrzedne);   //dodaje do sciezki wspołrzedne punktu w który kliknołem
        currentMarker = null; //to poczatek sciezki wiec current marker moge znullowac bo nie dopisze odleglosci do niego
    }
    else{
        path.push(wspolrzedne);  //dodaje kolejny punkt sciezki
        dystansTrasy = (google.maps.geometry.spherical.computeLength(path)/1000);//wyliczam dlugosc sciezki

        if(currentMarker!== null){  //jesli dodaje sciezke do istniejacego juz markera
            if(currentMarker.customInfo.dystans === 0){ //zeby nie nadpisac juz ustawionej wartosci
                currentMarker.customInfo.dystans = dystansTrasy.toFixed(3);
            }
            currentMarker = null;
        }
        else{ //zeby nie nadpisac juz ustawionej wartosci

            MarkeryDlaTras[MarkeryDlaTras.length-1].customInfo.dystans = dystansTrasy.toFixed(3);
        }
    }
}

function createControlPoint(event){

    var marker = new google.maps.Marker({
        position: new google.maps.LatLng(event.latLng.lat(), event.latLng.lng()),
        animation: google.maps.Animation.DROP,
        map: Gmap,
        icon:{
            path: google.maps.SymbolPath.CIRCLE,
            scale: 5,
            fillColor: '#9600E6',
            fillOpacity: 0.9,
            strokeColor: '#FFFFFF',
            strokeOpacity: 0.9,
            strokeWeight: 1.5
        },
        customInfo:{
            dystans: 0
        }
    });

    //event doczepiajacy sciezke do markera
    google.maps.event.addListener(marker, 'click', function() {

        if($("#overlayType").val().search("PATH") >= 0){
            dodajTrase(this.getPosition());
        }

    });
    google.maps.event.addListener(marker, 'mouseover', function() {

        var content = '<span style="color: red; font-weight:700;" >Dystans: </span>'+
            '<span style="color: black; font-weight:700;" >'+this.customInfo.dystans+' km</span>';

        Infowindow.setContent(content);
        Infowindow.open(Gmap, this);
    });

    google.maps.event.addListener(marker, 'mouseout', function() {

        Infowindow.close();
    });

    return marker;
}

function RysujTrase(startPoint, endPoint, Mode){  //metoda rysuja trase miedzy 2 punktami

    directionsDisplay = new google.maps.DirectionsRenderer({
        map: Gmap,
        polylineOptions:{               //styl sciezki
            strokeColor: '#3399FF',
            strokeOpacity: 0.7,
            strokeWeight: 5,
            icons: [{
                icon: {          //przerywana  linia
                    path: 'M 0,-1 0,1',
                    strokeOpacity: 0.2,
                    strokeColor: 'black',
                    scale: 4
                },
                offset: '0',
                repeat: '20px'
            }]
        },
        draggable: false,
        suppressMarkers: true,      //jesli true to nie pokazuja sie markery A i B(czyli tak jak chce)
        panel: document.getElementById("directions")  //panel <div> na informacje dojazdu
    });

    bounds = new google.maps.LatLngBounds();
    bounds.extend(startPoint);
    bounds.extend(endPoint);
    Gmap.fitBounds(bounds);

    var request = {
        origin: startPoint,
        destination: endPoint,
        unitSystem: google.maps.UnitSystem.METRIC,
        travelMode: google.maps.TravelMode[Mode]
    };

    directionsService.route(request, function (response, status) {

        if (status === google.maps.DirectionsStatus.OK) {
            //window.alert("Json: " + JSON.stringify(response));
            console.log("Json: " + JSON.stringify(response));
            dystansTrasy =  parseFloat(dystansTrasy) + (response.routes[0].legs[0].distance.value/1000);

            if(currentMarker!== null){  //jesli dodaje sciezke do istniejacego juz markera
                if(currentMarker.customInfo.dystans === 0){ //zeby nie nadpisac juz ustawionej wartosci
                    currentMarker.customInfo.dystans = dystansTrasy.toFixed(3);
                }
                currentMarker = null;
            }
            else{ //zeby nie nadpisac juz ustawionej wartosci
                MarkeryDlaTras[MarkeryDlaTras.length-1].customInfo.dystans = dystansTrasy.toFixed(3);
            }
            //alert(response.routes[0].legs[0].duration.value + " seconds");
           window.alert("Json: " + JSON.stringify(response));
           // window.alert("Json2: " + response.routes[0].legs[0].steps[0].polyline.points);
            
            directionsDisplay.setDirections(response);
        }
        else { //jesli sie nie udało wyznaczyc trasy to rysuje prosta
            if(Mode === "TRANSIT"){
                RysujTrase(startPoint, endPoint, "DRIVING");
            }
            else{

                var sciezkaTmp = new google.maps.Polyline({//jesli nie moge wyznaczyc drogi to rysuje polyline :)
                    path: [startPoint, endPoint],
                    map: Gmap,
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

                dystansTrasy =  parseFloat(dystansTrasy) + (google.maps.geometry.spherical.computeLength(sciezkaTmp.getPath())/1000);
                MarkeryDlaTras[MarkeryDlaTras.length-1].customInfo.dystans = dystansTrasy.toFixed(3);
            }
        }
    });
}

function ResetPolyline(){      //zapisuje sciezki i resetuje zmienne sciezki

    if(sciezka !== null){
        PolylineList.push(sciezka);
    }
    else if(path.length){
        PolylineAutoList.push(path);
    }

    dystansTrasy = 0;
    currentMarker = null;
    sciezka = null;
    path = new google.maps.Polyline().getPath();
    point1 = null;
    point2 = null;
}


/*########################### Circle / okręgi ############################################*/
function RysujOkrag(event){

    PF('sizeDialogVar').show();  //otwieram otwieram okno z opcjami okregu
    CircleList.push( new google.maps.Circle({
        strokeColor: '#0099FF',
        strokeOpacity: 0.8,
        strokeWeight: 1.5,
        fillColor: '#ffffff',
        fillOpacity: 0.3,
        map: Gmap,
        center: event.latLng,
        radius: 10          //wielkosc okregu
    }));

    google.maps.event.addListener(CircleList[CircleList.length-1], 'click', function() {    //dodawanie sciezki do okregu

        var overlayType = $("#overlayType").val();

        if($("#overlayType").val().search("PATH") >= 0){
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

    google.maps.event.addListener(CircleList[CircleList.length-1], 'mouseover', function() {

        this.setOptions({
            fillColor: '#ffffff',
            fillOpacity: 0.5,
            strokeColor: "#FF6633",
            strokeOpacity: 0.8
        });
    });

    google.maps.event.addListener(CircleList[CircleList.length-1], 'mouseout', function() {

        this.setOptions({
            fillColor: '#ffffff',
            fillOpacity: 0.3,
            strokeColor: "#0099FF",
            strokeOpacity: 0.8
        });
    });
}

function ChangeSize(){

    var sizeO = $("#sizeHidden").val();
    CircleList[CircleList.length-1].setRadius(parseInt(sizeO)*10);

}

function AcceptChangeSize(){

    CircleList[CircleList.length-1].title = $("#opisObszaru").val();
    $("#opisObszaru").val("");
    PF('sizeDialogVar').hide();
}

function CancelChangeSize(){

    CircleList.pop().setMap(null);
    PF('sizeDialogVar').hide();
}


/*############################## Prostokat / Rectangle ##########################################*/
function RysujProstokat(LeftTop, RightBot){

    RectangleList.push( new google.maps.Rectangle({
        bounds: new google.maps.LatLngBounds(LeftTop, RightBot),
        strokeColor: '#0099FF',
        strokeOpacity: 0.8,
        strokeWeight: 1.5,
        fillColor: '#ffffff',
        fillOpacity: 0.3,
        map: Gmap
    }));

    google.maps.event.addListener(RectangleList[RectangleList.length-1], 'click', function() {    //dodawanie sciezki do okregu

        if($("#overlayType").val().search("PATH") >= 0){

            dodajTrase(this.getBounds().getSouthWest());
        }
    });

    google.maps.event.addListener(RectangleList[RectangleList.length-1], 'mouseover', function() {

        this.setOptions({
            fillColor: '#ffffff',
            fillOpacity: 0.5,
            strokeColor: "#FF6633",
            strokeOpacity: 0.8
        });
    });

    google.maps.event.addListener(RectangleList[RectangleList.length-1], 'mouseout', function() {

        this.setOptions({
            fillColor: '#ffffff',
            fillOpacity: 0.3,
            strokeColor: "#0099FF",
            strokeOpacity: 0.8
        });
    });

}

/*######################### Polygon / Wielokat####################################*/

function RysujWielokat(){

    PolygonList.push( new google.maps.Polygon({
        paths: polygonPath,
        strokeColor: '#0099FF',
        strokeOpacity: 0.8,
        strokeWeight: 1.5,
        fillColor: '#ffffff',
        fillOpacity: 0.3,
        map: Gmap
    }));

    google.maps.event.addListener(PolygonList[PolygonList.length-1], 'mouseover', function() {

        this.setOptions({
            fillColor: '#ffffff',
            fillOpacity: 0.5,
            strokeColor: "#FF6633",
            strokeOpacity: 0.8
        });
    });

    google.maps.event.addListener(PolygonList[PolygonList.length-1], 'mouseout', function() {

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

/*########################  GEOKODOWANIE  #####################################*/
//Geocodowanie adres -> wspołrzedne
function codeAddress() {

    var address = $("#searchPanel\\:wyszukajAdresId").val();
    var Geocoder =  new google.maps.Geocoder();

    Geocoder.geocode( { 'address': address}, function(results, status) {
        if (status === google.maps.GeocoderStatus.OK){

            var Marker = new google.maps.Marker({
                position: results[0].geometry.location,
                map: Gmap
            });

            google.maps.event.addListener(Marker, 'mouseover', function() {

                Marker.setMap(null);
            });

            Gmap.setCenter(results[0].geometry.location); //centruje mape
        }
        else {
            if(status === "OVER_QUERY_LIMIT"){  //OVER_QUERY_LIMIT
                window.alert('LIMIT USLUGI GEOKODINGU' + status);
            }
            else{
                window.alert('WYSTAPIŁ BŁAD: ' + status);
            }
        }
    });
}

//Geocodowanie wspołrzedne ->  adres
function geocodeLatLng(SzukanyAdreslatlng) {

    var Geocoder =  new google.maps.Geocoder();

    Geocoder.geocode({'location': SzukanyAdreslatlng}, function(results, status) {
        if (status === google.maps.GeocoderStatus.OK) {
            if (results[1]) {
                // to jest pełny adres, 1 to miasto i kraj
                MarkerList[MarkerList.length-1].customInfo.adres = results[0].formatted_address;
            }
            else {
                window.alert('NIE ZNALEZIONO ADRESU');
            }
        }
        else {
            if(status === "OVER_QUERY_LIMIT"){  //OVER_QUERY_LIMIT
                window.alert('LIMIT USLUGI GEOKODINGU' + status);
            }
            else{
                window.alert('WYSTAPIŁ BŁAD: ' + status);
            }
        }
    });
}

/*#################################  Zapis Danych   ############################################*/

function ZapiszDaneDoBazy(){

    $("#loadingZapisTrasy").show(); //pokazuje loading bar :)
    ResetPolyline(); //reset ale tez i zapis aktualnych sciezek
    //
    //wyciagam markery z listy
    while(MarkerList.length){
        var marker = MarkerList.pop();

        MarkerListData.push({
            lat: marker.getPosition().lat(),
            lng: marker.getPosition().lng(),
            opis: marker.customInfo.opis,
            adres: marker.customInfo.adres,
            typ: marker.customInfo.typ,
            dystans: marker.customInfo.dystans
        });
    }

    //wyciagam markery dla tras z listy
    while(MarkeryDlaTras.length){
        var marker = MarkeryDlaTras.pop();

        MarkerListData2.push({
            lat: marker.getPosition().lat(),
            lng: marker.getPosition().lng(),
            dystans: marker.customInfo.dystans
        });
    }

    while(PolylineList.length){

        var polyPath = PolylineList.pop().getPath();
        SciezkiZakodowaneManualne.push({"path": encodePath(polyPath)});
    }

    while(PolylineAutoList.length){

        var polyPath = PolylineAutoList.pop();
        SciezkiZakodowaneAutomatyczne.push({"path": encodePath(polyPath)});
    }

    while(CircleList.length){

        var circle = CircleList.pop();

        zaznaczoneObszary.push({
            lat: circle.getCenter().lat(),
            lng: circle.getCenter().lng(),
            typ: "OKRAG",
            size: circle.getRadius()
            //  opis: circle.getTitle()
        });

    }
    while(PolygonList.length){

        var polygon = PolygonList.pop();

        zaznaczoneObszary.push({
            typ: "WIELOKAT",
            path: encodePath(polygon.getPath())   //zakodowana sciezka
        });
    }
    while(RectangleList.length){

        var rectangle = RectangleList.pop();

        zaznaczoneObszary.push({
            typ: "PROSTOKAT",
            bounds: rectangle.getBounds()
        });
    }


    var JSONBuilder = {
        "Markery": MarkerListData,
        "MarkeryDlaTras": MarkerListData2,
        "sciezkiManualne": SciezkiZakodowaneManualne,
        "sciezkiAutomatyczne": SciezkiZakodowaneAutomatyczne,
        "zaznaczoneObszary": zaznaczoneObszary
    };

    JSONBuilder = JSON.stringify(JSONBuilder);

    //alert(JSONBuilder);

    var dane = $("#FinalTripData");
    dane.val(JSONBuilder);



}

/*##############################CZYSZCZENIE ZAWARTOSCI MAPY###################################*/

function ClearMarkers(){

    while(MarkerList.length){
        MarkerList.pop().setMap(null);
    }
    MarkerListData = [];
}
function ClearCircles(){

    while(CircleList.length){
        CircleList.pop().setMap(null);
    }
}
function ClearPolylines(){

    while(PolylineList.length){
        PolylineList.pop().setMap(null);
    }
    SciezkiZakodowaneManualne = [];
    SciezkiZakodowaneAutomatyczne = [];
    //directionDisplay.setMap();
    //directionDisplay.set('directions', null);
    path = new google.maps.Polyline().getPath();
}
function ClearPolygons(){

    while(PolygonList.length){
        PolygonList.pop().setMap(null);
    }
}
function ClearRectangles(){

    while(RectangleList.length){
        RectangleList.pop().setMap(null);
    }
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

/*
function utworzMarker(){

    var marker = new google.maps.Marker({
        position: new google.maps.LatLng(51.246058159, 22.541917562),
        animation: google.maps.Animation.DROP,
        map: Gmap,
        title: "UMCS",
        icon:{
            url: './resources/images/markers/greenMarker.png',
            size: new google.maps.Size(30, 44),
            anchor: new google.maps.Point(14, 44)
        },
        customInfo:{
            opis: "przykładowy opis markera",
            info: "przykladowe informacje"
        }
    });



            var json =
            {
                "geocoded_waypoints":[
                        {"geocoder_status":"OK", "place_id":"ChIJFUSsRWJXIkcR10xv0STUb_A", "types":["street_address"]},
                        {"geocoder_status":"OK", "place_id":"ChIJu54NuWNXIkcR6d196l5EbOY", "types":["street_address"]}
                ],
                "routes":[{
                        "bounds":{
                                "south":51.246170000000006,
                                "west":22.54091,
                                "north":51.24649,
                                "east":22.54303},
                                "copyrights":"Dane do Mapy ©2016 Google",
                                "legs":[
                                    {
                                        "distance":{"text":"0,2 km","value":154},
                                        "duration":{"text":"1 min", "value":23},
                                        "end_address":"Akademicka 4, 20-400 Lublin, Polska",
                                        "end_location":{"lat":51.2464551,"lng":22.543026800000007},
                                        "start_address":"Idziego Radziszewskiego 11, 20-400 Lublin, Polska",
                                        "start_location":{"lat":51.2461749, "lng":22.54091440000002},
                                        "steps":[
                                            {
                                                "distance":{"text":"0,2 km","value":150},
                                                "duration":{"text":"1 min","value":22},
                                                "end_location":{"lat":51.2464904,"lng":22.54301099999998},
                                                "polyline":{"points":"q_xwHuoqhCAIMuAMiAa@yF"},
                                                "start_location":{"lat":51.2461749, "lng":22.54091440000002},
                                                "travel_mode":"DRIVING",
                                                "encoded_lat_lngs":"q_xwHuoqhCAIMuAMiAa@yF",
                                                "path":[
                                                    {"lat":51.246170000000006,"lng":22.54091},
                                                    {"lat":51.24618, "lng":22.540960000000002},
                                                    {"lat":51.24625, "lng":22.541390000000003},
                                                    {"lat":51.246320000000004, "lng":22.541760000000004},
                                                    {"lat":51.24649, "lng":22.543010000000002}
                                                ],
                                                "lat_lngs":[
                                                    {"lat":51.246170000000006, "lng":22.54091},
                                                    {"lat":51.24618, "lng":22.540960000000002},
                                                    {"lat":51.24625, "lng":22.541390000000003},
                                                    {"lat":51.246320000000004, "lng":22.541760000000004},
                                                    {"lat":51.24649, "lng":22.543010000000002}
                                                ],
                                                "instructions":"Kieruj się <b>Idziego Radziszewskiego</b> na <b>wschód</b> w stronę <b>plac Marii Curie-Skłodowskiej</b>",
                                                "maneuver":"",
                                                "start_point":{"lat":51.2461749, "lng":22.54091440000002},
                                                "end_point":{"lat":51.2464904, "lng":22.54301099999998}
                                            },
                                            {
                                                "distance":{"text":"4 m", "value":4},
                                                "duration":{"text":"1 min", "value":1},
                                                "end_location":{"lat":51.2464551, "lng":22.543026800000007},
                                                "maneuver":"turn-right",
                                                "polyline":{"points":"qaxwHy|qhCDC"},
                                                "start_location":{ "lat":51.2464904, "lng":22.54301099999998},
                                                "travel_mode":"DRIVING",
                                                "encoded_lat_lngs":"qaxwHy|qhCDC",
                                                "path":[
                                                    { "lat":51.24649,"lng":22.543010000000002},
                                                    {"lat":51.246460000000006,"lng":22.54303}
                                                ],
                                                "lat_lngs":[
                                                    {"lat":51.24649,"lng":22.543010000000002},
                                                    {"lat":51.246460000000006,"lng":22.54303}
                                                ],
                                                "instructions":"Skręć <b>w prawo</b> w <b>Akademicka</b>",
                                                "start_point":{"lat":51.2464904,"lng":22.54301099999998},
                                                "end_point":{"lat":51.2464551,"lng":22.543026800000007}
                                            }
                                        ],
                                        "traffic_speed_entry":[],
                                        "via_waypoint":[],
                                        "via_waypoints":[]
                                    }],
                                    "overview_polyline":"q_xwHuoqhCO_BMiAa@yFDC",
                                    "summary":"Idziego Radziszewskiego",
                                    "warnings":[],
                                    "waypoint_order":[],
                                    "overview_path":[
                                        {"lat":51.246170000000006,"lng":22.54091},
                                        {"lat":51.24625,"lng":22.541390000000003},
                                        {"lat":51.246320000000004,"lng":22.541760000000004},
                                        {"lat":51.24649,"lng":22.543010000000002},
                                        {"lat":51.246460000000006,"lng":22.54303}
                                    ]
                        }],
                        "status":"OK",
                        "request":{
                            "origin":{"lat":51.24613875111497, "lng":22.54093050956726},
                            "destination":{"lat":51.24645440175848,"lng":22.543022632598877},
                            "unitSystem":0,
                            "travelMode":"DRIVING"
                        }
                }

}*/

