import {Box, Button, Grommet, Header} from "grommet";
import {grommetTheme} from './GrommetTheme'
import {Home} from 'grommet-icons';
import {useEffect, useState} from "react";
import {getAirbnbs} from "./requests/getAirbnbs";
import './App.css'
import {AirBnBInformationLayer} from "./components/AirBnBInformationLayer";
import {MainMap} from "./components/MainMap";



function App() {
    const [airbnbs, setAirbnbs] = useState([])
    const [pois, setPois] = useState([])
    const [showInformation, setShowInformation] = useState(false)
    const [currentAirBnB, setCurrentAirBnB] = useState({})
    const [mapBounds, setMapBounds] = useState()
    const [showAirBnBs, setShowAirBnBs] = useState(true)


    useEffect(() => {
        async function fetchData() {
            let ab
            if (typeof mapBounds !== 'undefined') {
                ab = await getAirbnbs(mapBounds)
            } else {
                ab = await getAirbnbs()
            }
            return ab;
        }
        fetchData().then((data) => setAirbnbs(data))
    }, [mapBounds])


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
                        <MainMap airbnbs={airbnbs}
                                 pois={pois}
                                 setPois={setPois}
                                 setShowInformation={setShowInformation}
                                 currentAirBnB={currentAirBnB}
                                 setCurrentAirBnB={setCurrentAirBnB}
                                 setMapBounds={setMapBounds}
                                 showAirBnBs={showAirBnBs}
                                 setShowAirBnBs={setShowAirBnBs}
                        />
                        {showInformation && <AirBnBInformationLayer setShowInformation={setShowInformation}
                                                                    pois={pois}
                                                                    setPois={setPois}
                                                                    content={currentAirBnB}
                                                                    setShowAirBnBs={setShowAirBnBs}

                        />}
                    </Box>
                </Box>
            </Box>
        </Grommet>
    );
}

export default App;
