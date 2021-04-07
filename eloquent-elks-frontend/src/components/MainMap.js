import {Box} from "grommet";
import {MapContainer, Marker, TileLayer} from "react-leaflet";
import {airbnbLeafletIcon} from "./icons/airbnbLeafletIcon";
import {attractionLeafletIcon} from "./icons/attractionLeafletIcon";
import {getPois} from "../requests/getPois";

export const MainMap = props => {

    const handleAirBnBClick = async (event, airbnb) => {
        const { lat, lng } = event.latlng
        let pois = await getPois(lat, lng)
        props.setPois(pois)
        props.setShowInformation(true)
        props.setCurrentAirBnB(airbnb)
    }

    // const handleOutsideClick = () => {
    //     props.setPois([])
    // }


    return (
        <Box fill>
            <MapContainer center={[40.70, -74.02]}
                          zoom={13}
                          scrollWheelZoom={true}
                          style={{height: '100%', zIndex: 0}}
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
                                {/*<Popup>*/}
                                {/*    {poi.type}*/}
                                {/*</Popup>*/}
                            </Marker>
                        )
                    }
                )
                }
            </MapContainer>
        </Box>
    )
}