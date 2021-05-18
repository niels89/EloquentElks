import {Box, Button, CheckBoxGroup, Form, Layer, Spinner, Text} from "grommet";
import React, {useState} from "react";
import {getRecommendationLayer} from "../requests/getRecommendationLayer";
import {FormClose} from "grommet-icons";
import {attractionTypesList} from "../resources/Attractlion-list";


// Remove all cells that have a value of zero.
// const reduceGeoJson = (data) => {
//     let newFeatures = []
//     console.log(data.features)
//     data.features.forEach(feature => {
//         if (feature.properties.poiCount > 0) {
//             newFeatures.push(feature)
//         }
//     })
//     console.log(newFeatures)
//     data.features = newFeatures
//     return data
// }


export const AttractionTypeSelection = props => {
    const [value, setValue] = React.useState();
    const [fetchingRecommendation, setFetchingRecommendation] = useState(false);
    const [abortController, setAbortController] = useState()

    // Loading the recommendationLayer
    const onLoadRecommendation = () => {
        const controller = new AbortController();
        const signal = controller.signal;
        setAbortController(controller)
        setFetchingRecommendation(true)
        getRecommendationLayer(value, signal).then(data => {
            props.setRecommendationLayer(data)
            setFetchingRecommendation(false)
        });
    }


    // Abort loading the recommendationLayer
    const onClose = () => {
        setFetchingRecommendation(false)
        abortController.abort();
    }

    const newAttractionList = attractionTypesList


    // attractionTypes.map((name) => {return name.charAt(0).toUpperCase() + name.slice(1)})

    return (
        <Box margin={{"top": "medium"}}>
            <Form
                onSubmit={({value: values, touched}) =>
                    // 'touched' is a single boolean value indication of
                    // whether any of the checkboxes had changed.
                    console.log('Submit', values, touched)
                }
            >
                <Box height={'small'} margin={{"vertical": "medium"}} overflow={'scroll'}>
                    <CheckBoxGroup id="check-box-group-id"
                                   name="controlled"
                                   value={value}
                                   onChange={event => { setValue(event.value); console.log('Group1: ', event.value); }}
                                   options={newAttractionList.map(data => {
                                       data.label = <Box direction={"row"} gap={"small"}>
                                           {data.icon}
                                           {data.name}
                                       </Box>
                                       return (data)
                                   })}

                    />
                </Box >
                <Box>
                    <Button primary={true} label="Load recommendation" disabled={fetchingRecommendation} onClick={onLoadRecommendation}/>
                </Box>
            </Form>
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
    )
}
