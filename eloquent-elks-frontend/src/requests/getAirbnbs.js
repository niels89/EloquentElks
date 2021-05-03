import {getDomain} from "../helpers/getDomain";
import {getVersion} from "../helpers/getVersion";

/**
 * This is a get request on the airbnb service to get the airbnbs
 * @returns {list}
 */

export async function getAirbnbs(range) {
    const min = range[0]
    const max = range[1]
    let params = {min, max};
    let response = await fetch(`${getDomain(1340)}/api/${getVersion()}/airbnb?`+ new URLSearchParams(params).toString(), {method: "GET"})
    if (response.ok) {
        let data = await response.json()
        return data
    } else {
        console.log(response.error)
        alert('Something went wrong. Contact the developers')
    }
}
