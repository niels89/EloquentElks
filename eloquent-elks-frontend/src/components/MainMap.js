import {Box} from "grommet";
import {MapContainer, Marker, TileLayer, Tooltip, GeoJSON} from "react-leaflet";
import {airbnbLeafletIcon} from "./icons/airbnbLeafletIcon";
import {attractionLeafletIcon} from "./icons/attractionLeafletIcon";
import {getPois} from "../requests/getPois";
import {useState} from "react";



export const MainMap = props => {
    const [map, setMap] = useState([])


    const getColor = (d) => {
        // Color palette from https://colorbrewer2.org/#type=sequential&scheme=BuGn&n=3
        let palette = ['#edf8e9','#bae4b3','#74c476','#31a354','#006d2c']
        let i
        for (i = 1; i <= palette.length; i++) {
            // values of the property are between 0 and 1
            if (d < i * (1.0 - (-1.0)) / palette.length) {
                return palette[i - 1]
            }
        }
    };

    const geoJsonStyle = (feature) => {
        return {
            stroke: false,
            // the fillColor is adapted depending on the poiCount
            fillColor: getColor(feature.properties.poiCount),
            fillOpacity: 0.5
        };
    };


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
                {props.recommendation ? <GeoJSON data={props.recommendation} style={geoJsonStyle}/> : null}
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