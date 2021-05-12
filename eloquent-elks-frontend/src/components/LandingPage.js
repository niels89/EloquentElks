import { Box, Button, Grid, Layer, Text } from "grommet";
import { useState } from "react";
import { getAttractionTypes } from "../resources/attractions";

// holds the key to access local storage, which indicates
// if the app has already been visited
const storageKey = "hasBeenVisited";

export const LandingPage = props => {
    let hasBeenVisited = localStorage[storageKey];

    const [showLanding, setShowLanding] = useState(!hasBeenVisited);

    let attractions = getAttractionTypes("white", "50",);
    let numberOfAttractionTypes = attractions.length;
    let colsAndRows = Math.ceil(Math.sqrt(numberOfAttractionTypes));
    let rows = Array(colsAndRows).fill("small");
    let cols = Array(colsAndRows).fill("small");

    const closeLandingPage = () => {
        setShowLanding(false);
        localStorage[storageKey] = true;
    };

    return (
        <Box>
            {showLanding && (
                <Layer
                    onClickOutside={closeLandingPage}
                    onEsc={closeLandingPage}
                >
                    <Box flex align='start' justify='center' pad='large'>
                        <Text size="xlarge">Welcome to NYC! Please select your preferred types of attractions.</Text>
                    </Box>
                    <Box flex align='center' justify='center' pad='large'>
                        <Grid
                            gap="small"
                            columns={cols}
                            rows={rows}
                        >
                            {attractions.map((attr, i) =>
                                <Button key={i} primary onClick={() => props.toggleAttractionType(attr.value)} icon={attr.label} label={attr.caption}></Button>
                            )}
                        </Grid>
                    </Box>
                    <Box flex align='stretch' justify='center' pad='large'>
                        <Button primary label="Save" onClick={closeLandingPage} />
                    </Box>
                </Layer>
            )}
        </Box>

    );
}

