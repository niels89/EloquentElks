import { isProduction } from './isProduction';

/**
 * This helper function return the current domain of the API. If is production, the Production Heroku URL will returned.
 * Otherwise, the link localhost:8080 will be returned (Spring server default port)
 * @returns {string}
 */
export const getDomain = (port) => {
  const prodUrl = 'http://localhost:' + port;
  let devUrl = 'http://localhost:' + port;
  if (isProduction()) {
    return prodUrl;
  }
  return devUrl;
};
