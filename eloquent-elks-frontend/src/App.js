import {Box, Button, Grommet, Header, Spinner} from "grommet";
import {grommetTheme} from './GrommetTheme'
import {Home} from 'grommet-icons';
import {useEffect, useState} from "react";
import {getAirbnbs} from "./requests/getAirbnbs";
import './App.css'
import {AirBnBInformationLayer} from "./components/AirBnBInformationLayer";
import {MainMap} from "./components/MainMap";
import {getRecommendationLayer} from "./requests/getRecommendationLayer";

const reduceGeoJson = (data) => {
    let newFeatures = []
    console.log(data.features)
    data.features.forEach(feature => {
        if (feature.properties.poiCount > 0) {
            newFeatures.push(feature)
        }
    })
    console.log(newFeatures)
    data.features = newFeatures
    return data
}

function App() {
    const [airbnbs, setAirbnbs] = useState([])
    const [pois, setPois] = useState([])
    const [showInformation, setShowInformation] = useState(false)
    const [currentAirBnB, setCurrentAirBnB] = useState({})
    const [recommendationLayer, setRecommendationLayer] = useState(null)
    const [fetchingRecommendation, setFetchingRecommendation] = useState(false)

    // Loading the AirBnB Data
    useEffect(() => {
        async function fetchData() {
            return getAirbnbs();
        }
        fetchData().then((data) => setAirbnbs(data))
    }, [])


    //Loading the recommendationLayer
    useEffect(() => {
        async function fetchData() {
            return getRecommendationLayer([]);
        }
        setFetchingRecommendation(true)
        fetchData().then((data) => {
            setRecommendationLayer(reduceGeoJson(data));
            setFetchingRecommendation(false)
    })}, [])

    return (
        <Grommet theme={grommetTheme} full>
            <Box fill>
                <Header background='brand'>
                    <Button icon={<Home/>} hoverIndicator/>
                </Header>
                <Box direction='row' flex>
                    <Box width='medium'
                         background='light-2'
                         elevation='small'
                         align='center'
                         justify='center'
                    >
                        {fetchingRecommendation ? <Spinner/> : null}
                        sidebar
                    </Box>
                    <Box flex align='center' justify='center' >
                        <MainMap airbnbs={airbnbs}
                                 pois={pois}
                                 setPois={setPois}
                                 setShowInformation={setShowInformation}
                                 setCurrentAirBnB={setCurrentAirBnB}
                                 recommendation={recommendationLayer}
                        />
                        {showInformation && <AirBnBInformationLayer setShowInformation={setShowInformation}
                                                                    pois={pois}
                                                                    setPois={setPois}
                                                                    content={currentAirBnB}
                        />}
                    </Box>
                </Box>
            </Box>
        </Grommet>
    );
}

export default App;
