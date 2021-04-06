import {getDomain} from "../helpers/getDomain";
import {getVersion} from "../helpers/getVersion";

/**
 * This is a get request on ... to get the POIs
 * @returns {list}
 */
export async function getAirbnbs() {
    fetch(`${getDomain(8080)}/api/${getVersion()}/airbnb`, {method: "GET"})
        .then(response => {
            console.log(response);
            if (response.ok) {
                response.json()
                    .then(data => {
                        console.log(data);
                        return data
                    })
            }
            else {
                throw response
            }
        })
        .catch(err => {
            console.log(err);
            alert('Something went wrong. Contact the developers')
        })
}