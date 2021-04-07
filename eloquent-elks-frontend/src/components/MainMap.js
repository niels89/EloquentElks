import {Box} from "grommet";
import {MapContainer, Marker, TileLayer, Tooltip} from "react-leaflet";
import {airbnbLeafletIcon} from "./icons/airbnbLeafletIcon";
import {attractionLeafletIcon} from "./icons/attractionLeafletIcon";
import {getPois} from "../requests/getPois";
import {useState} from "react";



export const MainMap = props => {
    const [map, setMap] = useState([])

    const handleAirBnBClick = async (event, airbnb) => {
        const { lat, lng } = event.latlng
        let pois = await getPois(lat, lng)
        props.setPois(pois)
        props.setShowInformation(true)
        props.setCurrentAirBnB(airbnb)
        //TODO: Fix zoom such that it doesn't zoom out when zoomed in any further
        map.flyTo(event.latlng, 15)
    }

    // const handleOutsideClick = () => {
    //     props.setPois([])
    // }


    return (
        <Box fill>
            <MapContainer center={[40.73, -73.98]}
                          zoom={13}
                          scrollWheelZoom={true}
                          style={{height: '100%', zIndex: 0}}
                          whenCreated={map => setMap( map)}
            >
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