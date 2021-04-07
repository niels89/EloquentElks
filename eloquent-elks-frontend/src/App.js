import {Box, Button, Grommet, Header, Layer, Stack} from "grommet";
import {grommetTheme} from './GrommetTheme'
import {Home} from 'grommet-icons';
import {MapContainer, Marker, Popup, TileLayer} from "react-leaflet";
import {createRef, useEffect, useState} from "react";
import {getAirbnbs} from "./requests/getAirbnbs";
import {getPois} from "./requests/getPois";
import {attractionLeafletIcon} from "./components/icons/attractionLeafletIcon";
import {airbnbLeafletIcon} from "./components/icons/airbnbLeafletIcon";
import './App.css'
import {AirBnBInformationLayer} from "./components/AirBnBInformationLayer";
import {MainMap} from "./components/MainMap";



function App() {
    const [airbnbs, setAirbnbs] = useState([])
    const [pois, setPois] = useState([])
    const [showInformation, setShowInformation] = useState(false)

    useEffect(() => {
        async function fetchData() {
            let ab = await getAirbnbs()
            console.log(typeof ab )
            return ab;
        }
        fetchData().then((data) => setAirbnbs(data))
    }, [])

    useEffect( () => {
        console.log(airbnbs)
    }, [airbnbs])

    return (
        <Grommet theme={grommetTheme} full>
            <Box fill>
                <Header background="brand">
                    <Button icon={<Home/>} hoverIndicator/>
                </Header>
                <Box direction='row' flex>
                    <Box width='medium'
                         background='light-2'
                         elevation='small'
                         align='center'
                         justify='center'
                    >
                        sidebar
                    </Box>
                    <Box flex align='center' justify='center' >
                        <MainMap airbnbs={airbnbs} pois={pois} setPois={setPois} setShowInformation={setShowInformation}/>
                        {showInformation && <AirBnBInformationLayer setShowInformation={setShowInformation}/>}
                    </Box>
                </Box>
            </Box>
        </Grommet>
    );
}

export default App;
