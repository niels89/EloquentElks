import {divIcon} from "leaflet/dist/leaflet-src.esm";
import {renderToStaticMarkup} from "react-dom/server";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faHome, faMapMarker} from '@fortawesome/free-solid-svg-icons'
import {Box, Stack} from "grommet";


const attractionMarkup = renderToStaticMarkup(
    <Stack>
        <FontAwesomeIcon icon={faMapMarker} size={'3x'} color={'#e74e4e'}/>
        <Box align='center' alignSelf='center' margin={{left:'13px', top: '8px'}}>
            <FontAwesomeIcon icon={faHome} color={'#ffffff'}/>
        </Box>
    </Stack>
);


export const airbnbLeafletIcon = divIcon({
    html: attractionMarkup
})
