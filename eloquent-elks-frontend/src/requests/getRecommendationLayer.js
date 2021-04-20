import {getDomain} from "../helpers/getDomain";
import {getVersion} from "../helpers/getVersion";

/**
 * This is a get request on the POI service to get the pois
 * @returns {list}
 */
export async function getRecommendationLayer(attractionTypes) {
    let response = await fetch(`${getDomain(1337)}/api/${getVersion()}/recommendation/area`,
        {method: 'POST',
            body: {'attractionTypes': ['museum', 'attraction']},
            headers: {'Content-Type': 'application/json'}
        })
    if (response.ok) {
        let data = await response.json()
        return data
    } else {
        console.log(response.error())
        alert('Something went wrong. Contact the developers')
    }
}