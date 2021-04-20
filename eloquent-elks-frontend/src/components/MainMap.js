import {Box} from "grommet";
import {MapContainer, Marker, TileLayer, Tooltip} from "react-leaflet";
import {airbnbLeafletIcon} from "./icons/airbnbLeafletIcon";
import {attractionLeafletIcon} from "./icons/attractionLeafletIcon";
import {getPois} from "../requests/getPois";
import {useEffect, useState} from "react";
import {getAirbnbs} from "../requests/getAirbnbs";
import {getRecommendationLayer} from "../requests/getRecommendationLayer";
import {GeoJSON} from "leaflet/dist/leaflet-src.esm";



export const MainMap = props => {
    const [map, setMap] = useState([])
    const [recommendationLayer, setRecommendationLayer] = useState(null)

    const handleAirBnBClick = async (event, airbnb) => {
        const { lat, lng } = event.latlng
        let pois = await getPois(lat, lng)
        props.setPois(pois)
        props.setShowInformation(true)
        props.setCurrentAirBnB(airbnb)
        //TODO: Fix zoom such that it doesn't zoom out when zoomed in any further
        // ()=>{if (map.getZoom() > 15) { return 15} return map.getZoom()}
        map.flyTo(event.latlng, 15)
    }

    useEffect(() => {
        async function fetchData() {
            let RL = await getRecommendationLayer([])
            console.log(RL)
            return RL;
        }
        fetchData().then((data) => setRecommendationLayer(data))
    }, [])


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
                {!recommendationLayer ? null : (<GeoJSON data={recommendationLayer}/>) }
            </MapContainer>
        </Box>
    )
}