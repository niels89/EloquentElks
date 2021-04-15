import { isProduction } from './isProduction';

/**
 * This helper function return the current domain of the API. If is production, the Production Heroku URL will returned.
 * Otherwise, the link localhost:8080 will be returned (Spring server default port)
 * @returns {string}
 */
export const getDomain = () => {
  const protocol = process.env.ELOQUENTELKS_POI_API_PROTOCOL;
  const host = process.env.ELOQUENTELKS_POI_API_HOST;
  const port = process.env.ELOQUENTELKS_POI_API_PORT;

  return `${protocol}://${host}:${port}`;
};
