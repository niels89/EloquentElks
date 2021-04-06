import {getDomain} from "../helpers/getDomain";
import {getVersion} from "../helpers/getVersion";

/**
 * This is a get request on ... to get the POIs
 * @returns {list}
 */

// export async function getAirbnbs() {
//     fetch(`${getDomain(8080)}/api/${getVersion()}/airbnb`, {method: "GET"})
//         .then(response => {
//             if (response.ok) {
//                 response.json()
//                     .then(data => {
//                         return data
//                     })
//             }
//             else {
//                 throw response
//             }
//         })
//         .catch(err => {
//             console.log(err);
//             alert('Something went wrong. Contact the developers')
//         })
// }

export async function getAirbnbs() {
    let response = await fetch(`${getDomain(8080)}/api/${getVersion()}/airbnb`, {method: "GET"})
    if (response.ok) {
        let data = await response.json()
        return data
    } else {
        throw response
    }
}