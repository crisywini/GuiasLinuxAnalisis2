/**
 * Este script únicamente se va a ejecutar en la página donde se incluya, en este caso
 * lo incluí en registerProject.xhtml. Para incuírlo, en el template puse un ui:insert
 * que defino en la página del proyecto y listo.
 * Muchas gracias profe =) listo, cualquie cosa me escribe, chao
 * Saludos a lu(con buen genio)
 */
window.onload = function(){
	//map_map.on("locationfound", onLocationFound);
	//map_map.on("locationerror", onLocationError);
	map_map.on('click', onClickMarker);

	var icon = new L.Icon(
			{
				iconSize : [ 25, 41 ],
				iconAnchor : [ 12, 41 ],
				popupAnchor : [ 0, -45 ],
				iconUrl : '/ProyectoWeb/javax.faces.resource/marker-icon.png.xhtml?ln=images',
				shadowUrl : '/ProyectoWeb/javax.faces.resource/marker-shadow.png.xhtml?ln=images'
			});

	map_map.locate({
		setView : true,
		maxZoom : 16
	});

}

function onClickMarker(e) {

	layer.clearLayers();
	var icon = new L.Icon({iconSize: [25, 41], iconAnchor: [12, 41], popupAnchor: [0, -45], iconUrl: '/ProyectoWeb/javax.faces.resource/marker-icon.png.xhtml?ln=images', shadowUrl: '/ProyectoWeb/javax.faces.resource/marker-shadow.png.xhtml?ln=images'});
	var newMark = new L.marker(e.latlng,{icon:icon}).addTo(layer);

	remoteFunction([ {
		name : "lat",
		value : e.latlng.lat
	}, {
		name : "lng",
		value : e.latlng.lng
	} ])

}