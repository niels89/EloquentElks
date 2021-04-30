import {Box, Button, Grommet, Header} from "grommet";
import {grommetTheme} from './GrommetTheme'
import {Home} from 'grommet-icons';
import {useEffect, useState} from "react";
import {getAirbnbs} from "./requests/getAirbnbs";
import './App.css'
import {AirBnBInformationLayer} from "./components/AirBnBInformationLayer";
import {MainMap} from "./components/MainMap";
import {AttractionTypeSelection} from "./components/AttractionTypeSelection";



function App() {
    const [airbnbs, setAirbnbs] = useState([])
    const [pois, setPois] = useState([])
    const [showInformation, setShowInformation] = useState(false)
    const [currentAirBnB, setCurrentAirBnB] = useState({})
    const [recommendationLayer, setRecommendationLayer] = useState(null)


    // Loading the AirBnB Data
    useEffect(() => {
        async function fetchData() {
            return getAirbnbs();
        }
        fetchData().then((data) => setAirbnbs(data))
    }, [])




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
                        <AttractionTypeSelection setRecommendationLayer = {setRecommendationLayer}/>
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
