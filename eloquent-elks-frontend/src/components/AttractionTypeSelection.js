import {Box, Button, CheckBoxGroup, Form, FormField, Layer, Spinner, Text} from "grommet";
import React, {useState} from "react";
import {getRecommendationLayer} from "../requests/getRecommendationLayer";
import {FormClose} from "grommet-icons";

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
    const [value, setValue] = React.useState([]);
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

    const attractionTypes = ['pub', 'restaurant', 'library', 'plaza', 'fountain', 'bar', 'cafe'];
    // attractionTypes.map((name) => {return name.charAt(0).toUpperCase() + name.slice(1)})

    return (
        <Box height={'xxlarge'} margin={{"top": "medium"}} overflow={'scroll'}>
            <Form
                onSubmit={({value: values, touched}) =>
                    // 'touched' is a single boolean value indication of
                    // whether any of the checkboxes had changed.
                    console.log('Submit', values, touched)
                }
            >
                <FormField name="controlled">
                    <CheckBoxGroup id="check-box-group-id"
                                   name="controlled"
                                   value={value}
                                   onChange={({value: nextValue}) => setValue(nextValue)}
                                   options={attractionTypes}
                    />
                </FormField>
                <Button primary={true} label="Load recommendation" disabled={fetchingRecommendation} onClick={onLoadRecommendation}/>
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