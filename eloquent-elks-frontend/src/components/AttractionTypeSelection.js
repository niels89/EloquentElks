import { Box, Button, CheckBoxGroup, Form } from "grommet";
import React from "react";
import { getAttractionTypes } from "../resources/attractions";

export const AttractionTypeSelection = props => {
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
                    <Button primary={true} label="Load recommendation" disabled={props.fetchingRecommendation} onClick={props.onLoadRecommendation} />
                </Box>
            </Form>
        </Box>
    )
}
