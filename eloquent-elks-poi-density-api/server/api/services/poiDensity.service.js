import * as turf from '@turf/turf'
import { getPoisService } from './getPois.service';

class PoiDensityService {

  createPointList(pois) {
    return pois.map((poi) => {return turf.point([poi.longitude, poi.latitude])})
  }

  mapPoisToFeatureCollection(pois) {
    let pointList = this.createPointList(pois);
    return turf.featureCollection(pointList)
  }

  calculateDensity(pois) {
    let bbox = [-74.60,40.41,-73.07,41.05];
    let cellSide = 0.500;
    let options = {units: 'kilometers'};

    let pointLayer = this.mapPoisToFeatureCollection(pois)
    let gridLayer = turf.hexGrid(bbox, cellSide, options)

    let tagged = turf.tag(pointLayer, gridLayer, '', 'GridID');
    return gridLayer
  }



  async byType(attractionType) {
    let pois = await getPoisService(attractionType);
    return this.calculateDensity(pois);
  }
}

export default new PoiDensityService();
