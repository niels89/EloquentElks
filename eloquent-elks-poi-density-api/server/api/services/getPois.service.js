import fetch from 'node-fetch';
import { getPOIDomain } from '../helpers/getPOIDomain';

/**
 * This is a get request on the POI service to get the pois
 * @returns {list}
 */
export async function getPois(attractionType) {
  let response = await fetch(
    `${getPOIDomain()}/api/v1/poi/attraction?attractionType=${attractionType}`,
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
