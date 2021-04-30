import {Box, Button, CheckBoxGroup, Form, FormField, Layer, Spinner, Text} from "grommet";
import React, {useState} from "react";
import {getRecommendationLayer} from "../requests/getRecommendationLayer";
import {FormClose} from "grommet-icons";

// Remove all cells that have a value of zero.
const reduceGeoJson = (data) => {
    let newFeatures = []
    console.log(data.features)
    data.features.forEach(feature => {
        if (feature.properties.poiCount > 0) {
            newFeatures.push(feature)
        }
    })
    console.log(newFeatures)
    data.features = newFeatures
    return data
}


export const AttractionTypeSelection = props => {
    const [value, setValue] = React.useState([]);
    const [fetchingRecommendation, setFetchingRecommendation] = useState(false)

    // Loading the recommendationLayer
    async function fetchData() {
        setFetchingRecommendation(true)
        let recommendationLayer = await getRecommendationLayer(value);
        props.setRecommendationLayer(reduceGeoJson(recommendationLayer));
    }

    const onClose = () => {
        setFetchingRecommendation(false)
    }

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
                                   options={['pub', 'restaurant', 'library', 'plaza', 'fountain', 'bar', 'cafe']}
                    />
                </FormField>
                <Button primary={true} label="Load recommendation" disabled={fetchingRecommendation} onClick={()=>{ fetchData().then(() => setFetchingRecommendation(false))}}/>
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