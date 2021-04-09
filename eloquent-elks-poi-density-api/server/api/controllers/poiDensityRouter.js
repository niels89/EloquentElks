import * as express from 'express';
import PoiDensityController from './poiDensityController';

export default express.Router().get('/', PoiDensityController.byType);
