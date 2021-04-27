import {getDomain} from "../helpers/getDomain";
import {getVersion} from "../helpers/getVersion";

/**
 * This is a get request on the POI service to get the pois
 * @returns {list}
 */
export async function getRecommendationLayer(attractionTypes) {
    let response = await fetch(`${getDomain(1338)}/api/${getVersion()}/recommendation/area`,
        {method: 'POST',
            body: JSON.stringify({"attractionTypes": ["salon"]}),
            headers: {'Content-Type': 'application/json'}
        })
    if (response.ok) {
        return await response.json()
    } else {
        console.log(response.error)
        alert('Something went wrong. Contact the developers')
    }
}