import {getDomain} from "../helpers/getDomain";
import {getVersion} from "../helpers/getVersion";

/**
 * This is a get request on the airbnb service to get the airbnbs
 * @returns {list}
 */
export async function getAirbnbs(bbox) {
    // bbox = {north, east, south, west}
    let response = await fetch(`${getDomain(1340)}/api/${getVersion()}/airbnb?` + new URLSearchParams(bbox).toString(),
        {method: "GET"})
    if (response.ok) {
        let data = await response.json()
        return data
    } else {
        console.log(response.error())
        alert('Something went wrong. Contact the developers')
    }
}