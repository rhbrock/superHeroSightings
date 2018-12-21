/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* global google */

$('document').ready(
        
initMap()

);

var map;
function initMap() {


    //var uluru = {lat: 40.824577, lng: -73.951372};

    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 39.098745, lng: -94.590803},
        zoom: 4
    });

//    var marker = new google.maps.Marker({
//        position: uluru,
//        map: map
//    });
}
