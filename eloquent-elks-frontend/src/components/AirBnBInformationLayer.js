import React from 'react'
import {
    Anchor,
    Box,
    Button,
    Card,
    CardBody,
    CardFooter,
    Collapsible,
    Heading,
    Image,
    Layer, List,
    Paragraph,
    Stack
} from "grommet";
import {Close, Favorite, FormDown, FormUp, ShareOption} from "grommet-icons";
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
               margin={{top: '60px', right: 'small', bottom: 'medium'}}
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
                        <Stack>
                            <Image
                                fill={true}
                                src={img}
                                a11yTitle="mock_nyc_image"
                            />
                            <Button icon={<Close color={"white"}/>}
                                    hoverIndicator
                                    onClick={() => {
                                        props.setShowInformation(false);
                                        props.setPois([])
                                    }}
                            />
                        </Stack>

                    </CardBody>
                    <Box pad={{horizontal: 'medium'}}>
                        <Heading level="3" margin={{vertical: 'medium'}}>
                            {props.content.name}
                        </Heading>
                        <Paragraph margin={{top: 'none'}}>
                            Here we could write some basic information about the AirBnb, which is probably available in
                            the data (e.g. the price).
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
                                href="https://www.airbnb.ch/"
                                label="Book Now"
                            />
                        </Box>
                        <ExpandButton onClick={() => setOpen(!open)}/>
                    </CardFooter>
                    <Collapsible open={open}>
                        <Box pad={{horizontal: 'medium'}}
                             responsive={true}
                        >
                            <Heading level="4" margin={{vertical: 'medium'}}>
                                Points of Interest Nearby:
                            </Heading>
                            <Box margin={{bottom: 'medium'}}
                                 height={'small'}
                                 overflow='scroll'>
                                <List data={props.pois}/>
                            </Box>

                        </Box>
                    </Collapsible>
                </Card>
            </Box>
        </Layer>
    );
}