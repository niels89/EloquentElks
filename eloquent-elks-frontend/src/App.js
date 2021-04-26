import {Box, Button, Grommet, Header} from "grommet";
import {grommetTheme} from './GrommetTheme'
import {Home} from 'grommet-icons';
import {useEffect, useState} from "react";
import {getAirbnbs} from "./requests/getAirbnbs";
import './App.css'
import {AirBnBInformationLayer} from "./components/AirBnBInformationLayer";
import {MainMap} from "./components/MainMap";
import {PriceRangeSelector} from './components/PriceRangeSelector'



function App() {
    const [airbnbs, setAirbnbs] = useState([])
    const [pois, setPois] = useState([])
    const [showInformation, setShowInformation] = useState(false)
    const [currentAirBnB, setCurrentAirBnB] = useState({})
    const [range, setRange] = useState([0, 100]);

    useEffect(() => {
        async function fetchData(currentRange) {
            let ab = await getAirbnbs(currentRange)
            console.log(typeof ab )
            return ab;
        }
        fetchData(range).then((data) => setAirbnbs(data))
    }, [range])

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
                        <PriceRangeSelector label="Apply Price Filter" setRange = {setRange} range = {range} />
                        sidebar
                    </Box>
                    <Box flex align='center' justify='center' >
                        <MainMap airbnbs={airbnbs}
                                 pois={pois}
                                 setPois={setPois}
                                 setShowInformation={setShowInformation}
                                 setCurrentAirBnB={setCurrentAirBnB}
                        />
                        {showInformation && <AirBnBInformationLayer setShowInformation={setShowInformation}
                                                                    pois={pois}
                                                                    setPois={setPois}
                                                                    content={currentAirBnB} />}
                    </Box>
                </Box>
            </Box>
        </Grommet>
    );
}

export default App;
