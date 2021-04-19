import fetch from 'node-fetch';
import { getDomain } from '../helpers/getDomain';

/**
 * This is a get request on the POI service to get the pois
 * @returns {list}
 */
export async function getPoisService(attractionType) {
  let response = await fetch(
    `${getDomain()}/api/v1/poi/attraction?attractionType=${attractionType}`,
    { method: 'GET' }
  );
  if (response.ok) {
    let data = await response.json();
    return data;
  } else {
    console.log(response.status + ': ' + response.statusText);
    return;
  }
}
