import {Box, RangeSelector, Stack, Text} from "grommet";

const RANGE_MIN = 0;
const RANGE_MAX = 1000;

export const PriceRangeSelector = (props) => {

    return (
        <Box gap="small" pad="xlarge">
            {props.label ? <Text>{props.label}</Text> : null}
            <Stack>
                <Box background="light-4" height="6px" direction="row" />
                <RangeSelector
                    direction="horizontal"
                    min={RANGE_MIN}
                    max={RANGE_MAX}
                    values={props.range}
                    onChange={nextRange => {
                        props.setRange(nextRange);}
                    }
                />
            </Stack>
            <Box align="center">
                <Text size="small">{`${props.range[0]}$ - ${props.range[1]}$`}</Text>
            </Box>
        </Box>
    );

}



