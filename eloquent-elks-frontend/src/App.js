import {Box, Button, Grommet, Header,} from "grommet";
import { theme } from './GrommetTheme'
import { Home } from 'grommet-icons';
import {MapContainer, Marker, Popup, TileLayer} from "react-leaflet";



function App() {
  return (
    <Grommet theme={theme} full>
        <Box fill>
            <Header background="brand">
                <Button icon={<Home />} hoverIndicator />
            </Header>
            <Box direction='row' flex >
                <Box width='medium'
                     background='light-2'
                     elevation='small'
                     align='center'
                     justify='center'
                >
                    sidebar
                </Box>
                <Box flex align='center' justify='center'>
                    <Box fill >
                        <MapContainer center={[40.70, -74.02]} zoom={13} scrollWheelZoom={true} style={{height: '100%'}}>
                            <TileLayer
                                attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
                                url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                            />
                            <Marker position={[40.70, -74.02]}>
                                <Popup>
                                    Write Something here
                                </Popup>
                            </Marker>
                        </MapContainer>
                    </Box>
                </Box>
            </Box>
        </Box>
    </Grommet>
  );
}

export default App;
