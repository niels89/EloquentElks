import {Box, Button, Grommet, Header} from "grommet";
import {grommetTheme} from './GrommetTheme'
import {Home} from 'grommet-icons';
import {useEffect, useState} from "react";
import {getAirbnbs} from "./requests/getAirbnbs";
import './App.css'
import {AirBnBInformationLayer} from "./components/AirBnBInformationLayer";
import {MainMap} from "./components/MainMap";
import {AttractionTypeSelection} from "./components/AttractionTypeSelection";
import {PriceRangeSelector} from './components/PriceRangeSelector';
import {getFamousDistance} from "./requests/getFamousDistance";



function App() {
    const [airbnbs, setAirbnbs] = useState([])
    const [pois, setPois] = useState([])
    const [showInformation, setShowInformation] = useState(false)
    const [currentAirBnB, setCurrentAirBnB] = useState({})
    const [recommendationLayer, setRecommendationLayer] = useState(null)
    const [range, setRange] = useState([0, 100]);
    const [mapBounds, setMapBounds] = useState()
    const [showAirBnBs, setShowAirBnBs] = useState(true)
    const [distances, setDistances] = useState([])



    // Loading the AirBnB Data
    useEffect(() => {
        async function fetchData(currentRange) {
            let ab
            if (typeof mapBounds !== 'undefined') {
                ab = await getAirbnbs(mapBounds, currentRange)
            } else {
                ab = await getAirbnbs(null, currentRange)
            }
            return ab;
        }
        fetchData(range).then((data) => setAirbnbs(data))
    }, [mapBounds, range])

    // Load the distances to the famous attractions as soon as a user selects an AirBnB
    useEffect(() => {
        async function fetchData(lat, lon) {
            let ab = await getFamousDistance(lat, lon)
            return ab;
        }
        if (currentAirBnB.latitude) {
            fetchData(currentAirBnB.latitude, currentAirBnB.longitude).then((data) => setDistances(data))
        }

    }, [currentAirBnB])


    console.log(recommendationLayer)

    return (
        <Grommet theme={grommetTheme} full>
            <Box fill>
                <Header background='linear-gradient(to right, #228BE6, #69AEEA)'>
                    <Button icon={<Home color='light-1'/>} hoverIndicator/>
                </Header>
                <Box direction='row' flex>
                    <Box width='medium'
                         background='light-2'
                         elevation='small'
                         align='center'
                         justify='center'
                    >
                        <PriceRangeSelector label="Apply Price Filter" setRange = {setRange} range = {range} />
                        <AttractionTypeSelection setRecommendationLayer = {setRecommendationLayer}/>
                    </Box>
                    <Box flex align='center' justify='center' >
                        <MainMap airbnbs={airbnbs}
                                 pois={pois}
                                 setPois={setPois}
                                 setShowInformation={setShowInformation}
                                 currentAirBnB={currentAirBnB}
                                 setCurrentAirBnB={setCurrentAirBnB}
                                 recommendation={recommendationLayer}
                                 setMapBounds={setMapBounds}
                                 showAirBnBs={showAirBnBs}
                                 setShowAirBnBs={setShowAirBnBs}
                        />
                        {showInformation && <AirBnBInformationLayer setShowInformation={setShowInformation}
                                                                    pois={pois}
                                                                    setPois={setPois}
                                                                    content={currentAirBnB}
                                                                    setShowAirBnBs={setShowAirBnBs}
                                                                    distances={distances}
                        />}
                    </Box>
                </Box>
            </Box>
        </Grommet>
    );
}

export default App;
