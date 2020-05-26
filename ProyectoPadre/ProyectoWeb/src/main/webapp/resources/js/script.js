function onLoadBody() {
	map_map.on("locationfound", onLocationFound);
	map_map.on("locationerror", onLocationError);
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

function onLocationFound(e) {
	var radio = e.accuracy / 2;

	L.marker(e.latlng, {
		icon : icon
	}).addTo(map_map).bindPopup(
			"Tu te encuentras: " + radio + " metros de acá")
			L.circle(e.latlng, radio).addTo(map_map)

}
function onLocationError(e) {
	alert(e.message)
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