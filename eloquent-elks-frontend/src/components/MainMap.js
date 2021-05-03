import {Box} from "grommet";
import {MapContainer, Marker, TileLayer, Tooltip, useMapEvents} from "react-leaflet";
import {airbnbLeafletIcon} from "./icons/airbnbLeafletIcon";
import {attractionLeafletIcon} from "./icons/attractionLeafletIcon";
import {getPois} from "../requests/getPois";
import {useState} from "react";


const createMapBounds = map => {
    let mapBounds = map.getBounds()
    return {north: mapBounds._northEast.lat, east: mapBounds._northEast.lng, south: mapBounds._southWest.lat, west: mapBounds._southWest.lng}
}


const ZoomListener = props => {
    const map = useMapEvents({
        zoomend() {
            props.setMapBounds(createMapBounds(map))
        }
    })
    return null
}


export const MainMap = props => {
    const [map, setMap] = useState()

    const getFlyToZoom = () => {
        if (map.getZoom() < 15) {
            return 15
        }
        return map.getZoom()
    }

    const handleAirBnBClick = async (event, airbnb) => {
        const { lat, lng } = event.latlng
        let pois = await getPois(lat, lng)
        props.setPois(pois)
        props.setShowInformation(true)
        props.setCurrentAirBnB(airbnb)
        map.flyTo(event.latlng, getFlyToZoom())
    }

    const onMapCreation = map => {
        setMap(map)
    }


    return (
        <Box fill>
            <MapContainer center={[40.73, -73.98]}
                          zoom={13}
                          scrollWheelZoom={true}
                          style={{height: '100%', zIndex: 0}}
                          whenCreated={map => onMapCreation(map)}
            >
                <ZoomListener setMapBounds={props.setMapBounds}/>
                <TileLayer
                    attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors &copy; <a href="https://carto.com/attributions">CARTO</a>'
                    url="https://{s}.basemaps.cartocdn.com/rastertiles/voyager/{z}/{x}/{y}{r}.png"
                    subdomains='abcd'
                    maxZoom={19}
                />
                {props.airbnbs.map && props.airbnbs.map((airbnb, index) => {
                        return (
                            <Marker key={index}
                                    position={[airbnb.latitude, airbnb.longitude]}
                                    eventHandlers={{click: (event) => {handleAirBnBClick(event, airbnb)}}}
                                    icon={airbnbLeafletIcon}
                            >
                                {/*<Popup>*/}
                                {/*    {airbnb.name}*/}
                                {/*</Popup>*/}
                            </Marker>
                        )
                    }
                )
                }
                {props.pois.map && props.pois.map((poi, index) => {
                        return (
                            <Marker key={"POI" + index}
                                    position={[poi.latitude, poi.longitude]}
                                    icon={attractionLeafletIcon}
                            >
                                <Tooltip>
                                    {poi.type}
                                </Tooltip>
                            </Marker>
                        )
                    }
                )
                }
            </MapContainer>
        </Box>
    )
}