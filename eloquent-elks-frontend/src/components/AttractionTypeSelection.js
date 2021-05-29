import { Box, Button, CheckBoxGroup, Form, Layer, Spinner, Text } from "grommet";
import React, { useState } from "react";
import { getRecommendationLayer } from "../requests/getRecommendationLayer";
import { FormClose } from "grommet-icons";
import { getAttractionTypes } from "../resources/attractions";

export const AttractionTypeSelection = props => {
    const [fetchingRecommendation, setFetchingRecommendation] = useState(false);
    const [abortController, setAbortController] = useState()
    
    // Loading the recommendationLayer
    const onLoadRecommendation = () => {
        const controller = new AbortController();
        const signal = controller.signal;
        setAbortController(controller)
        setFetchingRecommendation(true)
        
        getRecommendationLayer(props.attractionTypes, signal).then(data => {
            props.setRecommendationLayer(data)
            setFetchingRecommendation(false)
        });
    }

    // Abort loading the recommendationLayer
    const onClose = () => {
        setFetchingRecommendation(false)
        abortController.abort();
    }

    const newAttractionList = getAttractionTypes("black", "20")

    return (
        <Box margin={{ "top": "medium" }}>
            <Form
                onSubmit={({ value: values, touched }) =>
                    // 'touched' is a single boolean value indication of
                    // whether any of the checkboxes had changed.
                    console.log('Submit', values, touched)
                }
            >
                <Box height={'small'} margin={{ "vertical": "medium" }} overflow={'scroll'}>
                    <CheckBoxGroup id="check-box-group-id"
                        name="controlled"
                        value={props.attractionTypes}
                        onChange={event => { props.setAttractionTypes(event.value.flat()); console.log('Group1: ', event.value.flat()); }}
                        options={newAttractionList.map(data => {
                            data.label = <Box direction={"row"} gap={"small"}>
                                {data.icon}
                                {data.caption}
                            </Box>
                            return (data)
                        })}

                    />
                </Box >
                <Box>
                    <Button primary={true} label="Load recommendation" disabled={fetchingRecommendation} onClick={onLoadRecommendation} />
                </Box>
            </Form>
            {fetchingRecommendation && (
                <Layer
                    position="bottom"
                    modal={false}
                    margin={{ vertical: 'medium', horizontal: 'small' }}
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
                        pad={{ vertical: 'xsmall', horizontal: 'small' }}
                        background="status-ok"
                    >
                        <Box align="center" direction="row" gap="xsmall">
                            <Spinner />
                            <Text>
                                Loading your recommendation layer. This may take some time.
                            </Text>
                        </Box>
                        <Button icon={<FormClose />} onClick={onClose} plain />
                    </Box>
                </Layer>)}
        </Box>
    )
}
