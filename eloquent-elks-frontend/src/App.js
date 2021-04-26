import {Box, Button, Grommet, Header} from "grommet";
import {grommetTheme} from './GrommetTheme'
import {Home} from 'grommet-icons';
import {useEffect, useState} from "react";
import {getAirbnbs} from "./requests/getAirbnbs";
import './App.css'
import {AirBnBInformationLayer} from "./components/AirBnBInformationLayer";
import {MainMap} from "./components/MainMap";
import PriceRange from './components/PriceRange'



function App() {
    const [airbnbs, setAirbnbs] = useState([])
    const [pois, setPois] = useState([])
    const [showInformation, setShowInformation] = useState(false)
    const [currentAirBnB, setCurrentAirBnB] = useState({})
    const initialRange = [0, 100]
    const [rangeInit] = useState(initialRange);


    useEffect(() => {
        async function fetchData() {
            let ab = await getAirbnbs(rangeInit)
            console.log(typeof ab )
            return ab;
        }
        fetchData().then((data) => setAirbnbs(data))
    }, [])

    useEffect( () => {
        console.log(airbnbs)
    }, [airbnbs])

    const modifyRange = async(tempRange) => {
        console.log("temp range is ",tempRange)
        await getAirbnbs(tempRange).then((data) => setAirbnbs(data))
    }

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
                        <PriceRange label="Apply Price Filter" onChange = {modifyRange}/>
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
