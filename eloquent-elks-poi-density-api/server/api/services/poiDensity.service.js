import * as turf from '@turf/turf';

export class PoiDensityService {
  constructor(getPois) {
    this.getPois = getPois;
  }

  // Function from https://github.com/turf-junkyard/turf-count/blob/master/index.js
  // The junkyard from turf should not be use anymore we copied the function from there and take responsibility.
  countPointInPolygons(polyFC, ptFC, outField) {
    for (var i = 0; i < polyFC.features.length; i++) {
      var poly = polyFC.features[i];
      if (!poly.properties) poly.properties = {};
      var values = 0;
      for (var j = 0; j < ptFC.features.length; j++) {
        var pt = ptFC.features[j];
        if (turf.booleanPointInPolygon(pt, poly)) {
          values++;
        }
      }
      poly.properties[outField] = values;
      poly.properties['id'] = i;
    }
    polyFC.features = polyFC.features.filter(
      (poly) => poly.properties[outField] > 0
    );
    return polyFC;
  }

  createPointList(pois) {
    return pois.map((poi) => {
      return turf.point([poi.longitude, poi.latitude]);
    });
  }

  mapPoisToFeatureCollection(pois) {
    let pointList = this.createPointList(pois);
    return turf.featureCollection(pointList);
  }

  calculateDensity(pois) {
    let bbox = [-74.6, 40.41, -73.71, 40.95];
    let cellSide = 0.25;
    let options = { units: 'kilometers' };

    let pointLayer = this.mapPoisToFeatureCollection(pois);
    let gridLayer = turf.hexGrid(bbox, cellSide, options);

    let counted = this.countPointInPolygons(gridLayer, pointLayer, 'poiCount');
    return counted;
  }

  async byType(attractionType) {
    let pois = await this.getPois(attractionType);
    return this.calculateDensity(pois);
  }
}
