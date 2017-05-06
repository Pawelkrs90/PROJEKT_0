
        var MarkerList = [];
        var marker = null;
        var map = null;
        var bounds = null;
        
         var symbolKwadrat = {
                path: 'M -2,0 0,-2 2,0 0,2 z',
                strokeColor: '#F00',
                fillColor: '#F00',
                fillOpacity: 1
        };
        var symbolX = {
                path: 'M -2,-2 2,2 M 2,-2 -2,2',
                strokeColor: '#292',
                strokeWeight: 4
                };
        var symbolO = {                    
                        path: google.maps.SymbolPath.CIRCLE,
                        scale: 7,
                        //anchor: (0, 0),    //(0, 0) jest domyślnie
                        strokeColor: 'white',
                        strokeWeight: 5,
                        strokeOpacity: 1,
                        fillColor: '#006699',
                        fillOpacity: 0.7                     
                    };
        
        
       
     
        $(document).ready(function(){
        
            rc(); //odswiezam mape...

            
       
        });
    

        
        function clickHandler(event){
    
            marker = new google.maps.Marker({
                    position: new google.maps.LatLng(event.latLng.lat(), event.latLng.lng()),
                    map: PF('map2').getMap(),
                    icon: symbolO
            });
           
        }

        
        function styleMap() {

        // Specify features and elements to define styles.
            var styleArray = [
                {
                    featureType: "all",
                    elementType: "all", //
                    stylers: [
                        { saturation: -95 },        // nasycenie -100 do 100     
                        { lightness: -35 }          //jasnosc
                    ]
                }/*,
                {
                    featureType: "road.arterial",      //drogi
                    elementType: "geometry",
                    stylers: [
                        { hue: "#00ffee" }, 
                        { saturation: 50 }
                    ]
                },
                {
                    featureType: "poi.business",
                    elementType: "labels",
                    stylers: [
                        { visibility: "off" }
                    ]
                }*/
            ];

            // Create a map object and specify the DOM element for display.
  
            PF('map2').getMap().setOptions({
                center: {lat: 37.77, lng: -122.447},
                styles: styleArray,
                zoom: 8

            });
  
 

}
 