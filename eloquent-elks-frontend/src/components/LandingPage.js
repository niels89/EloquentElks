import { Box, Button, Grid, Layer, Text } from "grommet";
import React, { useState } from "react";
import { getAttractionType, getAttractionTypes } from "../resources/attractions";

// holds the key to access local storage, which indicates
// if the app has already been visited
const storageKey = "hasBeenVisited";

export const LandingPage = props => {
    let hasBeenVisited = localStorage[storageKey];

    const [showLanding, setShowLanding] = useState(!hasBeenVisited);
    const [attractionTypes, setAttractionTypes] = useState([]);

    let attractions = getAttractionTypes("white", "50");
    let numberOfAttractionTypes = attractions.length;
    let colsAndRows = Math.ceil(Math.sqrt(numberOfAttractionTypes));
    let rows = Array(colsAndRows).fill("small");
    let cols = Array(colsAndRows).fill("small");

    const closeLandingPage = () => {
        setShowLanding(false);
        props.setAttractionTypes(attractionTypes);
        localStorage[storageKey] = true;
    };

    const isSelected = (value) => {
        return attractionTypes.indexOf(value) >= 0;
    }

    const mergeAttractionTypes = (attractionType) => {
        let currentAttractionTypes = [...attractionTypes];
        
        let indexOfElement = currentAttractionTypes.indexOf(attractionType);
        
        if(indexOfElement >= 0){
            currentAttractionTypes.splice(indexOfElement, 1);
        } else {
            currentAttractionTypes.push(attractionType);
        }
        
        setAttractionTypes(currentAttractionTypes);
    }

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
                            key={attractionTypes.flat().toString()}
                        >
                            {attractions.map((attr, i) => 
                                <Button key={i} primary={isSelected(attr.value)}  onClick={() => mergeAttractionTypes(attr.value) } icon={getAttractionType(attr.key, isSelected(attr.value) ? "white" : "black", "50").icon} label={attr.caption}></Button>
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

