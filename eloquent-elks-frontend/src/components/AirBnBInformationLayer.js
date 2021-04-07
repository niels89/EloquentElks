import React from 'react'
import {Anchor, Box, Button, Card, CardBody, CardFooter, Collapsible, Heading, Image, Layer, Paragraph} from "grommet";
import {Favorite, FormDown, FormUp, ShareOption} from "grommet-icons";
import img from "../resources/mock_nyc_airbnb.jpg"

export const AirBnBInformationLayer = props => {
 const [open, setOpen] = React.useState(false);
 const [favorite, setFavorite] = React.useState(false);

    const ExpandButton = ({...rest}) => {
        const Icon = open ? FormUp : FormDown;
        return (
            <Button
                hoverIndicator="light-4"
                icon={<Icon color="brand"/>}
                {...rest}
            />
        );
    };


    return (
        <Layer position='top-right'
               onEsc={() => props.setShowInformation(false)}
               modal={false}
               plain={true}
               margin={{top: '60px', right: 'small'}}
               animation="fadeIn"
        >
            <Box animation={{
                "type": "slideLeft",
                "delay": 0,
                "duration": 500,
                "size": "medium"
            }}>
                <Card elevation="large"
                      width="medium"
                >
                    <CardBody height='small'>
                        <Image
                            fill={true}
                            src={img}
                            a11yTitle="mock_nyc_image"
                        />
                    </CardBody>
                    <Box pad={{horizontal: 'medium'}} responsive={false}>
                        <Heading level="3" margin={{vertical: 'medium'}}>
                            Bridge
                        </Heading>
                        <Paragraph margin={{top: 'none'}}>
                            A structure carrying a road, path, railroad, or canal across a
                            river, ravine, road, railroad, or other obstacle.
                        </Paragraph>
                    </Box>
                    <CardFooter>
                        <Box direction="row" align="center" gap="small">
                            <Button icon={<Favorite color={favorite ? 'red' : undefined}/>}
                                    hoverIndicator
                                    onClick={() => {
                                        setFavorite(!favorite);
                                    }}
                            />
                            <Button icon={<ShareOption color="plain"/>} hoverIndicator/>
                            <Anchor
                                href="https://www.collinsdictionary.com/us/dictionary/english/bridge"
                                label="Learn More"
                            />
                        </Box>
                        <ExpandButton onClick={() => setOpen(!open)}/>
                    </CardFooter>
                    <Collapsible open={open}>
                        <Paragraph margin="medium" color="dark-3">
                            The greatest bridge builders of antiquity were the ancient Romans.
                            The Romans built arch bridges and aqueducts that could stand in
                            conditions that would damage or destroy earlier designs. Some
                            stand today.
                        </Paragraph>
                    </Collapsible>
                </Card>
            </Box>
        </Layer>
    );
}