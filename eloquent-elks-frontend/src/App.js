import {Box, Button, Grommet, Header, Layer, Spinner, Text} from "grommet";
import {grommetTheme} from './GrommetTheme'
import {FormClose, Home} from 'grommet-icons';
import {useEffect, useState} from "react";
import {getAirbnbs} from "./requests/getAirbnbs";
import './App.css'
import {AirBnBInformationLayer} from "./components/AirBnBInformationLayer";
import {MainMap} from "./components/MainMap";
import {AttractionTypeSelection} from "./components/AttractionTypeSelection";
import {PriceRangeSelector} from './components/PriceRangeSelector';
import {LandingPage} from './components/LandingPage';
import {getFamousDistance} from "./requests/getFamousDistance";
import {getRecommendationLayer} from "./requests/getRecommendationLayer";

function App() {
    const [airbnbs, setAirbnbs] = useState([])
    const [pois, setPois] = useState([])
    const [showInformation, setShowInformation] = useState(false)
    const [currentAirBnB, setCurrentAirBnB] = useState({})
    const [attractionTypes, setAttractionTypes] = useState([])
    const [recommendationLayer, setRecommendationLayer] = useState(null)
    const [range, setRange] = useState([0, 100]);
    const [mapBounds, setMapBounds] = useState()
    const [showAirBnBs, setShowAirBnBs] = useState(true)
    const [distances, setDistances] = useState([])
    const [fetchingRecommendation, setFetchingRecommendation] = useState(false);
    const [abortController, setAbortController] = useState()
    const [showLanding, setShowLanding] = useState(true);

    // Loading the recommendationLayer
    async function onLoadRecommendation(selectedAttractionTypes) {
        console.log("Fetching recommendation")
        console.log(attractionTypes)
        const controller = new AbortController();
        const signal = controller.signal;
        setAbortController(controller)
        setFetchingRecommendation(true)

        if (selectedAttractionTypes.length > 0) {
            let data = await getRecommendationLayer(selectedAttractionTypes, signal)
            setRecommendationLayer(data)
            setFetchingRecommendation(false)
        } else {
            setRecommendationLayer(null)
            setFetchingRecommendation(false)
        }
    }

    // Abort loading the recommendationLayer
    const onClose = () => {
        setFetchingRecommendation(false)
        abortController.abort();
    }

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
                        <PriceRangeSelector label="Apply Price Filter" setRange={setRange} range={range}/>
                        <AttractionTypeSelection attractionTypes={attractionTypes}
                                                 setAttractionTypes={setAttractionTypes}
                                                 fetchingRecommendation={fetchingRecommendation}
                                                 onLoadRecommendation={() => {onLoadRecommendation(attractionTypes)}}
                        />
                    </Box>
                    <Box flex align='center' justify='center'>
                        {showLanding && <LandingPage key={attractionTypes.length}
                                                     attractionTypes={attractionTypes}
                                                     setAttractionTypes={setAttractionTypes}
                                                     onLoadRecommendation={onLoadRecommendation}
                                                     setShowLanding={setShowLanding}/>}
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
                    {fetchingRecommendation && (
                        <Layer
                            position="bottom"
                            modal={false}
                            margin={{vertical: 'medium', horizontal: 'small'}}
                            onEsc={onClose}
                            responsive={false}
                            plain
                        >
                            <Box
                                align="center"
                                direction="row"
                                gap="small"
                                justify="between"
                                round="medium"
                                elevation="medium"
                                pad={{vertical: 'xsmall', horizontal: 'small'}}
                                background="status-ok"
                            >
                                <Box align="center" direction="row" gap="xsmall">
                                    <Spinner/>
                                    <Text>
                                        Loading your recommendation layer. This may take some time.
                                    </Text>
                                </Box>
                                <Button icon={<FormClose/>} onClick={onClose} plain/>
                            </Box>
                        </Layer>)}
                </Box>
            </Box>
        </Grommet>
    );
}

export default App;
