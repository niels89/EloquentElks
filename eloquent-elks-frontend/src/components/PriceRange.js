import {useState} from "react";
import {Box, RangeSelector, Stack, Text} from "grommet";
const RANGE_MIN = 0;
const RANGE_MAX = 1000;
const initialRange = [0, 100]

function PriceRange({ label, onChange }) {
    const [range, setRange] = useState(initialRange);

    onChange(range)
    return (
        <Box gap="small" pad="xlarge">
            {label ? <Text>{label}</Text> : null}
            <Stack>
                <Box background="light-4" height="6px" direction="row" />
                <RangeSelector
                    direction="horizontal"
                    min={RANGE_MIN}
                    max={RANGE_MAX}
                    values={range}
                    onChange={nextRange => {
                        setRange(nextRange);}
                    }
                />
            </Stack>
            <Box align="center">
                <Text size="small">{`${range[0]}$ - ${range[1]}$`}</Text>
            </Box>
        </Box>
    );

}


export default PriceRange
